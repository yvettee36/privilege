<%--
  Created by IntelliJ IDEA.
  User: yvettee
  Date: 2017/11/13
  Time: 20:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录界面</title>
</head>
<body>
<form action="/servlet/userServlet?method=login" method="post">
    用户名：<input type="text" name="userName"><br/>
    密码：<input type="text" name="passWord"><br/>
    <input type="submit" value="登陆">
</form>
</body>
</html>
