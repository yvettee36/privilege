<%--
  Created by IntelliJ IDEA.
  User: yvettee
  Date: 2017/11/10
  Time: 14:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加资源</title>
</head>
<body>
<form action="/servlet/resourceServlet?method=add" method="post">
    <table>
        <tr>
            <td>资源URI</td>
            <td>
                <input type="text" name="uri">
            </td>
        </tr>
        <tr>
            <td>资源描述</td>
            <td>
                <textarea rows="5" cols="50" name="description"></textarea>
            </td>
        </tr>

        <tr>
            <td></td>
            <td>
                <input type="submit" value="添加资源">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
