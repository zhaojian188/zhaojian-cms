/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: UserService.java 
 * @Prject: zhaojian-cms
 * @Package: com.zhaojian.service 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月13日
 * @version: V1.0   
 */
package com.zhaojian.service;

import com.github.pagehelper.PageInfo;
import com.zhaojian.beans.User;

/** 
 * @ClassName: UserService 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月13日 
 */
public interface UserService {

	/** 
	 * @Title: getPageList 
	 * @Description: TODO
	 * @param name
	 * @param page
	 * @return
	 * @return: PageInfo<User>
	 */
	PageInfo<User> getPageList(String name, Integer page);

	/** 
	 * @Title: getUserById 
	 * @Description: TODO
	 * @param userId
	 * @return
	 * @return: User
	 */
	User getUserById(Integer userId);

	/** 
	 * @Title: updateStatus 
	 * @Description: TODO
	 * @param userId
	 * @param status
	 * @return
	 * @return: int
	 */
	int updateStatus(Integer userId, int status);

}
