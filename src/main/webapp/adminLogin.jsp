<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>管理员登录</title>
    <link rel="stylesheet" href="static/my/css/adminLogin.css"  media="all">
    <link rel="stylesheet" href="static/layui/css/layui.css"  media="all">
</head>
<body>
    <canvas></canvas>
    <menu>
        <!--<form onsubmit="init(event)" style="z-index: -1">-->
            <input type="hidden" id="textInput" maxLength="90"  placeholder="Random text"/>
            <!--<button style="z-index: -1" >Run</button><br/>-->
            <!--<button class="active" type="button" onClick="setSpeed(event, 40)">Fast</button>-->
            <!--<button type="button" onClick="setSpeed(event, 15)">Slow</button>-->
        <!--</form>-->
    </menu>
    <div class="layui-input-block">
        <input type="text" id="adminIndexCode" style="z-index: 10002;position: absolute;width: 20%; margin-top: 24%; margin-left: 37%;" name="title" lay-verify="title" autocomplete="off" placeholder="请输入管理员账号" class="layui-input">
        <input type="text" id="adminPassWord" style="z-index: 10002;position: absolute;width: 20%; margin-top: 27%; margin-left: 37%;" name="title" lay-verify="title" autocomplete="off" placeholder="请输入管理员密码" class="layui-input">
        <button id="loginButton" style="z-index: 10002;position: absolute;width: 15%;margin-top: 30%; margin-left: 39.6%;" class="layui-btn layui-btn-fluid">登录</button>
    </div>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="static/layui/layui.js" charset="utf-8"></script>
    <script src="static/layui/layui.all.js"></script>
    <script src="static/my/js/adminLogin.js"></script>
    <script src="static/my/js/index.js"></script>
</body>
</html>