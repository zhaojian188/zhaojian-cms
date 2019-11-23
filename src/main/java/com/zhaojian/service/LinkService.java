/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: LinkService.java 
 * @Prject: zhaojian-cms
 * @Package: com.zhaojian.service 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月23日
 * @version: V1.0   
 */
package com.zhaojian.service;

import javax.validation.Valid;

import com.github.pagehelper.PageInfo;
import com.zhaojian.beans.Link;

/** 
 * @ClassName: LinkService 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月23日 
 */
public interface LinkService {

	/** 
	 * @Title: list 
	 * @Description: 列表+分页
	 * @param page
	 * @return
	 * @return: PageInfo
	 */
	PageInfo list(int page);

	/** 
	 * @Title: add 
	 * @Description: 添加
	 * @param link
	 * @return: void
	 */
	void add(Link link);

	/** 
	 * @Title: delete 
	 * @Description: TODO
	 * @param id
	 * @return: void
	 */
	void delete(int id);

}
