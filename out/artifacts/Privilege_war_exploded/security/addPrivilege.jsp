<%--
  Created by IntelliJ IDEA.
  User: yvettee
  Date: 2017/11/9
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加权限</title>
</head>
<body>
<form action="/servlet/privilegeServlet?method=addP" method="post">
    <table>
        <tr>
            <td>权限名称</td>
            <td>
                <input type="text" name="name">
            </td>
        </tr>
        <tr>
            <td>权限描述</td>
            <td>
                <textarea rows="5" cols="50" name="description"></textarea>
            </td>
        </tr>
        <tr>
            <td></td>
            <td>
                <input type="submit" value="添加权限">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
