
<%@ page language="java" contentType="text/html; charset=EUC-KR"
         pageEncoding="EUC-KR" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach items="${comments}" var="comment ">
    <c:if test="${comment.isDeleted==false}">
    ${comment.nickName} ${comment.regdate} <input type="button" value="���"> <br>
    ${comment.content}<br>
        <input type="button" value="����" onclick="window.location.href='/comment/delete?id=${comment.id}'">
        <input type="button" value="����">
    </c:if>
    <c:if test="${comment.isDeleted==true}">
        ������ ���Դϴ�.<br>
    </c:if>
    <br>${comment.isDeleted}
    ${comment.content}${comment.content}
</c:forEach>



<form method="post" action="/comment/write">
    content : <input type="<textarea name=" name="content" cols="30" rows="10"></textarea>
    <input type="submit">
</form>



</body>
</html>
