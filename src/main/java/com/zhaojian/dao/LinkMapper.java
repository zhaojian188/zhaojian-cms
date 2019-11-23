/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: LinkMapper.java 
 * @Prject: zhaojian-cms
 * @Package: com.zhaojian.dao 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月23日
 * @version: V1.0   
 */
package com.zhaojian.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.zhaojian.beans.Link;

/** 
 * @ClassName: LinkMapper 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月23日 
 */
public interface LinkMapper {

	/** 
	 * @Title: list 
	 * @Description: TODO
	 * @return
	 * @return: List<Link>
	 */
	@Select("SELECT * FROM cms_link ORDER BY created DESC")
	List<Link> list();

	/** 
	 * @Title: add 
	 * @Description: TODO
	 * @param link
	 * @return: void
	 */
	@Insert("INSERT INTO cms_link (name,url,created) "
			+ " VALUES (#{name},#{url},now())")
	void add(Link link);

	/** 
	 * @Title: delete 
	 * @Description: TODO
	 * @param id
	 * @return: void
	 */
	@Delete("DELETE FROM cms_link WHERE id=#{id}")
	void delete(@Param("id")int id);

}