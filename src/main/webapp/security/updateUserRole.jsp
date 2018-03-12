<%--
  Created by IntelliJ IDEA.
  User: yvettee
  Date: 2017/11/13
  Time: 19:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>更新用户角色</title>
</head>
<body>
<table frame="border" width="40%">
    <tr>
        <td>用户名</td>
        <td>${user.userName }</td>
    </tr>

    <tr>
        <td>用户描述</td>
        <td>${user.description }</td>
    </tr>

    <tr>
        <td>用户原有角色</td>
        <td>
            <c:forEach var="role" items="${user.roles}">
                ${role.name }<br/>
            </c:forEach>
        </td>
    </tr>

    <tr>
        <td>需授予的角色</td>
        <td>
            <!-- 当下面表单提交时，会给服务器带去：用户id和要授予的角色id  -->
            <form action="/servlet/userServlet?method=updateRole" method="post">
                <input type="hidden" name="userId" value="${user.id }">
                <c:forEach var="r" items="${list}">
                    <input type="checkbox" name="rId" value=${r.id }>${r.name }<br/>
                </c:forEach>
                <input type="submit" value="更新角色">
            </form>
        </td>
    </tr>
</table>
</body>
</html>
