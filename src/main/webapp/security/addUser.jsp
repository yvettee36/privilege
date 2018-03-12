<%--
  Created by IntelliJ IDEA.
  User: yvettee
  Date: 2017/11/13
  Time: 18:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加用户</title>
</head>
<body>
<form action="/servlet/userServlet?method=add" method="post">
    <table>
        <tr>
            <td>用户名</td>
            <td>
                <input type="text" name="userName">
            </td>
        </tr>
        <tr>
            <td>用户密码</td>
            <td>
                <input type="text" name="passWord">
            </td>
        </tr>
        <tr>
            <td>用户描述</td>
            <td>
                <textarea rows="5" cols="50" name="description"></textarea>
            </td>
        </tr>

        <tr>
            <td></td>
            <td>
                <input type="submit" value="添加用户">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
