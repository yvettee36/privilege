<%--
  Created by IntelliJ IDEA.
  User: yvettee
  Date: 2017/11/10
  Time: 17:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>更新角色权限</title>
</head>
<body>

<table frame="border" width="40%">
    <tr>
        <td>角色名</td>
        <td>${role.name }</td>
    </tr>

    <tr>
        <td>角色描述</td>
        <td>${role.description }</td>
    </tr>

    <tr>
        <td>角色原有权限</td>
        <td>
            <c:forEach var="p" items="${role.privileges}">
                ${p.name }<br/>
            </c:forEach>
        </td>
    </tr>

    <tr>
        <td>需授予的权限</td>
        <td>
            <!-- 当下面表单提交时，会给服务器带去：角色id和要授予的权限id  -->
            <form action="/servlet/roleServlet?method=updatePrivilege" method="post">
                <input type="hidden" name="roleId" value="${role.id }">
                <c:forEach var="p" items="${list}">
                    <input type="checkbox" name="pid" value=${p.id }>${p.name }<br/>
                </c:forEach>
                <input type="submit" value="更新权限">
            </form>
        </td>
    </tr>

</table>
</body>
</html>
