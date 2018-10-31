<%@ page language="java" contentType="text/html; charset=EUC-KR"
         pageEncoding="EUC-KR" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach items="${comments}" var="comment">
    <c:if test="${comment.isDeleted==false}">
    ${comment.nickName} ${comment.regdate} <input type="button" value="답글"> <br>
    ${comment.content}<br>
        <input type="button" value="수정" onclick="modifyForm('${comment.id}','${comment.content}')">
        <input type="button" value="삭제" onclick="window.location.href='/comment/delete?id=${comment.id}'">
    </c:if>
    <c:if test="${comment.isDeleted==true}">
        삭제된 글입니다.<br>
    </c:if>
    <br>
</c:forEach>



<form method="post" action="/comment/write">
    content : <input type="<textarea name=" name="content" cols="30" rows="10"></textarea>
    <input type="submit">
</form>

<script type="text/javascript">
    function modifyForm(id, content) {
        window.open("/comment/modifyform?id="+id+"&content="+content,"","width=570, height=350, resizable = no, scrollbars = no");

    }
</script>


</body>
</html>
