/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: CategoryMapper.java 
 * @Prject: zhaojian-cms
 * @Package: com.zhaojian.dao 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月14日
 * @version: V1.0   
 */
package com.zhaojian.dao;

import java.util.List;

import com.zhaojian.beans.Category;

/** 
 * @ClassName: CategoryMapper 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月14日 
 */
public interface CategoryMapper {

	/** 
	 * @Title: listByChannelId 
	 * @Description: TODO
	 * @param chnId
	 * @return
	 * @return: List<Category>
	 */
	List<Category> listByChannelId(int chnId);
	
}
