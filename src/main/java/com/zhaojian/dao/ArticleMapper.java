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
	 * @Description: TODO
	 * @param chnId
	 * @param categoryId
	 * @return
	 * @return: List<Article>
	 */
	List<Article> listByCat(@Param("chnId")int chnId, @Param("categoryId")int categoryId);


	/** 
	 * @Title: getById 
	 * @Description: TODO
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

	

}
