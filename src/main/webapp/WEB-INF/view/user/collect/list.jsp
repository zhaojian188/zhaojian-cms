<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  

<div class="table-responsive">
 <table class="table">
    <caption>我的收藏夹</caption>
    <thead>
      <tr>
        <th>id</th>
        <th width="40%">标题</th>
        <th>地址</th>
        <th>创建时间</th>
        <th>操作   <input type="button" value="添加" onclick="add()">
        </th>
       </tr>
    </thead>
    <tbody>
    	<c:forEach items="${info.list}"  var="collect">
    	  <tr>
	        <td>${collect.id}</td>
	        <td>${collect.name}</td>
	        <td>${collect.url}</td>
	        <td><fmt:formatDate pattern="YYYY年MM月dd号 HH:mm:ss" value="${collect.created}"></fmt:formatDate></td>
	         <td> <input type="button" value="修改" onclick="update(${collect.id})">
	             &nbsp;
	             <input type="button" value="删除" onclick="del(${collect.id})">
	         </td>
	      </tr>
      </c:forEach>
     </tbody>
  </table>
</div>
<ul class="pagination" style="text-align:center">
    <li><a href="javascript:goPage(${info.prePage})">&laquo;</a></li>
    <c:forEach begin="${info.pageNum-2 > 1 ? info.pageNum-2:1}" end="${info.pageNum+2 > info.pages ? info.pages:info.pageNum+2}" varStatus="index">    		
    	<c:if test="${info.pageNum!=index.index}">
    		<li><a href="javascript:goPage(${index.index})">${index.index}</a></li>
    	</c:if>
    	<c:if test="${info.pageNum==index.index}">
    		<li><a href="javascript:void"><strong> ${index.index} </strong> </a></li>
    	</c:if>
    	
    </c:forEach>
    <li><a href="javascript:goPage(${info.nextPage})">&raquo;</a></li>
</ul>

<script type="text/javascript">
	function goPage(page){
		var url="/collect/list?page="+page;
		$("#content").load(url);
	}
	
	function add(){
		$("#content").load("/collect/add")
	}
	/**
	 刷新当前页
	*/
	function refresh(){
		
		var url="/collect/list?page=${info.pageNum}";
		$("#content").load(url);
	}
	
	function del(id){
		$.get("/collect/delete",{id:id},function(msg){
			if(msg.result==1){
				alert("删除成功")
				refresh()
			}
			alert(msg.errorMsg)
		},"json")
	}
	//根据id修改当前收藏的文章地址
	function update(id){
		$("#content").load("/collect/update?id="+id)
	}
</script>
    