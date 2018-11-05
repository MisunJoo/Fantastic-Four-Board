<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>관리자 페이지</title>
    <script type="text/javascript">
        function check() {
            if (window.confirm("정말 수정하시겠습니까?") == true) {
                document.form.submit();
            } else {
                return false;
            }
        }
    </script>
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
        <th colspan="${permSize+1}">권한</th>
    </tr>
    <tr>
        <c:forEach items="${permissions}" var="permission">
            <th>${permission}</th>
        </c:forEach>
        <th>수정</th>
    </tr>
    </thead>
    <c:forEach items="${members}" var="member" varStatus="status">
        <form method="post" name="adminTable" onsubmit="return check()" action="/admin/update">
            <input type="hidden" name="id" value="${member.id}"/>
            <input type="hidden" name="email" value="${email}">
            <input type="hidden" name="pg" value="${pg}">
            <tr>
                <th>${member.id}</th>
                <th>${member.email}</th>
                <th>${member.nickName}</th>
                <c:forEach items="${permissions}" var="permission">
                <c:if test="${fn:contains(member.perms, permission)}">
                <th><input type="checkbox" name="checkPerm" value="${permission}" checked></th>
                </c:if>
                <c:if test="${not fn:contains(member.perms, permission)}">
                <th><input type="checkbox" name="checkPerm" value="${permission}"></th>
                </c:if>
                </c:forEach>
                <th><input type="submit" value="수정"/></th>
        </form>
    </c:forEach>
    </tr>
</table>
</form>

<form method="get" action="/admin/list">
    <select name="searchType">
        <option value="email">이메일</option>
    </select>
    <input type="search" name="email">
    <button type="submit">검색</button>
</form>

<c:forEach begin="1" step="1" end="${memberCount}" var="page">
    <a href="/admin/list?pg=${page}" class="btn btn-success"><b>${page}</b></a>
</c:forEach>


</body>
</html>
