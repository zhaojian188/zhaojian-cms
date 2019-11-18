/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: UserMapper.java 
 * @Prject: zhaojian-cms
 * @Package: com.zhaojian.dao 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月13日
 * @version: V1.0   
 */
package com.zhaojian.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.zhaojian.beans.User;

/** 
 * @ClassName: UserMapper 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月13日 
 */
public interface UserMapper {


	List<User> list(String name);


	User getById(Integer userId);


	@Update("UPDATE cms_user SET locked=${status} WHERE id=${userId}")
	int updateStatus(@Param("userId")Integer userId, @Param("status")int status);

	@Select("SELECT * FROM cms_user WHERE username = #{value} limit 1")
	User findByUserName(String username);


	/** 
	 * @Title: add 
	 * @Description: 注册
	 * @param user
	 * @return
	 * @return: int
	 */
	int add(User user);

}
