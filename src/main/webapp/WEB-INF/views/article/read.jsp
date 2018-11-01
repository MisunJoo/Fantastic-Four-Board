<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Read</title>
</head>
<body>
게시판읽기페이지<br>
${article.title} <br><br>
${articleContent.content}
<a href="/article/update?id=${article.id}">수정이</a>
</body>
</html>