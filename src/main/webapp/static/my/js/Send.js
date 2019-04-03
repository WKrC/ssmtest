$(function () {
    //判断阅读器是否设置
    var hasReader = true;
    $.ajax({
        type: "POST",
        async: false,
        url: "fetchReaderControl",
        success: function (result) {
            log(result);
            if(result == null || result == "") {
                tipSetReader();
                hasReader = false;
            }
        }
    })
    if (hasReader) {
        ControlDisplay();
        window.setInterval(ControlDisplay, 30000);//每隔三十秒检查
    }

})

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
            window.location.replace("ReaderSet.jsp");
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
            if (data != undefined  && data.resultCode == -2 && closeFlag != 3){
                tipSetReader();
                runFlag = false;
            }
        }
    })
    return runFlag;
}

//输入检查
function checkjijianValue() {
    var re = /^1\d{10}$/

    if ($("#sender").val() == "") {
        myOpenWindow('寄件人不可为空！');
        return false;
    }
    if ($("#sender_phone").val() == "") {
        myOpenWindow('寄件人手机不可为空！');
        return false;
    } else if (!re.test($("#sender_phone").val())) {
        myOpenWindow('寄件人手机格式不正确！');
        return false;
    }
    if ($("#sender_addr").val() == "") {
        myOpenWindow('寄件人地址不可为空！');
        return false;
    }
    if ($("#consignee").val() == "") {
        myOpenWindow('收件人不可为空！');
        return false;
    }
    if ($("#consignee_phone").val() == "") {
        myOpenWindow('收件人手机不可为空！');
        return false;
    } else if (!re.test($("#consignee_phone").val())) {
        myOpenWindow('收件人手机格式不正确！')
        return false;
    }
    if ($("#consignee_addr").val() == "") {
        myOpenWindow('收件人地址不可为空！');
        return false;
    }
    return true;
}

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

//寄件信息提交
$("#submitbutton").click(function () {
    if (checkjijianValue()) {
        $.ajax({
            url: "SendControl",
            type: "POST",
            async: false,
            data: {
                'sender': $("#sender").val(),
                'sender_phone': $("#sender_phone").val(),
                'sender_addr': $("#sender_addr").val(),
                'consignee': $("#consignee").val(),
                'consignee_phone': $("#consignee_phone").val(),
                'consignee_addr': $("#consignee_addr").val(),
                'remaker': $("#remaker").val()
            },
            success: function (result) {
                if (result && result.indexCode != undefined) {
                    layer.open({
                        type: 1,
                        title: false,//不显示标题栏
                        closeBtn: false,
                        offset: '350px',
                        area: '300px;',
                        shade: 0.8,
                        id: 'LAY_layuipro', //设定一个id，防止重复弹出
                        btn: ['我知道了'],
                        btnAlign: 'c',
                        moveType: 1, //拖拽模式，0或者1
                        content: '<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">您的物流单号为:' + result.indexCode + '<br>请妥善保管！<br>以方便您查询物流信息！</div>',
                        success: function (layero) {
                            var btn = layero.find('.layui-layer-btn');
                        }
                    });
                    clearValue();
                }
            }
        })
    }
})