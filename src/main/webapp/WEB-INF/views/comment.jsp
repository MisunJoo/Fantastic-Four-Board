<%@ page language="java" contentType="text/html; charset=EUC-KR"
         pageEncoding="EUC-KR" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach items="${comments}" var="comment">
    <c:if test="${comment.isDeleted==false}">
        ${comment.nickName} ${comment.regdate} <input type="button" value="답글"> <br>
        <c:choose>
            <c:when test="${(comment.id == commentId) and (modication=='true')}">
                <form method="post" action="/comment/modify">
                    <input type="hidden" name="id" value="${comment.id}">
                    content : <textarea autofocus name="content" cols="150" rows="10"> ${comment.content}</textarea>
                    <input type="submit">
                </form>
            </c:when>
            <c:otherwise>
                content : ${comment.content}<br>
                <input type="button" value="수정"
                       onclick="window.location.href='/comment/modifyform?id=${comment.id}&content=${comment.content}'; return false;">
                <input type="button" value="삭제" onclick="window.location.href='/comment/delete?id=${comment.id}'">
            </c:otherwise>
        </c:choose>
    </c:if>
    <c:if test="${comment.isDeleted==true}">
        삭제된 글입니다.<br>
    </c:if>
    <br>
</c:forEach>

<form method="post" action="/comment/write">
    content : <textarea name="content" cols="150" rows="10"></textarea>
    <input type="submit">
</form>


</body>
</html>
