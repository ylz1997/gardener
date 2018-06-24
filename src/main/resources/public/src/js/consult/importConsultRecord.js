define(['Util', 'dialog',/* 'js/constants/constants', */'text!src/consult/importConsultRecord.tpl',
    /* 'validator', 'js/consult/selectKnowledge', 'upload','loading'*/], function(Util, Dialog,/* Constants, */ImportConsultRecordTpl,/* Validator, SelectKnowledge, Upload,Loading*/ ){

    var initialize = function(tmpltId, cnslId, list){
        var knwlgId="";
        var dataArrays="";
        var loading;
        /**
         * 用于修剪字符串 兼容ie
         * @param str
         * @returns {XML|string|void|*}
         */
        /*var trim = function (str) {
            if (str) {
                return str.replace(/(^\s*)|(\s*$)/g, "");
            }else{
                return "";
            }
        };*/
        var loadingConfig = {
            el:'.ke-wrapper',                  //组件要绑定的容器，默认为body（此项可不配置或留空）
            position:'center',      //提示信息位置，顶部top|默认center中央
            width:'200',      //loading的宽度,非必须，默认300
            height:'auto',      //loading的宽度,非必须，默认auto
            mask:1,                 //是否显示遮罩， 0不显示|默认1显示
            animate:1,              //是否显示动画效果， 0不显示|默认1显示
            mode:'layer',     //展示方式 loadingLine线条方式|默认layer弹层方式
            text:'努力导入中...',       //提示文字，默认 加载中...
            icon:'dotCycle'  //文字前面的gif动画， 默认dotCycle有点组成的圈|cmcc移动图标|cmccLarge大的移动图标
        };
        var ok = function () {
            debugger;
            if(dataArrays == "") {
                new Dialog({
                    mode: 'tips',
                    maxWidth:'300px',
                    content: '请上传需要导入的文件！'
                });
                return false;
            }
            knwlgId= $('#fm02').attr('knwlgid');
            debugger
            if(knwlgId!="" && knwlgId!=undefined){
                isFlag=false;
                var confirmConfig = {
                    mode: 'confirm',
                    id: 'confirmId',
                    content: '当前关联知识会覆盖上传文件中的所有关联知识，您确定要覆盖吗？'
                }
                var confirmDialog = new Dialog(confirmConfig);
                confirmDialog.on('confirm',function(){
                    loading = new Loading(loadingConfig);
                    var param = {knwlgId: knwlgId, dataArrays: JSON.stringify(dataArrays), tmpltId:tmpltId, cnslId:cnslId};
                    Util.ajax.postJson(Constants.AJAXURL + "/consultData/iportConsultRecord", param, function (result){
                        loading.destroy();
                        debugger
                        if (result.returnCode =="0") {
                            new Dialog({
                                mode: 'tips',
                                tipsType: 'success',
                                maxWidth: '300px',
                                content: result.message
                            });
                        }else {
                            new Dialog({
                                mode: 'tips',
                                tipsType:'error',
                                maxWidth:'300px',
                                content: data.result.message
                            });
                        }
                    });
                })
            }else {
                loading = new Loading(loadingConfig);
                var param = {knwlgId: knwlgId, dataArrays: JSON.stringify(dataArrays), tmpltId:tmpltId, cnslId:cnslId};
                Util.ajax.postJson(Constants.AJAXURL + "/consultData/iportConsultRecord", param, function (result){
                    loading.destroy();
                    debugger
                    if (result.returnCode =="0") {
                        new Dialog({
                            mode: 'tips',
                            tipsType: 'success',
                            maxWidth: '300px',
                            content: result.message
                        });
                    }else {
                        new Dialog({
                            mode: 'tips',
                            tipsType:'error',
                            maxWidth:'300px',
                            content: data.result.message
                        });
                    }
                });
            }
        };
        var dialogConfig = {
            content: ImportConsultRecordTpl,
            title: '导入记录',
            width: 600,
            height: 130,
            modal: true,
            ok:ok,
            okValue: '确定',
            cancel: function(){
                return true;
            },
            cancelValue: '取消'
        };

        new Dialog(dialogConfig);
        //模板选择
        var config = {
            el: '#upload',
            className:'upload',
            paramName:'filesUpload',
            url: '/consultData/fileUpload?tmpltId='+tmpltId };
        /*var filesUpload = new Upload(config);

        //上传成功
        filesUpload.on('done',function(e,data){
            debugger
            $(".fileInput").css("display","none");
            var returnCode = data.result.returnCode;
            debugger
            if(returnCode=="0"){
                dataArrays=data.result.object;
            }
        });
        //上传失败
        filesUpload.on('fail',function(e,data){
            debugger
            $('.files').html('');
            new Dialog({
                mode: 'tips',
                tipsType:'error',
                maxWidth:'300px',
                content: data.result.message
            });
        });

        //删除附件
        filesUpload.on('remove',function(e,data){
            $(".fileInput").css("display","block");
        });
        */
        //关联知识
        $("#add-knwlg").click(function(){
           /* new SelectKnowledge($(this).parent().prev());*/
        });
        //清空关联知识
        $("#clean-knwlg").click(function(){
            $('#fm02').val("");
            $(this).parent().prev().attr("knwlgId",'');
        });

    };

    return initialize;
});