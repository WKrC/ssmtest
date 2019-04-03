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