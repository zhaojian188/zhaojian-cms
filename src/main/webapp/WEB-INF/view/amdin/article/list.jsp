<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!-- 模态框（Modal） -->
<div class="modal fade" id="articleDetailModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog" style="width:800px;height:1000px" >
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel">
					审核文章
				</h4>
			</div>
			<div class="modal-body"  style="height:500px;overflow-x:scroll;overflow-y:scroll">
				<h3 id="articleTitle"></h3>
				<br/>
				<div id="articleInfo"></div>
				<br/>
				<div id="articleContent"></div>
				
				
			</div>
			<div class="modal-footer">
				<input type="button" class="btn btn-default" data-dismiss="modal">关闭
				
				<input type="button" value="通过" onclick="apply(1)" class="btn btn-primary">
			
				<input type="button" value="拒绝" onclick="apply(2)" class="btn btn-danger">
					
				<input type="button" value="热门" onclick="setHot(1)" class="btn btn-warning"/>
				
				<input type="button" value="非热门" onclick="setHot(0)" class="btn btn-info"/>
					
				
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>

<table class="table">
  <caption>我的文章</caption>
  <thead>
    <tr>
      <th>id</th>
      <th width="50%">标题</th>
      <th>频道</th>
      <th>分类</th>
      <th>作者</th>
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
	       <td>${article.user.username}</td>
	       <td><fmt:formatDate value="${article.created}" pattern="yyyy-MM-dd"/></td>
	       <td>
	       <c:choose>
	       	<c:when test="${article.status==0}">
	       		待审核
	       	</c:when>
	       	<c:when test="${article.status==1}">
	       		审核通过
	       	</c:when>
	       	<c:when test="${article.status==2}">
	       		审核被拒
	       	</c:when>
	       	<c:otherwise>
	       		未知
	       	</c:otherwise>	
	       </c:choose>
	       </td>
	      <td>
	      	<%-- <input class="btn btn-primary btn-lg" data-toggle="modal" data-target="#articleDetailModal" onclick="toApply(${article.id})" value="审核" class="btn-info"/> --%>
	      	<input type="button" data-toggle="modal" data-target="#articleDetailModal" onclick="toApply(${article.id})" value="审核" class="btn-info"/>
	      	<input type="button" onclick="delArticle(${article.id})" value="删除" class="btn-danger"/>
	      </td>
	    </tr>
	   
   	</c:forEach>
  </tbody>
</table>

<ul class="pagination">
    <li><a href="javascript:goPage(${pageInfo.prePage})">&laquo;</a></li>
    <c:forEach begin="${pageInfo.pageNum-2 > 1 ? pageInfo.pageNum-2:1}" end="${pageInfo.pageNum+2 > pageInfo.pages ? pageInfo.pages:pageInfo.pageNum+2}" varStatus="index">    		
    	<c:if test="${pageInfo.pageNum!=index.index}">
    		<li><a href="javascript:goPage(${index.index})">${index.index}</a></li>
    	</c:if>
    	<c:if test="${pageInfo.pageNum==index.index}">
    		<li><a href="javascript:void"><strong> ${index.index} </strong> </a></li>
    	</c:if>
    	
    </c:forEach>
    <li><a href="javascript:goPage(${pageInfo.nextPage})">&raquo;</a></li>
</ul>

<script type="text/javascript">

	//全局变量 保存文章id
	var globalArticleId = 0;

	function goPage(page){
		var url="/admin/articles?page="+page ;
		$("#content").load(url);
	}
	
	/**
	*  status 1 通过  2 拒绝
	*/
	function apply(status) {
		
		$.post("/admin/applyArticle",{id:globalArticleId,status:status},
				function(data){
				if(data.result==1){
					alert("审核操作成功");
					$('#articleDetailModal').modal('hide');
					// 刷新列表数据
					//$("#content").load("/admin/articles?page=${pageInfo.pageNum}");
				}else{
					alert(data.errorMsg);
				}
		}
		,"json");
	}
	
	/**
	* 设置热门  status 1： 设置成热门  0 设置为非热门
	*/
	function setHot(status) {
		
		$.post("/admin/setArticleHot",{id:globalArticleId,status:status},
				function(data){
				if(data.result==1){
					alert("审核操作成功");
					$('#articleDetailModal').modal('hide');
					// 刷新列表数据
					//$("#content").load("/admin/articles?page=${pageInfo.pageNum}");
				}else{
					alert(data.errorMsg);
				}
		},
		"json"
		);
	}
	
	
	
	/**
	* 去审核文章  也就是弹出文章的详情页面
	*  以模态框的形式显示
	*/
	function toApply(articleId){
		$.post(
			"/admin/getArticle",
			{id:articleId},
			function(data){
				if(data.result==1){
					//alert(JSON.stringify(data.data));
					globalArticleId=data.data.id;
					
					$("#articleTitle").text(data.data.title);
					$("#articleContent").html(data.data.content);
					$("#articleInfo").text("作者：" + data.data.user.username  + 
						" 频道：" + data.data.channel.name +
						" 分类：" + data.data.category.name);
					
					//$("#content").load("/admin/articles?page=${pageInfo.pageNum}");
				}else{
					alert(data.errorMsg);
				}
		},"json")
		
	}

	function delArticle(articleId){
		$.post("/user/delArticle",{id:articleId},function(data){
			if(data.result==1){
				alert("删除成功");
				$("#content").load("/admin/articles?page=${pageInfo.pageNum}");
			}else{
				alert(data.errorMsg);
			}
		},"json")
	}
	//审核完成后，刷新到列表页面
	$('#articleDetailModal').on('hidden.bs.modal', 
		function () {
			location.reload(true);
		 	// 执行一些动作...
			$("#content").load("/admin/articles?page=${pageInfo.pageNum}");
			location.reload(true);
		  
	})
	
</script>

