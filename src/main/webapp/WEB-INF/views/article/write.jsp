<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="../head/head.jsp"/>
    <title>게시판 - 글쓰기</title>
</head>
<body>
<br>
<div class="ui container">
    <form class="ui form" method="post" action="/article/write" >
        <div class="ui equal width form">
            <div class="field">
                <label>제목</label>
                <input type="text" placeholder="제목을 입력해주세요." name="title" required>
            </div>
            <div class="field">
                <label>내용</label>
                <textarea placeholder="내용을 입력해주세요." name="content" required></textarea>
            </div>
            <input type="hidden" name="categoryId" value="${categoryId}">
            <div>
                <button type="submit" class="right floated ui primary button">
                    <i class="icon edit"></i>
                    등록
                </button>
                <button type="button" onclick="window.location.href='/article/list?categoryid=${categoryId}'" class="right floated ui button">
                    취소
                </button>
            </div>

        </div>
    </form>
</div>
</body>
</html>
