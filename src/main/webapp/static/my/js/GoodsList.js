$(function () {
    //判断阅读器是否设置
    var hasReader = true;
    $.ajax({
        type: "POST",
        async: false,
        url: "fetchReaderControl",
        success: function (result) {
            if(result == null || result == "") {
                tipSetReader();
                hasReader = false;
            }
        }
    })
    if (hasReader) {
        ControlDisplay_GoodsList();
        window.setInterval(ControlDisplay_GoodsList, 10000);//每隔十秒检查
    }

})
function queryGoodsList() {
    $.ajax({
        async: false,
        type: "POST",
        url: "fetchAllGoodsController",
        success: function (data) {
            doPaging(data);
        }
    })
}
//确认签收
var isOverArray = new Array() ;
//https://www.cnblogs.com/yunshangwuyou/p/9032560.html
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
                isOverArray.push(indexCode);
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
                if (eachVal.isOver == 1 || contains(isOverArray,eachVal.goodsIndexCode) > -1){
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
function tipSetReader() {
    layer.open({
        title: '警告',
        content: '<div style="text-align: center;padding: 10px; line-height: 22px; color: black; font-weight: normal;">当前未设置阅读器！</div>',
        btn: ['立即设置'],
        closeBtn: 0,
        skin: "layui-layer-molv",
        offset: ['40%', '43%'],
        yes: function(){
            layer.closeAll();
            window.location.replace("adminLogin.jsp");
        }
    })
}
//获取阅读器状态
function ReaderIsOnline() {
    var runFlag = true;
    $.ajax({
        type: "POST",
        async: false,
        url:"ReaderIsOnline",
        success: function (data) {
            if (data != undefined  && data.resultCode == 1){
                layer.closeAll();
            }
            if (data != undefined  && data.resultCode == 0){
                OpenNoCloseWindow("阅读器不在线！请确保连通阅读器！");
                runFlag = false;
            }
            if (data != undefined  && data.resultCode == -1){
                myOpenWindow("获取阅读器信息异常！");
                runFlag = false;
            }
            if (data != undefined  && data.resultCode == -2){
                tipSetReader();
                runFlag = false;
            }
            if (data != undefined  && data.resultCode == -3){
                tipSetReader();
                runFlag = false;
            }
        }
    })
    return runFlag;
}
