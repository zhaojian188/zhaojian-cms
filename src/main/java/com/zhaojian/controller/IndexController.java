/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: IndexController.java 
 * @Prject: zhaojian-cms
 * @Package: com.zhaojian.controller 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月14日
 * @version: V1.0   
 */
package com.zhaojian.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.zhaojian.beans.Article;
import com.zhaojian.beans.Category;
import com.zhaojian.beans.Channel;
import com.zhaojian.beans.Link;
import com.zhaojian.service.ArticleService;
import com.zhaojian.service.CategoryService;
import com.zhaojian.service.ChannelService;
import com.zhaojian.service.LinkService;

/** 
 * @ClassName: IndexController 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月14日 
 */
@Controller
public class IndexController {
	/**
	 * 注入频道
	 */
	@Autowired
	ChannelService channelService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ArticleService articleService;
	
	@Autowired
	LinkService linkService;

	
	/**
	 * 
	 * @Title: channel 
	 * @Description: 根据id查询文章内容
	 * @param request
	 * @param chnId
	 * @param categoryId
	 * @param page
	 * @return
	 * @return: String
	 */
	@RequestMapping("channel")
	public String channel(HttpServletRequest request,
			@RequestParam(defaultValue="1")int chnId,
			@RequestParam(defaultValue="0")int categoryId,
			@RequestParam(defaultValue="1")int page) {
		
		// 回传参数数值
		request.setAttribute("chnId", chnId);
		request.setAttribute("categoryId", categoryId);
		
		//获取所有的频道
		List<Channel> channels = channelService.list();
		request.setAttribute("channels", channels);
		
		//获取这个频道下所以的分类
		List<Category> categories = categoryService.listByChannelId(chnId);
		request.setAttribute("categories", categories);
		
		//文章分页
		PageInfo<Article> articles = articleService.listByCat(chnId,categoryId,page);
		request.setAttribute("articles", articles);
		//跳转到用户页面
		return "channelindex";
	}
	
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = { "index", "/" })
	public String index(HttpServletRequest request, @RequestParam(defaultValue = "1") int page) {

		//获取所有的频道
		List<Channel> channels = channelService.list();
		request.setAttribute("channels", channels);
		
		PageInfo<Article> hotList = articleService.hotList(page);
		
		List<Article> newArticles = articleService.getNewArticles(5);
		
		// 获取最新图片文章
		List<Article> imgArticles = articleService.getImgArticles(10);
		
		// 友情链接
		PageInfo<Link> info=  linkService.list(1);
		List<Link> linkList =  info.getList();
		
		request.setAttribute("hotList", hotList);
		request.setAttribute("newArticles", newArticles);
		request.setAttribute("imgArticles", imgArticles);
		request.setAttribute("linkList", linkList);
		
		
		
		
		
		return "index";
	}
			
}
