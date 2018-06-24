/**
 * @Description: 新增咨询模板
 * @Date:2018/03/07
 * @Author: wx
 */
define(['Util','validator','public/src/js/dialog/dialog','text!modules/consult/addConsultTemplet.tpl','js/constants/constants','checkboxes','crossAPI'],
    function(Util,Validator,Dialog,addConsultTemplet,Constants,Checkboxes,crossAPI){
/*
        var $el = $(addConsultTemplet);
        this.context=$el;               //系统变量-定义该模块的根节点*/
        var _root =  window.location.protocol+"//"+ window.location.host;

        var initialize = function (data) {

            //根据类型设置弹窗名
            if(data.isUpdate){
                logNm = "修改模板";
            }else if(data.isUpdate==false){
                logNm = "新增模板";
            }else{
                logNm = "添加相似";
            }

            //保存
            var ok = function() {
                var result = validator.form();
                if (!result) {
                    return false;
                }

                var authRegn = checkboxes.get();
                console.log(authRegn);
                if(authRegn==""){
                    new Dialog({
                        mode: 'tips',
                        delayRmove: 3,
                        content: '请选择适用地区',
                        tipsType:'error',
                        quickClose: true
                    });
                    return false;
                }
                var tmpltNm = $("input[name='consultTempletNm']").val();
                var param;
                var requestURL;
                if(data.isUpdate){
                    param= {
                        tmpltId : data.tmpltId,
                        tmpltNm : tmpltNm,
                        authRegn: authRegn
                    };
                    requestURL =  Constants.AJAXURL + '/consultTmplt/updateConsultTmpltInfo?v=' + new Date().getTime();
                }else if(data.isUpdate==false){
                    param= {
                        catlId : data.catlId,
                        tmpltNm : tmpltNm,
                        authRegn: authRegn
                    };
                    requestURL =  Constants.AJAXURL + '/consultTmplt/saveConsultTmpltInfo?v=' + new Date().getTime();
                }else{
                    param = {
                        tmpltId : data.tmpltId,
                        catlId : data.catlId,
                        tmpltNm : tmpltNm,
                        authRegn: authRegn
                    };
                    requestURL = Constants.AJAXURL+'/consultTmplt/extendConsultTmpltInfo?v='+new Date().getTime();
                }
                var isOk ;
                Util.ajax.ajax({
                    data: param,
                    url: requestURL,
                    async:false,
                    success: function (json) {
                        if(json.returnCode=="0") {
                            isOk = true;
                            new Dialog({
                                mode: 'tips',
                                tipsType: 'success',
                                content: '保存成功'
                            });
                            if (data['list'] != ''&&data['list']!=undefined) {
                                data['list'].search(data.dataParam);
                            }
                            crossAPI.on('tabSwitch',function(){ data['list'].sizeInit()});
                            crossAPI.destroyTab("编辑咨询表模板");
                            crossAPI.createTab({title:"编辑咨询表模板",url:_root+Constants.PREAJAXURL+"/src/modules/consult/consultTmpltDetail.html?tmpltId="+json.bean});
                        }else{
                            isOk = false;
                            new Dialog({
                                mode: 'tips',
                                tipsType:'error',
                                content: json.message
                            });
                        }
                    },
                    error: function (datas) {
                        return;
                    }
                },true);
                return isOk;
            };
            //取消
            var cancel = function(){
            };


            //dialog
            var config = {
                mode:'normal',
                title:logNm,
                content:addConsultTemplet,
                ok:ok,
                okValue: '保存',
                cancel: cancel,
                cancelValue: '取消',
                cancelDisplay:true,
                width:500,
                height:300,
                skin:'dialogSkin',  //设置对话框额外的className参数
                fixed:false,
                quickClose:false ,
                modal:true
            }
            new Dialog(config);  //初始化对话框

            //获取provnceList
            var provnceList;               //省名list，不包括全国
            var provnceLength;             //省份列表长度
            Util.ajax.ajax({
                type: "GET",
                url: Constants.AJAXURL + '/kmConfig/getDistrictBySuprRegnId?id=000&maxLevel=1',//路径
                async: false,
                success: function (data) {
                    provnceList = data.beans;
                    provnceLength=provnceList.length;
                }
            });


            //设置checkbox的新items
            var newItems=[];     //checkbox的items
            var itemCheckAll = {className:'quanxuan',label:'全选',value:'0'} ;
            var quoguo = {className:'checkbox1',label:'全国',value:'000'} ;
            newItems.push(itemCheckAll);
            newItems.push(quoguo);
            for(var i= 0;i<provnceLength;i++){
                var item = {} ;
                var provnce=provnceList[i];
                item['className']='checkbox1';
                item['value']=provnce['value'];
                item['label']=provnce['name'];
                newItems.push(item);
            }


            //checkbox
            var checkboxesConfig = {
                el:'#regnCheckBox',      //要绑定的容器
                className:'box',    //组件外围的className,默认横向|all-width纵向
                disabled:0,     //是否禁用，1禁用|0不禁用
                items: newItems
            }
            var checkboxes = new Checkboxes(checkboxesConfig);


            //修改或添加相似时，为控件赋初值
            if(data.isUpdate==false){
            }
            else{
                    //为模板名称控件赋初值
                $("input[name='consultTempletNm']").val(data.tmpltNm);
                    //根据是否为全选状态为复选框赋值
                var arr = data.authRegn.split(",");
                if(arr.length==provnceLength+1){                //加1指全国复选框
                    checkboxes.checkAll();
                }else{
                    if(data.authRegn){
                        checkboxes.set(data.authRegn);
                    }
                }
            }


            //校验
            var validatorConfig = {
                el: $("#addForm"),     //要验证的表单或表单容器
                dialog:true,    //是否弹出验证结果对话框,默认false
                pattern: {
                    noBrackets: "^[^<>]*$"
                },
                rules:{//配置指定name的校验规则
                    consultTempletNm:'required|max-255|noBrackets'     //设置name为consultTempletNm为必填且max长度为255
                },
                messages:{//配置指定校验规则，校验失败时的提示信息
                    consultTempletNm:{
                        noBrackets: "名称不能包含\'<\',\'>\'"
                    }
                }
            };
            var validator = new Validator(validatorConfig);
        }

        return initialize;

    });
