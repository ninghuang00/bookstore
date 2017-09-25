<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>后台首页的left导航</title>

    <script type="text/javascript">
        function test(e) {
            e.style.display = e.style.display == 'block' ? 'none' : 'block';
        }


    </script>
</head>
<body>
<ul>
    <li><a href="#" onclick="test(children[0])">分类管理
        <div class="dc">
            <html:link action="/CategoryUIAction" target="body">add category</html:link><br>
            <html:link action="/DispatcherAction?forward=goListCategory" target="body">list categories</html:link><br>
            <%--<a
                    href="${pageContext.request.contextPath }/manager/addcategory.jsp"
                    target="body">添加分类</a><br/>
                <a href="${pageContext.request.contextPath }/manager/CategoryServlet?method=listall"
                target="body">查看分类</a><br/>--%>
        </div>
    </a></li>
    <br>
    <li><a href="#" onclick="test(children[0])">图书管理
        <div>
            <%--改成struts框架的html标签之后就可以直接指定跳转action的调用方法,而且不用指定访问的Action的路径--%>
            <html:link action="/BookUIAction" target="body">add book</html:link><br>
            <html:link action="/DispatcherAction?forward=goListBook" target="body">list books</html:link><br>
            <%--<a
                    href="${pageContext.request.contextPath }/manager/BookServlet?method=addUI"
                    target="body">add book</a> <br/> <a
                href="${pageContext.request.contextPath }/manager/BookServlet?method=list"
                target="body">list book</a><br/>--%>


        </div>
    </a></li>
    <br>
    <li><a href="#" onclick="test(children[0])">订单管理
        <div>
            <html:link action="/OrderAction?method=listOrder&state=false" target="body">未发货订单</html:link><br>
            <html:link action="/OrderAction?method=listOrder&state=true" target="body">已经发货订单</html:link><br>
            <%--<a
                    href="${pageContext.request.contextPath }/manager/ListOrderServlet?state=false"
                    target="body">未发货订单</a><br/> <a
                href="${pageContext.request.contextPath }/manager/ListOrderServlet?state=true"
                target="body">已发货订单</a><br/>--%>
        </div>
    </a></li>
</ul>
</body>
</html>