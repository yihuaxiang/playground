<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <title>敖武的项目游乐园</title>
    <%@include file="./common/meta.jsp" %>
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
        margin-right: 20px;
        margin-bottom: 20px;
        box-shadow: 0px 0px 3px #ccc;
        border-radius: 2px;
      }

      .project-list li:hover {
        box-shadow: 1px 1px 3px #ccc;
      }

      .infos {
        position: absolute;
        bottom: 0px;
        left: 0px;
        right: 0px;
        height: 30px;
        line-height: 30px;
        padding-left: 20px;
        background: #cccccc70;
      }

      .infos h3 {
        display: inline;
      }

      li:hover .more-infos{
        display: block;
      }

      .more-infos {
        display: none;
        position: absolute;
        top: 50%;
        transform: translateY(-50%);
        text-align: center;
        left: 50%;
        z-index: 9;
        transform: translateX(-50%) translateY(-50%);
      }

      .more-infos p {
        margin: 0 auto;
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
        content: '';
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
<body class="content__default">
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
                <div class="infos">
                    <h3 class="name">
                        ${project.name}
                    </h3>
                </div>
                <div class="more-infos">
                    <p class="desc">
                        ${project.description}
                    </p>
                </div>
            </a>
        </li>
    </c:forEach>
</ul>
<script type="module" src="./lulu/all.js"></script>
<%@include file="common/pvLog.jsp"%>
</body>
</html>
