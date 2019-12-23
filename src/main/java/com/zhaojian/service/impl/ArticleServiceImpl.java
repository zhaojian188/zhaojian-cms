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
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhaojian.beans.Article;
import com.zhaojian.beans.Comment;
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
	
	@SuppressWarnings("rawtypes")
	@Autowired
	RedisTemplate redisTemplate;
	
	/* (non Javadoc) 
	 * @Title: 查询热门文章
	 * @Description: TODO
	 * @param page
	 * @return 
	 * @see com.zhaojian.service.ArticleService#hotList(int) 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public PageInfo<Article> hotList(int page) {
		//PageHelper.startPage(page, ConstantClass.PAGE_SIZE);
		//1.从redis中查询热门文章(每页显示10条)
		List<Article> list = redisTemplate.opsForList().range("hot_articles", 0, 9);
		//2.判断redis中的数据是否为空
		if(list!=null && list.size()>0) {
			//3.如果redis的数据是非空，则直接返回web层
			System.err.println("从redis中查询了热门文章============");
			//返回web层
			return new PageInfo<Article>(list);
		}
		//4.如果redis的数据为空，从mysql中查询数据，添加到redis，并返回给web层
		List<Article> mysqlDB = articleMapper.hostList();
		System.err.println("从mysql中查询了热门文章===========");
		//mysqlDB是一个集合,要toArray一下，返回数组，因为redis的list集合的值类型是数组类型
		redisTemplate.opsForList().leftPushAll("hot_articles", mysqlDB.toArray());
		return new PageInfo<Article>(mysqlDB);
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

	/* (non Javadoc) 
	 * @Title: add
	 * @Description: 添加文章
	 * @param article
	 * @return 
	 * @see com.zhaojian.service.ArticleService#add(com.zhaojian.beans.Article) 
	 */
	@Override
	public int add(Article article) {
		return articleMapper.add(article);
	}

	/* (non Javadoc) 
	 * @Title: update
	 * @Description: TODO
	 * @param article
	 * @return 
	 * @see com.zhaojian.service.ArticleService#update(com.zhaojian.beans.Article) 
	 */
	@Override
	public int update(Article article) {
		
		return articleMapper.update(article);
	}

	/* (non Javadoc) 
	 * @Title: faverite
	 * @Description: TODO
	 * @param userId
	 * @param articleId
	 * @return 
	 * @see com.zhaojian.service.ArticleService#faverite(java.lang.Integer, int) 
	 */
	@Override
	public int faverite(Integer userId, int articleId) {
		return articleMapper.favorite(userId,articleId);
	}

	/* (non Javadoc) 
	 * @Title: getImgArticles
	 * @Description: TODO
	 * @param i
	 * @return 
	 * @see com.zhaojian.service.ArticleService#getImgArticles(int) 
	 */
	@Override
	public List<Article> getImgArticles(int num) {
		return articleMapper.getImgArticles(num);
	}

	/* (non Javadoc) 
	 * @Title: myfavoriteById
	 * @Description: 我的收藏
	 * @param page
	 * @param id
	 * @return 
	 * @see com.zhaojian.service.ArticleService#myfavoriteById(int, java.lang.Integer) 
	 */
	@Override
	public PageInfo<Article> myfavoriteById(int page, int id) {
		PageHelper.startPage(page, ConstantClass.PAGE_SIZE);
		List<Article> list = articleMapper.myfavoriteById(id);
		PageInfo info = new PageInfo(list);
		return info;
	}

	/* (non Javadoc) 
	 * @Title: delFavorite
	 * @Description: TODO
	 * @param id
	 * @return 
	 * @see com.zhaojian.service.ArticleService#delFavorite(int) 
	 */
	@Override
	public int delFavorite(int id) {
		
		return articleMapper.delFavorite(id);
	}

	/* (non Javadoc) 
	 * @Title: comment
	 * @Description: 评论
	 * @param userId
	 * @param articleId
	 * @param content
	 * @return 
	 * @see com.zhaojian.service.ArticleService#comment(java.lang.Integer, int, java.lang.String) 
	 */
	@Override
	public int comment(Integer userId, int articleId, String content) {
		//插入评论表一条数据
		int result = articleMapper.addComment(userId, articleId, content);
		if(result>0) {
				// 让文章表中的评论数量自增1
				articleMapper.increaseCommentCnt(articleId);
		}else {
			return 0;
			}
		return result;
	}

	/* (non Javadoc) 
	 * @Title: commentlist
	 * @Description: 获取评论内容
	 * @param articleId
	 * @param page
	 * @return 
	 * @see com.zhaojian.service.ArticleService#commentlist(int, int) 
	 */
	@Override
	public PageInfo<Comment> commentlist(int articleId, int page) {
		PageHelper.startPage(page,10);
		
		return new PageInfo<Comment>(articleMapper.commentlist(articleId));
	}
	
	
	/* (non Javadoc) 
	 * @Title: updateHits
	 * @Description: TODO
	 * @param article 
	 * @see com.zhaojian.service.ArticleService#updateHits(com.zhaojian.beans.Article) 
	 */
	@Override
	public void updateHits(Article article) {
		
		articleMapper.updateHits(article);
	}

	

}
