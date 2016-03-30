function changeWindow(){
    var sizeInput = $("#window-size"),
        size = sizeInput.val();

    if(sizeInput.val() == "full"){
        $(".page-title").hide();
        $(".page-bar").hide();

        sizeInput.val("restore");
    }else{
        $(".page-title").show();
        $(".page-bar").show();

        sizeInput.val("full");
    }
}

$(document).ready(function(){
    var imageScale = $("#img_scale");
    imageScale.on("click", function(){
        changeWindow();
    });
})


