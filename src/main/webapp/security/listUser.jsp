<%--
  Created by IntelliJ IDEA.
  User: yvettee
  Date: 2017/11/13
  Time: 18:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>用户列表</title>
</head>
<body style="text-align: center;">
<br/> <br/>

<table width="80%">
    <tr>
        <td></td>
        <td></td>
        <td align="right">
            <a href="/servlet/userServlet?method=addUI">添加用户</a>
        </td>
    </tr>
</table>
<table width="80%" frame="border">
    <tr>
        <td>用户名称</td>
        <td>用户密码</td>
        <td>用户描述</td>
        <td>操作</td>
    </tr>

    <c:forEach var="user" items="${list}">
        <tr>
            <td>${user.userName }</td>
            <td>${user.passWord }</td>
            <td>${user.description }</td>
            <td>
                <a href="/servlet/userServlet?method=forUpdateUserRoleUI&id=${user.id }">为用户授予角色</a>
                <a href="#">删除</a>
                <a href="#">修改</a>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
