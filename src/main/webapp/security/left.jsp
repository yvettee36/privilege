<%--
  Created by IntelliJ IDEA.
  User: yvettee
  Date: 2017/11/9
  Time: 11:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body style="text-align: center;">

<a href="/servlet/privilegeServlet?method=getAll" target="main">权限管理</a>
<br/><br/>

<a href="/servlet/resourceServlet?method=getAll" target="main">资源管理</a>
<br/><br/>

<a href="/servlet/roleServlet?method=getAll" target="main">角色管理</a>
<br/><br/>

<a href="/servlet/userServlet?method=getAll" target="main">用户管理</a>
<br/><br/>
</body>
</html>
