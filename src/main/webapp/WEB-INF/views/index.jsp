<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="./head/head.jsp"/>
    <title>Fantastic-Four</title>
</head>
<body class="ui grid">

<jsp:include page="./nav/nav.jsp"/>
<div class="ui container twelve wide column" style="padding:50px 0px"> <!--메인 컨텐츠용 div-->
    <h1>HELLO WORLD</h1>
    <h2>가입해라 지금. 그리고 작성하라 글을</h2>
    <div class="ui segment">
        <div class="ui two column very relaxed grid">
            <div class="column">
                <h3>인기글</h3>
                <table class="ui selectable black striped celled padded table">
                    <thead>
                    <tr>
                        <th class="eight wide center aligned">제목</th>
                        <th class="three wide center aligned">글쓴이</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${articleListHit}" var="article">
                        <tr>
                            <c:choose>
                                <c:when test="${article.isDeleted == true}">
                                    <td>삭제된 글입니다.</td>
                                    <td class="center aligned"> - </td>
                                </c:when>
                                <c:otherwise>
                                    <td><a href="/article/read?id=${article.id}">
                                        <c:forEach begin="1" end="${article.depthLevel}" step="1">  <b>RE:</b></c:forEach>
                                            ${article.title}</a></td>
                                    <td class="center aligned">${article.nickName}</td>
                                </c:otherwise>
                            </c:choose>
                        </tr>
                    </c:forEach>

                    </tbody>
                </table>
            </div>
            <div class="column">
                <h3>최신글</h3>
                <table class="ui selectable black striped celled padded table">
                    <thead>
                    <tr>
                        <th class="eight wide center aligned">제목</th>
                        <th class="three wide center aligned">글쓴이</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${articleListNew}" var="article">
                        <tr>
                            <c:choose>
                                <c:when test="${article.isDeleted == true}">
                                    <td>삭제된 글입니다.</td>
                                    <td class="center aligned"> - </td>
                                </c:when>
                                <c:otherwise>
                                    <td><a href="/article/read?id=${article.id}">
                                        <c:forEach begin="1" end="${article.depthLevel}" step="1">  <b>RE:</b></c:forEach>
                                            ${article.title}</a></td>
                                    <td class="center aligned">${article.nickName}</td>
                                </c:otherwise>
                            </c:choose>
                        </tr>
                    </c:forEach>

                    </tbody>
                </table>
            </div>
        </div>
        <div class="ui vertical divider">AND</div>
    </div>

</div>
</body>
</html>
