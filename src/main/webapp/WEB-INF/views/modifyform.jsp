
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
        <b><span size="5" color="gray">��ۼ���</span></b>
        <hr size="1" width="550">
        <br>

        <div id="commentModifyForm">
            <form method="post" name="modifyInfo" target="parentForm" onsubmit="checkForm()">
                <input type="hidden" name="id" value="${comment.id}">
                <textarea rows="7" cols="70" name="content"> ${comment.content} </textarea>
                <br><br>
                <input type="submit" value="���" >
                <input type="button" value="���" onclick="window.close()">
            </form>
        </div>
    </div>

<script type="text/javascript">
    function checkForm(){
        var content = document.content.value
        if(!content){
            alert("������ �Է��ϼ���.");
        }else{
            var param = "content="+content+"&id="+document.id.value;

            httpRequest = getXMLHttpRequest();
            httpRequest.onreadystatechange=checkFunc;
            httpRequest.open("POST","/comment/modify",true);
            httpRequest.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=EUC-KR')
            httpRequest.send(param);
        }
    }
    function checkFunc() {
            // ������� �����´�
            var resultText = httpRequest.responseText;
            if(resultText==1){
                //�θ�â ���ΰ�ħ
                window.opener.document.location.reload();
                opener.updateForm=null;
                self.close();
            }


    }
</script>
</body>
</html>
