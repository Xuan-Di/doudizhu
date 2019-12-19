<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/12/12
  Time: 14:40
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
    <script type="text/javascript" src="${APP_PATH2}/static/js/jquery-1.12.4.min.js"></script>
    gaming...

    <style>
        * {
            margin: 0;
            padding: 0;

        }

        #container {
            width: 1000px;
            height: 700px;
            top: 0%;
            left: 50%;
            transform: translate(-50%, 0%);
            background-color: cadetblue;
            position: relative;

        }

        #container #p1 {
            width: 500px;
            height: 100px;
            display: flex;
            position: absolute;
            top: 75%;
            left: 50%;
            transform: translate(-50%, 0);
        }

        #container #p2 {
            width: 100px;
            height: 500px;
            display: flex;
            position: absolute;
            top: 10%;
            left: 10%;
            transform: translate(-50%, 0);
        }

        #container #p3 {
            width: 100px;
            height: 500px;
            display: flex;
            position: absolute;
            top: 10%;
            left: 90%;
            transform: translate(-50%, 0);


        }

        #container #p4 {
            width: 100px;
            height: 500px;
            display: flex;
            position: absolute;
            top: 10%;
            left: 50%;
            transform: translate(-50%, 0);


        }

        #container #show {
            width: 300px;
            height: 100px;
            display: flex;
            position: absolute;
            top: 35%;
            left: 50%;
            transform: translate(-50%, 0);


        }

        #container #qiang {
            width: 80px;
            height: 50px;
            line-height: 50px;
            border-radius: 10px;
            background-color: #f48d86;
            position: absolute;
            top: 60%;
            left: 50%;
            transform: translate(-50%, 0);
        }

        #container #sendpoke {
            width: 80px;
            height: 50px;
            line-height: 50px;
            border-radius: 10px;
            background-color: #f48d86;
            position: absolute;
            top: 60%;
            left: 60%;
            transform: translate(-50%, 0);
        }

        #container #dontsend {
            width: 80px;
            height: 50px;
            line-height: 50px;
            border-radius: 10px;
            background-color: #f48d86;
            position: absolute;
            top: 60%;
            left: 70%;
            transform: translate(-50%, 0);
        }

        .clear {
            display: none;
        }

        #edit area {
            position: absolute;
            width: 400px;
            height: 200px;
            border: 1px solid #000;
            background: #2aabd2;
            left: 0;

        }

        #edit {
            width: 800px;
            height: 200px;
            position: relative;
            top: 2%;
            left: 50%;
            transform: translate(-50%, 0);


        }

        #edit #text {
            position: absolute;
            left: 55%;
        }

        #send {
            position: absolute;
            right: 20%;

        }


    </style>
</head>
<body onload="timer">

<button onclick="xianshi()">发牌</button>


<div id="container">


    <div id="p1">

    </div>
    <div id="p2">

    </div>
    <div id="p3">

    </div>
    <div id="p4">

    </div>
    <div id="show"></div>
    <button onclick="pushTo()" id="qiang" class="">抢地主</button>
    <button onclick="sendTo()" id="sendpoke" class="">出牌</button>
    <button onclick="dontSend()" id="dontsend" class="">要不起</button>
</div>
<div id="edit">
    <area type="text" id="view"/>
    <input type="text" id="text"/>
    <button onclick="sendMsg()" id="send">发送</button>
</div>

