<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>前台首页头</title>
</head>
<body align="center">
    <form action="${pageContext.request.contextPath}/UpFile.do" method="post" enctype="multipart/form-data">

        用户名:<input type="text" name="username"><br>
        <%--上传文件1:<input type="file" name="list[0]"><br>
        上传文件2:<input type="file" name="list[1]"><br>--%>
        上传文件:<input type="file" name="upfile"><br>
        <input type="submit" value="upload">

    </form>
</body>
</html>