<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/6/1
  Time: 22:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>修改密码界面</title>
    <%
        pageContext.setAttribute("APP_PATH2",request.getContextPath());
    %>
    <script type="text/javascript" src="${APP_PATH2}/static/js/jquery-1.12.4.min.js"></script>
    <meta name="description" content="particles.js is a lightweight JavaScript library for creating particles.">
    <meta name="author" content="Vincent Garreau" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <link rel="stylesheet" media="screen" href="css/style.css">
    <link rel="stylesheet" type="text/css" href="css/zhuce.css"/>
    <link rel="stylesheet"href="css/bootstrap.min.css">
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <style>
        .ziti{color: darkslategray;font-weight: bold}
    </style>
</head>
<body>
<div id="particles-js">
    <div class="login">
        <div class="login-top ziti" align="center" >
            邮箱验证找回密码
        </div>

        <form  action="UpdateServlet" method="post" onsubmit="return validator()">
            <div class="form-group">
                <div class="col-sm-offset-1 col-sm-10">
                    <input type="text"class="form-control" placeholder="用户名"  name="username"  id="username">
                </div><br/><br/>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-1 col-sm-10">
                    <div class="input-group">
                        <input type="email"class="form-control" placeholder="电子邮件" name="uemail" id="uemail">
                        <!--   <span class="input-group-btn"><button class="btn btn-primary" class="getVerification">获取验证码</button> </span>-->
                        <input type="button" value="获取验证码" style="width:260px" class="btn-info" onclick="register()" />
                    </div>
                </div><br/><br/>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-1 col-sm-10">
                    <input type="text"class="form-control" placeholder="请输入验证码" name="test" id="test">

                </div><br/><br/>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-1 col-sm-10">
                    <input type="password" class="form-control" placeholder="设置密码" name="upasswd" id="upasswd">
                </div><br/><br/>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-1 col-sm-10">
                    <input type="password"class="form-control" placeholder="确认密码">
                </div><br/><br/>
            </div>

            <div class="submit">

                <button class="s_hover" data-form="submit" >完成</button>
            </div>

        </form>

    </div>
    <div class="sk-rotating-plane"></div>
</div>

<div style="text-align:center;">
</div>
<div style="text-align:center;">
</div>
<!-- scripts -->
<script src="js/particles.min.js"></script>
<script src="js/app.js"></script>
<script type="text/javascript">
    function register()
    {
        var $uemail = $("#uemail").val();
        $.ajax({
            url:"${APP_PATH2}/EmailServlet2",
            type:"GET",
           data:"uemail="+$uemail,
            success:function(result,testStatus)
            {

            },
            error:function(xhr,errorText,errorType){
                alert("系统异常");
            }
        });
    }

     function validator()
    {	console.log("ffff");
        var $test = $("#test").val();
        window.flag = "";
        $.ajax({
            url:"${APP_PATH2}/Match",
            请求方式:"post",
            data:"test="+$test,
            success:function(result,testStatus)
            {
                if(result=="true"){
                    window.flag = true;
                }else{
                    alert("验证码错误！");
                    window.flag=false;
                }
            },
            error:function(xhr,errorText,errorType){
                alert("系统异常");
            },
            async: false
        });
        return window.flag;
    }
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
</script>
</body>
</html>
