<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>관리자 페이지</title>
    <jsp:include page="../head/head.jsp"/>
    <script type="text/javascript">
        function check() {
            if (window.confirm("정말 수정하시겠습니까?") == true) {
                document.form.submit();
            } else {
                return false;
            }
        }

        $('.ui.checkbox')
            .checkbox()
        ;
    </script>
</head>
<body class="ui grid">
<div class="ui container" style="padding:50px 0px"> <!--메인 컨텐츠용 div-->
    <h2 class="ui header"> <a href="/"> 메인 페이지로 돌아가기</a></h2>
        <table class="ui structured black striped celled padded table">
            <thead>
            <tr>
                <th rowspan="2" class="one wide center aligned">id</th>
                <th rowspan="2" class="four wide center aligned">이메일</th>
                <th rowspan="2" class="three wide center aligned">닉네임</th>
                <th colspan="${permSize+1}" class="eight wide center aligned">권한</th>
            </tr>
            <tr>
                <c:forEach items="${permissions}" var="permission">
                    <th class="center aligned">${permission}</th>
                </c:forEach>
                <th class="center aligned">수정</th>
            </tr>
            </thead>
    <c:forEach items="${members}" var="member" varStatus="status">
        <form class="ui form" method="post" name="adminTable" onsubmit="return check()" action="/admin/update">
            <input type="hidden" name="id" value="${member.id}"/>
            <input type="hidden" name="email" value="${email}">
            <input type="hidden" name="pg" value="${pg}">
            <tr>
                <th class="center aligned">${member.id}</th>
                <th class="center aligned">${member.email}</th>
                <th class="center aligned">${member.nickName}</th>
                <c:forEach items="${permissions}" var="permission">
                <c:if test="${fn:contains(member.perms, permission)}">
                <th class="center aligned">
                        <input type="checkbox" name="checkPerm" value="${permission}" checked>
                </th>
                </c:if>
                <c:if test="${not fn:contains(member.perms, permission)}">
                <th class="center aligned"><input type="checkbox" name="checkPerm" value="${permission}"></th>
                </c:if>
                </c:forEach>
                <th class="center aligned"><input type="submit" value="수정"/></th>
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

</div>
</body>
</html>
