<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <title>敖武的项目游乐园</title>
    <link rel="shortcut icon" type="image/x-icon" href="https://fudongdong.com/favicon.ico"/>
    <meta name=viewport content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
    <meta name="keywords" content="敖武 项目 游乐园 demo 展览 敖武的博客"/>
    <meta name="description" content="敖武的项目游乐园，展示竞品项目"/>
    <link rel="stylesheet" href="./lulu/ui.css">
    <style>
      .title {
        text-align: center;
      }

      .project-list {
        list-style: none;
        display: flex;
        flex-wrap: wrap;
        justify-content: center;
        padding-left: unset;
      }

      .project-list li {
        position: relative;
        cursor: pointer;
      }

      .project-list li a:after {
        content: '';
        position: absolute;
        left: 0px;
        right: 0px;
        width: 100%;
        height: 100%;
        top: 0px;
        background-color: rgba(0, 0, 0, 0);
        transition: all 0.3s ease-in-out;
        text-align: center;
        line-height: 213px;
      }

      .project-list li a:hover:after {
        content: '点击访问';
        position: absolute;
        left: 0px;
        right: 0px;
        width: 100%;
        height: 100%;
        top: 0px;
        background-color: #cccc;
        text-align: center;
        line-height: 213px;
      }

      .project-list li a {

      }

      .project-list li a img {
        width: 316px;
        height: 213px;
        transition: all 0.3s ease;
      }

      .project-list li a img:hover {
        box-shadow: 2px 2px 4px #ccc;
      }
    </style>
</head>
<body>
<h2 class="title">
    敖武的项目游乐园
    <a href="https://github.com/yihuaxiang/playground">
        <img alt="GitHub Repo stars" src="https://img.shields.io/github/stars/yihuaxiang/playground?style=social">
    </a>
</h2>
<ul class="project-list">
    <c:forEach items="${list}" var="project">
        <li>
            <a href="${project.url}">
                <img src="${project.preview}"/>
            </a>
        </li>
    </c:forEach>
</ul>
<script type="module" src="./lulu/all.js"></script>
</body>
</html>
