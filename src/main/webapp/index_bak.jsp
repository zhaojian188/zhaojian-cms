<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

 <meta name="viewport" content="width=device-width, initial-scale=1.0">
 <link href="/resource/bootstrap/css/bootstrap.css" rel="stylesheet">  
 <script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script> 
 <script type="text/javascript" src="/resource/bootstrap/js/bootstrap.js"></script>
 <title>基本模板</title>

</head>
<body>
<!-- 导航条 -->
<nav class="navbar navbar-default  navbar-light bg-light">
  
  <div class="container-fluid">
    <!-- logo -->
    <div class="navbar-header">
      <a class="navbar-brand" href="#">
        <img alt="Brand" src="/resource/images/logo.png">
      </a>
    </div>
    
  
   
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1"> 
    <!-- 输出条 -->
     <form class="navbar-form navbar-left">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Search">
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
      </form>
      
      <ul class="nav navbar-nav navbar-right">
        
        <li><a href="#"><img src="/resource/images/avatar1.jpg" height="50px" weight="50px"/> </a></li>
        
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">Action</a></li>
            <li><a href="#">Another action</a></li>
            <li><a href="#">Something else here</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="#">Separated link</a></li>
          </ul>
        </li>
      </ul>
      </div>
      </div>
</nav>

<div class="container-fluid " style="background:yellow">
	<div class="container" style="background:grey; min-height:500px" >
		<div class="row"></div>
			<div class="col-md-2" style="background:blue;min-height:200px ">
			
			<ul class="list-group">
  <li class="list-group-item">Cras justo odio</li>
  <li class="list-group-item">Dapibus ac facilisis in</li>
  <li class="list-group-item">Morbi leo risus</li>
  <li class="list-group-item">Porta ac consectetur ac</li>
  <li class="list-group-item">Vestibulum at eros</li>
</ul>

			</div>
			
			<div class="col-md-8" style="background:red;min-height:300px">
			</div>
			
			<div class="col-md-2" style="background:green;min-height:300px">
			</div>
		</div>
	</div>
	
</div>

<nav class="navbar navbar-default navbar-fixed-bottom   navbar-light bg-light">

</nav>


</body>
</html>