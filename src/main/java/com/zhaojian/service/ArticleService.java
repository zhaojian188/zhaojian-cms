/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: ArticleService.java 
 * @Prject: zhaojian-cms
 * @Package: com.zhaojian.service 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月14日
 * @version: V1.0   
 */
package com.zhaojian.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.zhaojian.beans.Article;

/** 
 * @ClassName: ArticleService 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月14日 
 */
public interface ArticleService {

	/** 
	 * @Title: hotList 
	 * @Description: 获取热门文章
	 * @param page
	 * @return
	 * @return: PageInfo<Article>
	 */
	PageInfo<Article> hotList(int page);

	/** 
	 * @Title: getNewArticles 
	 * @Description: 获取最新文章, i：获取的个数
	 * @param i
	 * @return
	 * @return: List<Article>
	 */
	List<Article> getNewArticles(int i);

	/** 
	 * @Title: listByCat 
	 * @Description: 根据频道或者分类获取文章
	 * @param chnId
	 * @param categoryId
	 * @param page
	 * @return
	 * @return: PageInfo<Article>
	 */
	PageInfo<Article> listByCat(int chnId, int categoryId, int page);

	/** 
	 * @Title: getById 
	 * @Description: 根据文章id 获取文章的内容
	 * @param id
	 * @return
	 * @return: Article
	 */
	Article getById(Integer id);

	/** 
	 * @Title: listByUser 
	 * @Description: 获取文章列表
	 * @param page
	 * @param id
	 * @return
	 * @return: PageInfo<Article>
	 */
	PageInfo<Article> listByUser(int page, Integer userId);

	/** 
	 * @Title: delete 
	 * @Description: 管理员后台页面，管理员审核后逻辑删除需要删除的文章(不是在数据库真的删除，只是在前台做一个假的删除)
	 * @param id
	 * @return
	 * @return: int
	 */
	int delete(int id);

	/** 
	 * @Title: getPageList 
	 * @Description: 根据状态查询文章
	 * @param status
	 * @param page
	 * @return
	 * @return: PageInfo<Article>
	 */
	PageInfo<Article> getPageList(int status, Integer page);

	/** 
	 * @Title: getDetailById 
	 * @Description: 获取文章详情  不考虑文章的状态
	 * @param id
	 * @return
	 * @return: Article
	 */
	Article getDetailById(int id);

	/** 
	 * @Title: checkExist 
	 * @Description: 判断文章是否存在
	 * @param id
	 * @return
	 * @return: Article
	 */
	Article checkExist(int id);
	
	/** 
	 * @Title: apply 
	 * @Description: 审核文章
	 * @param id
	 * @param status
	 * @return
	 * @return: int
	 */
	int apply(int id, int status);

	/** 
	 * @Title: setHot 
	 * @Description: 设置是否为热门
	 * @param id
	 * @param status
	 * @return
	 * @return: int
	 */
	int setHot(int id, int status);

	/** 
	 * @Title: add 
	 * @Description: 添加文章
	 * @param article
	 * @return
	 * @return: int
	 */
	int add(Article article);

	/** 
	 * @Title: update 
	 * @Description: TODO
	 * @param article
	 * @return
	 * @return: int
	 */
	int update(Article article);

	


}
