/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: ChannelService.java 
 * @Prject: zhaojian-cms
 * @Package: com.zhaojian.service 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月14日
 * @version: V1.0   
 */
package com.zhaojian.service;

import java.util.List;

import com.zhaojian.beans.Channel;

/** 
 * @ClassName: ChannelService 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月14日 
 */
public interface ChannelService {

	/** 
	 * @Title: list 
	 * @Description: TODO
	 * @return
	 * @return: List<Channel>
	 */
	List<Channel> list();
	
}
