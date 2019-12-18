/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: ImportMysql2ES.java 
 * @Prject: zhaojian-cms
 * @Package: com.zhaojian.service 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年12月17日
 * @version: V1.0   
 */
package com.zhaojian.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zhaojian.beans.Article;
import com.zhaojian.dao.ArticleMapper;
import com.zhaojian.dao.ArticleReposit;

/** 
 * @ClassName: ImportMysql2ES 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年12月17日 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-beans.xml")
public class ImportMysql2ES {
	
	@Autowired
	ArticleMapper articleMapper;
	//注入es仓库，实现简单的增删改查操作
	@Autowired
	ArticleReposit articleReposit;
	
	
	@Test
	public void testImport() {
		//从mysql数据库中查询所有article对象，放入List集合
		List<Article> list = articleMapper.findArticle();
		//把从mysql查询的集合对象的数据存到es索引库中
		articleReposit.saveAll(list);
	}

}
