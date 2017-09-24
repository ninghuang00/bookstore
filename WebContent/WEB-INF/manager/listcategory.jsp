<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>列出所有分类</title>
</head>
<body align="center">
	<br>
	<br>
	<table align="center" frame="border" width="60%" border="1">
		<tr>
			<td>分类名称</td>
			<td>分类描述</td>
			<td>操作</td>
		</tr>
		<c:forEach items="${categories }" var="cate">
			<tr>
				<td>${cate.name}</td>
				<td>${cate.description}</td>
				<td><a
					href="${pageContext.request.contextPath }/manager/CategoryServlet?method=delete&id=${cate.id }">删除</a>
					<a
					href="${pageContext.request.contextPath }/manager/CategoryServlet?method=update&id=${cate.id }">修改</a>
				</td>
			</tr>
			<br>
		</c:forEach>

	</table>
</body>
</html>