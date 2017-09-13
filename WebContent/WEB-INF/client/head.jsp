<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>前台首页头</title>
</head>
<body align="center">
	<br>
	<h1>万事屋书店</h1>
	<br>

	<br>
	<div style="float: left; margin-left: 550px">
		<a
			href="${pageContext.request.contextPath }/client/IndexServlet?method=getall"
			target="body">home page</a> <a
			href="${pageContext.request.contextPath }/client/buycart.jsp"
			target="body">shopping cart</a> <a href="#">check orders</a>
	</div>

	<div style="float: right">
		<c:if test="${user==null }">
			<form
				action="${pageContext.request.contextPath }/client/LoginServlet"
				method="post">
				username:<input type="text" name="username" style="width: 60px">
				password:<input type="password" name="password" style="width: 60px">
				<input type="submit" value="sign in"> <input type="button"
					value="sign up"
					onclick="javascript:window.parent.body.location.href='${pageContext.request.contextPath}/WEB-INF/client/register.jsp'">
			</form>
		</c:if>
		
		<c:if test="${user!=null }">
			welcome:${user.username }<a href="${pageContext.request.contextPath }/client/LogoutServlet">log out</a>
		</c:if>

	</div>

</body>
</html>