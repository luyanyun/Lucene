<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; utf-8" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<body>
<shiro:authenticated> <%--authenticated 标签判断用户有没有通过认证 --%>
热烈欢迎您:<shiro:principal/><%--注意shiro 为我们提供了很多功能 例如session的管理并且shiro给我提供了自己一系列的标签 --%>
    <ul>
        <li>轮播图</li>
        <shiro:hasRole name="normal"> <%--Shiro的授权方式两部分 角色的授权和资源的授权 --%>
        <li>用户</li>
            <ul>
                <shiro:hasPermission name="create"><li>增</li></shiro:hasPermission>
                <shiro:hasPermission name="delete"><li>删</li></shiro:hasPermission>
                <li>改</li>
            </ul>
        </shiro:hasRole>
        <li>日志</li>
        <shiro:hasRole name="super"> <%--注意每条判断语句都会执行 授权部分的代码 查询一次数据库开销太大 必须引入缓存  --%>
            <li>管理员</li>
        </shiro:hasRole>
    </ul>
</shiro:authenticated>
<shiro:notAuthenticated>
    <a href="${pageContext.request.contextPath}/user/login.jsp">您好还未登陆,摸我登录一哈</a>
</shiro:notAuthenticated>
</body>
</html>
