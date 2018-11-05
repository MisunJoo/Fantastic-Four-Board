<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
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
<body>
<form method="post" id="login" name="login" onsubmit="return checkForm()" action="/login">
    e-mail : <input type="email" name="email" placeholder="e-mail을 입력하세요" maxlength="20" value="${email}"><br>
    password :<input type="text" name="password" placeholder="password를 입력하세요" maxlength="20" value="${password}"><br>
    <input type="submit" value="로그인">
</form>

</body>
</html>
