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
    <div class="ui twelve wide column">
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

    <div class="ui container twelve wide column" style="display: inline-block"> <!--댓글 부분 div-->
        <div class="ui divider" style="border-top: 1px solid rgba(255,255,255,.1);"></div>
        <c:if test="${comments.size()>0}">
            <h3 class="ui dividing header">댓글</h3>
        </c:if>
        <div class="ui comments">
        <c:forEach items="${comments}" var="comment">
            <div class="comment">
                <div class="content">
                    <c:if test="${comment.isDeleted==false}">
                    <a class="author">${comment.nickName}</a>
                    <div class="metadata">
                        <span class="date">date:${comment.regdate}</span>
                        <span class="date">ip:${comment.ipAddress}</span>
                    </div>
                    <!-- 기본은 댓글 content를 출력하고, 수정버튼을 누르면 수정form이 나옴 -->
                    <c:choose>
                    <c:when test="${(comment.id == commentId) and (modification=='true')}">
                        <form class="ui form" method="post" action="/comment/modify">
                            <input type="hidden" value="${article.id}" name="articleId">
                            <input type="hidden" name="id" value="${comment.id}">
                            <textarea autofocus name="content" style="height:30px"> ${comment.content}</textarea>
                            <input type="button" value="수정 취소" onclick="window.location.href='/comment/list'">
                            <input type="submit" value="등록">
                        </form>
                    </c:when>
                    <c:otherwise>
                        <div class="text">
                            <!-- depthLevel에 따라 들여쓰 출력하는 부분-->
                            <c:if test="${comment.depthLevel > 0}">
                                <c:forEach begin="1"  step="1" end="${comment.depthLevel}">
                                    &nbsp;&nbsp;<b>RE:</b>
                                </c:forEach>
                            </c:if>
                                ${comment.content}
                        </div>
                        <div class="actions">
                            <a class="reply" onclick="window.location.href='/comment/write?id=${comment.id}&articleid=${article.id}'">답글</a>
                            <a class="reply" onclick="window.location.href='/comment/modifyform?id=${comment.id}&content=${comment.content}&articleid=${article.id}'; return false;">수정</a>
                            <a class="reply" onclick="window.location.href='/comment/delete?id=${comment.id}&articleid=${article.id}'">삭제</a>
                        </div>
                    </c:otherwise>
                    </c:choose>
                    </c:if>
                    <c:if test="${comment.isDeleted==true}">
                        <div style="margin: 15px 0px 35px">
                        삭제된 댓글입니다.
                        </div>
                    </c:if>

                    <!-- 답댓글을 작성하는 부분 -->
                    <c:if test="${(comment.id == commentId) and (addChild=='true')}">
                        <form class="ui form" method="post" action="/comment/write">
                            <input type="hidden" value="${article.id}" name="articleId">
                            <input type="hidden" name="groupId" value="${comment.groupId}">
                            <input type="hidden" name="depthLevel" value="${comment.depthLevel}">
                            <input type="hidden" name="groupSeq" value="${comment.groupSeq}">
                            <textarea autofocus name="content" style="height:30px"> </textarea>
                            <input type="button" value="취소" onclick="window.location.href='/comment/list'">
                            <input type="submit" value="등록">
                        </form>
                    </c:if>
                    <div class="ui divider"></div>
                    </c:forEach>

                    <c:forEach begin="1" end="${totalPage}" step="1" var="page">
                        <a href="/article/read?id=${article.id}&page=${page}&posts=${posts}"><b>${page}</b></a>
                    </c:forEach>


                    <!--댓글 쓰는 Form-->
                    <div>
                        <form class="ui form" method="post" action="/comment/write">
                            <input type="hidden" value="${article.id}" name="articleId">
                            <textarea name="content" style="height:30px"></textarea>
                            <button type="submit" style="margin-top: 10px" class="right floated ui secondary button">
                                <i class="icon edit"></i>
                                댓글 등록
                            </button>
                        </form>
                    </div>

                </div>
                </div>
            </div>
    </div> <!--댓글 부분 /div-->

</div>

</body>
</html>