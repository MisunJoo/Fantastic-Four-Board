<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>

<c:forEach items="${comments}" var="comment">
    <c:if test="${comment.isDeleted==false}">
        <!-- 답글 출력하는 부분-->
        <c:if test="${comment.depthLevel > 0}">
            <c:forEach begin="0"  step="1" end="${comment.depthLevel}">
                &nbsp;&nbsp;&nbsp;
            </c:forEach>
            ㄴ
        </c:if>
        <div style="display: inline;">
                ${comment.nickName} ${comment.upddate} ip:${comment.ipAddress}
            <input type="button" value="답글"
                   onclick="window.location.href='/comment/write?id=${comment.id}'"><br>

        <!-- 댓글을 수정하는 부분 -->
        <c:choose>
            <c:when test="${(comment.id == commentId) and (modification=='true')}">
                <form method="post" action="/comment/modify">
                    <input type="hidden" name="id" value="${comment.id}">
                    content : <textarea autofocus name="content" cols="150" rows="10"> ${comment.content}</textarea>
                    <input type="button" value="수정 취소" onclick="window.location.href='/comment/list'">
                    <input type="submit" value="등록">
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

        <!-- 답댓글을 작성하는 부분 -->
        <c:if test="${(comment.id == commentId) and (addChild=='true')}">
            <form method="post" action="/comment/write">
                <input type="hidden" name="groupId" value="${comment.groupId}">
                <input type="hidden" name="depthLevel" value="${comment.depthLevel}">
                <input type="hidden" name="groupSeq" value="${comment.groupSeq}">
                content : <textarea autofocus name="content" cols="150" rows="10"> </textarea>
                <input type="button" value="취소" onclick="window.location.href='/comment/list'">
                <input type="submit" value="등록">
            </form>
        </c:if>
    </div>
</c:forEach>

<!--댓글 쓰는 Form-->
<form method="post" action="/comment/write">
    content : <textarea name="content" cols="150" rows="10"></textarea>
    <input type="submit" value="등록">
</form>


</body>
</html>
