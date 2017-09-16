<%@ page import="cn.hn.golobals.Gender" %>
<%@ page import="cn.hn.golobals.Preference" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>注册界面</title>
</head>
<body>
<html:form action="/CustomerRegisterAction" method="post">

    <table align="center" frame="border" width="50%" border="1">
        <tr>
            <td>用户名</td>
            <td>
                <html:text property="username"/>
            </td>
            <td>
                <html:errors property="username"/>
            </td>
        </tr>


        <tr>
            <td>密码</td>
            <td>
                <html:password property="password"/>
            </td>
            <td>
                <html:errors property="password"/>
            </td>
        </tr>

        <tr>
            <td>确认密码</td>
            <td>
                <html:password property="password2"/>
            </td>
            <td>
                <html:errors property="password2"/>
            </td>

        </tr>

        <tr>
            <td>性别</td>
            <td>
                <c:forEach items="<%=Gender.values()%>" var="gender">
                    <html:radio property="gender" value="${gender.name}">${gender.value}</html:radio>
                </c:forEach>
            </td>
            <td>
                <html:errors property="gender"/>
            </td>

        </tr>

        <tr>
            <td>生日</td>
            <td>
                <html:text property="birthday"/>
            </td>
            <td>
                <html:errors property="birthday"/>
            </td>
        </tr>

        <tr>
            <td>收入</td>
            <td>
                <html:text property="income"/>
            </td>
            <td>
                <html:errors property="income"/>
            </td>
        </tr>

        <tr>
            <td>城市</td>
            <td>
                <html:select property="city">
                    <html:option value="beijing">北京</html:option>
                    <html:option value="shanghai">上海</html:option>
                    <html:option value="guangzhou">广州</html:option>
                </html:select>
            </td>

        </tr>

        <tr>
            <td>爱好</td>
            <td>
                <c:forEach var="pre" items="<%=Preference.values()%>">
                    <html:checkbox property="preference" value="${pre.name}">${pre.value}</html:checkbox>
                </c:forEach>
            </td>
            <td>
                <html:errors property="preference"/>
            </td>
        </tr>

        <tr>
            <td>邮箱</td>
            <td>
                <html:text property="email"/>
            </td>
            <td>
                <html:errors property="email"/>
            </td>
        </tr>

        <tr>
            <td>
                <html:reset value="重置"/>
            </td>
            <td>
                <html:submit value="提交"/>
            </td>
        </tr>
    </table>
</html:form>
</body>
</html>