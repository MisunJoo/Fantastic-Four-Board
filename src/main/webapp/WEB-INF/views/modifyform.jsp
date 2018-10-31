
<%@ page language="java" contentType="text/html; charset=EUC-KR"
         pageEncoding="EUC-KR" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <div id="wrap">
        <br>
        <b><span size="5" color="gray">댓글수정</span></b>
        <hr size="1" width="550">
        <br>

        <div id="commentModifyForm">
            <form name="modifyInfo" target="parentForm">
                <textarea rows="7" cols="70" name="content">${comment.content}</textarea>
                <br><br>
                <input type="button" value="등록" onclick="/comment/">
                <input type="button" value="취소" onclick="">
            </form>
        </div>
    </div>

</body>
</html>
