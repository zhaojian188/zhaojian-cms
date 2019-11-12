/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: UserController.java 
 * @Prject: zhaojian-cms
 * @Package: com.zhaojian.controller 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月12日
 * @version: V1.0   
 */
package com.zhaojian.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/** 
 * @ClassName: UserController 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月12日 
 */
@Controller
@RequestMapping("user")
public class UserController {
	//  httppxxxx://user/hello
	@RequestMapping(value="hello",method=RequestMethod.GET)
	public String tet(HttpServletRequest request) {
		request.setAttribute("info", "hello");
		return "user/test";
	}
}
