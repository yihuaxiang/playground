<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <title>敖武的项目游乐园-IP查询</title>
    <%@include file="./common/meta.jsp" %>
    <meta name="description" content="IP 查询，可视化查询，API 查询接口"/>
    <style></style>
</head>
<body>
<div class="search">
    <div class="hd">
        <h1 style="display: flex;align-items: center;justify-content: center;color: rgb(60, 60, 60)">
            <span>
            IP归属地查询
            </span>
            <img src="https://fudongdong-statics.oss-cn-beijing.aliyuncs.com/autoupload/2022-04-09/11c7f5b2af10433eb33081aa55217ef7.svg"
                 width="35" alt="iP查询">
        </h1>
        <p style="font-size: 16px; color: rgb(105, 105, 105);text-align: center;">您${type}的iP地址是：[${ip}] 来自：${city}</p>
    </div>
    <div class="bd" style="margin-bottom: 15px;text-align: center;">
        <div class="search"
             style="display: inline-block; margin-bottom: 7px; border: 2px solid #2095f2; border-radius: 3px;">
            <form name="ipForm">
                <input class="input-text" id="ip" type="text" name="ip" size="16" placeholder="请输入iP地址"
                       style="min-width: 200px; height: 20px; padding: 10px; border: medium none; line-height: 20px; vertical-align: middle; outline: none;">
                <input class="input-button" type="submit" value="查询"
                       style="width: 100px; height: 40px; line-height: 40px; vertical-align: middle; font-family: Tahoma, Arial, Helvetica, 寰蒋闆呴粦; font-size: 16px; color: rgb(255, 255, 255); cursor: pointer; appearance: none; border-width: medium; border-style: none; border-color: initial; border-image: initial; background: rgb(32, 149, 242); outline: none; border-radius: 0px;">
            </form>
        </div>
    </div>
    <%@include file="common/pvLog.jsp" %>
</body>
</html>
