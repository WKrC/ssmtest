<%--
  Created by IntelliJ IDEA.
  User: 天下有雪
  Date: 2019-04-02
  Time: 22:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>读写器设置</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="static/my/css/ReaderSendCommom.css">
    <link rel="stylesheet" href="static/layui/css/layui.css"  media="all">
</head>
<body>
<div id="lampadario">
    <input hidden id="lightButton" type="radio" name="switch" value="on" />
    <input hidden type="radio" name="switch" value="off" checked="checked" />
    <label for="switch"></label>
    <div id="filo"></div>
    <div id="lampadina">
        <div id="sorpresa">
            <div id="footer">

            </div>
            <div id="shadow">

            </div>
        </div>
    </div>
</div>
<div id="DisplayBlock" class="layui-input-block" style="top: -5%;display: none;">
    <input type="text" id="ReaderName" style="z-index: 3;position: absolute;width: 20%; margin-top: 24%; margin-left: 37%;" name="title" lay-verify="title" autocomplete="off" placeholder="请输入读写器名" class="layui-input">
    <input type="text" id="ReaderAddress" style="z-index: 3;position: absolute;width: 20%; margin-top: 27%; margin-left: 37%;" name="title" lay-verify="title" autocomplete="off" placeholder="请输入读写器地址" class="layui-input">
    <input type="text" id="ReaderGPS" style="z-index: 3;position: absolute;width: 12%; margin-top: 30%; margin-left: 37%;" readonly name="title" lay-verify="title" autocomplete="off" placeholder="请获取经纬度" class="layui-input">
    <button id="getGPSButton" style="z-index: 3;position: absolute;width: 7.5%;margin-top: 30%; margin-left: 49.5%;" class="layui-btn layui-btn-fluid">获取经纬度</button>
    <button id="saveReaderbutton" style="z-index: 3;position: absolute;width: 20%;margin-top: 33%; margin-left: 37%;" class="layui-btn layui-btn-fluid">保存设置</button>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.min.js" charset="UTF-8"></script>
<script src="static/layui/layui.js" charset="UTF-8"></script>
<script src="static/layui/layui.all.js" charset="UTF-8"></script>
<script type="text/javascript" src="https://webapi.amap.com/maps?v=1.4.13&key=fd3585a2b13c4d64289b052510448825&plugin=AMap.Geocoder"></script>
<script src="static/my/js/ReaderSendCommom.js" charset="UTF-8"></script>
<script src="static/my/js/ReaderSet.js" charset="UTF-8"></script>
<script src="static/my/js/Common.js" charset="UTF-8"></script>
</body>
</html>
