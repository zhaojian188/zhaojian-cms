/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: SendArticle.java 
 * @Prject: zhaojian-cms
 * @Package: com.zhaojian.test 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年12月24日
 * @version: V1.0   
 */
package com.zhaojian.test;

import java.io.File;
import java.io.IOException;

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
 * @时间: 2019年12月24日 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:producer.xml")
public class SendArticle {
	//注入kafka模板
	@Autowired
	KafkaTemplate kafkaTemplate;
	
	
	@Test
	public void testKafka() throws IOException {
		//用io流读取文件夹
		File file = new File("D:/1707D");
		//得到一个文件数组
		File[] listFiles = file.listFiles();
		//遍历文件
		for (File file2 : listFiles) {
			//将文件名作为Article对象的title属性值
			String title = file2.getName().replace(".txt", "");
			//文本内容作为Article对象的content属性值
			String content = FileUtils.readFileString(file2, "UTF-8");
			//创建文章对象
			Article article = new Article();
			//标题
			article.setTitle(title);
			//文章内容
			article.setContent(content);
			//在文本内容中截取前140个字作为摘要
			if(content.length()>140){
				article.setDigest(content.substring(0, 139));
			}else{
				//如果读取的文章内容不够140个字，则有多少个字取多少个作为文章摘要
				article.setDigest(content.substring(0, content.length()));
			}
			//设置频道id和分类id
			int[] randomToCMS = RandomUtils.getRandomToCMS(9);
			//频道id
			article.setChannelId(randomToCMS[0]);
			article.setCategoryId(randomToCMS[1]);
			//设置userId
			article.setUserId(50);
			//点击量
			article.setHits(RandomUtils.getRandomInt(0, 10));
			//是否为热门  0非热门 1热门
			article.setHot(RandomUtils.getRandomInt(0, 1));
			//文章状态
			article.setStatus(RandomUtils.getRandomInt(0, 1));
			//是否删除
			article.setDeleted(0);
			//把article对象转为JSON字符串
			String jsonString = JSON.toJSONString(article);
			//kafka向监听器发消息
			kafkaTemplate.send("articles", jsonString);
			
			
			
		}
		
		
		
	}

}
