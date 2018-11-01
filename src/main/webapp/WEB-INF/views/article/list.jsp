<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
헬롱~<br>
<c:forEach items="${articleList}" var="article">
    <a href="/article/read?id=${article.id}">${article.title} </a><br>
</c:forEach>
<a href="/article/write?categoryid=${categoryId}">글쓰기</a>
</body>
</html>
