<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh">
<head>
    <title>物流列表</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="static/my/css/ReaderSendCommom.css">
    <link rel="stylesheet" href="static/layui/css/layui.css"  media="all">
    <!-- 引入分页插件 -->
    <link rel="stylesheet" type="text/css" href="static/paginationjs-master-2.1.0/dist/pagination.css" />
    <style>
        td{
            text-align: center;
        }
    </style>
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
<div id="nuknow"  style="position: absolute;width: 50%;margin-left: 25%;margin-top: 14%;">
    <table id="dataTable" class="layui-table" style="margin: 0px;">
        <colgroup>

        </colgroup>
        <thead>
        <tr>
            <th style="text-align: center">物流单号</th>
            <th style="text-align: center">寄件人</th>
            <th style="text-align: center">寄件人联系方式</th>
            <th style="text-align: center">收件人</th>
            <th style="text-align: center">收件人联系方式</th>
            <th style="text-align: center">操作</th>
        </tr>
        </thead>
        <tbody id="dataList">
        <tr>
            <td>0123456789111213</td>
            <td>吴康榕</td>
            <td>13132560116</td>
            <td>吴沁</td>
            <td>18759012643</td>
            <td><button class="layui-btn" onclick="ConfirmReceive()">确认签收</button></td>
        </tr>
        <tr>
            <td>0123456789111213</td>
            <td>吴康榕</td>
            <td>13132560116</td>
            <td>吴沁</td>
            <td>18759012643</td>
            <td><button class="layui-btn layui-btn-disabled">已签收</button></td>
        </tr>
        </tbody>
    </table>
    <div id="pagediv" style="margin-top: 1%;margin-left: 40%"></div>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.min.js" charset="UTF-8"></script>
<script src="static/layui/layui.js" charset="UTF-8"></script>
<script src="static/layui/layui.all.js" charset="UTF-8"></script>
<script type="text/javascript"
        src="static/paginationjs-master-2.1.0/dist/pagination.min.js"></script>
<script>
    $(function () {
        $.ajax({
            async: false,
            type: "POST",
            url: "fetchAllGoodsController",
            success: function (data) {
                doPaging(data);
            }
        })
    })
    //确认签收
    function ConfirmReceive(indexCode) {
        $.ajax({
            async: false,
            data: {
                'indexCode':indexCode
            },
            type: "POST",
            url: "ConfirmReceiveController",
            success: function (data) {
                if (data.resultCode == 1) {
                    var StrHtml ="<button class=\"layui-btn layui-btn-disabled\">已签收</button>";
                    $("#" + indexCode).html(StrHtml);

                }
                if (data.resultCode == -1) {
                    myOpenWindow("签收失败！");
                }
            }
        })
    }
    // 分页处理
    function doPaging(data,pagesize){
        if(isNaN(pagesize) || pagesize<=0){
            pagesize = 7; // 默认页面大小为5条记录
        }
        // 获取显示分页控件的div
        $('#pagediv').pagination({
            dataSource: data, // 分页的数据源（可以是ajax查询成功之后的json数据对象）
            pageSize: pagesize,// 每页显示的记录条数
            showGoInput: true, // 是否显示goin按钮
            showGoButton: true,
            callback: function(data, pagination) {
                // 分页处理
                // 解析拼写的html内容
                var strHtml = "";
                $.each(data, function(index, eachVal) {
                    strHtml+="<tr>";

                    strHtml+="<td>";
                    strHtml+=eachVal.goodsIndexCode;
                    strHtml+="</td>";

                    strHtml+="<td>";
                    strHtml+=eachVal.sender;
                    strHtml+="</td>";

                    strHtml+="<td>";
                    strHtml+=eachVal.sender_phone;
                    strHtml+="</td>";

                    strHtml+="<td>";
                    strHtml+=eachVal.consignee;
                    strHtml+="</td>";

                    strHtml+="<td>";
                    strHtml+=eachVal.consignee_phone;
                    strHtml+="</td>";

                    if (eachVal.isOver == 1){
                        strHtml+="<td>";
                        strHtml+="<button class=\"layui-btn layui-btn-disabled\">已签收</button>";
                        strHtml+="</td>";
                    } else {
                        strHtml+="<td id='" + eachVal.goodsIndexCode + "'>";
                        strHtml+="<button class=\"layui-btn\" onclick=\"ConfirmReceive(" + eachVal.goodsIndexCode + ")\">确认签收</button>";
                        strHtml+="</td>";
                    }
                    strHtml+="</tr>";
                });

                // 把解析的html内容，使用dom操作赋值到表格中
                $("#dataList").html(strHtml);
            }
        })
    }
</script>
</body>
</html>