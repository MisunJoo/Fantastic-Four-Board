<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>관리자 페이지</title>
</head>
<body>

<table border="1">
    <thead>
        <tr>
            <th rowspan="3">id</th>
            <th rowspan="3">이메일</th>
            <th rowspan="3">닉네임</th>
        </tr>
        <tr>
            <th colspan="3">권한</th>
        </tr>
        <tr>
            <th>Read</th>
            <th>Write</th>
        </tr>
    </thead>
<c:forEach items="${members}" var="member">
    <tr>
        <th>${member.id}</th>
        <th>${member.email}</th>
        <th>${member.nickName}</th>
        <th><input type="checkbox" name="read"> </th>
        <th><input type="checkbox" name="write"></th>
    </tr>
</c:forEach>
</table>

<form method="get" action="/admin/list">
    <select name="searchType">
        <option value="email">이메일</option>
    </select>
    <input type="search" name="email">
    <button type="submit">검색</button>
</form>

</body>
</html>
