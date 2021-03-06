<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="three wide column ui container sidebar inverted vertical left menu overlay visible"> <!--네이게이션용 사이드바 div-->
    <h1 style="text-align: center;margin-top: 15px;"><a href="/" style="color:ghostwhite">Fantastic-Four</a></h1>
    <div style="margin-top: 30px;">
        <div class="ui inverted segment" style="text-align: center">
            <c:if test="${sessionScope.member == null}">
            <button class="ui inverted button" onclick="window.location.href='/login'"><i class="sign-in icon"></i>로그인</button>
            <button class="ui inverted button" onclick="window.location.href='/member/signup'"><i class="user icon"></i>회원가입</button>
            </c:if>
            <c:if test="${sessionScope.member != null}">
            <button class="ui inverted button" onclick="window.location.href='/logout'"><i class="sign-out icon"></i>${sessionScope.member.nickName} 로그아웃</button>
                <c:if test="${sessionScope.member.id ==1}">
                    <button class="ui inverted button" onclick="window.location.href='/admin/list'"><i class="sign-out icon"></i>관리자페이지</button>
                </c:if>
            </c:if>
        </div>
    </div>
    <div style="margin-top: 50px">
        <c:forEach items="${categoryList}" var="category">
            <a class="item" style="font-size: 20px" href="/article/list?categoryid=${category.id}">${category.name}<i class="comments outline icon"></i></a>
        </c:forEach>
    </div>
</div>
