<%--
  Created by IntelliJ IDEA.
  User: XLJ
  Date: 2020/7/17
  Time: 14:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Shiro权限</title>
</head>
<body>
<h1>用户登录</h1>
<%--${pageContext.request.contextPath}当前项目的根路径--%>
<form action="${pageContext.request.contextPath}/user/login" method="post">
    用户名：<input type="text" name="username"> <br>
    密码  ：<input type="text" name="password"> <br>
    <input type="submit" value="登录">
</form>
</body>
</html>
