/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: ArticleListener.java 
 * @Prject: zhaojian-cms
 * @Package: com.zhaojian.kafka 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年12月11日
 * @version: V1.0   
 */
package com.zhaojian.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.listener.MessageListener;

import com.alibaba.fastjson.JSON;
import com.zhaojian.beans.Article;
import com.zhaojian.dao.ArticleMapper;
import com.zhaojian.dao.ArticleReposit;

/** 
 * @ClassName: ArticleListener 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年12月24日 
 */
public class ArticleListener implements MessageListener<String, String>{
//	做mysql增删改查用的
	@Autowired
	ArticleMapper articleMapper;
//	redis模板，做redis数据库增删改查用的
	@Autowired
	RedisTemplate redisTemplate;
	
//	es做简单的增删改查的类
	@Autowired
	ArticleReposit articleReposit;
	
	/* (non Javadoc) 
	 * @Title: onMessage
	 * @Description: 实现消费者接收消息的方法
	 * @param data
	 * @see org.springframework.kafka.listener.GenericMessageListener#onMessage(java.lang.Object) 
	 */
	
	//收消息的方法
	@Override
	public void onMessage(ConsumerRecord<String, String> data) {
		String jsonString = data.value();
		//你是怎么知道这个消息是修改操作或者是接收的文章呢?
		if(jsonString.startsWith("update")) {
			System.err.println(jsonString);
			//如果是update开头的消息,我们就认为是通知修改的操作
			redisTemplate.delete("hot_articles");
			//========es数据据库修改TODO==========
			//按"="进行切割
			String[] split = jsonString.split("=");
			//切割之后把json串解析为对象类型保存到es中，重新添加即是修改操作
			articleReposit.save(JSON.parseObject(split[1],Article.class));
		}else if(jsonString.startsWith("del")) {
			System.err.println(jsonString);
			redisTemplate.delete("hot_articles");
			//es数据据库删除TODO
			String[] split = jsonString.split("=");
			articleReposit.deleteById(Integer.parseInt(split[1]));
		}else if(jsonString.startsWith("add")) {
			System.err.println(jsonString);
			//说明是添加操作
			redisTemplate.delete("hot_articles");
			//es数据据库添加TODO
			String[] split = jsonString.split("=");
			articleReposit.save(JSON.parseObject(split[1],Article.class));
		}else {
			System.err.println("收到了消息");
			Article article = JSON.parseObject(jsonString, Article.class);
			//保存到mysql
			articleMapper.add(article);
		}
	}

}
