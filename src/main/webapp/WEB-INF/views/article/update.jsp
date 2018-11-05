<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="../head/head.jsp"/>
    <title>수정페이지</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body class="ui grid">
<br>
<jsp:include page="../nav/nav.jsp"/>
<div class="ui container twelve wide column" style="padding:50px 0px"> <!--메인 컨텐츠용 div-->
    <form class="ui form" method="post" action="/article/update" enctype="multipart/form-data">
        <div class="ui equal width form">
            <div class="field">
                <label>제목</label>
                <input type="text" placeholder="제목을 입력해주세요." name="title" value="${article.title}" required>
            </div>
            <div class="field">
                <label>내용</label>
                <textarea placeholder="내용을 입력해주세요." name="content" required>${articleContent.content}</textarea>
            </div>
            <div class="field">
                <input type="file" name="file"/>
            </div>
            <input type="hidden" name="id" value="${article.id}">
            <div>
                <button type="submit" class="right floated ui secondary button">
                    <i class="icon edit"></i>
                    등록
                </button>
                <button type="button" onclick="window.location.href='/article/list?categoryid=${article.categoryId}'"
                        class="right floated ui button">
                    취소
                </button>
            </div>

        </div>
    </form>
</div>
</body>
</html>
