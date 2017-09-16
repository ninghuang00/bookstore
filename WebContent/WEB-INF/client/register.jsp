<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>register page</title>
</head>
<body>
<br>
<br>
<%--<form action="${pageContext.request.contextPath }/client/RegisterServlet" method="post" align="center">--%>
<%--<form action="${pageContext.request.contextPath}/Register.do" method="post" align="center">--%>
<%--
	如果html:标签发现session域中有org.apache.struts.action.TOKEN为随机数，它会自动隐藏字段
--%>
<html:form action="/RegisterAction" style="align-items: center">
    <table align="center" border="1" width="50% ">
        <tr>
            <td>username</td>
            <td>
                    <%--username:<input type="text" name="username" ><br>--%>
                <html:text property="username"/>
            </td>
            <td>
                <html:errors property="username"/>
            </td>
        </tr>

        <tr>
            <td>password</td>
            <td>
                    <%--password:<input type="password" name="password"><br>--%>
                <html:password property="password"/>
            </td>
            <td>
                <html:errors property="password"/>
            </td>
        </tr>
        <tr>
            <td>phonenumber</td>
            <td>
                    <%--phonenum:<input type="text" name="phonenumber"><br>--%>
                <html:text property="phonenumber"/>
            </td>
            <td>
                <html:errors property="phonenumber"/>
            </td>
        </tr>
        <tr>
            <td>address</td>
            <td>
                    <%--address :<input type="text" name="address"><br>--%>
                <html:text property="address"/>
            </td>
            <td>
                <html:errors property="address"/>
            </td>
        </tr>
        <tr>
            <td>email</td>
            <td>
                    <%--e-mail  :<input type="text" name="email"><br>--%>
                <html:text property="email"/>
            </td>
            <td>
                <html:errors property="email"/>
            </td>
        </tr>
        <tr>
            <td>
                <html:reset value="reset"/>
            </td>
            <td>
                <html:submit value="submit"/>
            </td>
        </tr>
    </table>

    <%--<input type="submit" value="submit">--%>
    <%--</form>--%>
</html:form>
</body>
</html>