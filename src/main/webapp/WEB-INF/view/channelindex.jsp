<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
 <link href="/resource/bootstrap/css/bootstrap.css" rel="stylesheet">  
 <script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script> 
 <script type="text/javascript" src="/resource/bootstrap/js/bootstrap.js"></script>
 <title>cms频道文章页面</title>
<style type="text/css">
	.menu li{
		font-size:20px;
		text-align:center;
	}
	.menu li:hover{
		background:gray;
	}
</style>
</head>
<body>
	
 <!-- 导航条 头部 -->
<!-- <nav class="navbar navbar-default" style="height:80px">
  	
  	logo
  	<div class="navbar-header">
      <a class="navbar-brand" href="#">
        <img alt="Brand" src="/resource/images/logo.png">
      </a>
    </div>
    
	 中间的搜索框
     <form class="navbar-form"  style="float:none; margin-top:25px; text-align:center;" >
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Search">
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
      </form>
  
    
    搜索框和右侧登录信息
    <div class="collapse navbar-collapse" style="float:none;text-align:right;margin-bottom:20px"  id="bs-example-navbar-collapse-1" >
      <ul class="nav navbar-nav">
        
      </ul>
      
      头像信息
      <ul class="nav navbar-nav navbar-right" >
        <li><a href="#"><img width="40px" height="40px" src="/resource/images/aa.gif"/> </a></li>
        
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
          		张三 <span class="caret"></span></a>
          <ul class="dropdown-menu ">
            <li><a href="#">个人中心</a></li>
            <li><a href="#">个人设置</a></li>
            <li><a href="#">修改头像</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="#">退出登录</a></li>
          </ul>
        </li>
      </ul>
    </div>/.navbar-collapse

	</nav> -->
	
	<!-- 导航条 头部 -->
	<nav class="navbar navbar-default">
	  	<jsp:include page="./common/top.jsp">
	</nav>

  <div class="container-fluid" >
	<div class="container" style="minheight:200px" >
		<div class="row">
			<!-- 左侧菜单 -->
			<div class="col-md-2" style="minheight:200px;margin-top:20px" >
				
					<ul class="list-group menu">
						<li class="list-group-item" data="/" >热门文章</li>
						<c:forEach items="${channels}" var="channel" varStatus="index">
					    	<li class="list-group-item ${chnId==channel.id? "active":"" }" data="/channel?chnId=${channel.id}">${channel.name}</li>
					    </c:forEach>
					</ul>
			</div>
			
			<!-- 中间的内容 -->
			<div class="col-md-8" style="background:white;minheight:200px" >
			<div>
			
			<nav class="navbar navbar-default" role="navigation">
			    <div class="container-fluid">
			    <div>
			        <ul class="nav navbar-nav">
			        	<li <c:if test="${categoryId==0}"> class="active" </c:if> ><a href="javascript:gotoCat(0)" >全部</a></li>
			        	<c:forEach items="${categories}" var="cat">
			            	<li <c:if test="${cat.id==categoryId}"> class="active" </c:if> >
			            	<a href="javascript:gotoCat(${cat.id})" >${cat.name}</a>
			            	</li>
			            </c:forEach>
			        </ul>
			    </div>
			    </div>
			</nav>		
						
			</div>
					
					<!-- 放文章的列表 -->
					<div >
						<c:forEach items="${articles.list}" var="article" >
						<div class=row>
							<hr>
							<div class="col-md-2"><img height="80px" width="80px" src="/pic/${article.picture}"></div>
							<div class="col-md-10">
								<a href="javascript:showArticle(${article.id})">${article.title}</a>
								<br>
								 频道：<a>${article.channel.name}</a> &nbsp;&nbsp;
								 分类：<a>${article.category.name}</a>
								<br>
								<br>
								${article.user.username} 发布于  <fmt:formatDate value="${article.created}" pattern="yyyy-MM-dd"/> 
							</div>
							
						</div>
						</c:forEach>
						<div class="row">
							<ul class="pagination">
								    <li><a href="/channel?chnId=${chnId}&categoryId=${categoryId}&page=${articles.prePage}">&laquo;</a></li>
								    <c:forEach begin="${articles.pageNum-2 > 1 ? articles.pageNum-2:1}" end="${articles.pageNum+2 > articles.pages ? articles.pages:articles.pageNum+2}" varStatus="index">    		
								    	<c:if test="${articles.pageNum!=index.index}">
								    		<li><a href="/channel?chnId=${chnId}&categoryId=${categoryId}&page=${index.index}">${index.index}</a></li>
								    	</c:if>
								    	<c:if test="${articles.pageNum==index.index}">
								    		<li><a href="/channel?chnId=${chnId}&categoryId=${categoryId}&page=${index.index}"><strong> ${index.index} </strong> </a></li>
								    	</c:if>
								    	
								    </c:forEach>
								    <li><a href="/channel?chnId=${chnId}&categoryId=${categoryId}&page=${articles.nextPage}">&raquo;</a></li>
								</ul>
						</div>
					</div>
					 
			</div>
			<!-- 中间的内容结束 -->
			
			
			<div class="col-md-2" style="minheight:200px" >
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">面板标题</h3>
					</div>
					<div class="panel-body">
						这是一个基本的面板
					</div>
				</div>
				
				<div class="panel panel-info">
					<div class="panel-heading">
						<h3 class="panel-title">最新文章</h3>
					</div>
					<div class="panel-body">
						<c:forEach items="${newArticles}" var="article" varStatus="index">
							${index.index+1} . <a>${article.title}</a>
							<br/>
						</c:forEach>
					</div>
				</div>
			
			</div>
		</div>
		
	</div>

</div>



<!-- 底部 -->
<nav class="navbar navbar-default">
  <div class="container-fluid">
  </div>
</nav>

<script type="text/javascript">
	function gotoCat(catId){
		location.href="/channel?chnId=${chnId}&categoryId="+catId;
	}
</script>

<script type="text/javascript" src="/resource/js/cms_index.js"></script>

</body>
</html>