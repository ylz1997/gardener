define(['Util', 'public/src/js/dialog/dialog', 'js/constants/constants','groupSearchForm', 'list',
        'js/knowledgeManage/docRespPrsn', 'js/consult/addConsult', 'crossAPI'],
    function(Util, Dialog, Constants, GroupSearchForm, List, SelectUser, AddConsult, crossAPI){
        var groupSearchForm;
        var list;
        var userInfo;
        /**
         * 用于修剪字符串 兼容ie8
         *
         * @param str
         * @returns {XML|string|void|*}
         */
/*        var trim = function (str) {
            if (str) {
                return str.replace(/(^\s*)|(\s*$)/g, "");
            }else{
                return "";
            }
        };*/

        //获取用户信息
/*        var getUserInfo = function(){
            Util.ajax.getJson(Constants.AJAXURL + "/user/session",{},function(data){
                if(data.returnCode == 0){
                    userInfo = {};
                    userInfo.value = data.bean.provnce;
                    Util.ajax.postJson(Constants.AJAXURL + "/kmConfig/getCacheCityByRegnId", {regnId: userInfo.value}, function(data){
                        if(data.returnCode == 0){
                            if(data.bean){
                                userInfo.name = data.bean.regnNm;
                            }else{
                                userInfo.name = userInfo.value;
                            }
                        }else{
                            userInfo.name = userInfo.value;
                        }
                    }, true);
                }else{
                    new Dialog({
                        mode: 'tips',
                        tipsType: 'error',
                        content: data.responseJSON.message
                    });
                }
            },true);
        };*/

/*        var formatRegn = function(regnId, $src){
            var regnId = regnId;
            if(!regnId){
                return "";
            }
            Util.ajax.postJson(Constants.AJAXURL + '/kmConfig/getRegnNmsByIds', {ids: regnId}, function (data) {
                if (data.returnCode == 0) {
                    if (data.object && data.object != "null" && JSON.stringify(data.object) != "{}") {
                        regnId = data.object;
                        $src.html('<span title="' + regnId + '">' + regnId + '</span>');
                    }else{
                        $src.html('<span title="' + regnId + '">' + regnId + '</span>');
                    }
                }
            });
        };*/

/*        var formatUser = function(staffCode, $src){
            var userName = staffCode;
            if(!userName){
                return "";
            }
            Util.ajax.getJson(Constants.AJAXURL + '/kmGroup/getTramsName', {emapvPrsnId: userName}, function (data) {
                if (data.returnCode == 0) {
                    if (data.bean && data.bean != "null") {
                        userName = data.bean;
                        $src.html('<span title="' + userName + '">' + userName + '</span>');
                    }else{
                        $src.html('<span title="' + userName + '">' + userName + '</span>');
                    }
                }
            });
        };*/

        var searchList = function(){
            var param = {};
            var cnslNm = trim($("input[name=cnslNm]").val());
            if(cnslNm){
                param.cnslNm = cnslNm;
            }
            var regnId = trim($("select[name=regnId]").val());
            if(regnId){
                param.regnId = regnId;
            }
            var opPrsnId = trim($("input[name=opPrsnId]").val());
            if(opPrsnId){
                param.opPrsnId = $("input[name=opPrsnId]").attr("data");
            }
            var startTime = trim($("input[name=startTime]").val());
            if(startTime){
                param.startTime = startTime;
            }
            var endTime = trim($("input[name=endTime]").val());
            if(endTime){
                param.endTime = endTime;
            }

            list.search(param);
        };

        var formConfig = {
            el: $("#form-contain"),
            column: 3,
            items: [
                {
                    element: 'input',
                    label: '咨询表名称',
                    name: 'cnslNm'
                },{
                    element: 'select',
                    label: '地区',
                    name: 'regnId',
                    config: {
                        value: 0
                    }
                },{
                    element: 'input',
                    label: '最近采编人',
                    name: 'opPrsnId',
                    config:{
                        attr:{
                            //placeholder: '请输入采编人',
                            readonly: true
                        }
                    }
                },{
                    element: 'doubleDate',
                    label: ['开始时间', '结束时间'],
                    config: [{
                        type: "datetime",
                        format: 'yyyy-MM-dd HH:mm:ss',
                        clearFunc: function(){
                            groupSearchForm.options.items[3].compt[1].options.min = '2000-01-01 00:00:00';
                        },
                        done: function(dates){
                            groupSearchForm.options.items[3].compt[1].options.min = dates;
                        }
                    }, {
                        type: "datetime",
                        format: 'yyyy-MM-dd HH:mm:ss',
                        clearFunc: function(){
                            groupSearchForm.options.items[3].compt[0].options.max = '2099-01-01 00:00:00';
                        },
                        done: function(dates){
                            groupSearchForm.options.items[3].compt[0].options.max = dates;
                        }
                    }],
                    name: ['startTime', 'endTime']
                }
            ],
            button: {
                position: 'right',
                config: {
                    items: [
                        {
                            text: '查询',
                            type: '1',
                            click: searchList
                        }, {
                            text: '重置',
                            type: '0',
                            click: function () {
                                groupSearchForm.reset();
                                groupSearchForm.options.items[3].compt[0].options.max = '2099-01-01 00:00:00';
                                groupSearchForm.options.items[3].compt[1].options.min = '2000-01-01 00:00:00';
                            }
                        }
                    ]
                }
            }
        };

        var listConfig = {
            el: "#list-contain",
            ajax: {
                method: 'GET'
            },
            field: {
                boxType: 'radio',
                key: 'cnslId',
                items: [
                    {text: '咨询表名称', name: 'cnslNm', width: '20%', title: 'cnslNm',
                        render: function(item, val, $src){
                            return '<a href="javascript: void(0)" class="link-consult link-blue" tmpltId="' + item.tmpltId + '" cnslId="' + item.cnslId + '">' + val + '</a>';
                        }
                    },
                    {text: '地区', name: 'regnId', width: '10%',
                        render: function (item, val, $src) {
                            formatRegn(val, $src);
                        }
                    },
                    {text: '模板', name: 'tmpltId', width: '12%',
                        render: function(item, val, $src){
                            Util.ajax.getJson(Constants.AJAXURL + "/consultTmplt/getTmplt/" + val,{}, function(data){
                                if(data.returnCode == 0 && data.object){
                                    $src.html("<span title='" + data.object.tmpltNm + "'>" + data.object.tmpltNm + "</span>");
                                }else{
                                    $src.html(val);
                                    $src.title = val;
                                }
                            });
                        }
                    },
                    {text: '编辑时间', name: 'modfTime', width: '15%', title: 'modfTime'},
                    {text: '最近采编人', name: 'opPrsnId', width: '10%',
                        render: function (item, val, $src) {
                            formatUser(val, $src);
                        }
                    }
                ],
                button: {
                    text: '操作',
                    width: "15%",
                    render: function (e, item) {
                        var html = '<a cnslId="' + item.cnslId + '" href="javascript:void(0);" class="link-blue consult-edit">修改</a>' +
                            '<span class="color-ccc">|</span>' +
                            '<a tmpltId="' + item.tmpltId + '" href="javascript:void(0);" class="link-blue consult-download">下载模板</a>' +
                            '<span class="color-ccc">|</span>' +
                            '<a cnslId="' + item.cnslId + '" href="javascript:void(0);" class="link-blue consult-delete">删除</a>';
                        return html;
                    }
                }
            },
            page: {//分页配置
                customPages: [5, 10, 20, 50],
                perPage: 10,
                total:true,
                align: 'right'
            },
            data: {
                url: Constants.AJAXURL + "/kmConsult/getConsultList"
            }
        };

        var eventInit = function(){
            $("input[name=opPrsnId]").click(function (e) {
                //点击清空时  不触发弹出事件
                var maxX = $(this).offset().left + $(this).innerWidth() - 25;
                if(e.pageX > maxX&&$(this).hasClass('clear-bg')) {
                    $(this).val('');
                    $(this).attr("data", "");
                    return;
                }
                new SelectUser(this);
            });

            $("#btn-add").click(function(){
                new AddConsult(searchList);
            });
        };

        var deleteConsult = function(cnslId){
            var confirmDialog = new Dialog({
                mode: 'confirm',
                title: '删除提示',
                content: '确认删除该条记录?'
            });

            confirmDialog.on("confirm", function(){
                Util.ajax.postJson(Constants.AJAXURL + "/kmConsult/deleteConsult", {cnslId: cnslId}, function(data){
                    if(data.returnCode == 0){
                        new Dialog({
                            mode: 'tips',
                            tipsType: 'success',
                            content: "删除成功"
                        });

                        searchList();
                    }else{
                        new Dialog({
                            mode: 'tips',
                            tipsType: 'error',
                            content: data.responseJSON.message
                        });
                    }
                }, true);
            });
        };

        var initialize = function(){
            getUserInfo();
            formConfig.items[1].datas = [userInfo];
            groupSearchForm = new GroupSearchForm(formConfig);
            list = new List(listConfig);
            list.search();
            list.on("afterBuild", function(){
                $(".consult-edit").click(function(){
                    new AddConsult(searchList, $(this).attr("cnslId"));
                });
                $(".consult-download").click(function(){
                    window.location.href = Constants.AJAXURL + "/kmConsult/downldConsult?cnsultTmpltId="+$(this).attr("tmpltId")+"&v=" + new Date().getTime();
                });
                $(".consult-delete").click(function(){
                    deleteConsult($(this).attr("cnslId"));
                });

                $(".link-consult").click(function(){
                    var _root = window.location.protocol + "//" + window.location.host;
                    var cnslId = $(this).attr("cnslId");
                    var tmpltId = $(this).attr("tmpltId");
                    crossAPI.destroyTab("咨询表记录");
                    crossAPI.createTab('咨询表记录', _root + Constants.PREAJAXURL +  '/src/modules/consult/consultDataManageList.html?cnslId=' + cnslId + '&tmpltId=' + tmpltId);
                });
            });
            eventInit();
        };

        return initialize();
    });