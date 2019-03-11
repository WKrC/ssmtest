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
</head>
<body>
<!-- COIDEA:header START -->
<header class="coidea-header">

</header>
<!-- COIDEA:header END -->

<!-- COIDEA:demo START -->
<section id="ci-particles">
    <canvas id="canvas" ></canvas>
    <h2 id="headline">寄件  查询</h2>
</section>
<!-- COIDEA:demo END -->

<script src="static/assets/js/demo.js"></script>
</body>
</html>

