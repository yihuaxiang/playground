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
        bottom: 0px;
        left: 0px;
        right: 0px;
        height: 30px;
        line-height: 30px;
        padding-left: 20px;
      }

      .infos h3 {
        font-size: 15px;
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
        width: 266px;
        height: 173px;
        transition: all 0.3s ease;
      }

      .project-list li a img:hover {
        box-shadow: 2px 2px 4px #ccc;
      }
    .title {
      margin: 0px;
      padding-left: 20px;
      display: flex;
      padding-right: 20px;
      font-size: 14px;
      font-weight: 700;
      color: black;
          height: 39px;
          background-color: #fafafa;
          border-bottom: 1px solid #e9e9e9;
          line-height: 39px;
    }

    .title a.github {
        flex-grow: 1;
        text-align: left;
        padding-left: 5px;
    }
    html,body {
      margin: 0;
    }
     a {
     color: #0275d8;
         text-decoration: none;
     }
    </style>
</head>
<body class="content__default">
<h2 class="title">
    敖武的项目游乐园
    <a class="github" href="https://github.com/yihuaxiang/playground">
        <img style="vertical-align: middle;" alt="GitHub Repo stars" src="https://img.shields.io/github/stars/yihuaxiang/playground?style=social">
    </a>
  <a href="https://z.wiki">返回作者的博客</a>
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
