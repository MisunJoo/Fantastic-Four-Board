<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="./head/head.jsp"/>
    <title>회원가입 페이지</title>
</head>
<body class="ui grid">
<div class="ui container middle aligned center aligned grid" style="padding:50px 0px"> <!--메인 컨텐츠용 div-->
    <div class="ten wide column">
        <h2 class="ui teal image header">
            <%--<img src="assets/images/logo.png" class="image">--%>
            <div class="content" style="color: black">
                회원가입 정보를 입력하세요.
            </div>
        </h2>
<form class="ui large form" method="post" id="signUp" name="signUp" onsubmit="return checkForm()" action="/member/signup">
    <div class="ui stacked segment">
    <div class="field">
        <%--<label style="float:left">이메일 주소</label>--%>
        <div class="ui left icon input">
            <i class="envelope icon"></i><input type="email" name="email" placeholder="이메일 주소" maxlength="20" value="${email}">
        </div>
    </div>
        <div id="checkPwd"></div>
    <div class="two fields">
        <div class="field">
            <%--<label style="float:left">비밀번호</label>--%>
            <div class="ui left icon input">
            <i class="lock icon"></i><input type="password" placeholder="비밀번호" name="password">
            </div>
        </div>
        <div class="field">
            <%--<label style="float:left">비밀번호 확인</label>--%>
            <div class="ui left icon input">
                <i class="lock icon"></i> <input type="password" placeholder="비밀번호 확인" name="passwordCheck">
            </div>
        </div>
    </div>
    <div class="field">
        <%--<label style="float:left">닉네임</label>--%>
        <div class="ui left icon input">
            <i class="user icon"></i><input type="text" placeholder="닉네임" name="nickName" maxlength="20" value="${nickName}">
        </div>
    </div>
        <div class="two fields">
            <div class="field">
                <button class="ui fluid large secondary submit button" type="submit">회원가입</button>
            </div>
            <div class="field">
                <a class="ui fluid large button" style="float:right" href="/">취소</a>
            </div>
        </div>


    </div>
    <div>
    <%--<input class="ui secondary button" style="float:right" type="submit" value="회원 가입">--%>

    </div>

</form>
<script language="javascript">
    window.onload=function(){
        if(${duplication=='nickName'}) {
            alert("닉네임 중복입니다.")
        }else if(${duplication=='email'}){
            alert("이메일 중복입니다.")
        }
    };

    function checkForm(){
        var email = document.signUp.email;

        //이메일 입력 유무 체크
        if(email.value == '') {
            window.alert("이메일을 입력하세오");
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
            document.getElementById('checkPwd').innerHTML = "<i class=\"ban icon\"></i>비밀번호가 일치하지 않습니다.";
            return false;
        } else {
            document.getElementById('checkPwd').style.color = "black";
            document.getElementById('checkPwd').innerHTML = "암호가 확인 되었습니다."
        }
    }
</script>
    </div>
</div>
</body>
</html>


