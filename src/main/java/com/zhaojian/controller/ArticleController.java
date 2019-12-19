/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: ArticleController.java 
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.zhaojian.beans.Article;
import com.zhaojian.beans.Category;
import com.zhaojian.beans.Comment;
import com.zhaojian.beans.Image;
import com.zhaojian.beans.TypeEnum;
import com.zhaojian.common.CmsAssert;
import com.zhaojian.common.MsgResult;
import com.zhaojian.service.ArticleService;
import com.zhaojian.service.CategoryService;

/** 
 * @ClassName: ArticleController 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月14日 
 */

@RequestMapping("article")
@Controller
public class ArticleController {
	
	@Autowired
	ArticleService articleService;
	
	@Autowired
	CategoryService catService; 
	/**
	 * 
	 * @Title: showDetail 
	 * @Description: 查看文章
	 * @param request
	 * @param id
	 * @return
	 * @return: String
	 */
	@RequestMapping("showdetail")
	public String showDetail(HttpServletRequest request,Integer id) {
		
		Article article = articleService.getById(id); 
		CmsAssert.AssertTrueHtml(article!=null, "文章不存在");
		
		request.setAttribute("article",article);
		if(article.getArticleType()==TypeEnum.HTML) {
			return "article/detail";
		}else {
			//获取json转换器
			Gson gson = new Gson();
			//文章内容转换成集合对象
			List<Image> imgs = gson.fromJson(article.getContent(), List.class);
			article.setImgList(imgs);
			System.out.println("article "+article);
			return "article/detailimg";
			
		}
		
		
	}
	/**
	 * 
	 * @Title: getCategoryByChannel 
	 * @Description: 获取频道信息
	 * @param chnId
	 * @return
	 * @return: MsgResult
	 */
	@RequestMapping("getCategoryByChannel")
	@ResponseBody
	public MsgResult getCategoryByChannel(int chnId) {
		//List<Category> categories =  
		List<Category> categories = catService.listByChannelId(chnId);
		return new MsgResult(1, "",  categories);
		
	}
	/**
	 * 
	 * @Title: commentlist 
	 * @Description: 获取该用户的所有评论
	 * @param request
	 * @param id
	 * @param page
	 * @return
	 * @return: String
	 */
	@RequestMapping("commentlist")
	//@ResponseBody
	public String commentlist(HttpServletRequest request, int id,
			@RequestParam(defaultValue="1") int page) {
		
		PageInfo<Comment> pageComment =  articleService.commentlist(id,page);
		request.setAttribute("pageComment", pageComment);
		return "article/comments";
		//return new MsgResult(1,"获取成功",pageComment);
		
	}
}
