/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: LinkServiceImpl.java 
 * @Prject: zhaojian-cms
 * @Package: com.zhaojian.service.impl 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月27日
 * @version: V1.0   
 */
package com.zhaojian.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhaojian.beans.Link;
import com.zhaojian.dao.LinkMapper;
import com.zhaojian.service.LinkService;

/** 
 * @ClassName: LinkServiceImpl 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月27日  
 */
@Service
public class LinkServiceImpl implements LinkService {
	@Autowired
	private LinkMapper linkMapper;

	/*(non Javadoc) 
	 * @Title: list
	 * @Description: 列表
	 * @param page
	 * @return 
	 * @see com.zhaojian.service.LinkService#list(int) 
	 */
	 
	@Override
	public PageInfo list(int page) {
		PageHelper.startPage(page, 10);
		
		return new PageInfo<Link>(linkMapper.list());
	}

	/*(non Javadoc) 
	 * @Title: add
	 * @Description: TODO
	 * @param link 
	 * @see com.zhaojian.service.LinkService#add(com.zhaojian.beans.Link) 
	 */
	@Override
	public void add(Link link) {
		linkMapper.add(link);
	}

	/*(non Javadoc) 
	 * @Title: delete
	 * @Description: 删除链接
	 * @param id 
	 * @see com.zhaojian.service.LinkService#delete(int) 
	 */
	@Override
	public void delete(int id) {
		linkMapper.delete(id);
	}

	/*(non Javadoc) 
	 * @Title: get
	 * @Description: TODO
	 * @param id
	 * @return 
	 * @see com.zhaojian.service.LinkService#get(int) 
	 */
	@Override
	public Link get(int id) {
		return linkMapper.get(id);
	}

	/* (non Javadoc) 
	 * @Title: update
	 * @Description: TODO
	 * @param link 
	 * @see com.zhaojian.service.LinkService#update(com.zhaojian.beans.Link) 
	 */
	@Override
	public int update(Link link) {
		return linkMapper.update(link);
	}
}
