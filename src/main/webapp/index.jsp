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
    <h2 id="headline" style="color: #212121;float: left;">寄件  查询</h2>
    <h2 id="ReaderSet" style="color: #212121; bottom: 0px; right: 0px; width: auto; margin-left: 90%;cursor: pointer;">阅读器设置入口</h2>
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
                <li class="jijianli"><span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp寄件人&nbsp:</span>&nbsp<input id="sender" autocomplete="off" type="text"></li>
                <li class="jijianli"><span>寄件人手机&nbsp:</span>&nbsp<input id="sender_phone" autocomplete="off" type="text"></li>
                <li class="jijianli"><span>寄件人地址&nbsp:</span>&nbsp<input id="sender_addr" autocomplete="off" type="text"></li>
                <li class="jijianli"><span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp收件人&nbsp:</span>&nbsp<input id="consignee" autocomplete="off" type="text"></li>
                <li class="jijianli"><span>收件人手机&nbsp:</span>&nbsp<input id="consignee_phone" autocomplete="off" type="text"></li>
                <li class="jijianli"><span>收件人地址&nbsp:</span>&nbsp<input id="consignee_addr" autocomplete="off" type="text"></li>
                <li class="jijianli"><span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp备注&nbsp:</span>&nbsp<input id="remaker" autocomplete="off" type="text"></li>
                <li class="jijianli"><span>&nbsp&nbsp&nbsp</span><input id="submitbutton" type="button" class="mybutton" value="提交"></li>
                <li class="chaxunli"><span>&nbsp&nbsp&nbsp物流单号&nbsp:</span>&nbsp<input id="goodsIndexCode"  autocomplete="off" type="text"></li>
                <li class="chaxunli"><span>&nbsp&nbsp&nbsp</span><input id="chaxunbutton" class="mybutton" type="button"  value="查询"></li>
                <li class="Readerli"><span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp阅读器名&nbsp:</span><input id="ReaderName" class="required" autocomplete="off" type="text"></li>
                <li class="Readerli"><span>&nbsp&nbsp&nbsp&nbsp阅读器地址&nbsp:</span><input id="ReaderAddress" class="required" autocomplete="off" type="text"></li>
                <li class="Readerli"><span style="margin-left: 8%;">阅读器经纬度&nbsp:</span><input id="ReaderGPS" readonly class="required" autocomplete="off" type="text"><input type="button" id="getGPSButton" class="mybutton" value="获取经纬度"></li>
                <li class="Readerli"><span>&nbsp&nbsp&nbsp</span><input id="saveReaderbutton" class="mybutton" type="button"  value="保存设置"></li>
            </ul>
        </nav>
        <div id="nav-bg"></div>
    </div>
</section>
<!-- COIDEA:demo END -->

<script src="static/assets/js/demo.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="static/layui/layui.js"></script>
<script src="static/layui/layui.all.js"></script>
<script type="text/javascript" src="https://webapi.amap.com/maps?v=1.4.13&key=fd3585a2b13c4d64289b052510448825&plugin=AMap.Geocoder"></script>
<script type="text/javascript" src="static/my/js/index.js"></script>
</body>
</html>

