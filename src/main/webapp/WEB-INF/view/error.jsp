<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/resource/bootstrap/css/bootstrap.css">  
<script type="text/javascript" src="/resource/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/js/jquery.validate.js"></script>
<script type="text/javascript" src="/resource/My97DatePicker/WdatePicker.js"></script>
<link rel="stylesheet" href="/resource/css/index3.css">
<title>错误提示</title>
</head>
<body>
	<div class="alert alert-danger" role="alert">
  				${errorInfo}
    </div>
</body>
</html>