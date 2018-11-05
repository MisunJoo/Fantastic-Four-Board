<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="three wide column ui container sidebar inverted vertical left menu overlay visible"> <!--네이게이션용 사이드바 div-->
    <div style="margin-top: 50px;">
        <c:if test="${sessionScope.member == null}">
        <button class="ui facebook button" onclick="window.location.href='/login'">로그인</button>
        <button class="ui facebook button" onclick="window.location.href='/member/signup'">회원가입</button>
        </c:if>
        <c:if test="${sessionScope.member != null}">
            <button class="ui youtube button"> ${sessionScope.member.nickName} 님</button>
            <button class="ui facebook button" onclick="window.location.href='/logout'">로그아웃</button>
        </c:if>
    </div>
    <div style="margin-top: 200px">
        <c:forEach items="${categoryList}" var="category">
            <a class="item" style="font-size: 20px" href="/article/list?categoryid=${category.id}">${category.name}</a>
        </c:forEach>
    </div>
</div>
