/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: ChannelServiceImpl.java 
 * @Prject: zhaojian-cms
 * @Package: com.zhaojian.service.impl 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月14日
 * @version: V1.0   
 */
package com.zhaojian.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhaojian.beans.Channel;
import com.zhaojian.dao.ChannelMapper;
import com.zhaojian.service.ChannelService;

/** 
 * @ClassName: ChannelServiceImpl 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月14日 
 */
@Service
public class ChannelServiceImpl implements ChannelService{
	
	@Autowired
	ChannelMapper channelMapper;
	/* (non Javadoc) 
	 * @Title: list
	 * @Description: TODO
	 * @return 
	 * @see com.zhaojian.service.ChannelService#list() 
	 */
	@Override
	public List<Channel> list() {
		return channelMapper.list();
	}
	
}
