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
import com.zhaojian.common.CmsAssert;
import com.zhaojian.common.ConstantClass;
import com.zhaojian.common.Md5;
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

	/* (non Javadoc) 
	 * @Title: register
	 * @Description: TODO
	 * @param user
	 * @return 
	 * @see com.zhaojian.service.UserService#register(com.zhaojian.beans.User) 
	 */
	@Override
	public int register(User user) {
		//用户名是否存在
		User existUser  = findByName(user.getUsername());
		CmsAssert.AssertTrue(existUser==null,"该用户名已经存在");
				
		//加盐
		user.setPassword(Md5.password(user.getPassword(),
				user.getUsername().substring(0, 2)));
		return userMapper.add(user);
	}

	/* (non Javadoc) 
	 * @Title: login
	 * @Description: TODO
	 * @param user
	 * @return 
	 * @see com.zhaojian.service.UserService#login(com.zhaojian.beans.User) 
	 */
	@Override
	public User login(User user) {
		User loginUser = findByName(user.getUsername());
		if(loginUser==null)
			return null;
		
		// 计算加盐加密后的密码
		String pwdSaltMd5 = Md5.password(user.getPassword(),
				user.getUsername().substring(0, 2));
		
		//数据库中密码与用户输入的密码一致  则登录成功
		if(pwdSaltMd5.equals(loginUser.getPassword())) {
			return loginUser;
		}else {
			//登录失败
			return null;
		}
	}

	/* (non Javadoc) 
	 * @Title: findByName
	 * @Description: TODO
	 * @param username
	 * @return 
	 * @see com.zhaojian.service.UserService#findByName(java.lang.String) 
	 */
	@Override
	public User findByName(String username) {
		return userMapper.findByUserName(username);
	}
	
	

}
