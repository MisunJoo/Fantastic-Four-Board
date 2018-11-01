<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Read</title>
</head>
<body>
게시판읽기페이지<br>
글번호 : ${article.id}<br>
글제목 : ${article.title} <br><br>
글내용<br>${articleContent.content}
<a href="/article/update?id=${article.id}">수정이</a>
<a href="/article/delete?id=${article.id}&categoryid=${article.categoryId}">삭제</a><br>
<a href="/article/reply?id=${article.id}">답변달기</a>
<a href="/article/list?categoryid=${article.categoryId}">목록으로</a><br>
</body>
</html>