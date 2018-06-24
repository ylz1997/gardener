define(['dialog',
    'text!src/kids/kidsInput.tpl'
],function (Dialog,
            KidsInput) {

    var init = function () {
        $("#btn-add").click(function () {
            new Dialog(
                {mode:"confirm",
                    id:"kidsInput",
                    content:KidsInput,
                    title:"新增学生录入",
                    ok:function () {
                        var params = new Object();
                        $(".kidsClz").each(function(){
                            params[$(this).attr("name")] = $(this).val();
                        })
                        $.ajax({
                            url:"/Kids/add",
                            method:"POST",
                            contentType:"application/json",
                            data:JSON.stringify(params),
                            success:function (data) {
                                data = JSON.parse(data);
                                if(data.result == true){
                                    new Dialog({
                                        mode: 'tips',
                                        tipsType: 'success',
                                        content: "保存成功"
                                    });
                                }
                            },
                            error:function (data) {
                                debugger;
                                new Dialog({
                                    mode: 'tips',
                                    tipsType: 'error',
                                    content: data.responseJSON.error
                                });
                                return;
                            }
                        })
                    },
                });
        });

    }

    return init;
})