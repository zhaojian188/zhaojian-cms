/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: ArticleServiceImpl.java 
 * @Prject: zhaojian-cms
 * @Package: com.zhaojian.service.impl 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月14日
 * @version: V1.0   
 */
package com.zhaojian.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhaojian.beans.Article;
import com.zhaojian.common.ConstantClass;
import com.zhaojian.dao.ArticleMapper;
import com.zhaojian.service.ArticleService;

/** 
 * @ClassName: ArticleServiceImpl 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月14日 
 */
@Service
public class ArticleServiceImpl implements ArticleService{
	
	@Autowired
	ArticleMapper articleMapper;
	
	/* (non Javadoc) 
	 * @Title: 查询历史文章
	 * @Description: TODO
	 * @param page
	 * @return 
	 * @see com.zhaojian.service.ArticleService#hotList(int) 
	 */
	@Override
	public PageInfo<Article> hotList(int page) {
		return new PageInfo<Article>(articleMapper.hostList());
	}

	/* (non Javadoc) 
	 * @Title: getNewArticles
	 * @Description: 得到最新的文章
	 * @param i
	 * @return 
	 * @see com.zhaojian.service.ArticleService#getNewArticles(int) 
	 */
	@Override
	public List<Article> getNewArticles(int i) {
		return articleMapper.newList(i);
	}

	/* (non Javadoc) 
	 * @Title: listByCat
	 * @Description: TODO
	 * @param chnId
	 * @param categoryId
	 * @param page
	 * @return 
	 * @see com.zhaojian.service.ArticleService#listByCat(int, int, int) 
	 */
	@Override
	public PageInfo<Article> listByCat(int chnId, int categoryId, int page) {
		
		PageHelper.startPage(page, ConstantClass.PAGE_SIZE);
		
		return new PageInfo<Article>(articleMapper.listByCat(chnId,categoryId));
	}

	/* (non Javadoc) 
	 * @Title: getById
	 * @Description: TODO
	 * @param id
	 * @return 
	 * @see com.zhaojian.service.ArticleService#getById(java.lang.Integer) 
	 */
	@Override
	public Article getById(Integer id) {
		return articleMapper.getById(id);
	}

	/* (non Javadoc) 
	 * @Title: listByUser
	 * @Description: 获取文章列表
	 * @param page
	 * @param id
	 * @return 
	 * @see com.zhaojian.service.ArticleService#listByUser(int, java.lang.Integer) 
	 */
	@Override
	public PageInfo<Article> listByUser(int page, Integer userId) {
		PageHelper.startPage(page, ConstantClass.PAGE_SIZE);
		return new PageInfo<Article>(articleMapper.listByUser(userId));
	}

	/* (non Javadoc) 
	 * @Title: delete
	 * @Description:管理员后台页面，管理员审核后逻辑删除需要删除的文章(不是在数据库真的删除，只是在前台做一个假的删除)
	 * @param id
	 * @return 
	 * @see com.zhaojian.service.ArticleService#delete(int) 
	 */
	@Override
	public int delete(int id) {
		return articleMapper.delete(id);
	}

	/* (non Javadoc) 
	 * @Title: getPageList
	 * @Description: TODO
	 * @param status
	 * @param page
	 * @return 
	 * @see com.zhaojian.service.ArticleService#getPageList(int, java.lang.Integer) 
	 */
	@Override
	public PageInfo<Article> getPageList(int status, Integer page) {
		PageHelper.startPage(page, ConstantClass.PAGE_SIZE);
		return new PageInfo<Article>(articleMapper.listByStatus(status));
	}
	
	/* (non Javadoc) 
	 * @Title: getDetailById
	 * @Description: TODO
	 * @param id
	 * @return 
	 * @see com.zhaojian.service.ArticleService#getDetailById(int) 
	 */
	@Override
	public Article getDetailById(int id) {
		return  articleMapper.getDetailById(id);
	}

	/* (non Javadoc) 
	 * @Title: checkExist
	 * @Description: TODO
	 * @param id
	 * @return 
	 * @see com.zhaojian.service.ArticleService#checkExist(int) 
	 */
	@Override
	public Article checkExist(int id) {
		return  articleMapper.checkExist(id);
	}

	/* (non Javadoc) 
	 * @Title: apply
	 * @Description: TODO
	 * @param id
	 * @param status
	 * @return 
	 * @see com.zhaojian.service.ArticleService#apply(int, int) 
	 */
	@Override
	public int apply(int id, int status) {
		
		return articleMapper.apply(id,status);
	}

	/* (non Javadoc) 
	 * @Title: setHot
	 * @Description: TODO
	 * @param id
	 * @param status
	 * @return 
	 * @see com.zhaojian.service.ArticleService#setHot(int, int) 
	 */
	@Override
	public int setHot(int id, int status) {
		
		return articleMapper.setHot(id,status);
	}

}
