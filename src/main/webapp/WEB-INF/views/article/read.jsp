<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="../head/head.jsp"/>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>게시판 - 글 읽기</title>
</head>
<body>
<div class="ui container">

    <div class="ui comments">
        <div class="comment">
            <div class="content">
                <a class="author">글쓴이 : ${article.nickName}</a>
                <div class="metadata">
                    <div class="date">날짜 : ${article.regdate}</div>
                    <div class="date">조회수 ${article.hit}</div>
                </div>
            </div>
        </div>
    </div>

    <div class="ui message">
        <div class="header">
            ${article.title}
        </div>
    </div>
    <div class="ui message" style="min-height: 150px">
        <p>${articleContent.content}</p>
    </div>

    <div class="ui container">
        <div class="ui container">
            <button type="submit" onclick="window.location.href='/article/list?categoryid=${article.categoryId}'" class="left floated ui primary button">
                목록
            </button>
        </div>
        <div class="ui container">
            <button type="submit" onclick="window.location.href='/article/reply?id=${article.id}'" class="right floated ui primary button">
                <i class="icon edit"></i>
                답변달기
            </button>
            <button type="submit" onclick="window.location.href='/article/write?categoryid=${article.categoryId}'" class="right floated ui primary button">
                <i class="icon edit"></i>
                글쓰기
            </button>
            <button type="button" onclick="window.location.href='/article/update?id=${article.id}'" class="right floated ui button">
                수정
            </button>
            <button type="button" onclick="window.location.href='/article/delete?id=${article.id}'" class="right floated ui button">
                삭제
            </button>
            <a href="/article/download?id=${article.id}">다운로d</a>
        </div>
    </div>

</div>

</body>
</html>