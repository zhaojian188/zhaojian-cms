/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: TestChannel.java 
 * @Prject: zhaojian-cms
 * @Package: com.zhaojian.service 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月14日
 * @version: V1.0   
 */
package com.zhaojian.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zhaojian.beans.Channel;
import com.zhaojian.service.ChannelService;

/** 
 * @ClassName: TestChannel 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月14日 
 */
public class TestChannel extends TestBase{
	@Autowired
	ChannelService channelService;
	
	@Test
	 public void testList() {
		List<Channel> list = channelService.list();
		 list.forEach(x->{
			 System.out.println(" 频道是 " + x);
		 });
	 }
}
