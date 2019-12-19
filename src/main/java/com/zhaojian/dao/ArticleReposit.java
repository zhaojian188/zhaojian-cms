/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: ArticleReposit.java 
 * @Prject: zhaojian-cms
 * @Package: com.zhaojian.dao 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年12月18日
 * @version: V1.0   
 */
package com.zhaojian.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.zhaojian.beans.Article;

/** 
 * @ClassName: ArticleReposit 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年12月18日 
 */
//创建ArticleReposit继承ElasticsearchRepository这个elasticsearch仓库
public interface ArticleReposit extends ElasticsearchRepository<Article, Integer>{

}
