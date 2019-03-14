<%--
  Created by IntelliJ IDEA.
  User: wukangrong
  Date: 2019/3/12
  Time: 10:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
    <title>覆盖物的添加与移除</title>
    <link rel="stylesheet" href="https://a.amap.com/jsapi_demos/static/demo-center/css/demo-center.css" />
    <script src="https://cache.amap.com/lbs/static/es5.min.js"></script>
    <script src="https://webapi.amap.com/maps?v=1.4.13&key=fd3585a2b13c4d64289b052510448825&&plugin=AMap.Scale,AMap.OverView,AMap.ToolBar"></script>
    <script type="text/javascript" src="https://cache.amap.com/lbs/static/addToolbar.js"></script>
    <style>
        html,
        body,
        #container {
            width: 100%;
            height: 100%;
        }

        label {
            width: 55px;
            height: 26px;
            line-height: 26px;
            margin-bottom: 0;
        }
        button.btn {
            width: 80px;
        }
    </style>
</head>
<body>
<div id="container"></div>
<%--<div class="input-card" style="width:24rem;right: 6%; bottom: 0%">--%>
    <%--<h4>添加、删除覆盖物</h4>--%>
    <%--<div class="input-item">--%>
        <%--<label>Marker：</label>--%>
        <%--<button class="btn" id="add-marker" style="margin-right:1rem;">添加Marker</button>--%>
        <%--<button class="btn" id="remove-marker">删除Marker</button>--%>
    <%--</div>--%>
    <%--<div class="input-item">--%>
        <%--<label>Circle：</label>--%>
        <%--<button class="btn" id="add-circle" style="margin-right:1rem;">添加Circle</button>--%>
        <%--<button class="btn" id="remove-circle">删除Circle</button>--%>
    <%--</div>--%>
<%--</div>--%>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="static/layui/layui.js"></script>
<script src="static/layui/layui.all.js"></script>
<script>
    $(function () {
        var map = new AMap.Map('container', {
                resizeEnable: true,
                zoom:11,
                center: [116.397428, 39.90923]
            }),
            scale = new AMap.Scale({
                visible: true
            }),
            toolBar = new AMap.ToolBar({
                visible: true
            }),
            overView = new AMap.OverView({
                visible: true
            });
        var lineArr = [
            [116.478935,39.997761],
            [116.478939,39.997825],
            [116.478912,39.998549],
            [116.478912,39.998549],
            [116.478998,39.998555],
            [116.478998,39.998555],
            [116.479282,39.99856],
            [116.479658,39.998528],
            [116.480151,39.998453],
            [116.480784,39.998302],
            [116.480784,39.998302],
            [116.481149,39.998184],
            [116.481573,39.997997],
            [116.481863,39.997846],
            [116.482072,39.997718],
            [116.482362,39.997718],
            [116.483633,39.998935],
            [116.48367,39.998968],
            [116.484648,39.999861]
        ];
        var lineArr2 = new Array() ;
        $.ajax({
            type: "POST",
            url : "fetchLogisticsInfoController",
            async: false,
            success : function (result) {
                var GPSList = result.GPSList;
                var PositionList = result.PositionList;
                console.info(GPSList);
                GPSList.forEach(function (node) {
                    node = node.replace("\"","");
                    var arr = node.split(",");
                    var arr_num = [];
                    arr.forEach(function (n) {
                       var m = Number(n);
                       arr_num.push(m);
                    })
                    lineArr2.push(arr_num);
                });
                var position_str = "";
                PositionList.forEach(function (node) {
                    position_str += "已到达" + node + "<br><br>";
                })
                layer.open({
                    type: 1
                    ,title: "物流信息"
                    ,offset: ['50px', '1800px']
                    ,content: '<div style="padding: 40px 40px; font-size: 15px;">'+ position_str +'</div>'
                    ,shade: 0 //不显示遮罩
                });
            }
        })
        var marker = new AMap.Marker({
            map: map,
            position: [116.478935,39.997761],
            icon: "https://webapi.amap.com/images/car.png",
            offset: new AMap.Pixel(-26, -13),
            autoRotation: true,
            angle:-90,
        });
        map.addControl(scale);
        map.addControl(toolBar);
        map.addControl(overView);
        scale.show();
        toolBar.show();
        overView.show();
        overView.open();
        // 绘制轨迹
        var polyline = new AMap.Polyline({
            map: map,
            path: lineArr2,
            showDir:true,
            strokeColor: "#28F",  //线颜色
            // strokeOpacity: 1,     //线透明度
            strokeWeight: 6,      //线宽
            // strokeStyle: "solid"  //线样式
        });

        var passedPolyline = new AMap.Polyline({
            map: map,
            // path: lineArr,
            strokeColor: "#AF5",  //线颜色
            // strokeOpacity: 1,     //线透明度
            strokeWeight: 6,      //线宽
            // strokeStyle: "solid"  //线样式
        });

        marker.on('moving', function (e) {
            passedPolyline.setPath(e.passedPath);
        });

        map.setFitView();
        marker.moveAlong(lineArr2, 2000000);

    })
    //事件绑定
    document.querySelector("#add-marker").onclick = function() {
        map.add(marker);
        map.setFitView();
    }
    document.querySelector("#remove-marker").onclick = function() {
        map.remove(marker);
        map.setFitView();
    }
    document.querySelector("#add-circle").onclick = function() {
        map.add(circle);
        map.setFitView();
    }
    document.querySelector("#remove-circle").onclick = function() {
        map.remove(circle);
        map.setFitView();
    }
</script>
</body>
</html>
