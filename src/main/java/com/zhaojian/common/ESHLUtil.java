/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: ControllerInterceptor.java 
 * @Prject: zhaojian-cms
 * @Package: com.zhaojian.common 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年12月23日
 * @version: V1.0   
 */
package com.zhaojian.common;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Component;

import com.github.pagehelper.PageInfo;

/**
 * 
 * @ClassName: ESHLUtil 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年12月23日
 * @param <T>
 */
@Component
public class ESHLUtil<T>  {

	private static ElasticsearchTemplate elasticsearchTemplate;
	
	private AggregatedPage<T> aggregatedPage;
	
	private PageInfo<T> pageInfo;
	
	
	
	private void setAggregatedPage(AggregatedPage<T> aggregatedPage) {
		this.aggregatedPage = aggregatedPage;
	}

	private void setPageInfo(PageInfo<T> pageInfo) {
		this.pageInfo = pageInfo;
	}

	@Autowired
	private void setElasticsearchTemplate(ElasticsearchTemplate elasticsearchTemplate) {
		ESHLUtil.elasticsearchTemplate = elasticsearchTemplate;
	}
	
	// 查询操作,高亮显示
	public static <T> ESHLUtil<T> selectPageObjects(Class<T> clazz, Integer page, Integer rows,String[] fieldNames ,String sortField, String value) {
		ESHLUtil<T> ESHLUtil = new ESHLUtil<T>();
		
		PageInfo<T> info = new PageInfo<T>();
		info.setPageNum(page);
		info.setPageSize(rows);
		
		AggregatedPage<T> pageInfo = null;
		// 创建Pageable对象 主键的实体类属性名
		final Pageable pageable = PageRequest.of(page - 1, rows, Sort.by(Sort.Direction.ASC, sortField));
		// 查询对象
		SearchQuery query = null;
		// 查询条件高亮的构建对象
		QueryBuilder queryBuilder = null;

		if (value != null && !"".equals(value)) {
			// 高亮拼接的前缀与后缀
			String preTags = "<font color=\"red\">";
			String postTags = "</font>";

			// 定义创建高亮的构建集合对象
			HighlightBuilder.Field nameField = new HighlightBuilder
	                .Field("*").matchedFields(fieldNames)
	                .preTags(preTags)
	                .postTags(postTags);
			
			// 创建queryBuilder对象
			queryBuilder = QueryBuilders.multiMatchQuery(value, fieldNames);
			query = new NativeSearchQueryBuilder().withQuery(queryBuilder).withHighlightFields(nameField)
					.withPageable(pageable).build();

			pageInfo = elasticsearchTemplate.queryForPage(query, clazz, new SearchResultMapper() {

				public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable1) {

					List<T> content = new ArrayList<T>();
					long total = 0l;

					try {
						// 查询结果
						SearchHits hits = response.getHits();
						if (hits != null) {
							// 获取总记录数
							total = hits.getTotalHits();
							// 获取结果数组
							SearchHit[] searchHits = hits.getHits();
							// 判断结果
							if (searchHits != null && searchHits.length > 0) {
								// 遍历结果
								for (int i = 0; i < searchHits.length; i++) {
									// 对象值
									T entity = clazz.newInstance();

									// 获取具体的结果
									SearchHit searchHit = searchHits[i];

									// 获取对象的所有的字段
									Field[] fields = clazz.getDeclaredFields();

									// 遍历字段对象
									for (int k = 0; k < fields.length; k++) {
										// 获取字段对象
										Field field = fields[k];
										// 暴力反射
										field.setAccessible(true);
										// 字段名称
										String fieldName = field.getName();
										if (!fieldName.equals("serialVersionUID") && !fieldName.equals("user")
												&&!fieldName.equals("channel")&&!fieldName.equals("category")
												&&!fieldName.equals("articleType")&&!fieldName.equals("imgList")) {
											
											HighlightField highlightField = searchHit.getHighlightFields()
													.get(fieldName);
											if (highlightField != null) {
												// 高亮 处理 拿到 被<font color='red'> </font>结束所包围的内容部分
												String value = highlightField.getFragments()[0].toString();
												// 注意一下他是否是 string类型
												field.set(entity, value);
											} else {
												// 获取某个字段对应的 value值
												Object value = searchHit.getSourceAsMap().get(fieldName);
												// 获取字段的类型
												Class<?> type = field.getType();
												if (type == Date.class) {
													// bug
													if (value != null) {
														field.set(entity, new Date(Long.valueOf(value + "")));
													}
												}else if(type == java.sql.Date.class) {
													if (value != null) {
														field.set(entity, new java.sql.Date(Long.valueOf(value + "")));
													}
												} else {
													field.set(entity, value);
												}
											}
										}
									}

									content.add(entity);
								}
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

					return new AggregatedPageImpl<T>(content, pageable, total);
				}
			});

		} else {
			// 没有查询条件的的时候，获取es中的全部数据 分页获取
			query = new NativeSearchQueryBuilder().withPageable(pageable).build();
			pageInfo = elasticsearchTemplate.queryForPage(query, clazz);
		}
		
		info.setTotal((int) pageInfo.getTotalElements());
		ESHLUtil.setPageInfo(info);
		ESHLUtil.setAggregatedPage(pageInfo);
		
		return ESHLUtil;
	}
	
	
	public AggregatedPage<T> getAggregatedPage() {
		return this.aggregatedPage;
	}
	
	public PageInfo<T> getPageInfo(){
		this.pageInfo.setList(this.aggregatedPage.getContent());
		int pages = (int) (this.pageInfo.getTotal() / this.pageInfo.getPageSize() + ((this.pageInfo.getTotal() % this.pageInfo.getPageSize() == 0) ? 0 : 1 ));
		this.pageInfo.setPages(pages);
		
		return this.pageInfo;
	}
	
}
