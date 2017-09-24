<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>订单详细</h2>
	<br>
	<table border="1" width="60%">
		<tr>
			<td>书名</td>
			<td>售价</td>
			<td>数量</td>
			<td>应收货款</td>
		</tr>
		<c:forEach items="${order.orderitems }" var="orderitem">
			<tr>
				<td>${orderitem.book.name }</td>
				<td>${orderitem.book.price }</td>
				<td>${orderitem.quantity }</td>
				<td>${orderitem.price }</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="2">总计收款</td>
			<td colspan="2">${order.price }元</td>
		</tr>


	</table>

	<br>
	<br>
	<h2>收货人详细地址</h2>
	<table border="1" width="60%">
		<tr>
			<td>用户</td>
			<td>电话</td>
			<td>地址</td>
			<td>邮箱</td>
		</tr>
		<tr>
			<td>${order.user.username }</td>
			<td>${order.user.phonenumber }</td>
			<td>${order.user.address }</td>
			<td>${order.user.email }</td>
		</tr>
	</table>

	<a href="${pageContext.request.contextPath }/manager/ConfirmOrderServlet?orderid=${order.id }">确认发货</a>
	<br>
</body>
</html>