<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>수정페이지</title>
</head>
<body>
<form method="post" action="/article/update">
    name : 세션에서받아온다.<br>
    title : <input type="text" name="title" value="${article.title}"><br>
    content : <textarea name="content" col="50" rows="6">${articleContent.content}</textarea><br>
    <input type="hidden" name="id" value="${article.id}"/>
    <input type="submit">
</form>
</body>
</html>
