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
import org.springframework.kafka.listener.MessageListener;

import com.alibaba.fastjson.JSON;
import com.zhaojian.beans.Article;
import com.zhaojian.dao.ArticleMapper;

/** 
 * @ClassName: ArticleListener 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年12月11日 
 */
public class ArticleListener implements MessageListener<String, String>{
	@Autowired
	ArticleMapper articleMapper;
	
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
		System.err.println("收到消息:........");
		//把json类型通过阿里巴巴的fastjson转为对象类型
		Article article = JSON.parseObject(jsonString, Article.class);
		//把通过kafka监听的消息存入到mysql数据库中
		articleMapper.add(article);
		
	}

}
