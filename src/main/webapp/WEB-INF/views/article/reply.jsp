<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>게시판 답글쓰기 페이지</title>
</head>
<body>
<h1>답글쓰기</h1>
<br>

<form method="post" action="/article/reply">
    title : <input type="text" name="title"><br>
    content : <textarea name="content" col="50" rows="6"></textarea><br>

    <input type="hidden" name="parentId" value="${parentId}">
    <input type="submit">
</form>

</body>
</html>
