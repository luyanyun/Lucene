<%@page contentType="text/html; utf-8" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<body>
<form action="${pageContext.request.contextPath}/user/shiro" method="post">
    名字:<input type="text" name="name">
    密码:<input type="password" name="password">
    <input type="submit" value="提交">
</form>
</body>
</html>
