<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>前端显示页面</title>
</head>
<body style="text-align: center;">
	<br>
	<br>
	<div id="content" style="width: 1600px" align="center">
		<div id="category"
			style="float: left; width: 200px; border: 1px solid red; text-align: left; height: 300px; margin-left: 400px"
			height="300px">
			<br> 分类列表：<br>
			<c:forEach items="${categories }" var="cate">
				<li>
					<a href="${pageContext.request.contextPath }/client/IndexServlet?category_id=${cate.id}">${cate.name }</a>
				</li><br>
			</c:forEach>
		</div>

		<div id="bookandpage"
			style="float: left; margin-left: 50px">
			<div id="books" style="float: top">
				<c:forEach items="${page.list }" var="book">
					<div style="float: left">
						<img width="100px" height="150px"
							src="${pageContext.request.contextPath }/images/${book.imageName}">
					</div>
					<div id="bookinfo"
						style="float: left; margin-left: 10px; text-align: left">
						<ul>
							<li>书名：${book.name }</li>
							<li>作者：${book.author }</li>
							<li>价格：${book.price }</li>
							<li>简介：${book.description }</li>
							<li><a href="${pageContext.request.contextPath }/client/BuyServlet?bookid=${book.id}">buy it</a></li>
						</ul>
					</div>
					<!-- 做div浮动之后为了不影响后面的页面显示效果要使用一下语句 -->
					<div style="clear: both"></div>
					<br>
				</c:forEach>
			</div>

			<div id="page" style="float:bottom">
				<br> 当前第【${page.pagenum }】页&nbsp;&nbsp;
				<c:forEach var="pagenum" begin="${page.startPage }"
					end="${page.endPage }">
		[<a
						href="${pageContext.request.contextPath }/client/IndexServlet?category_id=${param.category_id }&pagenum=${pagenum}">${pagenum }</a>]
	</c:forEach>
				总共【${page.totalpage }】页码，总共【${page.totalrecord }】条记录
			</div>
		</div>
	</div>
</body>
</html>