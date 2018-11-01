<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
헬롱~
<c:forEach items="${articleList}" var="article">
    ${article.title}</br>
</c:forEach>
</body>
</html>
