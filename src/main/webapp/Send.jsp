<%--
  Created by IntelliJ IDEA.
  User: 天下有雪
  Date: 2019-04-02
  Time: 22:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>寄件</title>
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
    <input type="text" id="sender" style="z-index: 3;position: absolute;width: 20%; margin-top: 24%; margin-left: 37%;" name="title" lay-verify="title" autocomplete="off" placeholder="请输入寄件人" class="layui-input">
    <input type="text" id="sender_phone" style="z-index: 3;position: absolute;width: 20%; margin-top: 27%; margin-left: 37%;" name="title" lay-verify="title" autocomplete="off" placeholder="请输入寄件人联系方式址" class="layui-input">
    <input type="text" id="sender_addr" style="z-index: 3;position: absolute;width: 20%; margin-top: 30%; margin-left: 37%;" name="title" lay-verify="title" autocomplete="off" placeholder="请输入寄件人地址" class="layui-input">
    <input type="text" id="consignee" style="z-index: 3;position: absolute;width: 20%; margin-top: 33%; margin-left: 37%;" name="title" lay-verify="title" autocomplete="off" placeholder="请输入收件人" class="layui-input">
    <input type="text" id="consignee_phone" style="z-index: 3;position: absolute;width: 20%; margin-top: 36%; margin-left: 37%;" name="title" lay-verify="title" autocomplete="off" placeholder="请输入收件人联系方式" class="layui-input">
    <input type="text" id="consignee_addr" style="z-index: 3;position: absolute;width: 20%; margin-top: 39%; margin-left: 37%;" name="title" lay-verify="title" autocomplete="off" placeholder="请收件人地址" class="layui-input">
    <input type="text" id="remaker" style="z-index: 3;position: absolute;width: 20%; margin-top: 42%; margin-left: 37%;" name="title" lay-verify="title" autocomplete="off" placeholder="请输入备注" class="layui-input">
    <button id="submitbutton" style="z-index: 3;position: absolute;width: 20%;margin-top: 45%; margin-left: 37%;" class="layui-btn layui-btn-fluid">提交</button>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="static/my/js/ReaderSendCommom.js" charset="UTF-8"></script>
<script src="static/layui/layui.js" charset="UTF-8"></script>
<script src="static/layui/layui.all.js" charset="UTF-8"></script>
<script src="static/my/js/Common.js" charset="UTF-8"></script>
<script src="static/my/js/Send.js" charset="UTF-8"></script>
</body>
</html>
