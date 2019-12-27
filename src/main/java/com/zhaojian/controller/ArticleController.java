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
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
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
	
	@Autowired
	RedisTemplate redisTemplate;
	
	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;
	//线程池任务执行器
	@Autowired
	ThreadPoolTaskExecutor executor;
	/**
	 * 
	 * @Title: showDetail 
	 * @Description: 根据文章id,查看文章
	 * @param request
	 * @param id
	 * @return
	 * @return: String
	 */
	@RequestMapping("showdetail")
	public String showDetail(HttpServletRequest request,Integer id) {
		
		Article article = articleService.getById(id); 
		/**
		 * 为CMS系统文章最终页（详情页），每访问一次就同时往文章表的浏览量字段加1，
		 * 如果一篇文章集中一时刻上百万次浏览，就会给数据库造成压力。
		 * 现在请你利用Redis提高性能，当用户浏览文章时，将“Hits_${文章ID}_${用户IP地址}”为key，查询Redis里有没有该key，
		 * 如果有key，则不做任何操作。如果没有，则使用Spring线程池异步执行数据库加1操作，
		 * 并往Redis保存key为Hits_${文章ID}_${用户IP地址}，value为空值的记录，而且有效时长为5分钟。
		 */
		//获取本机ip
		String ip = request.getLocalAddr();
		//将“Hits_${文章ID}_${用户IP地址}”为key
		String key = "Hits_"+id+"_"+ip;
		//查询redis的key
		String data = (String) redisTemplate.opsForValue().get(key);
		//如果有key，则不做任何操作。
		if(data == null) {
			//如果没有，则使用Spring线程池异步执行数据库加1操作
			executor.execute(new Runnable() {
				
				@SuppressWarnings("unchecked")
				@Override
				public void run() {
					//点击量+1
					article.setHits(article.getHits()+1);
					//修改mysql数据库的数据
					articleService.updateHits(article);
					//并往Redis保存key为Hits_${文章ID}_${用户IP地址}，value为空值的记录，而且有效时长为5分钟
					redisTemplate.opsForValue().set(key, "", 5, TimeUnit.MINUTES);
					
				}
			});
		}
		
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
