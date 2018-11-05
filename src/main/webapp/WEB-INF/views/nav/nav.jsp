<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="three wide column ui container sidebar inverted vertical left menu overlay visible"> <!--네이게이션용 사이드바 div-->
    <div style="margin-top: 200px">
    <c:forEach items="${categoryList}" var="category">
        <a class="item" href="/article/list?categoryid=${category.id}">${category.name}</a>
    </c:forEach>
    </div>
</div>
