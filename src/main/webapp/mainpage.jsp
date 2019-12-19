<%@ page import="org.springframework.web.context.request.SessionScope" %><%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/12/12
  Time: 11:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%
        pageContext.setAttribute("APP_PATH2", request.getContextPath());
    %>
<!-- 引入jquery  -->
    <script type="text/javascript" src="${APP_PATH2}/static/js/jquery-1.12.4.min.js"></script></head>
<style>
    #table {
            width: 500px;
            height: 500px;
            background-color: cadetblue;
            border-radius: 50%;
            margin: 100px auto;
            position: relative;
        }
        #table .deng{
            width: 60px;
            height: 60px;
            background-color: #1a252f;
        }
        #table .deng{
            width: 60px;
            height: 60px;
            background-color: #c7254e
        }
        .light{

        }
        #table .di{
            width: 60px;
            height: 30px;
            background-color: #000000;
        }
        #table #light1 {
            left: -15%;
            top: 50%;
            transform: translate(0,-50%);
            position: absolute;

        }
        #table #light2 {
            right: -15%;
            top: 50%;
            transform: translate(0, -50%);
            position: absolute;
        }
        #table #light3 {
            left: 50%;
            bottom: -15%;
            transform: translate(-50%,0);
            position: absolute;
        }
</style>
<body onload="Timer">
<div id="table">
    <div id="light1" class="deng light">
        <div id="deng1"></div>
        <div class="di"></div>
    </div>
    <div id="light2" class="deng">
        <div id="deng2"></div>
        <div class="di"></div>
    </div>
    <div id="light3" class="deng">
        <div id="deng3"></div>
        <div class="di"></div>
    </div>
</div>
<button id="name" onclick="window.location.href='${APP_PATH2}/join?uname=<%=session.getAttribute("uname")%>'">join</button>

<script type="text/javascript">
    var Timer=setInterval(function sendMsg() {
            $.ajax({
                url: "${APP_PATH2}/queryPeople",
                type: "GET",
                success: function (result) {
                    var peopleCount=result.extend.peopleCount;
                    // alert(peopleCount);
                    if(peopleCount===1){
                        document.getElementById("deng1").classList.add("light");
                    }
                    if(peopleCount===2){
                        document.getElementById("deng1").classList.add("light");
                        document.getElementById("deng2").classList.add("light");
                    }
                    if(peopleCount===3){
                        document.getElementById("deng1").classList.add("light");
                        document.getElementById("deng2").classList.add("light");
                        document.getElementById("deng3").classList.add("light");
                    }
                }
            });
        },4000);

</script>
</body>
</html>
