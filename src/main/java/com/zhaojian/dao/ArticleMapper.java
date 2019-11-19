/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: ArticleMapper.java 
 * @Prject: zhaojian-cms
 * @Package: com.zhaojian.dao 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月14日
 * @version: V1.0   
 */
package com.zhaojian.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.zhaojian.beans.Article;

/** 
 * @ClassName: ArticleMapper 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月14日 
 */
public interface ArticleMapper {
	/** 
	 * @Title: newList 
	 * @Description: 获取最新文章
	 * @param i 获取的个数
	 * @return
	 * @return: List<Article>
	 */
	List<Article> newList(int i);
	
	
	/** 
	 * @Title: hostList 
	 * @Description: 获取热门文章
	 * @return
	 * @return: List<Article>
	 */
	List<Article> hostList();


	/** 
	 * @Title: listByCat 
	 * @Description: 根据频道获取文章
	 * @param chnId
	 * @param categoryId
	 * @return
	 * @return: List<Article>
	 */
	List<Article> listByCat(@Param("chnId")int chnId, @Param("categoryId")int categoryId);


	/** 
	 * @Title: getById 
	 * @Description: 根据id获取文章
	 * @param id
	 * @return
	 * @return: Article
	 */
	Article getById(Integer id);


	/** 
	 * @Title: listByUser 
	 * @Description: 获取文章列表
	 * @param userId
	 * @return
	 * @return: List<Article>
	 */
	List<Article> listByUser(Integer userId);


	/** 
	 * @Title: delete 
	 * @Description: 管理员后台页面，管理员审核后逻辑删除需要删除的文章(不是在数据库真的删除，只是在前台做一个假的删除)
	 * @param id
	 * @return
	 * @return: int
	 */
	@Update(" UPDATE cms_article SET  deleted=1 WHERE id=#{value} ")
	int delete(int id);


	/** 
	 * @Title: listByStatus 
	 * @Description: 管理员根据状态查询文章
	 * @param status
	 * @return
	 * @return: List<Article>
	 */
	List<Article> listByStatus(int status);


	/** 
	 * @Title: getDetailById 
	 * @Description: 获取文章详情，不考虑状态
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
	@Select("SELECT id, title,user_id AS userId FROM cms_article WHERE id = #{value}")
	@ResultType(Article.class)
	Article checkExist(int id);


	/** 
	 * @Title: apply 
	 * @Description: 审核文章
	 * @param id
	 * @param status
	 * @return
	 * @return: int
	 */
	@Update(" UPDATE cms_article SET  status=#{status} "
			+ " WHERE id=#{id} ")
	int apply(@Param("id") int id, @Param("status") int status);


	/** 
	 * @Title: setHot 
	 * @Description: 设置热门
	 * @param id
	 * @param status
	 * @return
	 * @return: int
	 */
	@Update(" UPDATE cms_article SET  hot=#{status} "
			+ " WHERE id=#{id} ")
	int setHot(int id, int status);

	

}