<script type="text/javascript">

    var imgArr = [
        "img/Group0.png", "img/Group1.png", "img/Group2.png", "img/Group3.png", "img/Group4.png", "img/Group5.png",
        "img/Group6.png", "img/Group7.png", "img/Group8.png", "img/Group9.png", "img/Group10.png", "img/Group11.png",
        "img/Group12.png", "img/Group13.png", "img/Group14.png", "img/Group15.png", "img/Group16.png", "img/Group17.png",
        "img/Group18.png", "img/Group19.png", "img/Group20.png", "img/Group21.png", "img/Group22.png", "img/Group23.png",
        "img/Group24.png", "img/Group25.png", "img/Group26.png", "img/Group27.png", "img/Group28.png", "img/Group29.png",
        "img/Group30.png", "img/Group31.png", "img/Group32.png", "img/Group33.png", "img/Group34.png", "img/Group35.png",
        "img/Group36.png", "img/Group37.png", "img/Group38.png", "img/Group39.png", "img/Group40.png", "img/Group41.png",
        "img/Group42.png", "img/Group43.png", "img/Group44.png", "img/Group45.png", "img/Group46.png", "img/Group47.png",
        "img/Group48.png", "img/Group49.png", "img/Group50.png", "img/Group51.png", "img/Group52.png", "img/Group53.png"];
    <%   List   list=new   ArrayList();
    list=(List)session.getAttribute("pokerList");
    %>
    <%   List   dipailist=new   ArrayList();
    dipailist=(List)session.getAttribute("dipaiList");
    %>

    var obj1 = <%=list%>;
    var obj2 = [1, 2, 3, 5, 6, 4];
    var obj3 = [1, 2, 3, 5, 6, 4];
    var dipai = <%=dipailist%>;
    var upArray = [];

    //抢地主
    function pushTo() {
        if (dipai == null) {
            alert("地主已被别人抢了!");
            return;
        }
        for (var i = 0; i < obj1.length; i++) {
            document.getElementById("" + obj1[i]).remove();
        }
        obj1.push(...dipai);
        change();
        document.getElementById("qiang").classList.add("clear");
        var name1 = "${sessionScope.uname}";
        $.ajax({
            url: "${APP_PATH2}/qiangdizhu",
            data: "uname=" + name1,
            type: "GET",
            success: function (result) {

            }
        });
    }

    //出不起牌
    function dontSend() {
        var name1 = "${sessionScope.uname}";
        $.ajax({
            url: "${APP_PATH2}/dontSend",
            data: "uname=" + name1,
            type: "GET",
            success:
                function (result) {
                var message = result.extend.dontclick;
                alert(message);
                }
        });
    }

    //转化出牌数组为索引数组
    function translant(arr) {
        var translantarr = [];
        for (var i = 0; i < arr.length; i++) {
            translantarr[i] = Math.floor((arr[i] - 2) / 4);
        }
        return translantarr;
    }

    //出牌
    function sendTo() {
        if (upArray.length == 2) {
            var translantarr = translant(upArray);
            if (translantarr[0] != translantarr[1]) {
                alert("您的牌不符合规则.");
                return;
            }
        }
        if (upArray.length == 3) {
            alert("您的牌不符合规则");
            return;
        }
        if (upArray.length == 4) {
            var tranlantarr = translant(upArray);
            var set = new Set(tranlantarr);
            if (set.size > 2) {
                alert("您的牌不符合规则");
                return;
            }
        }
        if (upArray.length >= 5) {
            var tranlantarr = translant(upArray);
            tranlantarr.sort(function (a, b) {
                return a - b;
            });
            if ((tranlantarr[upArray.length - 1] - tranlantarr[0] + 1) != upArray.length
            &&((tranlantarr[upArray.length-1]-tranlantarr[0]+1)*2)!=upArray.length) {
                alert("您的牌不符合规则");
                return;
            }
        }
        var name1 = "${sessionScope.uname}";
        var obj = {};
        obj['uname'] = name1;
        var string = upArray.toString();
        obj['sendpokearray'] = string;
        // alert(upArray);
        $.ajax({
            url: "${APP_PATH2}/sendpoke",
            method: "post",
            contentType: 'application/json',
            data: JSON.stringify(obj),
            dataType: "json",
            success: function (result) {
                var less = result.extend.less;
                if (less == "ok") {
                    for (var s = 0; s < upArray.length; s++) {
                        var div = document.createElement("div");
                        div.id = upArray[s];
                        div.style = "width:110px;height:160px;background-image:url(" + imgArr[upArray[s]] + "); background-size:cover;margin-left:50px;left:" + f(s) + ";position:absolute;";
                        document.getElementById("show").append(div);
                    }
                    upArray = [];
                    for (var i = 0; i < obj1.length; i++) {
                        document.getElementById("" + obj1[i]).remove();
                    }
                    obj1 = result.extend.newpoke;
                    change();
                } else {
                    alert(less);
                }
            }
        });
    }

    function xianshi() {
        change();
    }

    function f(index) {
        var result = (index) * 6;
        return result + "%";
    }

    function change() {
        //这个索引是按0-n的顺序设置它们展示的偏移
        for (var [key, value] of obj1.entries()) {
            //该索引是根据数组元素的id,找到对应的图片进行显示
            var div = document.createElement('div');
            div.name = "false"
            div.id = value;
            console.log(key + "," + value);
            div.addEventListener('click', function () {
                if (this.name == "false") {
                    this.style.transform = 'translate(0,-50px)';
                    this.name = "true";
                    console.log(this.name)
                    upArray.push(this.id)
                    console.log(upArray)
                } else if (this.name == "true") {
                    this.style.transform = 'translate(0,0)';
                    this.name = "false";
                    console.log(this.name)
                    for (var i = 0; i < upArray.length; i++) {
                        if (upArray[i] == this.id) {
                            upArray.splice(i, 1);
                        }
                    }
                    console.log(upArray);
                }
            });
            div.style = "width:110px;height:160px;background-image:url(" + imgArr[value] + "); background-size:cover;margin-left:50px;left:" + f(key) + ";position:absolute;transition:transform 0.2s linear";
            document.getElementById("p1").append(div);
        }
    }

    var j = 0;
    for (var value of obj2) {
        //该索引是根据数组元素的id,找到对应的图片进行显示
        var div = document.createElement('div');
        div.id = value;
        div.style = "width:100px;height:100px;background-color:green;border:1px solid #000;background-size:cover;top:" + f(j++) + ";position:absolute;";
        document.getElementById("p2").append(div);
    }
    var k = 0;
    for (var value of obj3) {
        //该索引是根据数组元素的id,找到对应的图片进行显示
        var div = document.createElement('div');
        div.id = value;
        div.style = "width:100px;height:100px;background-color:green;border:1px solid #000;background-size:cover;top:" + f(k++) + ";position:absolute;";
        document.getElementById("p3").append(div);
    }
    var t = 0;
    for (var value of dipai) {
        //该索引是根据数组元素的id,找到对应的图片进行显示
        var div = document.createElement('div');
        div.id = value;
        div.style = "width:100px;height:100px;background-color:green;border:1px solid #000;background-size:cover;top:" + f(t++) + ";position:absolute;";
        document.getElementById("p4").append(div);
    }

    //发送消息
    function sendMsg() {
        var msg = document.getElementById("text").value;
        document.getElementById("text").value = "";
        var name1 = "${sessionScope.uname}";
        $.ajax({
            url: "${APP_PATH2}/msg",
            data: "msg=" + msg + "&uname=" + name1,
            type: "GET",
            success: function (result) {
            }
        });
    }

    //ajax轮询
    var otherarray = [];
    var timer = setInterval(function () {
        var name1 = "${sessionScope.uname}";
        $.ajax({
            url: "${APP_PATH2}/recevie",
            data: "uname=" + name1,
            type: "GET",
            success: function (result) {
                console.log(result);
                // alert(result.extend.result);
                otherarray = result.extend.othersend;
                if (otherarray[0] != -1) {
                    for (var s = 0; s < otherarray.length; s++) {
                        var div = document.createElement("div");
                        div.id = otherarray[s];
                        div.style = "width:110px;height:160px;background-image:url(" + imgArr[otherarray[s]] + "); background-size:cover;margin-left:50px;left:" + f(s) + ";position:absolute;";
                        document.getElementById("show").append(div);
                    }
                }
                var message = result.extend.result;
                if (message != "123") {
                    var span = document.createElement('span');
                    span.innerHTML = message + "<br>"
                    document.getElementById("view").append(span);
                }
                dipai = result.extend.dipaili;
            }
        });
    }, 3000);
</script>
</body>
</html>
