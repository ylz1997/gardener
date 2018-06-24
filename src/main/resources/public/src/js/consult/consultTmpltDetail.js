require([
    'js/constants/constants',
    'list',
    'public/src/js/dialog/dialog',
    'Util',
    'select',
    'groupSearchForm',
    'counter',
    'js/consult/addConsultTmplt', 'crossAPI'
],function(Constants, List, Dialog, Util, Select, GroupSearchForm, Counter,AddConsultTmplt, crossAPI) {
    var tmpltId;
    var dataType;
    var isFilter;
    var isRequired;
    var groupSearchForm = null;
    var list;
    var regnIds;
    var tmpltNm;
    var _root = window.location.protocol + "//" + window.location.host;
    // 序列化url查询参数
    function serilizeUrl(url) {
        var result = {};
        // url = url.split("?")[1];
        var map = url.split("&");
        for(var i = 0, len = map.length; i < len; i++){
            result[map[i].split("=")[0]] = map[i].split("=")[1];
        }
        return result;
    }

    var searchURL = decodeURI(window.location.search);
    searchURL = searchURL.substring(1, searchURL.length);
    // 参数序列化
    var searchData = serilizeUrl(decodeURI(searchURL));
    tmpltId = searchData.tmpltId;
    if(!tmpltId){
        return;
    }

    var postSeqnoCall = function (item, argeSeqno) {
        if(isNaN(argeSeqno)||argeSeqno>32767||argeSeqno<-32768){
            new Dialog({//提示
                mode: 'tips',
                tipsType:"error",
                content: '输入数值异常!'
            });
            return false;
        }
        Util.ajax.postJson(Constants.AJAXURL + "/consultTmplt/modifyTmpltColmArgeSeqno", {colmId:item.colmId, argeSeqno:argeSeqno}, function (data) {
            if(data.returnCode == 0){
                new Dialog({
                    mode:"tips",
                    tipsType:"success",
                    content:"保存成功"
                })
                list.search();
            }
            else{
                new Dialog({
                    mode:"tips",
                    tipsType:"error",
                    content:data.responseJSON.message
                })
            }
        },true)
    }
    //加载模板名称和地区
    Util.ajax.getJson(Constants.AJAXURL + '/consultTmplt/getTmplt/' + tmpltId,
        function (data) {
            if(data && data.object){
                if(data.object.tmpltNm){
                    tmpltNm = data.object.tmpltNm;
                    $("#tmpltNm").html(data.object.tmpltNm);
                    $("#tmpltNm").attr("title", data.object.tmpltNm);
                }
                if(data.object.authRegn){
                    regnIds = data.object.authRegn;
                    var regnNm = "";
                    Util.ajax.getJson(Constants.AJAXURL + "/consultTmplt/transDistrictNm/" + regnIds , function (data) {
                        if(data.object){
                            $("#authRegn").html(data.object);
                        }
                    })
                } else {
                    regnIds = "";
                }
            }
        });

    var listConfig = {
        el:'#listResult',  //要绑定的容器
        ajax:{    //所有jquery.ajax的配置项均可配置，这些配置将替换底层组件的配置；慎用
            method:'GET'
        },
        field:{     //字段设置
            //boxType:'checkbox',     //行类型，checkbox复选框|radio单选(以选中行特殊颜色标识单选，无单选框)
            key:'colmId',           //主键，必须设置该项
            items:[     //列设置，必须设置该项
                {
                    text:'字段名称', //列文本设置
                    name:'colmNm',    //列字段设置
                    title:'colmNm',     //鼠标移入该单元格，要显示内容的字段
                    className:'w120',   //列classs属性
                    width:100      //设置该列的宽度
                },
                {
                    text:'字段类型', //列文本设置
                    name:'colmTypeCd',    //列字段设置
                    className:'w120',   //列classs属性
                    width:100,      //设置该列的宽度
                    render:function(item,val,$src){
                        for(var i in dataType){
                            if(val == dataType[i].value){
                                return dataType[i].name;
                            }
                        }
                    }
                },
                {
                    text:'排序号',
                    name:'argeSeqno',
                    width:100,
                    render:function (item,val,$src) {
                        var myCounter = new Counter({
                            el:$src, //要绑定的容器
                            label:'', //步进器左侧label文本
                            labelWidth:'60px', //label的宽度，默认"30%"
                            max:32767, //第一个步进器最大值，默认无
                            min:-32768  //第一个步进器最小值，默认无
                        });
                        myCounter.set(item.argeSeqno)
                        $src.find("input").change(function() {
                            postSeqnoCall(item, $(this).val());
                        })

                        $src.find("div.sn-btn.sn-addBtn").on("click", function(){
                            postSeqnoCall(item, $(this).parent().siblings("div").children("input").val());
                        });
                        $src.find("div.sn-btn.sn-minusBtn").on("click", function(){
                            postSeqnoCall(item, $(this).parent().siblings("div").children("input").val());
                        });

                    }
                },
                {
                    text:'表达式', //列文本设置
                    name:'tsvldRegexp',    //列字段设置
                    title:'tsvldRegexp',     //鼠标移入该单元格，要显示内容的字段
                    className:'w120',   //列classs属性
                    width:100      //设置该列的宽度
                },
                {
                    text:'必填', //列文本设置
                    name:'requiredFlag',    //列字段设置
                    className:'w120',   //列classs属性
                    width:100,      //设置该列的宽度
                    render:function(item,val,$src){
                        for(var i in isRequired){
                            if(val == isRequired[i].value){
                                return isRequired[i].name;
                            }
                        }
                    }
                },
                {
                    text:'筛选项', //列文本设置
                    name:'fltrCondFlag',    //列字段设置
                    className:'w120',   //列classs属性
                    width:100,      //设置该列的宽度
                    render:function(item,val,$src){
                        for(var i in isFilter){
                            if(val == isFilter[i].value){
                                return isFilter[i].name;
                            }
                        }
                    }
                },
                {
                    text:'字段描述', //列文本设置
                    name:'colmDesc',    //列字段设置
                    title:'colmDesc',     //鼠标移入该单元格，要显示内容的字段
                    className:'w120',   //列classs属性
                    width:100      //设置该列的宽度
                },
                {
                    text:'操作', //列文本设置
                    name:'colmDesc',    //列字段设置
                    className:'w120',   //列classs属性
                    width:100,      //设置该列的宽度
                    render:function(item,val,$src){  //重写列表展示
                        return '<a class="link-blue modifyBtn" href="javascript:void(0)" colmid='+ item.colmId + '>修改</a>' +
                            '<span class="color-ccc">|</span>' +
                            '<a class="link-blue deleteBtn" href="javascript:void(0)" colmid="'+ item.colmId + '" colmnm="' + item.colmNm + '">删除</a>';
                    }
                }
            ]
        },
        page:{  //分页设置
            // noPages: true, // 设置是否配置分页器，枚举值true | false;设为true时，不配置分页器，默认值false
            customPages:[5,10,20,50],     //可选择每页显示多少条
            perPage:10,     //默认每页显示多少条记录
            align: 'right'  //是否显示总记录数
        },
        data:{  //数据源设置
            url:Constants.AJAXURL+'/consultTmplt/getTmpltColumnList?tmpltId='+tmpltId //数据接口路径
        }
    }

    //取数据字典
    Util.ajax.ajax({
        type: "GET",
        url: Constants.AJAXURL + '/kmConfig/getDataByCode?codeTypeCd=' + "NGKM.CONSULT.DATETYPE" + '&v=' + new Date().getTime(),
        async: false,
        success: function (data) {
            if(data.object!=null){
                dataType = data.beans;
            }
        }
    });
    //取数据字典
    Util.ajax.ajax({
        type: "GET",
        url: Constants.AJAXURL + '/kmConfig/getDataByCode?codeTypeCd=' + "NGKM.CONSULT.ISFILTER" + '&v=' + new Date().getTime(),
        async: false,
        success: function (data) {
            if(data.object!=null){
                isFilter = data.beans;
            }
        }
    });
    //取数据字典
    Util.ajax.ajax({
        type: "GET",
        url: Constants.AJAXURL + '/kmConfig/getDataByCode?codeTypeCd=' + "NGKM.CONSULT.ISREQUIRED" + '&v=' + new Date().getTime(),
        async: false,
        success: function (data) {
            if(data.object!=null){
                isRequired = data.beans;
            }
        }
    });

    var config_dialog = {
        mode: 'normal', //弹窗模式，可选：normal标准弹窗、tips提示弹窗、confirm确认弹窗；默认normal；
        title: '新增字段', //弹窗标题；
        content: "<div id=\"newTmpltColumnForm\">\n</div>", //弹窗内容，normal弹窗中，可以是string、html代码段,tpl对象。默认为loading状态（由点组成的圈。大段代码建议使用 tpl。tips弹窗和confirm弹窗中只能是string。
        ok: function() {
            return saveOrUpdateColm();
        }, // 点击确定按钮的回调函数，可为空。如果指定 return false 则执行完方法后不关闭弹窗，默认 return true。若不配置该项，则不会出现确定按钮。
        okValue: '确定', //确定按钮的文本 默认是 ‘ok’
        cancel: function() {
            groupSearchForm = null;
            return true;
        }, // 点击取消按钮的回调函数，可为空。如果指定 return false 则执行完方法后不关闭弹窗，默认 return true。若不配置该项，则不会出现取消按钮。
        cancelValue: '取消', //取消按钮的文本 默认是 ‘关闭’
        cancelDisplay: true, //是否显示取消按钮 默认true显示|false不显示
        width: 600, //弹窗宽度，默认值为600
        height: 300, //弹窗高度，默认值为400
        padding:'0 16px 1em 10px',//(默认值: 继承 css 文件设置) 设置消息内容与消息容器的填充边距，即 style padding属性；仅在normal标准弹窗中生效；
        align: 'bottom left',//设置弹窗与其他元素的对齐方式。仅在show(elem)与showModal(elem)传入元素时生效。默认值: "bottom left"。可选："top left" "top" "top right" "right top" "right" "right bottom" "bottom right" "bottom" "bottom left" "left bottom" "left" "left top"。
        //skin: 'dialogSkin dialogSkin2',//设置弹窗额外的className参数,多个className请使用空格隔开。
        //fixed: false, //是否开启固定定位 normal标准弹窗默认false不开启；confirm弹窗默认状态为true开启
        //quickClose: false, //点击空白处快速关闭 默认false不关闭|true关闭
        modal: true ,//是否开启模态框状态，即背景遮罩。默认false不开启,confirm弹窗默认状态为true
        backdropBackground:'#000',//设置遮罩颜色，默认 #000。
        backdropOpacity:0.4, //设置遮罩透明度，默认 0.4，取值范围：0~1 。
        //beforeOpen: function() {console.log('弹窗打开时执行。')}, //弹窗打开之前执行。
        escClose:true,//esc键快速关闭弹窗，默认为true
        zIndex:990   // 弹窗的z-index值，默认是1024。
    }

    var saveOrUpdateColm = function () {
        if(!groupSearchForm.validator.form()){
            return false;
        }
        else{
            var callBakResult;
            var dataPush = $.extend(groupSearchForm.getValue(),{tmpltId:tmpltId, colmId:$("#colmId").val()});
            if($("#crtTime").val()){
                dataPush = $.extend(dataPush, {crtTime: $("#crtTime").val()});
            }
                Util.ajax.postJson(Constants.AJAXURL + '/consultTmplt/saveOrUpdateKeys', dataPush, function (data) {
                    if(data && data.returnCode){
                        if(data.returnCode == 0){
                            new Dialog({
                                mode: 'tips',
                                tipsType: 'success',
                                content: '提交成功!'
                            });
                            groupSearchForm = null;
                            callBakResult = true;
                            list.search();
                        }
                        else{
                            new Dialog({
                                mode: 'tips',
                                tipsType: 'error',
                                content: data.responseJSON.message
                            });
                            callBakResult = false;
                        }
                    }
                },true);
                return callBakResult;
        }
    }

    $(document).ready(function(){

        var showDialog = function (tmpltKeys) {
            if(groupSearchForm){
                return;
            }
            new Dialog(config_dialog);
            var grpSearchFormConfig = {
                el: '#newTmpltColumnForm', // * 要绑定的容器
                column: 2, //表单文本框的列数(每一行表单项的个数)，枚举值：2、3、4，默认4。
                advancedQuery: -1, //启用高级查询,items的index(从1开始)大于该数字的item会被隐藏；默认-1不启用
                items: [ // 表单属性信息 及页面显示顺序
                    {
                        element: 'input',
                        label: '<i style="color:red;padding:2px;">*</i>字段名称', // 输入框label（必须）
                        name: 'colmNm', //该表单元素的name，对应getDate方法获取到的json数据的名（必须）
                        validator: {
                            rules: ["required","noBrackets","max-255"],
                            messages: [,"不能包含\"<\"、\">\"","最大长度不能超过255"]
                        },
                        config:{
                            attr: {
                                value: tmpltKeys?tmpltKeys.colmNm:""
                            }
                        }
                    },
                    {
                        element: 'select',
                        label: '<i style="color:red;padding:2px;">*</i>字段类型', // 输入框label（必须）
                        name: 'colmTypeCd',
                        datas: dataType,
                        config:{
                            disabled: tmpltKeys?true:false,
                            value: tmpltKeys?tmpltKeys.colmTypeCd:0
                        }
                    },
                    {
                        element: 'input', //子组件类型（必须）
                        label: '<i style="color:red;padding:2px;">*</i>字段编码', // 输入框label（必须）
                        name: 'colmCode', //该表单元素的name，对应getDate方法获取到的json数据的名（必须）
                        validator: {
                            rules: ["required","noBrackets","max-40"],
                            messages: [,"不能包含\"<\"、\">\"","最大长度不能超过40"]
                        },
                        config:{
                            attr: {
                                value: tmpltKeys?tmpltKeys.colmCode:""
                            }
                        }
                    },
                    {
                        element: 'input', //子组件类型（必须）
                        label: '表达式<a class="lk-zhujie" href="javascript:void(0)"><i class="icon km-zhujie"></i></a>', // 输入框label（必须）
                        name: 'tsvldRegexp', //该表单元素的name，对应getDate方法获取到的json数据的名（必须）
                        validator: {
                            rules: ["noBrackets","max-255"],
                            messages: ["不能包含\"<\"、\">\"","最大长度不能超过255"]
                        },
                        config:{
                            attr: {
                                value: tmpltKeys?tmpltKeys.tsvldRegexp:""
                            }
                        }
                    },
                    {
                        element: 'input', //子组件类型（必须）
                        label: '提示语', // 输入框label（必须）
                        name: 'hintCntt', //该表单元素的name，对应getDate方法获取到的json数据的名（必须）
                        validator: {
                            rules: ["noBrackets","max-4000"],
                            messages: ["不能包含\"<\"、\">\"","最大长度不能超过4000"]
                        },
                        config:{
                            attr: {
                                value: tmpltKeys?tmpltKeys.hintCntt:""
                            }
                        }
                    },
                    {
                        element: 'select', //子组件类型（必须）
                        label: '<i style="color:red;padding:2px;">*</i>是否必填', // 输入框label（必须）
                        name: 'requiredFlag',
                        config:{
                            value: tmpltKeys?tmpltKeys.requiredFlag+"":0
                        },
                        datas: isRequired
                    },
                    {
                        element: 'select', //子组件类型（必须）
                        label: '<i style="color:red;padding:2px;">*</i>是否为筛选项', // 输入框label（必须）
                        name: 'fltrCondFlag',
                        config:{
                            value: tmpltKeys?tmpltKeys.fltrCondFlag+"":0
                        },
                        datas: isFilter
                    },
                    {
                        element: 'textarea', //子组件类型（必须）
                        label: '字段描述', // 输入框label（必须）
                        name: 'colmDesc',
                        colspan: "2",
                        validator: {
                            rules: ["noBrackets","max-4000"],
                            messages: ["不能包含\"<\"、\">\"","最大长度不能超过4000"]
                        }
                        //max-255
                        /*config:{
                            value: tmpltKeys?tmpltKeys.colmDesc:""
                        }*/
                    },
                    {
                        element: 'any', //自定义的子组件，允许用户往组件中插入任意结构（必须）。和其他子组件不同,它只接受这4个配置项,且不会被组件的get/set等方法覆盖到。
                        content: "<input type=\"hidden\" id='colmId'>",
                        label: '', //非必须
                        colspan: '0' //该组件所占列数，非必须，默认1
                    },
                    {
                        element: 'any', //自定义的子组件，允许用户往组件中插入任意结构（必须）。和其他子组件不同,它只接受这4个配置项,且不会被组件的get/set等方法覆盖到。
                        content: "<input type=\"hidden\" id='crtTime'>",
                        label: '', //非必须
                        colspan: '0' //该组件所占列数，非必须，默认1
                    }
                ]
            };
            groupSearchForm = new GroupSearchForm(grpSearchFormConfig);
            groupSearchForm.validator.addMethod("noBrackets",function (str) {
                return new RegExp("^[^<>]*$").test(str);
            });
            groupSearchForm.child("colmTypeCd").on("change", function(e,valueObj) {
                if(valueObj.value == "rel" || valueObj.value == "date"){
                    $("#newTmpltColumnForm").find("input[name=tsvldRegexp]").attr("disabled", true);
                }else{
                    $("#newTmpltColumnForm").find("input[name=tsvldRegexp]").attr("disabled", false);
                }
            });
            /**
             * 直接配置textarea的config不起作用，没办法，这么写好了
             */
            if(tmpltKeys){
                $("textarea[name='colmDesc']").val(tmpltKeys.colmDesc);
                $("#colmId").val(tmpltKeys.colmId);
                $("#crtTime").val(tmpltKeys.crtTime);
                $("input[name='colmCode']").attr("disabled","disabled");
            }
            $(".lk-zhujie").click(function () {
                crossAPI.createTab('常用表达式', _root + Constants.PREAJAXURL +  "/src/modules/consult/regexpStaticPage.html");
                //window.open(Constants.PREAJAXURL + "/src/modules/consult/regexpStaticPage.html")
            })
        }

        $("#newColmBtn").click(function () {
            showDialog();
        })
        //修改模板名称地区
        $("#updateTmpltInfoBtn").click(function () {
            var transParam = {
                isUpdate:true,
                authRegn: regnIds,
                tmpltId : tmpltId,
                tmpltNm: tmpltNm
            };
            new AddConsultTmplt(transParam);

        });



        list = new List(listConfig);
        list.search();
        list.on('afterBuild',function(result){
            $("input.sn-counterContent").css("margin-left", "1px");
            $(".modifyBtn").click(function () {
                var colmId = $(this).attr("colmid");

                Util.ajax.getJson(Constants.AJAXURL + '/consultTmplt/getTmpltColumn',
                    {colmId: colmId},
                    function (data) {
                        if(data && data.object){
                            if(data.object) {
                                showDialog(data.object);
                            }
                        }
                    });

            })
            $(".deleteBtn").click(function () {
                var colmNm = $(this).attr("colmnm");
                var colmId = $(this).attr("colmId");
                new Dialog({
                    mode: 'confirm',
                    content: '是否删除' + colmNm,
                    cancelDisplay: true,
                    ok:function () {
                        Util.ajax.postJson(Constants.AJAXURL + '/consultTmplt/deleteTmpltColm',
                            {colmId: colmId},
                            function (data) {
                                if(data && data.returnCode){
                                    if(data.returnCode == 0) {
                                        new Dialog({
                                            mode:"tips",
                                            tipsType:"success",
                                            content:"成功"
                                        })
                                        list.search();
                                        return true;
                                    }
                                    else{
                                        new Dialog({
                                            mode:"tips",
                                            tipsType:"error",
                                            content:data.responseJSON.message
                                        })
                                    }

                                }
                            });
                    }
                });
            })
        });
    });
})
