<%--<%@ page language="java" contentType="text/html; charset=UTF-8" %>--%>
<%--<html>--%>
<%--<head>--%>
    <%--<meta charset="UTF-8">--%>
<%--</head>--%>
<%--<body>--%>
<%--<h2>Hello World!</h2>--%>
<%--<input id="button" type="button" value="请求">--%>
<%--<form id="one" action="twoAction" method ="post">--%>
    <%--<span>名字:</span><input name="name" type="text">--%>
    <%--<span style="display: block;"></span>--%>
    <%--<span>密码:</span><input name="password" type="text">--%>
    <%--<input id="button2" type="submit" value="另一个请求">--%>
<%--</form>--%>

<%--<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.min.js"></script>--%>
<%--<script type="text/javascript">--%>
    <%--$(document).ready(function() {--%>
        <%--$("#button").click(function () {--%>
            <%--$.ajax({--%>
                <%--type: "POST",--%>
                <%--url : "oneAction",--%>
                <%--data: $("#one").serialize(),--%>
                <%--async : false,--%>
                <%--success : function (data) {--%>
                    <%--console.info(data);--%>
                    <%--data.forEach(function (node) {--%>
                        <%--alert(node);--%>
                    <%--})--%>
                <%--}--%>
            <%--})--%>
        <%--})--%>
    <%--})--%>
<%--</script>--%>
<%--</body>--%>
<%--</html>--%>
<%--
  Created by IntelliJ IDEA.
  User: wukangrong
  Date: 2019/3/8
  Time: 11:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Hello World!</title>
    <link rel="stylesheet" type="text/css" href="static/assets/css/base.css" />
    <link rel="stylesheet" type="text/css" href="static/my/css/donghua.css" />
</head>
<body>
<!-- COIDEA:header START -->
<header class="coidea-header">

</header>
<!-- COIDEA:header END -->

<!-- COIDEA:demo START -->
<section id="ci-particles">
    <canvas id="canvas" ></canvas>
    <h2 id="headline" style="color: #212121;">寄件  查询</h2>
    <div id="jijian" style="">
    </div>
    <div id="chaxun" style="">
    </div>
    <div id="top-head-inner">
        <div id="nav-toggle">
            <div>
                <span></span>
                <span></span>
                <span></span>
            </div>
        </div>
        <nav id="global-nav">
            <ul>
                <li class="jijianli"><span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp寄件人&nbsp:</span>&nbsp<input id="sender" type="text"></li>
                <li class="jijianli"><span>寄件人手机&nbsp:</span>&nbsp<input id="sender_phone" type="text"></li>
                <li class="jijianli"><span>寄件人地址&nbsp:</span>&nbsp<input id="sender_addr" type="text"></li>
                <li class="jijianli"><span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp收件人&nbsp:</span>&nbsp<input id="consignee" type="text"></li>
                <li class="jijianli"><span>收件人手机&nbsp:</span>&nbsp<input id="consignee_phone" type="text"></li>
                <li class="jijianli"><span>收件人地址&nbsp:</span>&nbsp<input id="consignee_addr" type="text"></li>
                <li class="jijianli"><span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp备注&nbsp:</span>&nbsp<input id="remaker" type="text"></li>
                <li class="jijianli"><span>&nbsp&nbsp&nbsp</span><input id="submitbutton" type="button"  value="提交"></li>
                <li class="chaxunli"><span>&nbsp&nbsp&nbsp物流单号&nbsp:</span>&nbsp<input id="goodsIndexCode" type="text"></li>
                <li class="chaxunli"><span>&nbsp&nbsp&nbsp</span><input id="chaxunbutton" type="button"  value="查询"></li>
            </ul>
        </nav>
        <div id="nav-bg"></div>
    </div>
</section>
<!-- COIDEA:demo END -->

<script src="static/assets/js/demo.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
    var closeFlag = 0;
    var jijiandatas = {
        'sender': $("#sender").val(),
        'sender_phone': $("#sender_phone").val(),
        'sender_addr': $("#sender_addr").val(),
        'consignee': $("#consignee").val(),
        'consignee_phone': $("#consignee_phone").val(),
        'consignee_addr': $("#consignee_addr").val(),
        'remaker': $("#remaker").val()
    }
    var chaxundatas = {
        'goodsIndexCode' : $("#goodsIndexCode").val()
    }
    $(function () {
        $("#jijian").click(function () {
            $(".chaxunli").css("display", "none");
            $('body').toggleClass('open');
            closeFlag = 1;
        })
        $("#chaxun").click(function () {
            $(".jijianli").css("display", "none");
            $('body').toggleClass('open');
            closeFlag = 2;
        })
        $('#nav-toggle').click(function(){
            if (closeFlag > 0){
                $('body').toggleClass('open');
                $(".jijianli").css("display", "block");
                $(".chaxunli").css("display", "block");
                closeFlag = 0;
            }
        });

        $("#submitbutton").click(function () {
            $.ajax({
                url : "jijianControl",
                type: "POST",
                data : {
                    'sender': $("#sender").val(),
                    'sender_phone': $("#sender_phone").val(),
                    'sender_addr': $("#sender_addr").val(),
                    'consignee': $("#consignee").val(),
                    'consignee_phone': $("#consignee_phone").val(),
                    'consignee_addr': $("#consignee_addr").val(),
                    'remaker': $("#remaker").val()
                },
                success : function(result) {
                    if(result&&result.indexCode != undefined){
                        alert(result.indexCode);
                    }
                }
            })
        })

        $("#chaxunbutton").click(function () {
            $.ajax({
                url : "chaxunControl",
                type: "POST",
                data : {
                    'goodsIndexCode' : $("#goodsIndexCode").val()
                },
                success : function(result) {
                    if(result != undefined){
                        console.info(result);
                    }
                }
            })
        })
    })
</script>
</body>
</html>

