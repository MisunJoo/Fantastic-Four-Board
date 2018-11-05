<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="../head/head.jsp"/>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>게시판 - 글 읽기</title>
</head>
<body class="ui grid">
<jsp:include page="../nav/nav.jsp"/>
<div class="ui container twelve wide column" style="padding:50px 0px"> <!--메인 컨텐츠용 div-->
    <div class="ui comments">
        <div class="comment">
            <div class="content">
                <a class="author">글쓴이 : ${article.nickName}</a>
                <div class="metadata">
                    <div class="date">날짜 : ${article.regdate}</div>
                    <div class="date">조회수 ${article.hit}</div>
                </div>
            </div>
        </div>
    </div>

    <div class="ui message">
        <div class="header">
            ${article.title}
        </div>
    </div>

        <div class="ui message" style="min-height: 150px">
            <p>${articleContent.content}</p>
        </div>

    <c:if test="${fileInfo != null}">
        <div class="ui message">
            <p><a href="/article/download/${fileInfo.articleId}"> ${fileInfo.originName} - ${fileInfo.size}B</a></p>
        </div>
    </c:if>
    <div>
        <div>
            <button type="submit" onclick="window.location.href='/article/list?categoryid=${article.categoryId}'" class="left floated ui secondary button">
                목록
            </button>
        </div>
        <div>
            <button type="submit" onclick="window.location.href='/article/reply?id=${article.id}'" class="right floated ui secondary button">
                <i class="icon edit"></i>
                답변달기
            </button>
            <button type="submit" onclick="window.location.href='/article/write?categoryid=${article.categoryId}'" class="right floated ui secondary button">
                <i class="icon edit"></i>
                글쓰기
            </button>
            <button type="button" onclick="window.location.href='/article/update?id=${article.id}'" class="right floated ui button">
                수정
            </button>
            <button type="button" onclick="window.location.href='/article/delete?id=${article.id}&categoryid=${article.categoryId}'" class="right floated ui button">
                삭제
            </button>
        </div>
    </div>

    <div> <!--댓글 부분 div-->
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
                ${comment.nickName} ${comment.regdate} ip:${comment.ipAddress}
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
            <input type="hidden" value="${article.id}" name="articleId">
            content : <textarea name="content" cols="150" rows="10"></textarea>
            <input type="submit" value="등록">
        </form>

    </div> <!--댓글 부분 /div-->

</div>

</body>
</html>