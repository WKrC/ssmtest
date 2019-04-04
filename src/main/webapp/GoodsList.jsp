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
    <input hidden id="lightButton" type="radio"  name="switch" value="on" />
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
<div id="DisplayBlock"  style="display: none;position: absolute;width: 50%;margin-left: 25%;margin-top: 14%;">
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
        <%--<tr>--%>
            <%--<td>0123456789111213</td>--%>
            <%--<td>吴康榕</td>--%>
            <%--<td>13132560116</td>--%>
            <%--<td>吴沁</td>--%>
            <%--<td>18759012643</td>--%>
            <%--<td><button class="layui-btn" onclick="ConfirmReceive()">确认签收</button></td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td>0123456789111213</td>--%>
            <%--<td>吴康榕</td>--%>
            <%--<td>13132560116</td>--%>
            <%--<td>吴沁</td>--%>
            <%--<td>18759012643</td>--%>
            <%--<td><button class="layui-btn layui-btn-disabled">已签收</button></td>--%>
        <%--</tr>--%>
        </tbody>
    </table>
    <div id="pagediv" style="margin-top: 1%;margin-left: 40%;display: none;"></div>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.min.js" charset="UTF-8"></script>
<script src="static/layui/layui.js" charset="UTF-8"></script>
<script src="static/layui/layui.all.js" charset="UTF-8"></script>
<script type="text/javascript"
        src="static/paginationjs-master-2.1.0/dist/pagination.min.js"></script>
<script src="static/my/js/Common.js"></script>
<script src="static/my/js/GoodsList.js"></script>
<script src="static/my/js/ReaderSendCommom.js"></script>
</body>
</html>