<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <title>敖武的项目游乐园-IP查询</title>
    <%@include file="./common/meta.jsp" %>
    <meta name="description" content="IP 查询，可视化查询，API 查询接口"/>
    <style>
        .api-doc-line {
          font-size: 12px;
          color: rgb(105, 105, 105);
          margin: 0;
          line-height: 18px;
        }
    </style>
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
                <input list="ips" data-sublist="" class="input-text" id="ip" type="text" name="ip" size="16" placeholder="请输入iP地址"
                       style="min-width: 200px; height: 20px; padding: 5px; border: medium none; line-height: 20px; vertical-align: middle; outline: none;">
                <datalist id="ips">
                    <option value="123.123.42.137">
                    <option value="111.13.149.108">
                    <option value="123.151.137.18">
                    <option value="203.205.251.178">
                    <option value="14.197.245.125">
                    <option value="1.2.127.255">
                    <option value="14.197.210.114">
                    <option value="36.96.247.255">
                </datalist>
                <input class="input-button" type="submit" value="查询"
                       style="width: 100px; height: 30px; line-height: 30px; vertical-align: middle; font-family: Tahoma, Arial, Helvetica, 寰蒋闆呴粦; font-size: 16px; color: rgb(255, 255, 255); cursor: pointer; appearance: none; border-width: medium; border-style: none; border-color: initial; border-image: initial; background: rgb(32, 149, 242); outline: none; border-radius: 0px;">
            </form>
        </div>
    </div>
    <div class="module" style="max-width: 500px; margin: 0 auto;">
        <div class="hd" style="position: relative; background: #f5f5f5; height: 40px; border: 1px solid #dbdbdb; border-bottom: none;">
            <h3 style="color: #555;display: block; border-left: 3px solid #2095f2; padding-left: 8px; line-height: 20px; text-decoration: none; font-size: 14px; margin: 10px 0;">接口使用文档</h3>
        </div>
        <div class="bd" style="padding: 10px 20px; border: 1px solid #dbdbdb; line-height: 32px; overflow: hidden;">
            <p class="api-doc-line">
                接口地址：<b>https://playground.fudongdong.com/lbs/getIp</b>
            </p>
            <p class="api-doc-line">
                接口参数：<b>ip</b>，参数举例：<b>153.37.191.6</b>
            </p>
            <p class="api-doc-line">
                请求示例：<a href="https://playground.fudongdong.com/lbs/getIp?ip=153.37.191.6">https://playground.fudongdong.com/lbs/getIp?ip=153.37.191.6</a>
            </p>
        </div>
    </div>
    <%@include file="common/pvLog.jsp" %>
</body>
</html>
