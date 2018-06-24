define(['text!/src/js/assets/dialog/confirm.tpl','hdb','bootstrap', 'toastr'], function (ConfirmTpl,Hdb, Bootstrap, Toastr) {
    var option;
    var initialize = function (options) {
        option = options;
        if(options.mode == "tips")
        {
            Toastr.options.positionClass = 'toast-top-center';
            if(options.content){
                if(options.tipsType == "success" ){
                    Toastr.success(options.content);
                }
                if(options.tipsType == "warning" ){
                    Toastr.warning(options.content);
                }
                if(options.tipsType == "info" ){
                    Toastr.info(options.content);
                }
                if(options.tipsType == "error" ){
                    Toastr.error(options.content);
                }
            }
        }
        /**
         * 参数：title:标题;content:内容tpl;id:窗体唯一标识;mode:窗体类型
         */
        if(options.mode == "confirm" || !options.mode){
            if(!options.id)
            {
                console.log("窗体id不能为空");
                return;
            }

            //已经存在dialog，必须先移除窗体对象
            if($("#"+options.id).length>0) {
                console.log("已经存在！" + options.id);
                $("#" + options.id).remove();
            }

            //进行初始化构建
            var template = Hdb.compile(ConfirmTpl);
            var htmlString = template(options);
            $("body").append(htmlString);

            if(options.ok){
                $("#btnConfirm"+options.id).click(function () {
                    options.ok.call();
                    $("#"+options.id).remove();
                })
            }
            if(options.cancel){
                $("#btnCancel"+options.id).click(function () {
                    options.cancel.call();
                    $("#"+options.id).remove();
                })
            }
            $("#"+options.id).modal({backdrop:false });
        }
    }

    $.extend(initialize.prototype,{
        hide:function () {
            $("#"+option.id).modal("hide");
        }
    });
    return initialize;
})
