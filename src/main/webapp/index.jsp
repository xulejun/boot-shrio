<%--
  Created by IntelliJ IDEA.
  User: XLJ
  Date: 2020/7/17
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<html>
<head>
    <title>Shiro权限</title>
</head>
<body>

<h1>系统主页</h1>
<h1><shiro:principal/></h1>
<shiro:authenticated>
    认证之后展示的内容<br>
</shiro:authenticated>
<shiro:notAuthenticated>
    未认证展示的内容<br>
</shiro:notAuthenticated>

<a href="${pageContext.request.contextPath}/user/login">退出登录</a>
<ul>
    <%--    只有admin权限用户才能看到--%>
    <shiro:hasAnyRoles name="user,admin">
        <li><a href="">用户管理</a>
            <ul>
                <%--以标签的形式对权限进行控制--%>
                <shiro:hasPermission name="user:add:*">
                    <li><a href="">增加</a>
                </shiro:hasPermission>
                <shiro:hasPermission name="user:delete:*">
                    <li><a href="">删除</a>
                </shiro:hasPermission>
                <shiro:hasPermission name="user:update:*">
                    <li><a href="">修改</a>
                </shiro:hasPermission>
                <shiro:hasPermission name="order:find:*">
                    <li><a href="">查询</a>
                </shiro:hasPermission>
            </ul>
        </li>
    </shiro:hasAnyRoles>
    <shiro:hasRole name="admin">
        <li><a href="">商品管理</a></li>
        <li><a href="">订单管理</a></li>
        <li><a href="">物流管理</a></li>
    </shiro:hasRole>
</ul>

</body>
</html>
