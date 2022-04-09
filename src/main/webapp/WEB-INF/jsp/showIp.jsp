<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <title>敖武的项目游乐园-IP查询</title>
    <%@include file="./common/meta.jsp" %>
    <style></style>
</head>
<body>
<h2 class="title">
    IP查询，您当前IP为${ip}，所在城市为${city}。
</h2>
<%@include file="common/pvLog.jsp" %>
</body>
</html>
