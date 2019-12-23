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
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.zhaojian.beans.Article;
import com.zhaojian.beans.Category;
import com.zhaojian.beans.Channel;
import com.zhaojian.beans.Link;
import com.zhaojian.common.ConstantClass;
import com.zhaojian.common.ESHLUtil;
import com.zhaojian.dao.ArticleReposit;
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
	//注入es仓库
	@Autowired
	ArticleReposit articleReposit;
	
	//注入spring整合es的模板
	@Autowired
	ElasticsearchTemplate elasticsearchTemplate;
	
	/**
	 * 
	 * @Title: searchES 
	 * @Description: 根据ES索引库做模糊查询，搜索相关的文章
	 * @param m
	 * @param key
	 * @return
	 * @return: String
	 */
	@GetMapping("index")
	public String searchES(Model m,HttpServletRequest request,String key,
			@RequestParam(defaultValue="1")int page,
			@RequestParam(defaultValue="1")int chnId,
			@RequestParam(defaultValue="0")int categoryId) {
		
		/*//如果当前页等于0
		
		//注入仓库
		//1.根据标题来搜索文章并且搜索的关键字高亮显示 
		AggregatedPage<?> selectObjects = HLUtils.selectObjects(elasticsearchTemplate, Article.class, page, ConstantClass.PAGE_SIZE, new String [] {"title"}, "id", key);
		//返回要查询包含关键字的集合
		List<Article> list = (List<Article>) selectObjects.getContent();
		//2.用es查询好的数据做分页处理
		//把查询到的集合放入PageInfo分页工具类，进行分页
		PageInfo<Article> info = new PageInfo<>(list);
		
		//设置当前页
		info.setPageNum(page);
		//设置每页展示的数据
		info.setPageSize(ConstantClass.PAGE_SIZE);
		//设置数据总条数
		info.setTotal(selectObjects.getTotalElements());
		//获取总页(三木运算判断，如果总条数%每页展示的数据==0，他就是总页数，如果不是，则是总页数+1)
		int pages = (int) (selectObjects.getTotalElements()%ConstantClass.PAGE_SIZE==0?selectObjects.getTotalElements()/ConstantClass.PAGE_SIZE:selectObjects.getTotalElements()/ConstantClass.PAGE_SIZE+1);
		if(selectObjects.getTotalElements()%ConstantClass.PAGE_SIZE == 0){
			int pages = (int) (selectObjects.getTotalElements() / ConstantClass.PAGE_SIZE);
		} else {
			int pages = (int) (selectObjects.getTotalElements() / ConstantClass.PAGE_SIZE + 1);
		}
		//设置总页数
		info.setPages(pages);
		//如果当前页等于最后一页
		if(page == pages){
			//就让当前页等于最后一页
			page=pages;
		}
		//设置上一页
		info.setPrePage(page-1);
		//设置下一页
		info.setNextPage(page+1);*/
		
		if(page==0) {
			//就让当期前页等于1
			page = 1;
		}
		
		ESHLUtil<Article> selectPageObjects = ESHLUtil.selectPageObjects(Article.class, page, ConstantClass.PAGE_SIZE, new String [] {"title"}, "id", key);
		PageInfo<Article> info = selectPageObjects.getPageInfo();
		//设置上一页
		info.setPrePage(page-1);
		//设置下一页
		info.setNextPage(page+1);
		
		//分页对象放到model作用域里面
		m.addAttribute("hotList", info);
		//把模糊查询的值放入model作用域，做全局查询的回显时用
		m.addAttribute("key", key);
		
		// 回传参数数值
		request.setAttribute("chnId", chnId);
		request.setAttribute("categoryId", categoryId);
		
		//获取所有的频道
		List<Channel> channels = channelService.list();
		request.setAttribute("channels", channels);
				
		//获取这个频道下所以的分类
		List<Category> categories = categoryService.listByChannelId(chnId);
		request.setAttribute("categories", categories);
		
		//获取热门文章
		PageInfo<Article> hotList = articleService.hotList(page);
		request.setAttribute("hotList", hotList);
		
		
		//最新文章
		List<Article> newArticles = articleService.getNewArticles(5);
		request.setAttribute("newArticles", newArticles);
		
		// 获取最新图片文章
		List<Article> imgArticles = articleService.getImgArticles(10);
		request.setAttribute("imgArticles", imgArticles);
		
		// 友情链接
		PageInfo<Link> pageInfo=  linkService.list(1);
		List<Link> linkList =  pageInfo.getList();
		request.setAttribute("linkList", linkList);
		
		return "index";
	}

	
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
