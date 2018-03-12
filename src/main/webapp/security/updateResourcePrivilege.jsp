<%--
  Created by IntelliJ IDEA.
  User: yvettee
  Date: 2017/11/10
  Time: 15:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>更新资源权限页面</title>
</head>
<body>
<table frame="border" width="40%">
    <tr>
        <td>资源URI</td>
        <td>${resource.uri }</td>
    </tr>

    <tr>
        <td>资源描述</td>
        <td>${resource.description }</td>
    </tr>

    <tr>
        <td>资源原有权限</td>
        <td>${resource.privilege.name }</td>
    </tr>

    <tr>
        <td>需授予的权限</td>
        <td>
            <!-- 当下面表单提交时，会给服务器带去：资源id和要授予的权限id  -->
            <form action="/servlet/resourceServlet?method=updatePrivilege"
                  method="post">
                <input type="hidden" name="rid" value="${resource.id }">
                <c:forEach var="p" items="${list}">
                    <input type="radio" name="pid" value=${p.id }>${p.name }<br/>
                </c:forEach>
                <input type="submit" value="更新权限">
            </form>
        </td>
    </tr>

</table>
</body>
</html>
