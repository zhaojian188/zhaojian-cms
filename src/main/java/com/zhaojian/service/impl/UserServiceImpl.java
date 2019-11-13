/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: UserServiceImpl.java 
 * @Prject: zhaojian-cms
 * @Package: com.zhaojian.service.impl 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月13日
 * @version: V1.0   
 */
package com.zhaojian.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhaojian.beans.User;
import com.zhaojian.common.ConstantClass;
import com.zhaojian.dao.UserMapper;
import com.zhaojian.service.UserService;

/** 
 * @ClassName: UserServiceImpl 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月13日 
 */
@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserMapper userMapper;

	/* (non Javadoc) 
	 * @Title: getPageList
	 * @Description: TODO
	 * @param name
	 * @param page
	 * @return 
	 * @see com.zhaojian.service.UserService#getPageList(java.lang.String, java.lang.Integer) 
	 */
	@Override
	public PageInfo<User> getPageList(String name, Integer page) {
		
		PageHelper.startPage(page, ConstantClass.PAGE_SIZE);
		
		return new PageInfo<User>(userMapper.list(name));
	}

	/* (non Javadoc) 
	 * @Title: getUserById
	 * @Description: TODO
	 * @param userId
	 * @return 
	 * @see com.zhaojian.service.UserService#getUserById(java.lang.Integer) 
	 */
	@Override
	public User getUserById(Integer userId) {
		return userMapper.getById(userId);
	}

	/* (non Javadoc) 
	 * @Title: updateStatus
	 * @Description: TODO
	 * @param userId
	 * @param status
	 * @return 
	 * @see com.zhaojian.service.UserService#updateStatus(java.lang.Integer, int) 
	 */
	@Override
	public int updateStatus(Integer userId, int status) {
		return userMapper.updateStatus(userId,status);
	}
	
	

}
