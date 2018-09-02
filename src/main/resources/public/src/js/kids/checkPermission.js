define(['jquery'],function ($) {

    var init = function(permission, btnEl){
        $.ajax({
            url:"/auth/checkPermission",
            method:"GET",
            data:{permission:permission},
            success:function (callData) {
                callData = JSON.parse(callData);
                if(callData.result == true){
                    btnEl.removeClass("hideMenu");
                }
            }
        });
    }

    return init;

})