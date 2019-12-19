/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: ImportMysql2ES.java 
 * @Prject: zhaojian-cms
 * @Package: com.zhaojian.service 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年12月18日
 * @version: V1.0   
 */
package com.zhaojian.test;

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
 * @时间: 2019年12月18日 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-beans.xml")
public class ImportMysql2ES {
	
	@Autowired
	ArticleMapper articleMapper;
	//注入es仓库，实现简单的增删改查操作
	@Autowired
	ArticleReposit articleReposit;
	//冲突解决方案:
	//1.java.lang.ClassNotFoundException: com.fasterxml.jackson.core.type.ResolvedType
	// 解决:找到pom文件,粘贴上<!-- 解决冲突的依赖 -->,等待加载完毕,就ok了
	//2.到pom文件里修改jetty版本为:9.4.9.v20180320
	//3.到pom文件里修改validate版本
	//    <validator.version>5.1.0.Final</validator.version>
	
	@Test
	public void testImport() {
		//从mysql数据库中查询所有article对象，放入List集合
		List<Article> list = articleMapper.findArticle();
		//把从mysql查询的集合对象的数据存到es索引库中
		articleReposit.saveAll(list);
	}
	
	
}
