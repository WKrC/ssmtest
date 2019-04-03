$(function () {
    ControlDisplay();
    window.setInterval(ControlDisplay, 30000);//每隔三十秒检查
})
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

            if (data != undefined  && data.resultCode == -3){
                OpenNoCloseWindow("阅读器不在线！请确保连通阅读器！");
                runFlag = false;
            }
        }
    })
    return runFlag;
}

var map = new AMap.Map("container", {
    resizeEnable: true
});

var geocoder,marker;
function geoCode() {
    if (!geocoder) {
        geocoder = new AMap.Geocoder({
            // city: "010", //城市设为北京，默认：“全国”
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

//获取GPS
$("#getGPSButton").click(function () {
    if ($(ReaderAddress).val() == "") {
        myOpenWindow('阅读器地址不可为空！');
    } else {
        geoCode();
    }
});

function checkValue() {
    if($("#ReaderName").val() == ""){
        myOpenWindow('阅读器名不可为空！');
        return false;
    }else if($("#ReaderAddress").val() == ""){
        myOpenWindow('阅读器地址不可为空！');
        return false;
    }else if($("#ReaderGPS").val() == ""){
        myOpenWindow('阅读器经纬度不可为空！');
        return false;
    }
    return true;
}

//保存阅读器设置
$("#saveReaderbutton").click(function () {

    if (checkValue()){
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