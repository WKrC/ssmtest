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

