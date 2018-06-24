define(['Util', 'dialog',/* 'js/constants/constants',*/ 'text!src/consult/batchUpdateConsultData.tpl',
    'date'/*, 'validator', 'js/consult/selectKnowledge'*/], function(Util, Dialog,/* Constants,*/ tpl, date /*,Validator,SelectKnowledge*/ ){

    var initialize = function(search, keys, tmpltId, cnslId, recIds){
        var validator;
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
            if(data.returnCode == 0){
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
            mode: 'normal',
            title: '批量更新咨询表记录',
            width: 600,
            height: 400,
            modal: true,
            ok: function(){
                /*if(!validator.form()){
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

                    if(JSON.stringify(param) == "{}"){
                        return true;
                    }

                    //提交保存数据
                    var params = {param: JSON.stringify(param), tmpltId: tmpltId, cnslId: cnslId, recIds: recIds};
                    var url = /*Constants.AJAXURL +*/ "/consultData/batchUpdateConsultData";

                    Util.ajax.postJson(url, params, function(data){
                        if(data.returnCode == 0){
                            new Dialog({
                                mode: 'tips',
                                tipsType: 'success',
                                content: '更新成功,同步索引中,请稍后查询'
                            });
                            search();
                            return true;
                        }else{
                            new Dialog({
                                mode: 'tips',
                                tipsType: 'error',
                                content: data.responseJSON.message
                            });
                            return false;
                        }

                        search();
                    }, true);
                /*}*/
            },
            okValue: '确定',
            cancel: function(){
                return true;
            },
            cancelValue: '取消'
        };

        var template = Util.hdb.compile(tpl);
        dialogConfig.content = template(keys);

        new Dialog(dialogConfig);

        $(".date-contain").each(function(){
            new date({
                el: $(this),
                isReadOnly:true,
                type:"datetime",
                name: this.id
            });
        });

        var validConfig = {
            el: $("#form"),
            dialog:true
        };

        var pattern = {
            integer1: '^[1-9]\\d{0,17}$'
        };
        var rules = {};
        var messages = {};
        $(keys).each(function(){
            var rule = "";
            var message = {};

            if(getType(this.colmId) == "num"){
                rule += "integer1|";
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

/*        validConfig.pattern = pattern;
        validConfig.messages = messages;
        validConfig.rules = rules;

        validator =  new Validator(validConfig);
        validator.addMethod("max10000", function(str){
            return trim(str).length - 10000 > 0 ? false: true;
        });

        validator.addMethod("noBrackets", function(str){
            return trim(str).indexOf("<") == -1 && trim(str).indexOf(">") == -1 ? true: false;
        });*/

/*
        $("#update-knwlg").click(function(){
            new SelectKnowledge($(this).parent().prev());
        });
*/

    };

    return initialize;
});