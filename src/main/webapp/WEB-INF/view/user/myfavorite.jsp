<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script> 
<table class="table">
  <caption>我的收藏</caption>
  <thead>
    <tr>
      <th>id</th>
      <th width="40%">标题</th>
      <th>频道</th>
      <th>分类</th>
      <th>发布日期</th>
      <th>操作</th>
      </tr>
  </thead>
  <tbody>
    <c:forEach items="${pageInfo.list}" var="article">
	    <tr class="active">
	       <td>${article.id}</td>
	       <td>${article.title}</td>
	       <td>${article.channel.name}</td>
	       <td>${article.category.name}</td>
	       <td><fmt:formatDate value="${article.created}" pattern="yyyy-MM-dd"/></td>
	       
	      <td>
	      	<!-- 查看文章详情 -->
	        <input type="button" onclick="showArticle(${article.id})" value="查看" class="btn-info"/>
	      	<!-- 取消收藏 -->
	      	<input type="button" onclick="delFavorite(${article.id})" value="取消收藏"  class="btn-danger"/>
	      </td>
	      </tr>
   	</c:forEach>
  </tbody>
</table>
<div class="row" style="text-align: center;padding-top:1px">
<ul class="pagination" style="text-align:center">
    <li><a href="javascript:goPage(${pageInfo.prePage})">&laquo;</a></li>
    <c:forEach begin="${pageInfo.pageNum-2 > 1 ? info.pageNum-2:1}" end="${pageInfo.pageNum+2 > info.pages ? info.pages:info.pageNum+2}" varStatus="index">    		
    	<c:if test="${pageInfo.pageNum!=index.index}">
    		<li><a href="javascript:goPage(${index.index})">${index.index}</a></li>
    	</c:if>
    	<c:if test="${pageInfo.pageNum==index.index}">
    		<li><a href="javascript:void"><strong> ${index.index} </strong> </a></li>
    	</c:if>
    	
    </c:forEach>
    <li><a href="javascript:goPage(${pageInfo.nextPage})">&raquo;</a></li>
</ul>
</div>

<script type="text/javascript">

	function goPage(page){
		var url="/user/myfavorite?page="+page ;
		$("#content").load(url);
	}
	
	function showArticle(articleId) {
		location="/article/showdetail?id="+articleId;
	}

	function delFavorite(articleId){
		$.post(
				"/user/delFavorite",
				{id:articleId},function(data){
				if(data.result==1){
					alert("取消收藏成功");
					$("#content").load("/user/myfavorite?page=${pageInfo.pageNum}");
				}else{
					alert(data.errorMsg);
				}
			},
			"json"
			)
	}
</script>