<%@ page import="java.util.Date" %>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

    <script type="application/javascript">

        function fresh() {
//            alert("fresh");
            window.location.href = './index.jsp'
        }

        function ajax() {
            var xhr = new XMLHttpRequest();
            var username = document.getElementById("u").value;
            var password = document.getElementById("p").value;
//            var token = document.getElementById("token").value;
//            alert(username);
//            alert(password);
            xhr.onreadystatechange=function()
            {
                if (xhr.readyState==4 && xhr.status==200)
                {
                    var result = xhr.responseText;
                    if(result==1)
                            fresh();
                    else if(result==0)
                            alert("错误的用户名或密码!");
                    else if(result==2)
                            alert("请输入用户名或密码!");
                    else
                            alert("服务器繁忙！")
                }
            }

//            alert(password);
            xhr.open("POST","User/login.htm",true);
            xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
            xhr.send("username="+username+"&password="+password+"");
        };

        window.onload = function () {
            document.getElementById('LoginButton').addEventListener('click',ajax,false);
        }
        
        function logout() {
            if (confirm("你确定要注销吗？")){
                window.location.href="User/logout.htm";
            }
        }
    </script>

    <title>西邮云管家</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script type="text/javascript" src="${pageContext.request.contextPath}/user/js/jquery-1.9.0.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/user/js/login.js"></script>
    <link href="${pageContext.request.contextPath}/user/css/login2.css" rel="stylesheet" type="text/css" />
</head>
<body>

<% String tokenValue = new Date().getTime()+""; %>

<c:if test="${sessionScope.userLoginBean==null}">
    <h1>西邮云管家<sup>V1.0</sup></h1>

    <div class="login" style="margin-top:50px;">

        <div class="header">
            <div class="switch" id="switch"><a class="switch_btn_focus" id="switch_qlogin" href="javascript:void(0);" tabindex="7">快速登录</a>
                <a class="switch_btn" id="switch_login" href="javascript:void(0);" tabindex="8">快速注册</a><div class="switch_bottom" id="switch_bottom" style="position: absolute; width: 64px; left: 0;"></div>
            </div>
        </div>


        <div class="web_qr_login" id="web_qr_login" style="display: block; height: 235px;">

            <!--登录-->
            <div class="web_login" id="web_login">


                <div class="login-box">


                    <div class="login_form">
                        <form action="User/login.htm" name="loginform" accept-charset="utf-8" id="login_form" class="loginForm" method="post"><input type="hidden" name="did" value="0"/>
                            <input type="hidden" name="to" value="log"/>

                            <div class="uinArea" id="uinArea">
                                <label class="input-tips" for="u">帐号：</label>
                                <div class="inputOuter" id="uArea">

                                    <input type="text" id="u" name="username" class="inputstyle"/>
                                </div>
                            </div>

                            <div class="pwdArea" id="pwdArea">
                                <label class="input-tips" for="p">密码：</label>
                                <div class="inputOuter" id="pArea">

                                    <input type="password" id="p" name="password" class="inputstyle"/>
                                </div>
                            </div>

                            <div style="padding-left:50px;margin-top:20px;"><input id="LoginButton" type="button" value="登 录" style="width:150px;" class="button_blue"/></div>
                        </form>
                    </div>

                </div>

            </div>
            <!--登录end-->
        </div>

        <!--注册-->
        <div class="qlogin" id="qlogin" style="display: none; ">

            <div class="web_login">
                <form name="form2" id="regUser" accept-charset="utf-8"  action="User/add.htm" method="post">
                    <input type="hidden" name="to" value="reg"/>
                    <input type="hidden" name="did" value="0"/>
                    <ul class="reg_form" id="reg-ul">
                        <div id="userCue" class="cue">快速注册请注意格式</div>
                        <li>

                            <label for="user"  class="input-tips2">用户名：</label>
                            <div class="inputOuter2">
                                <input type="text" id="user" name="user_l" maxlength="16" class="inputstyle2"/>
                            </div>

                        </li>

                        <li>
                            <label for="passwd" class="input-tips2">密码：</label>
                            <div class="inputOuter2">
                                <input type="password" id="passwd"  name="passwd_l" maxlength="16" class="inputstyle2"/>
                            </div>

                        </li>
                        <li>
                            <label for="passwd2" class="input-tips2">确认密码：</label>
                            <div class="inputOuter2">
                                <input type="password" id="passwd2" name="" maxlength="16" class="inputstyle2" />
                            </div>

                        </li>

                        <li>
                            <label for="email" class="input-tips2">邮箱：</label>
                            <div class="inputOuter2">

                                <input type="text" id="email" name="email" maxlength="10" class="inputstyle2"/>
                            </div>

                        </li>

                        <li>
                            <div class="inputArea">
                                <input type="button" id="reg"  style="margin-top:10px;margin-left:85px;" class="button_blue" value="同意协议并注册"/> <a href="" class="zcxy" target="_blank">注册协议</a>
                            </div>

                        </li><div class="cl"></div>
                    </ul></form>


            </div>


        </div>
        <!--注册end-->
    </div>
    <div class="jianyi">*推荐使用ie8或以上版本ie浏览器或Chrome内核浏览器访问本站</div>
    </c:if>
    <c:if test="${sessionScope.userLoginBean!=null}">
        welcome：${sessionScope.user.nick==""?sessionScope.user.username:sessionScope.user.nick}&nbsp;&nbsp;
        <%--<a href=logout>logout</a>&nbsp;&nbsp;--%>
        <button id="logoutButton" onclick="logout()">logout</button>&nbsp;&nbsp;
        <a href="html5.htm">upload</a>&nbsp;&nbsp;
        <a href="file/showAll">show</a>

    </c:if>
</body>
</html>

