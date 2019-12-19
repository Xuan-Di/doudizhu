<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/6/1
  Time: 19:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>登录界面</title>
    <base href="<%=basePath%>">
    <meta name="description" content="particles.js is a lightweight JavaScript library for creating particles.">
    <meta name="author" content="Vincent Garreau"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" media="screen" href="css/style.css">
    <link rel="stylesheet" type="text/css" href="css/reset.css"/>
    <style>
        .ziti{color: darkslategray;font-weight: bold}
    </style>
</head>
<body>
<form action="queryUser" method='post'>
    <div id="particles-js">
        <div class="login">
            <div class="login-top ziti" style="text-align: center">
                登录
            </div>
            <div class="rg">
                <a href="zhuce4.html">去注册</a>
            </div>
            <div class="login-center clearfix">
                <div class="login-center-img"><img src="img/name.png"/></div>
                <div class="login-center-input">
                    <input type="text" name="uname" value="" placeholder="请输入您的账号" onfocus="this.placeholder=''"
                           onblur="this.placeholder='请输入您的账号'"/>
                    <div class="login-center-input-text">账号</div>
                </div>
            </div>
            <div class="login-center clearfix">
                <div class="login-center-img"><img src="img/password.png"/></div>
                <div class="login-center-input">
                    <input type="password" name="pwd" value="" placeholder="请输入您的密码" onfocus="this.placeholder=''"
                           onblur="this.placeholder='请输入您的密码'"/>
                    <div class="login-center-input-text">密码</div>
                </div>
            </div>

            <div class="reg-bad"><a href="update.jsp">找回密码</a></div>
            <div class="reg-bac"><a href="kuaisudenglu2.html">快速登录</a></div>

            <div class="checbox">
                <!--<a href="#">找回密码</a></div>-->
                <div class="reg-bae">
                    <label>
                        <p><input type="checkbox" name="checkboxname" value="">
                            <small>记住密码</small>
                        </p>
                    </label>
                </div>
            </div>
           <div class="login-button">
                登录
            </div>
            <!--  <input type="submit" value="登录" class="login-button">
             <br/>-->
             <div class="div2">
                 <small>第三方登录</small>
             </div>
             <div class="login-button1">
                 <a href="#"><img alt="ee" src="img/qq.png"></a>
             </div>
             <div class="login-button2">
                 <a href="#"><img alt="ee" src="img/weixin.png"></a>
             </div>
             <div class="login-button3">
                 <a href="#"><img alt="ee" src="img/weibo.png"></a>
             </div>

         </div>
         <div class="sk-rotating-plane"></div>

     </div>
 </form>
 <div style="text-align:center;"></div>
 <!-- scripts -->
<script src="js/particles.min.js"></script>
<script src="js/app.js"></script>
<script type="text/javascript">
    function hasClass(elem, cls) {
        cls = cls || '';
        if (cls.replace(/\s/g, '').length == 0) return false; //当cls没有参数时，返回false
        return new RegExp(' ' + cls + ' ').test(' ' + elem.className + ' ');
    }

    function addClass(ele, cls) {
        if (!hasClass(ele, cls)) {
            ele.className = ele.className == '' ? cls : ele.className + ' ' + cls;
        }
    }

    function removeClass(ele, cls) {
        if (hasClass(ele, cls)) {
            var newClass = ' ' + ele.className.replace(/[\t\r\n]/g, '') + ' ';
            while (newClass.indexOf(' ' + cls + ' ') >= 0) {
                newClass = newClass.replace(' ' + cls + ' ', ' ');
            }
            ele.className = newClass.replace(/^\s+|\s+$/g, '');
        }
    }

    document.querySelector(".login-button").onclick = function () {
        addClass(document.querySelector(".login"), "active")
        setTimeout(function () {
            addClass(document.querySelector(".sk-rotating-plane"), "active")
            document.querySelector(".login").style.display = "none"
        }, 800);
        setTimeout(function () {
            removeClass(document.querySelector(".login"), "active")
            removeClass(document.querySelector(".sk-rotating-plane"), "active")
            document.querySelector(".login").style.display = "block"
            alert("登录成功")
        }, 5000);
        document.getElementsByTagName("form")[0].submit();
    }
</script>

</body>
</html>