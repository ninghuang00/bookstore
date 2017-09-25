<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>show orders</title>
</head>
<body>
	<br>
	<h2 align="center">order list</h2>

	<table border="1" width="80%" align="center">
		<tr>
			<td>订单号</td>
			<td>订单人</td>
			<td>订单时间</td>
			<td>订单总价</td>
			<td>订单状态</td>
			<td>操作</td>
		</tr>

		<c:forEach var="order" items="${orders }">
			<tr>
				<td>${ order.id}</td>
				<td>${ order.user.username}</td>
				<td>${ order.ordertime}</td>
				<td>${ order.price}</td>
				<td>${ order.state==true?'已发货':'未发货'}</td>
				<td>
					<a href="#">删除</a>
					<%--<a href="${pageContext.request.contextPath }/manager/OrderDetailServlet?orderid=${order.id}">查看明细</a>--%>
					<html:link action="/OrderAction?method=detailOrder&orderid=${order.id}">查看明细</html:link><br>
				</td>
			</tr>

		</c:forEach>

	</table>
</body>
</html>