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
		
		//获取放文章的文件夹的路径
		File file = new File("D:/1707D");
		//遍历这个文件夹获取文件夹的每篇文章
		File[] listFiles = file.listFiles();
		//循环，获取每篇文章
		for (File file2 : listFiles) {
			//标题，把文章名中的.txt，替换成""，做文章标题
			String title = file2.getName().replace(".txt", "");
			//使用工具类读取文章内容，做内容
			String content = FileUtils.readFileString(file2, "UTF-8");
			//声明Article对象
			Article article = new Article();
			//把属性赋值给对象
			//标题
			article.setTitle(title);
			//内容
			article.setContent(content);
			//摘要，如果大于140个字，则截取前140个字作为摘要，否则，有多少个字，截取多少个字作为摘要
			if(content.length()>140){
				article.setDigest(content.substring(0, 139));
			} else {
				article.setDigest(content.substring(0, content.length()));
			}
			//生成1-8的随机频道id
			int[] randomForCMS = RandomUtils.getRandomToCMS(9);
			//生成频道id
			article.setChannelId(randomForCMS[0]);
			//生成分类id
			article.setCategoryId(randomForCMS[1]);
			//普通用户的id
			article.setUserId(50);
			//点击量
			article.setHits(0);
			//设置热门 0,1随机
			article.setHot(RandomUtils.getRandomInt(0, 1));
			//审核 0,1随机
			article.setStatus(RandomUtils.getRandomInt(0, 1));
				
			article.setDeleted(0);
			String jsonString = JSON.toJSONString(article);
			//向kafka消费者发消息
			kafkaTemplate.send("articles",jsonString);
		}
	}
	
}
