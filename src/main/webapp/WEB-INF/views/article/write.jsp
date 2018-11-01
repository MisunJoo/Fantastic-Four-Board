<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>게시판쓰기페이지</title>
</head>
<body>
<h1>게시판쓰기페이지</h1>
<br>

<form method="post" action="/article/write">
    name : 세션에서받아온다.<br>
    title : <input type="text" name="title"><br>
    content : <textarea name="content" col="50" rows="6"></textarea><br>
    <input type="hidden" name="categoryId" value="${categoryId}">
    <input type="submit">
</form>

</body>
</html>
