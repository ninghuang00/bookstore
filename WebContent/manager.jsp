<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>后台首页</title>
</head>
<frameset rows="25%,*">
    <frame src="${pageContext.request.contextPath}/DispatcherAction.do?forward=goManagerHead" name="head"/>
    <%--<frame src="${pageContext.request.contextPath}/manager/head.jsp" name="head.jsp">--%>

    <%--通过分发Action访问WEB-INF目录下的资源--%>
    <%--<html:frame action="${pageContext.request.contextPath}/DispatcherAction.do?forward=goManagerHead" name="head"/>--%>


    <frameset cols="18%,*">
        <frame src="${pageContext.request.contextPath}/DispatcherAction.do?forward=goManagerLeft" name="left"/>
        <frame src="${pageContext.request.contextPath}/DispatcherAction.do?forward=goManagerBody" name="body"/>
    </frameset>
</frameset>
</html>