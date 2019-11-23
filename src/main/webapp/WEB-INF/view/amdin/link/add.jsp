<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<form:form modelAttribute="link" id="form">
	网名:<form:input path="name"/><form:errors path="name"/>
	<br><br>
	网址:<form:input path="url"/><form:errors path="url"/>
	<br><br>
	<input type="button" value="添加" onclick="submit()">
</form:form>

<script type="text/javascript">
	function submit() {
		$.post(
			"/link/add",
			$("#form").serialize(),
			function(msg) {
				/* 把需要添加的内容追加到盒子里面 */
				$("#content").html(msg);
			}
		)
	}
</script>