<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>buy cart</title>
</head>
<body style="text-align:center">
	<table align="center" border="1" width="80%">
		<tr>
			<td>bookname</td>
			<td>author</td>
			<td>unitprice</td>
			<td>quantity</td>
			<td>subtotal</td>
			<td>operation</td>
		</tr>
		<c:forEach var="me" items="${cart.map }">
			<tr>
				<td>${me.value.book.name }</td>
				<td>${me.value.book.author }</td>
				<td>${me.value.book.price }</td>
				<td>${me.value.quantity }</td>
				<td>${me.value.price }</td>
				<td><a href="${pageContext.request.contextPath }/client/BuyServlet?method=remove&bookid=${me.value.book.id}">remove</a></td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="3">total price</td>
			<td colspan="3">${cart.price }</td>
		</tr>
	</table>
	<a href="${pageContext.request.contextPath }/client/OrderServlet">place an order</a>
</body>
</html>