<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>物流查询</title>
    <link rel="stylesheet" href="static/layui/css/layui.css"  media="all">
    <link rel="stylesheet" href="static/my/css/QueryGoods.css" media="all">
</head>
<body style="width: 100%; height: 100%; margin: 0; padding: 0" onmouseup="stopMove();" onresize="windowResize();">

    <div id="starsContainer" onmousedown="startMove();" onmouseup="stopMove();">
        <div id="stars" onmousedown="startMove();" onmouseup="stopMove();"></div>
    </div>

    <div id="sun" onmousedown="startMove();" onmouseup="stopMove();"></div>

    <div id="sunDay" onmousedown="startMove();" onmouseup="stopMove();"></div>

    <div id="sunSet" onmousedown="startMove();" onmouseup="stopMove();"></div>

    <div id="sky" onmousedown="startMove();" onmouseup="stopMove();"></div>

    <div class="star" style="left: 250px; top: 30px;"></div>
    <div class="star" style="left: 300px; top: 25px;"></div>
    <div class="star" style="right: 40px; top: 40px;"></div>
    <div class="star" style="right: 80px; top: 45px;"></div>
    <div class="star" style="right: 120px; top: 20px;"></div>

    <div id="horizon" onmousedown="startMove();" onmouseup="stopMove();"></div>

    <div id="horizonNight" onmousedown="startMove();" onmouseup="stopMove();"></div>

    <div id="moon" onmousedown="startMove();" onmouseup="stopMove();"></div>

    <div id="mountainRange">
        <div id="mountain" onmousedown="startMove();" onmouseup="stopMove();"></div>
    </div>

    <div id="division" onmousedown="startDraggingDivision();" onmouseup="stopMove();"></div>

    <div id="water" onmousedown="startMove();" onmouseup="stopMove();"></div>

    <div id="waterReflectionContainer" onmousedown="startMove();" onmouseup="stopMove();">
        <div id="waterReflectionMiddle" onmousedown="startMove();" onmouseup="stopMove();"></div>
    </div>
    <div id="waterDistance" onmousedown="startMove();" onmouseup="stopMove();"></div>
    <div id="darknessOverlaySky" onmousedown="startMove();" onmouseup="stopMove();"></div>
    <div id="darknessOverlay"></div>
    <div id="oceanRippleContainer"></div>
    <div id="oceanRipple"></div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <input type="text" id="goodsIndexCode" style="z-index: 10002;position: absolute;width: 20%; margin-top: 28%; margin-left: 33%;" name="title" lay-verify="title" autocomplete="off" placeholder="请输入物流单号" class="layui-input">
            <button id="querybutton" style="z-index: 10002;position: absolute;width: 5%;margin-top: 28%; margin-left: 54%;" class="layui-btn layui-btn-fluid">物流查询</button>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="static/layui/layui.js" charset="utf-8"></script>
    <script src="static/layui/layui.all.js"></script>
    <script src="static/my/js/QueryGoods.js"></script>
    <script src="static/my/js/Common.js"></script>
</body>
</html>