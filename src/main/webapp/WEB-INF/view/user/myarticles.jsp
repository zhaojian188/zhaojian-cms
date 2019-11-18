<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<table class="table">
  <caption>我的文章</caption>
  <thead>
    <tr>
      <th>id</th>
      <th width="50%">标题</th>
      <th>频道</th>
      <th>分类</th>
      <th>发布日期</th>
      <th>状态</th>
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
	       <c:choose>
	       	<c:when test="article.status==0">
	       		待审核
	       	</c:when>
	       	<c:when test="article.status==1">
	       		审核通过
	       	</c:when>
	       	<c:when test="article.status==2">
	       		审核被拒
	       	</c:when>
	       	<c:otherwise>
	       		未知
	       	</c:otherwise>	
	       </c:choose>
	       </td>
	      <td>
	      	<input type="button" onclick="" value="修改" class="btn-info"/>
	      	<input type="button" onclick="delArticle(${article.id})" value="删除"  class="btn-danger"/>
	      </td></tr>
   	</c:forEach>
  </tbody>
</table>
<script>
	function delArticle(articleId){
		$.post("/user/delArticle",{id:articleId},function(data){
			if(data.result==1){
				alert("删除成功");
			}else{
				alert(data.errorMsg);
			}
		},"json")
	}
</script>
