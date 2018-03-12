<%--
  Created by IntelliJ IDEA.
  User: yvettee
  Date: 2017/11/10
  Time: 17:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加角色</title>
</head>
<body>
<body>
<form action="/servlet/roleServlet?method=add" method="post">
    <table>
        <tr>
            <td>角色名称</td>
            <td>
                <input type="text" name="name">
            </td>
        </tr>
        <tr>
            <td>角色描述</td>
            <td>
                <textarea rows="5" cols="50" name="description"></textarea>
            </td>
        </tr>

        <tr>
            <td></td>
            <td>
                <input type="submit" value="添加角色">
            </td>
        </tr>
    </table>
</form>
</body>
</body>
</html>
