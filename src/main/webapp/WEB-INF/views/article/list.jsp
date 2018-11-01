<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
헬롱~<br>
<c:forEach items="${articleList}" var="article">
    <a href="/article/read?id=${article.id}">${article.title} </a> ${article.nickName}<br>
</c:forEach>
<a href="/article/write?categoryid=${categoryId}">글쓰기</a>
<form method="post" action="/article/search">
    <select name="searchType">
        <option value="제목">제목</option>
        <option value="내용">내용</option>
        <option value="제목+내용">제목+내용</option>
        <option value="이름">이름</option>
    </select>
    <input type="search" name="searchWord">
    <input type="hidden" name="categoryId" value="${categoryId}">
    <button type="submit">검색</button>
</form>
</body>
</html>
