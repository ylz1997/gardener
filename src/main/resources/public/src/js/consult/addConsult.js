define(['text!src/consult/addConsult.tpl',
            'src/js/consult/selectTmplt',
            'dialog'], function( tpl, SelectTmplt, Dialog){
    /**
     * 用于修剪字符串 兼容ie8
     *
     * @param str
     * @returns {XML|string|void|*}
     */
    var trim = function (str) {
        if (str) {
            return str.replace(/(^\s*)|(\s*$)/g, "");
        }else{
            return "";
        }
    };

    var initialize = function(callBack, cnslId){
        var dialogConfig = {
            id: 'addConsultDialog',
            mode: 'confirm',
            title: '新增咨询表',
            content: tpl,
            ok: function(){
                //TODO 增加validation
                //if(validator.form()){
                var url;
                if(consult){
                    url = "/kmConsult/updateConsult";
                }else{
                    url = "/kmConsult/addConsult";
                }

                var param = {
                    cnslId: trim($("#form").find("input[name=cnslId]").val()),
                    cnslNm: trim($("#form").find("input[name=cnslNm]").val()),
                    tmpltId: trim($("#form").find("input[name=tmpltId]").attr("tmpltId")),
                    rmk: trim($("#form").find("textarea[name=rmk]").val())
                };

                $.ajax({
                    url:url,
                    method:"POST",
                    async:true,
                    data:param,
                    success:function (data) {
                        if(consult){
                            $("#containerDiv").scrollTop(0);
                            new Dialog({
                                mode: 'tips',
                                tipsType: 'success',
                                content: '修改成功'
                            });
                        }else{
                            new Dialog({
                                mode: 'tips',
                                tipsType: 'success',
                                content: '新增成功'
                            });
                        }
                        //刷新页面
                        callBack.call();
                    },
                    error:function (data) {
                        new Dialog({
                            mode: 'tips',
                            tipsType: 'error',
                            content: data.responseJSON.message
                        });
                        return;
                    }
                })

                return true;
                /*}else{
                    return false;
                }*/
            },
            cancel: function(){
                return true;
            },
            width: 600,
            height: 205
        };

        var consultInit = function () {
            if(consult){
                dialogConfig.title = "修改咨询表";
            }
            new Dialog(dialogConfig);

            if(consult){
                $("#form").find("input[name=cnslId]").val(cnslId);
                $("#form").find("input[name=cnslNm]").val(consult.cnslNm);
                $("#form").find("textarea[name=rmk]").val(consult.rmk);

                //TODO 模板名称回显
                $.ajax({
                    url:"/consultTmplt/getTmplt/"+ consult.tmpltId,
                    method:"GET",
                    async:true,
                    data:{},
                    success:function (data) {
                        if(JSON.parse(data)){
                            var data = JSON.parse(data);
                            if(data.object){
                                $("#form").find("input[name=tmpltId]").attr("tmpltId", consult.tmpltId);
                                $("#form").find("input[name=tmpltId]").val(data.object.tmpltNm);
                            }
                        }
                    },
                    error:function (data) {
                        $("#form").find("input[name=tmpltId]").val(consult.tmpltId);
                    }
                })
            }

            $("#form").find("input[name=cnslNm]").on("mouseover", function(){
                $(this).attr("title", $(this).val());
            });

            $("#form").find("input[name=tmpltId]").on("mouseover", function(){
                $(this).attr("title", $(this).val());
            });
            //模板选择按钮的点击事件
            $("#comsult-tmplt").click(function(){
                //首先校验当前咨询表下是否有咨询记录信息
                if(cnslId){
                    new Dialog({
                        mode: 'tips',
                        content: '修改咨询表时, 模板不能更改'
                    });
                }else{
                    new SelectTmplt($(this).parent().prev());
                }
            });
        }

        var consult;
        if(cnslId){
            //根据id查询咨询表详细信息
            $.ajax({
                url:"/kmConsult/getConsultContent",
                method:"GET",
                async:true,
                data:{cnslId: cnslId},
                success:function (data) {
                    if(JSON.parse(data)){
                        consult = JSON.parse(data).data;
                    }
                    consultInit();
                },
                error:function (data) {
                    new Dialog({
                        mode: 'tips',
                        tipsType: 'error',
                        content: data.responseJSON.message
                    });
                    return;
                }
            })
        }
        else{
            consultInit();
        }
        var validatorConfig = {
            el: $("#form"),
            dialog:true,
            pattern: {
                noBrackets: "^[^<>]*$"
            },
            rules:{
                cnslNm: 'required|max255|noBrackets|repeat',     //设置name=email 的元素为必填项，并且是邮箱格式
                tmpltId: 'required',
                rmk: 'required|max4000'
            },
            messages: {
                cnslNm: {
                    required: '请输入咨询表名称',
                    max255: '咨询表名称不能超过255个字符',
                    noBrackets: '咨询表名称不能包含<>',
                    repeat: '咨询表名称重复'
                },
                tmpltId: {
                    required: '请选择咨询表模板'
                },
                rmk: {
                    required: '请输入咨询表描述信息',
                    max4000: '咨询表描述信息不能超过4000个字符'
                }
            }
        };

        //TODO 找一个validator的替代

/*        var validator = new Validator(validatorConfig);
        validator.addMethod("max255", function(){
            var cnslNm = trim($("#form").find("input[name=cnslNm]").val());
            if(cnslNm){
                if(cnslNm.length > 255){
                    return false;
                }else{
                    return true;
                }
            }else{
                return false;
            }
        });

        validator.addMethod("max4000", function(){
            var rmk = trim($("#form").find("textarea[name=rmk]").val());
            if(rmk){
                if(rmk.length > 4000){
                    return false;
                }else{
                    return true;
                }
            }else{
                return false;
            }
        });
        validator.addMethod("repeat", function(){
            var param = {};
            var flag = false;
            param.cnslNm = trim($("#form").find("input[name=cnslNm]").val());
            if(consult){
                param.cnslId = consult.cnslId;
            }
            Util.ajax.getJson(Constants.AJAXURL + "/kmConsult/checkConsultNm", param, function(data){
                if(data.returnCode == 0){
                    flag = data.object;
                }else{
                    new Dialog({
                        mode: 'tips',
                        tipsType: 'error',
                        content: data.responseJSON.message
                    });
                }
            }, true);
            return flag;
        });*/
    };

    return initialize;
});