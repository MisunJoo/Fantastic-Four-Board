<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>수정페이지</title>
</head>
<body>
<form class="ui form" method="post" action="/article/update" >
    <div class="ui equal width form">
        <div class="field">
            <label>제목</label>
            <input type="text" placeholder="제목을 입력해주세요." name="title" value="${article.title}" required>
        </div>
        <div class="field">
            <label>내용</label>
            <textarea placeholder="내용을 입력해주세요." name="content" required>${articleContent.content}</textarea>
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
</body>
</html>
