/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: SendArticle.java 
 * @Prject: zhaojian-cms
 * @Package: com.zhaojian.test 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年12月23日
 * @version: V1.0   
 */
package com.zhaojian.test;

import java.io.File;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.zhaojian.FileUtils;
import com.zhaojian.RandomUtils;
import com.zhaojian.beans.Article;

/** 
 * @ClassName: SendArticle 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年12月23日 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:producer.xml")
public class SendArticle {
	
	@Autowired
	KafkaTemplate kafkaTemplate;
	
	@Test
	public void test() throws Exception {
		
		
		File file = new File("D:/1707D");
		//获取文件夹的每篇文章
		File[] listFiles = file.listFiles();
		for (File file2 : listFiles) {
			//标题
			String title = file2.getName().replace(".txt", "");
			//内容
			String content = FileUtils.readFileString(file2, "UTF-8");
			
			Article article = new Article();
			article.setTitle(title);
			article.setContent(content);
			if(content.length()>140){
				article.setDigest(content.substring(0, 139));
			} else {
				article.setDigest(content.substring(0, content.length()));
			}
			//生成1-8的随机频道id
			int[] randomForCMS = RandomUtils.getRandomForCMS(9);
			article.setChannelId(randomForCMS[0]);
			article.setCategoryId(randomForCMS[1]);
			//普通用户的id
			article.setUserId(50);
			//点击量
			article.setHits(0);
			//设置热门
			article.setHot(RandomUtils.getRandomInt(0, 1));
			//审核
			article.setStatus(RandomUtils.getRandomInt(0, 1));
				
			article.setDeleted(0);
			String jsonString = JSON.toJSONString(article);
			//kafka发消息
			kafkaTemplate.send("articles",jsonString);
			
		}
	}
	
}
