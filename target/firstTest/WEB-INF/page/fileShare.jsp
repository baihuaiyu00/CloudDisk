
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2016/5/14
  Time: 14:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <script type="application/javascript">

    function turn() {
        self.location="Turn.htm";
//          self.location="sDownload";
    }

    function ajax() {;
//        alert("ajax running");
        var xhr = new XMLHttpRequest();
        var code = document.getElementById("inputCode").value;
        xhr.onreadystatechange=function()
        {
//            alert("running funcotion 2");
            if (xhr.readyState==4 && xhr.status==200)
            {
                var result = xhr.responseText;
//                alert(result);
                if(result==1)
                    turn();
                else if(result==0)
                    alert("提取码错误，请重试!");
                else
                    alert("服务器繁忙！")
            }
        }

        xhr.open("POST","scheck",true);
        xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
        xhr.send("code="+code);
    };

    window.onload = function () {
        document.getElementById('checkButton').addEventListener('click',ajax,false);
    }
</script>

    <h3>${sessionScope.fileShareBean.userName}分享了文件${sessionScope.fileShareBean.fileName}</h3>

    <%--<input type="hidden" id="fileCode" value="${sessionScope.fileShareBean.shareWebsite}">--%>
    <form method="post" action="">
        <label>
            <input type="text" name="code" id="inputCode">
        </label>
        <input type="button" value="确认" id="checkButton">
    </form>

</body>
</html>
