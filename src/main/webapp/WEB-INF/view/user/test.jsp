<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <meta http-equiv="X-UA-Compatible" content="IE=edge">
 
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <link href="/resource/bootstrap/css/bootstrap.css" rel="stylesheet">  
 <script type="text/javascript" src="/resource/js/jquery-1.8.3.js"></script> 
 <script type="text/javascript" src="/resource/bootstrap/js/bootstrap.js"></script>
<title>Insert title here</title>
</head>
<body>
	
	<%-- ${info} --%>
	<div class=" container">
	<div class="jumbotron">
		<h1>我的第一个BootStrap页面</h1>
		<p>重置窗口大小，查看响应式效果！</p>
	</div>
	  <div class="row">
    <div class="col-md-4">
      <h3>第一列</h3>
      <p>学技术，从W3Cschool开始！</p>
    </div>
    <div class="col-md-4">
      <h3>第二列</h3>
      <p>学技术，从W3Cschool开始！</p>
    </div>
    <div class="col-md-4">
      <h3>第三列</h3>        
      <p>学技术，从W3Cschool开始！</p>
    </div>
  </div>
</div>

	<div class="container"> 
                <div class="row"> 
                    <div class="col-sm"> 1 of 2 </div> 
                    <div class="col-sm"> 2 of 2 </div> 
                </div> 
           <div class="row"> 
                   <div class="col-sm"> 1 of 3 </div> 
                   <div class="col-sm"> 2 of 3 </div> 
                   <div class="col-sm"> 3 of 3 </div> 
           </div> 
	</div>
	
	<div class="container">
		<div class="row">
			<div class="col-md-3" style="background: red ; height: 200px"></div>
			<div class="col-md-6" style="background: pink ; height: 200px"></div>
			<div class="col-md-3" style="background: skyblue ; height: 200px"></div>
		</div>
	</div>
</body>
</html>