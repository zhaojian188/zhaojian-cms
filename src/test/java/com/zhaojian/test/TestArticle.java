/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: TestArticle.java 
 * @Prject: zhaojian-cms
 * @Package: com.zhaojian.service 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月14日
 * @version: V1.0   
 */
package com.zhaojian.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageInfo;
import com.zhaojian.beans.Article;
import com.zhaojian.service.ArticleService;


/** 
 * @ClassName: TestArticle 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月14日 
 */
public class TestArticle extends TestBase{
	@Autowired
	ArticleService articleService;
	
	@Test
	public void testHotList() {
		PageInfo<Article> hotList = articleService.hotList(1);
		List<Article> list = hotList.getList();
		list.forEach(a->{
			System.out.println(" a is " + a);
		});
	}
}
