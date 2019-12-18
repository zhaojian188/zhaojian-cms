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

/** 
 * @ClassName: ArticleListener 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年12月11日 
 * 
 * kafka消息监听器
 */
public class ArticleListener implements MessageListener<String, String>{
	@Autowired
	ArticleMapper articleMapper;
	//注入redisTemplate模板,使用redis命令操作redis数据库
	@Autowired
	RedisTemplate redisTemplate;
	
	/* (non Javadoc) 
	 * @Title: onMessage
	 * @Description: 实现消费者接收消息的方法
	 * @param data
	 * @see org.springframework.kafka.listener.GenericMessageListener#onMessage(java.lang.Object) 
	 */
	@Override
	public void onMessage(ConsumerRecord<String, String> data) {
		//返回从kafka接收的消息为json类型
		String jsonString = data.value();
		//判断kafka通知的消息是修改操作还是增加操作还是删除操作
		if(jsonString.startsWith("update")) {
			System.out.println(jsonString);
			//如果是update开头的消息，我们就就认为是通知修改的操作(先把整体的redis数据库数据全部数据，等mysql修改完数据之后，再redis上更新)
			redisTemplate.delete("hot_articles");
			//es数据库修改操作TODO
		}else if(jsonString.startsWith("del")){
			System.out.println(jsonString);
			//redis数据库删除操作
			redisTemplate.delete("hot_articles");
			//es数据库修改操作TODO
		}else if(jsonString.startsWith("add")) {
			System.out.println(jsonString);
			//redis数据库添加操作(先把整体的redis数据库数据全部数据，等mysql添加完数据之后，再redis上更新)
			redisTemplate.delete("hot_articles");
			//es数据库修改操作TODO
		}else{
			System.err.println("收到消息:........");
			//把json类型通过阿里巴巴的fastjson转为对象类型
			Article article = JSON.parseObject(jsonString, Article.class);
			//把通过kafka监听的消息存入到mysql数据库中
			articleMapper.add(article);
		}
		
		
	}

}
