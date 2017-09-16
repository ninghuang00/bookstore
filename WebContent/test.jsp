<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%--
  Created by IntelliJ IDEA.
  User: huangning
  Date: 2017/9/14
  Time: 下午2:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>registerTest</title>
</head>
<body>
<html:link action="/CustomerRegisterUI">register</html:link><br>
<html:link action="/UpFileUI">upload</html:link><br>
<a href="${pageContext.request.contextPath}/DownloadFile.do">download</a>

<html:link action="/BookAction?method=add">add book</html:link><br>
<html:link action="/BookAction?method=delete">delete book</html:link><br>
<html:link action="/BookAction?method=update">update book</html:link><br>
<html:link action="/BookAction?method=find">find book</html:link><br>

</body>
</html>
