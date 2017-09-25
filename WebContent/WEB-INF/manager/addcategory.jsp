<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<html:form action="/CategoryAction" method="post">
		分类名称：<input type="text" name="name"><br>
		分类描述：<textarea rows="5" cols="40" name="description"></textarea><br>
		<input type="submit" value="添加">
	</html:form>
</body>
</html>