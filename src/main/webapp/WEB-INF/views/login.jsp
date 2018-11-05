<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="./head/head.jsp"/>
    <title>Login 페이지</title>

    <script language="javascript">
        window.onload=function(){
            if(${loginCheck=='invalid'}) {
                alert("이메일 혹은 비밀번호를 확인해주세요. ");
            }
        };

        function checkForm() {
            var email = document.login.email;

            if(email.value=='') {
                window.alert("이메일을 입력하세요");
                email.focus();
                return false;
            }

            if(document.login.password.value==''){
                window.alert("비밀번호를 입력하세요");
                document.login.password.focus();
                return false;
            }
        }

    </script>

</head>
<body class="ui grid">

<div class="ui container middle aligned center aligned grid" style="padding:50px 0px"> <!--메인 컨텐츠용 div-->
    <div class="seven wide column">
        <h2 class="ui teal image header">
            <%--<img src="assets/images/logo.png" class="image">--%>
            <div class="content" style="color: black">
                로그인 정보를 입력하세요.
            </div>
        </h2>
        <form class="ui large form" method="post" id="login" name="login" onsubmit="return checkForm()" action="/login">
            <div class="ui stacked segment">
                <div class="field">
                    <div class="ui left icon input">
                        <i class="envelope icon"></i>
                        <input type="email" name="email" placeholder="이메일 주소" value="${email}">
                    </div>
                </div>
                <div class="field">
                    <div class="ui left icon input">
                        <i class="lock icon"></i>
                        <input type="password" name="password" placeholder="비밀번호" value="${password}">
                    </div>
                </div>
                <div class="two fields">
                    <div class="field">
                        <button class="ui fluid large secondary submit button">로그인</button>
                    </div>
                    <div class="field">
                        <a class="ui fluid large button" style="float:right" href="/">취소</a>
                    </div>
                </div>
            </div>
            <div class="ui error message"></div>
        </form>
        <div class="ui message">
            로그인 아이디가 없으신가요? <a href="#">회원가입</a>
        </div>
    </div>
</div>
</body>
</html>
