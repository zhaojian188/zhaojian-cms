/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: CategoryServiceImpl.java 
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

import com.zhaojian.beans.Category;
import com.zhaojian.dao.CategoryMapper;
import com.zhaojian.service.CategoryService;

/** 
 * @ClassName: CategoryServiceImpl 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月14日 
 */
@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	CategoryMapper categoryMapper;

	/* (non Javadoc) 
	 * @Title: listByChannelId
	 * @Description: TODO
	 * @param chnId
	 * @return 
	 * @see com.zhaojian.service.CategoryService#listByChannelId(int) 
	 */
	@Override
	public List<Category> listByChannelId(int chnId) {
		
		return categoryMapper.listByChannelId(chnId);
	}
}
