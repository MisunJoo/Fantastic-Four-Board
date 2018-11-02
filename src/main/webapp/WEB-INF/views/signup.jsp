<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" id="signUp" name="signUp" onsubmit="return checkForm()" action="/member/signup">
    e-mail : <input type="email" name="email" placeholder="e-mail" maxlength="20"value="${email}"><br>
    password : <input type="password" placeholder="password" name="password" maxlength="20"> <br>
    password check : <input type="password" placeholder="passwordCheck" name="passwordCheck" maxlength="20"><br>
    <div id="checkPwd">동일한 암호를 입력하세요</div>
    nickname : <input type="text" placeholder="nickname" name="nickName" maxlength="20" value="${nickName}"><br>
    <input type="submit" value="회원 가입">
    <input type="reset" value="취소">
</form>
<script language="javascript">
    window.onload=function(){
        if(${duplication=='nickName'}) {
            alert("닉네임 중복입니다.")
        }else if(${duplication=='email'}){
            alert("이메일 중복입니다.")
        }
        alert(${duplication});
    };

    function checkForm(){
        var email = document.signUp.email;

        //이메일 입력 유무 체크
        if(email.value == '') {
            window.alert("아이디를 입력하시오");
            email.focus();
            return false; //submit event 중지
        } else if (!(email.value.length >= 3 && email.value.length <= 100)){
            window.alert("이메일을 3~20자 이내로 입력하시오.");
            email.focus();
            return false;
        }

        //암호 입력 유무 체크

        if(document.signUp.password.value != document.signUp.passwordCheck.value) {
            document.getElementById('checkPwd').style.color = "red";
            document.getElementById('checkPwd').innerHTML = "일치하지 않습니다.";
            return false;
        } else {
            document.getElementById('checkPwd').style.color = "black";
            document.getElementById('checkPwd').innerHTML = "암호가 확인 되었습니다."
        }
    }
</script>

</body>
</html>


