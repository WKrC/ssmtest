function ControlDisplay() {
    var lightButton = document.getElementById("lightButton");
    if (ReaderIsOnline()) {
        lightButton.checked = true;
        $("#DisplayBlock").css("display", "block");
    }else {
        lightButton.checked = false;
        $("#DisplayBlock").css("display", "none");
    }
}
function ControlDisplay_GoodsList() {
    var lightButton = document.getElementById("lightButton");
    if (ReaderIsOnline()) {
        lightButton.checked = true;
        queryGoodsList();
        $("#DisplayBlock").css("display", "block");
        $("#pagediv").css("display", "block");
    }else {
        lightButton.checked = false;
        $("#DisplayBlock").css("display", "none");
        $("#pagediv").css("display", "none");

    }
}
