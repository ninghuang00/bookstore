<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<html:form action="/BookAction?method=add" method="post" enctype="multipart/form-data">
    <table>
        <tr>
            <td>bookname</td>
            <td>
                <html:text property="name"/>
            </td>
            <td>
                <html:errors property="name"/>
            </td>

        </tr>
        <tr>
            <td>author</td>
            <td>
                <html:text property="author"/>
            </td>
            <td>
                <html:errors property="author"/>
            </td>
        </tr>
        <tr>
            <td>price</td>
            <td>
                <html:text property="price"/>
            </td>
            <td>
                <html:errors property="price"/>
            </td>
        </tr>
        <tr>
            <td>picture</td>
            <td><input type="file" name="imageName" /></td>
            <%--<td><html:file property="imageName"/></td>--%>

        </tr>
        <tr>
            <td>description</td>
            <td><textarea rows="5" cols="50" name="description"></textarea></td>
        </tr>
        <tr>
            <td>category</td>
            <td>
                <html:select property="category_id">
                    <c:forEach items="${categories}" var="cate">
                        <html:option value="${cate.id}">${cate.name}</html:option>
                    </c:forEach>
                </html:select>
            </td>
                <%--<td><select name="category_id">
                    <c:forEach items="${categories }" var="cate">
                        <option value="${cate.id }">${cate.name }</option>
                    </c:forEach>
                </select></td>--%>
        </tr>
        <tr>
            <td><input type="reset" value="reset"></td>
            <td><input type="submit" value="submit"></td>
        </tr>

    </table>

</html:form>

</body>
</html>