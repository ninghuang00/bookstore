<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body align="center">
	<br>
	<br>
	<table border="1" align="center" width="80%">
		<tr>
			<td>bookname</td>
			<td>author</td>
			<td>price</td>
			<td>image</td>
			<td>descrption</td>
			<td>operation</td>
		</tr>
		<c:forEach items="${page.list }" var="book">
			<tr>
				<td>${book.name }</td>
				<td>${book.author }</td>
				<td>${book.price }</td>
				<td><a
					href="${pageContext.request.contextPath }/images/${book.imageName}">click
						to watch</a></td>
				<td>${book.description }</td>
				<td><a
					href="${pageContext.request.contextPath }/manager/BookServlet?method=update&id=${book.id}">update</a>
					<a
					href="${pageContext.request.contextPath }/manager/BookServlet?method=delete&id=${book.id}">delete</a>
				</td>
			</tr>
		</c:forEach>

	</table>
	<br>
	当前第【${page.pagenum }】页&nbsp;&nbsp;
	<c:forEach var="pagenum" begin="${page.startPage }" end="${page.endPage }">
		[<a href="${pageContext.request.contextPath }/manager/BookServlet?method=list&pagenum=${pagenum}">${pagenum }</a>]
	</c:forEach>
	总共【${page.totalpage }】页码，总共【${page.totalrecord }】条记录
</body>
</html>