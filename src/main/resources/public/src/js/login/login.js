require(['jquery','dialog'],function ($, Dialog) {
    $(document).ready(function(){   
        $("#loginBtn").click(function () {
            $.ajax({
                url:"/login",
                method:"POST",
                data:{userName:$("#userName").val(),password:$("#password").val()},
                success:function (callData) {
                        callData = JSON.parse(callData);
                        //登录成功
                        if(callData.url){
                            new Dialog({
                                mode: 'tips',
                                tipsType: 'success',
                                content: callData.msg
                            });
                            setTimeout(function () {
                                window.location.href = callData.url;
                            },3000)
                        }
                        else{
                            new Dialog({
                                mode: 'tips',
                                tipsType: 'error',
                                content: callData.msg
                            });
                        }
                },
                error:function (data) {
                    if(data.result != true){
                        new Dialog({
                            mode: 'tips',
                            tipsType: 'error',
                            content: data.responseJSON.error
                        });
                    }
                }
            })
        })
        $("#password").keydown(function (event) {
            if(event.keyCode == 13){
                $("#loginBtn").click();
            }
        })
    })
})
