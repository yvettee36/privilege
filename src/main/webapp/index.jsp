<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="itcast" uri="/itcast" %>
<html>
<body>
欢迎您：${user.userName } <a href="/userServlet?method=logout">注销</a>

<br/><br/>
<a href="/login.jsp">登陆</a>
<br/><br/>

控制标签用户需要有添加分类的权限值，才可以看到超链接
<itcast:permission value="添加分类">
    <a href="/manager/addSort">添加分类</a>
</itcast:permission>

<itcast:permission value="删除分类">
<a href="/servlet/deleteSort">删除分类</a>
</itcast:permission>

<itcast:permission value="修改分类">
<a href="/servlet/updateSort">修改分类</a>
</itcast:permission>

<itcast:permission value="查找分类">
<a href="/servlet/findSort">查找分类</a>
</itcast:permission>

</body>
</html>
