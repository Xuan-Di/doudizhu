<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/6/1
  Time: 21:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>系统界面</title>
    <%
        pageContext.setAttribute("APP_PATH",request.getContextPath());
    %>

    <!-- 引入jquery  -->
    <script type="text/javascript" src="${APP_PATH}/static/js/jquery-1.12.4.min.js"></script>
    <!--  引入bootstrap样式  -->
    <link href="${APP_PATH}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="${APP_PATH}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</head>
<body>


<div class="row">
    <div class="col-md-4">
        <button type="button" class="btn btn-primary btn-lg btn-block btn btn-info"  onclick="next()">员工管理</button>
    </div>
    <div class="col-md-4">
        <button type="button" class="btn btn-primary btn-lg btn-block btn btn-info"  onclick="next()">培训管理</button>
    </div>
    <div class="col-md-4">
        <button type="button" class="btn btn-primary btn-lg btn-block btn btn-info"  onclick="next1()">招聘管理</button>
    </div>
</div>
<div class="row">
    <div class="col-lg-3 col-md-4 col-sm-6 col-xs-12 ">
        <img src="img/afei.png" class="img-responsive img-thumbnail">
        <h1 class="page-header" >公司简介</h1>
        <p>我给网页栅格系统下的定义为：以规则的网格阵列来指导和规范网页中的版面布局以及信息分布。
            网页栅格系统是从平面栅格系统中发展而来。对于网页设计来说，栅格系统的使用，不仅可以让网页的信息呈现更加美观易读，更具可用性。而且，对于前端开发来说，网页将更加的灵活与规范。</p>
    </div>

<div class="row">
    <div class="col-lg-3 col-md-4 col-sm-6 col-xs-12 col-md-offset-1">
        <img src="img/ban.jpg" class="img-responsive img-thumbnail">
        <h1 class="page-header" >主要产品</h1>
        <p>
            <a href="#">火影忍者</a> <br>
            <a href="#">海贼王</a> <br>
            <a href="#">进击的巨人</a> <br>
            <a href="#">刀剑神域</a> <br>

        </p>
    </div>

<div class="row">
    <div class="col-lg-3 col-md-4 col-sm-6 col-xs-12 col-md-offset-1">
        <img src="img/weishou.jpg" class="img-responsive img-thumbnail">
        <h1 class="page-header" >公司简介</h1>
        <p>我给网页栅格系统下的定义为：以规则的网格阵列来指导和规范网页中的版面布局以及信息分布。
            网页栅格系统是从平面栅格系统中发展而来。对于网页设计来说，栅格系统的使用，不仅可以让网页的信息呈现更加美观易读，更具可用性。而且，对于前端开发来说，网页将更加的灵活与规范。</p>
    </div>
</div>
</div>
</div>

    <div class="row">
        <div class="col-lg-3 col-md-4 col-sm-6 col-xs-12 ">
    <div class="progress">
        <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
            60%
        </div>
        </div>
    </div>
    </div>
    <nav class="navbar navbar-default navbar-fixed-bottom navbar-inverse">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">Brand</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="#">Link <span class="sr-only">(current)</span></a></li>
                    <li><a href="#">Link</a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="index.jsp" class="btn-info">员工管理</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="zhaoping.jsp" class="btn-info">招聘管理</a></li>


                        </ul>
                    </li>
                </ul>
                <form  name="form" method="get" class="navbar-form navbar-left">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Search" name="serch" id="serch">
                    </div>
                    <button type="submit" class="btn btn-default"  >Submit</button>
                </form>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="#">Link</a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">个人中心 <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="Text.jsp" class="btn btn-info">修改密码</a></li>
                            <li><a href="login.jsp" class="btn btn-danger">用户注销</a></li>
                        </ul>
                    </li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>
　　<script type="text/javascript">
    function next(){
        window.location = "index.jsp";
    }
    function next1(){
        window.location = "zhaoping.jsp";
    }
    //function serchy(){
       // alert("hahah");
       // window.location = "index.jsp";
   // }
        $("form").submit(function (e) {
            var $serch = $("#serch").val();
            if ($serch =="员工管理") {
                e.preventDefault();
                window.location.href = "index.jsp";
                return ;
            }else if($serch =="招聘管理"){
                e.preventDefault();
                window.location.href = "zhaoping.jsp";
                return ;
            }else{
                e.preventDefault();
                return ;
            }
        });




</script>
</body>
</html>
