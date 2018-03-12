<%--
  Created by IntelliJ IDEA.
  User: yvettee
  Date: 2017/11/9
  Time: 13:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>权限列表</title>
</head>
<body style="text-align: center;">
<br/> <br/>

<table width="60%">
    <tr>
        <td></td>
        <td></td>
        <td align="right">
            <a href="/servlet/privilegeServlet?method=addUI">添加权限</a>
        </td>
    </tr>
</table>
<table width="60%" frame="border">
    <tr>
        <td>权限名称</td>
        <td>权限描述</td>
        <td>操作</td>
    </tr>

    <c:forEach var="p" items="${list}">
        <tr>
            <td>${p.name }</td>
            <td>${p.description }</td>
            <td>
                <a href="#">删除</a>
                <a href="#">修改</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
