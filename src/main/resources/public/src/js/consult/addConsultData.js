define(['Util', 'dialog',/* 'js/constants/constants',*/ 'text!src/consult/addConsultData.tpl',
    'date'/*, 'validator', 'js/consult/selectKnowledge'*/], function(Util, Dialog, /*Constants,*/ tpl, MyDate/*, Validator, SelectKnowledge*/){

    var initialize = function(callBack, keys, tmpltId, cnslId, recIds){
        //注册一个判断相等的Helper,判断v1是否等于v2
        Util.hdb.registerHelper("equal",function(v1,v2,options){
            if(v1==v2){
                //满足添加继续执行
                return options.fn(this);
            }else{
                //不满足条件执行{{else}}部分
                return options.inverse(this);
            }
        });

        Util.ajax.getJson("/consultTmplt/getTmpltColumnNoPage", {tmpltId: tmpltId}, function(data){
            if(data && data.total>0){
                keys = data.beans;
            }
        }, true);

        //获取咨询表字段类型
        var getType = function(value){
            for(var i = 0, j = keys.length; i < j;i ++){
                if(keys[i].colmId == value){
                    return keys[i].colmTypeCd;
                }
            }
        };

        //咨询表记录修改时的回显内容
        var consultData;

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

        var dialogConfig = {
            id:"addConsultDataDialog",
            mode: 'confirm',
            title: '新增咨询表记录',
            width: 600,
            height: 400,
            modal: true,
            ok: function(){
/*                if(!validator.form()){
                    return false;
                }else{*/
                    var param = {};
                    $("#form").find("input").each(function(){
                        if(trim($(this).val())){
                            if($(this).attr("knwlgId")){
                                param[$(this).attr("name")] = $(this).attr("knwlgId");
                            }else{
                                param[$(this).attr("name")] = $(this).val();
                            }
                        }
                    });

                    $("#form").find("textarea").each(function(){
                        if(trim($(this).val())){
                            param[$(this).attr("name")] = $(this).val();
                        }
                    });

                    //提交保存数据
                    var params = {param: JSON.stringify(param), tmpltId: tmpltId, cnslId: cnslId, recIds: recIds};

                    if(params.param == "{}"){
                        return true;
                    }

                    var url = "/consultData/addOrUpdateConsultData";

                    Util.ajax.postJson(url, params, function(data){
                        if(data.returnCode == 0 ){
                            if(recIds){
                                new Dialog({
                                    mode: 'tips',
                                    tipsType: 'success',
                                    content: '更新成功'
                                });
                            }else{
                                new Dialog({
                                    mode: 'tips',
                                    tipsType: 'success',
                                    content: '新增成功'
                                });
                            }
                            callBack();
                            return true;
                        }else{
                            new Dialog({
                                mode: 'tips',
                                tipsType: 'error',
                                content: data.returnMessage
                            });
                            return false;
                        }
                    }, true);
                /*}*/
            },
            cancel: function(){
                return true;
            },
        };

        if(recIds){
            dialogConfig.title = "更新咨询表记录";
            var dataFlag = false;
            Util.ajax.getJson("/consultData/getConsultData", {recId: recIds}, function(data){
                if(data.returnCode == 0){
                    consultData = data.beans;
                }else{
                    new Dialog({
                        mode: 'tips',
                        tipsType: 'error',
                        content: data.responseJSON.message
                    });
                    dataFlag = true;
                }
            }, true);

            if(dataFlag){
                return;
            }
        }

        var template = Util.hdb.compile(tpl);
        dialogConfig.content = template(keys);

        new Dialog(dialogConfig);

        $(".date-contain").each(function(){
            new MyDate({
                el: $(this),
                isReadOnly:true,
                type:"datetime",
                name: this.id
            });
        });

        if(consultData){
            $(consultData).each(function(){
                if(getType(this.colmId) == "date"){
                    //对日期类型的值进行转换
                    if(this.keyval && this.keyval.match("^[1-9]\\d*$")){
                        var datetimeType = "";
                        var date = new Date(parseInt(this.keyval));
                        datetimeType += date.getFullYear();  //年
                        datetimeType += "-";
                        datetimeType += date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : (date.getMonth() + 1);
                        datetimeType += "-";
                        datetimeType += date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
                        datetimeType += " ";
                        datetimeType += date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
                        datetimeType += ":";
                        datetimeType += date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
                        datetimeType += ":";
                        datetimeType += date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
                        $("input[name=" + this.colmId + "]").val(datetimeType);
                    }else{
                        $("input[name=" + this.colmId + "]").val(this.keyval);
                    }
                }else{
                    var colmId = this.colmId;
                    if($("input[name=" + this.colmId + "][knwlgId]").length){
                        $("input[name=" + this.colmId + "]").attr("knwlgId", this.keyval);
                        //知识名称的回显
                        Util.ajax.getJson("/docEditPus/getNmById",{knwlgId: this.keyval},function(data){
                            if(data.returnCode == 0 && data.object){
                                $("input[name=" + colmId + "]").val(data.object);
                            }else{
                                $("input[name=" + colmId + "]").val(this.keyval);
                            }

                        }, true);
                    }else if(getType(this.colmId) == "mes"){
                        $("textarea[name=" + colmId + "]").val(this.keyval);
                    }else{
                        $("input[name=" + colmId + "]").val(this.keyval);
                    }
                }
            });
        }

/*        var validConfig = {
            el: $("#form"),
            dialog:true
        };*/

        var pattern = {
            integer1: '^[1-9]\\d{0,17}$'
        };
        var rules = {};
        var messages = {};
        $(keys).each(function(){
            var rule = "";
            var message = {};
            if(this.requiredFlag){
                rule += "required|";
                message.required = "此项必填";
            }

            if(getType(this.colmId) == "num"){
                rule += "integer1|";
                rule += "max_18|";
                message.integer1 = "请输入18位以内非0开头的数字";
            }

            if(this.tsvldRegexp){
                if(this.tsvldRegexp.indexOf("^") != 0){
                    this.tsvldRegexp = "^" + this.tsvldRegexp;
                }

                if(this.tsvldRegexp.substring(this.tsvldRegexp.length - 1) != "$"){
                    this.tsvldRegexp = this.tsvldRegexp + "$";
                }

                pattern["r" + this.colmId] = this.tsvldRegexp;
                rule += "r" + this.colmId + "|";
                message["r" + this.colmId] = this.hintCntt ? this.hintCntt : "不符合表达式 " + this.tsvldRegexp;
            }

            rule += "max10000|noBrackets";
            rules[this.colmId] = rule;

            message.max10000 = "字段不能超过10000";
            message.noBrackets = "字段中不能包含<>";
            messages[this.colmId] = message;
        });

/*
        validConfig.pattern = pattern;
        validConfig.messages = messages;
        validConfig.rules = rules;

        var validator =  new Validator(validConfig);
        validator.addMethod("max10000", function(str){
            return trim(str).length - 10000 > 0 ? false: true;
        });

        validator.addMethod("noBrackets", function(str){
            return trim(str).indexOf("<") == -1 && trim(str).indexOf(">") == -1 ? true: false;
        });
*/

/*        $("#add-knwlg").click(function(){
            new SelectKnowledge($(this).parent().prev());
        });*/

    };

    return initialize;
});