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
                <li class="jijianli"><span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp寄件人&nbsp:</span>&nbsp<input id="sender" autocomplete="off" type="text"></li>
                <li class="jijianli"><span>寄件人手机&nbsp:</span>&nbsp<input id="sender_phone" autocomplete="off" type="text"></li>
                <li class="jijianli"><span>寄件人地址&nbsp:</span>&nbsp<input id="sender_addr" autocomplete="off" type="text"></li>
                <li class="jijianli"><span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp收件人&nbsp:</span>&nbsp<input id="consignee" autocomplete="off" type="text"></li>
                <li class="jijianli"><span>收件人手机&nbsp:</span>&nbsp<input id="consignee_phone" autocomplete="off" type="text"></li>
                <li class="jijianli"><span>收件人地址&nbsp:</span>&nbsp<input id="consignee_addr" autocomplete="off" type="text"></li>
                <li class="jijianli"><span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp备注&nbsp:</span>&nbsp<input id="remaker" autocomplete="off" type="text"></li>
                <li class="jijianli"><span>&nbsp&nbsp&nbsp</span><input id="submitbutton" type="button"  value="提交"></li>
                <li class="chaxunli"><span>&nbsp&nbsp&nbsp物流单号&nbsp:</span>&nbsp<input id="goodsIndexCode" autocomplete="off" type="text"></li>
                <li class="chaxunli"><span>&nbsp&nbsp&nbsp</span><input id="chaxunbutton" type="button"  value="查询"></li>
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
<script type="text/javascript">
    var closeFlag = 0;
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
                clearValue();
            }
        });

        $("#submitbutton").click(function () {
            if (!checkjijianValue()) {
                return false;
            }
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
                        layer.open({
                            type: 1
                            ,title: false //不显示标题栏
                            ,closeBtn: false
                            ,offset: '350px'
                            ,area: '300px;'
                            ,shade: 0.8
                            ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
                            ,btn: ['我知道了']
                            ,btnAlign: 'c'
                            ,moveType: 1 //拖拽模式，0或者1
                            ,content: '<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">您的物流单号为:' + result.indexCode + '<br>请妥善保管！<br>以方便您查询物流信息！</div>'
                            ,success: function(layero){
                                var btn = layero.find('.layui-layer-btn');
                                // btn.find('.layui-layer-btn0').attr({
                                //     href: 'http://www.baidu.com/'
                                //     ,target: '_blank'
                                // });
                            }
                        });
                        $('body').toggleClass('open');
                        $(".jijianli").css("display", "block");
                        $(".chaxunli").css("display", "block");
                        closeFlag = 0;
                        clearValue();
                    }
                }
            })
        })

        $("#chaxunbutton").click(function () {
            if (!checkchaxunValue()) {
                return false;
            }
            $.ajax({
                url : "chaxunControl",
                type: "POST",
                data : {
                    'goodsIndexCode' : $("#goodsIndexCode").val()
                },
                success : function(result) {
                    if(result != undefined && result[0] != undefined){
                        layer.msg('即将跳转物流信息页面！', {
                            time: 3000 //3s后自动关闭
                        });
                        clearValue();
                        window.location.replace("map.jsp");
                    }else {
                        layer.msg('无此物流单号信息！', {
                            offset: '400px',
                            anim: 2
                        });
                    }
                }
            })
        })
    })
    
    function clearValue() {
        $("#sender").val("");
        $("#sender_phone").val("");
        $("#sender_addr").val("");
        $("#consignee").val("");
        $("#consignee_addr").val("");
        $("#consignee_phone").val("");
        $("#remaker").val("");
        $("#goodsIndexCode").val("");
    }
    function checkjijianValue() {
        var re = /^1\d{10}$/

        if ($("#sender").val() == "") {
            layer.msg('寄件人不可为空！', {
                offset: '300px',
                anim: 2
            });
            return false;
        }
        if ($("#sender_phone").val() == "") {
            layer.msg('寄件人手机不可为空！', {
                offset: '300px',
                anim: 2
            });
            return false;
        }else if (!re.test($("#sender_phone").val())){
            layer.msg('寄件人手机格式不正确！', {
                offset: '300px',
                anim: 2
            });
            return false;
        }
        if ($("#sender_addr").val() == "") {
            layer.msg('寄件人地址不可为空！', {
                offset: '300px',
                anim: 2
            });
            return false;
        }
        if ($("#consignee").val() == "") {
            layer.msg('收件人不可为空！', {
                offset: '300px',
                anim: 2
            });
            return false;
        }
        if ($("#consignee_phone").val() == "") {
            layer.msg('收件人手机不可为空！', {
                offset: '300px',
                anim: 2
            });
            return false;
        }else if (!re.test($("#consignee_phone").val())){
            layer.msg('收件人手机格式不正确！', {
                offset: '300px',
                anim: 2
            });
            return false;
        }
        if ($("#consignee_addr").val() == "") {
            layer.msg('收件人地址不可为空！', {
                offset: '300px',
                anim: 2
            });
            return false;
        }
        return true;
    }
    function checkchaxunValue() {
        if ($("#goodsIndexCode").val() == "") {
            layer.msg('物流单号不可为空！', {
                offset: '400px',
                anim: 2
            });
            return false;
        }
        return true;
    }
</script>
</body>
</html>

