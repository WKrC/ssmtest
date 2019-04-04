$(function () {
    //判断阅读器是否设置
    // $.ajax({
    //     type: "POST",
    //     url: "fetchReaderControl",
    //     success: function (result) {
    //         log(result);
    //         if(result == null || result == "") {
    //             tipSetReader();
    //         }
    //     }
    // })
    // //ReaderIsOnline();
    //window.setInterval(ReaderIsOnline, 30000);//每隔三十秒检查
})
function tipSetReader() {
    layer.open({
        title: '警告',
        content: '<div style="text-align: center;padding: 10px; line-height: 22px; color: black; font-weight: normal;">当前未设置阅读器！</div>',
        btn: ['立即设置'],
        closeBtn: 0,
        skin: "layui-layer-molv",
        offset: ['40%', '45%'],
        yes: function(){
            $(".jijianli").css("display", "none");
            $(".chaxunli").css("display", "none");
            layer.closeAll();
            $('body').toggleClass('open');
            closeFlag = 3;
        }
    })
}
//获取阅读器状态
function ReaderIsOnline() {
    var runFlag = true;
    $.ajax({
        type: "POST",
        url:"ReaderIsOnline",
        success: function (data) {
            if (data != undefined  && data.resultCode == 1){
                layer.closeAll();
            }
            if (data != undefined  && data.resultCode == 0){
                OpenNoCloseWindow("阅读器不在线！请确保连通阅读器！dd");
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
var closeFlag = 0;
var map = new AMap.Map("container", {
    resizeEnable: true
});

var geocoder,marker;
function geoCode() {
    if (!geocoder) {
        geocoder = new AMap.Geocoder({
            city: "010", //城市设为北京，默认：“全国”
        });
    }
    var address = document.getElementById('ReaderAddress').value;
    geocoder.getLocation(address, function (status, result) {
        if (status === 'complete' && result.geocodes.length) {
            var lnglat = result.geocodes[0].location
            document.getElementById('ReaderGPS').value = lnglat;
        }
    });
}
//寄件入口
$("#jijian").click(function () {
    $(".chaxunli").css("display", "none");
    $(".Readerli").css("display", "none");
    $('body').toggleClass('open');
    closeFlag = 1;
})
//查询入口
$("#chaxun").click(function () {
    $(".jijianli").css("display", "none");
    $(".Readerli").css("display", "none");
    $('body').toggleClass('open');
    closeFlag = 2;
})

//阅读器入口
$("#ReaderSet").click(function () {
    $(".chaxunli").css("display", "none");
    $(".jijianli").css("display", "none");
    $('body').toggleClass('open');
    closeFlag = 3;
    $.ajax({
        type: "POST",
        url: "fetchReaderControl",
        success: function (result) {
            console.info(result);
            $("#ReaderName").val(result.readerName);
            $("#ReaderAddress") .val(result.readerAddress);
            $("#ReaderGPS").val(result.readerGPS);
        }
    })
})
function log(msg){
    console.info(msg);
}
$('#nav-toggle').click(function () {
    if (closeFlag > 0) {
        $('body').toggleClass('open');
        $(".jijianli").css("display", "block");
        $(".chaxunli").css("display", "block");
        $(".Readerli").css("display", "block");
        closeFlag = 0;
        clearValue();
    }
});

//获取GPS
$("#getGPSButton").click(function () {
    if ($(ReaderAddress).val() == "") {
        myOpenWindow('阅读器地址不可为空！');
    } else {
        geoCode();
    }
});

//保存阅读器设置
$("#saveReaderbutton").click(function () {
    var runFlag = true;
    if($("#ReaderName").val() == ""){
        myOpenWindow('阅读器名不可为空！');
        runFlag = false;
    }else if($("#ReaderAddress").val() == ""){
        myOpenWindow('阅读器地址不可为空！');
        runFlag = false;
    }else if($("#ReaderGPS").val() == ""){
        myOpenWindow('阅读器经纬度不可为空！');
        runFlag = false;
    }
    if (runFlag){
        $.ajax({
            type: "POST",
            data:{
               'ReaderName': $("#ReaderName").val(),
               'ReaderAddress': $("#ReaderAddress") .val(),
               'ReaderGPS': $("#ReaderGPS").val()
            },
            url: "saveOrUpdateReaderSettingController",
            success: function (result) {
                if (result != undefined && result.resultCode == 1){
                    myOpenWindow("设置成功！");
                }
                if (result != undefined && result.resultCode == 2){
                    myOpenWindow("更新设置成功！");
                }
                if (result != undefined && result.resultCode == -1){
                    myOpenWindow("设置异常！");
                }
                if (result != undefined && result.resultCode == -2){
                    myOpenWindow("读写器连接失败！请确保连通阅读器！");
                }
                if (result != undefined && result.resultCode == -3){
                    myOpenWindow("读写器写入地址失败！");
                }
                if (result != undefined && result.resultCode == -4){
                    myOpenWindow("阅读器不在线！更新失败！");
                }
            }
        })
    }

})
$("#ReaderAddress").focus(function () {
    $("#ReaderGPS").val("");
})

//寄件信息提交
$("#submitbutton").click(function () {
    if (!checkjijianValue()) {
        return false;
    }
    $.ajax({
        url: "jijianControl",
        type: "POST",
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
                $('body').toggleClass('open');
                $(".jijianli").css("display", "block");
                $(".chaxunli").css("display", "block");
                $(".Readerli").css("display", "block");
                closeFlag = 0;
                clearValue();
            }
        }
    })
})

//查询按钮
$("#chaxunbutton").click(function () {
    if (!checkchaxunValue()) {
        return false;
    }
    $.ajax({
        url: "chaxunControl",
        type: "POST",
        data: {
            'goodsIndexCode': $("#goodsIndexCode").val()
        },
        success: function (result) {
            if (result != "") {
                layer.msg('即将跳转物流信息页面！', {
                    time: 3000 //3s后自动关闭
                });
                clearValue();
                window.location.replace("map.jsp");
            } else {
                myOpenWindow('无此物流单号信息！');
            }
        }
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

function checkchaxunValue() {
    if ($("#goodsIndexCode").val() == "") {
        myOpenWindow('物流单号不可为空！');
        return false;
    }
    return true;
}


//提示弹出层
function myOpenWindow(msg) {
    layer.closeAll();
    layer.open({
        title: '来自程序猿的提示',
        content: '<div style="text-align: center;padding: 10px; line-height: 22px; color: black; font-weight: normal;">' + msg + '</div>',
        skin: "layui-layer-molv",
        offset: ['40%', '43.5%']
    });
}

//错误提示弹出层
function OpenNoCloseWindow(msg) {
    layer.closeAll();
    layer.open({
        title: '来自程序猿的提示',
        content: '<div style="text-align: center;padding: 10px; line-height: 22px; color: black; font-weight: normal;">' + msg + '</div>',
        skin: "layui-layer-molv",
        offset: ['40%', '45%'],
        closeBtn: 0,
        btn: null
    });
}