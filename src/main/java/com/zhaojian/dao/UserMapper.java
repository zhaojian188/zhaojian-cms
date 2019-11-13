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
import org.apache.ibatis.annotations.Update;

import com.zhaojian.beans.User;

/** 
 * @ClassName: UserMapper 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月13日 
 */
public interface UserMapper {

	/** 
	 * @Title: list 
	 * @Description: TODO
	 * @param name
	 * @return
	 * @return: List<User>
	 */
	List<User> list(String name);

	/** 
	 * @Title: getById 
	 * @Description: TODO
	 * @param userId
	 * @return
	 * @return: User
	 */
	User getById(Integer userId);

	/** 
	 * @Title: updateStatus 
	 * @Description: TODO
	 * @param userId
	 * @param status
	 * @return
	 * @return: int
	 */
	@Update("UPDATE cms_user SET locked=${status} WHERE id=${userId}")
	int updateStatus(@Param("userId")Integer userId, @Param("status")int status);

}
