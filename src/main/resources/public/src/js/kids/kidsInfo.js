define(['dialog',
    'text!src/kids/kidsInput.tpl',
    'jquery',
    'datatables',
    'hdb'
],function (Dialog,
            KidsInput,
            $,
            DataTables,
            Hdb) {
    var table;
    var sexParam = {};
    var relation = {};
    var classes = {};

    $.ajax({
        url:"/SysPara/getParamByCode",
        method:"GET",
        data:{code:"sex"},
        success:function (callData) {
            callData = JSON.parse(callData);
            if(callData.result == true){
                sexParam = callData.param;
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

    $.ajax({
        url:"/SysPara/getParamByCode",
        method:"GET",
        data:{code:"relation"},
        success:function (callData) {
            callData = JSON.parse(callData);
            if(callData.result == true){
                relation = callData.param;
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

    $.ajax({
        url:"/SysPara/getParamByCode",
        method:"GET",
        data:{code:"class"},
        success:function (callData) {
            callData = JSON.parse(callData);
            if(callData.result == true){
                classes = callData.param;
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

    //注册一个判断相等的Helper,判断v1是否等于v2
    Hdb.registerHelper("equal",function(v1,v2,options){
        if(v1==v2){
            //满足添加继续执行
            return options.fn(this);
        }else{
            //不满足条件执行{{else}}部分
            return options.inverse(this);
        }
    });
    var exchangeDataDic = function (dataDic, data) {
        for(var i=0; i < dataDic.length; i++){
            if(dataDic[i].value == data){
                return dataDic[i].name;
            }
        }
        return data;
    }
    var genOperation = function (row) {
        var html = "<a class='modifyBtn' href='javascript:void(0)' kId='" + row.kId + "'>修改</a>";
        return html;
    }
    var getParam = function () {
        var param = {};
        $(".searchParam").each(function () {
            var id = $(this).attr("id");
            var value = $(this).val();
            param[id] = value;
        });
        return param;
    }
    var search = function () {
        table.ajax.reload();
    }
    var tableInit = function () {
        table = $("#list-contain").DataTable( {
            lengthChange: true,
            lengthMenu: [ 10, 25, 50, 75, 100 ],
            searching: false,
            serverSide: true,
            ajax: {
                "url": "/Kids/list",
                "data": function ( d ) {
                    return $.extend( {}, d, getParam() );
                }
            },
            columns: [
                {data: 'kId', title:"人员编号"},
                {data: 'chNm', title:"中文名"},
                {data: 'enNm', title:"英文名"},
                {data: 'sex', title:"性别",render: function (data, type, row, meta) {
                    return exchangeDataDic(sexParam, data);
                }},
                {data: 'pNm', title:"家长姓名"},
                {data: 'pRelation', title:"家长关系",render: function (data) {
                    return exchangeDataDic(relation, data);

                }},
                {data: 'phone', title:"联系方式"},
                {data: 'address', title:"家庭住址"},
                {data: 'classId', title:"所在班级", render: function (data) {
                    return exchangeDataDic(classes, data);
                }},
                {data: 'crtTime', title:"创建时间"},
                {data: 'modfTime', title:"修改时间"},
                {
                    data: 'cnslColmId',
                    title: "操作",
                    render: function (data, type, row, meta) {
                        return genOperation(row);
                    }
                }
            ]
        });
        table.on( 'draw', function () {
            $(".modifyBtn").click(function () {
                var kId = $(this).attr("kId");
                $.ajax({
                    url:"/Kids/get",
                    method:"POST",
                    data:{kId:kId},
                    success:function (data) {
                        data = JSON.parse(data);
                        if(data.result == true){
                            var tmp = Hdb.compile(KidsInput);
                            data.sexParam = sexParam;
                            data.relation = relation;
                            data.classes = classes;
                            var html = tmp(data);
                            new Dialog(
                                {
                                    mode:"confirm",
                                    id:"kidsInput",
                                    content:html,
                                    title:"修改学生录入",
                                    ok:function () {
                                        var params = new Object();
                                        $(".kidsClz").each(function(){
                                            params[$(this).attr("name")] = $(this).val();
                                        })
                                        $.ajax({
                                            url:"/Kids/edit",
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
                                                    search();
                                                }
                                            },
                                            error:function (data) {
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
            })
        });

        /*        table.on( 'draw', function () {
                    $(".deleteBtn").click(function () {
                        deleteConfirm($(this).attr("cnslid"));
                    })
                    $(".downloadTmpBtn").click(function(){
                        window.location.href = "/kmConsult/downldConsult?cnsultTmpltId="+$(this).attr("tmpltId")+"&v=" + new Date().getTime();
                    });
                    $(".modifyBtn").click(function(){
                        new AddConsult(searchList, $(this).attr("cnslId"));
                    });
                    $(".link-consult").click(function(){
                        var cnslId = $(this).attr("cnslId");
                        var tmpltId = $(this).attr("tmpltId");
                        window.open('/src/consult/consultDataManageList.html?cnslId=' + cnslId + '&tmpltId=' + tmpltId);
                    });
                });*/
    }

    var init = function () {
        $("#btn-add").click(function () {
            var data = {};
            data.sexParam = sexParam;
            data.relation = relation;
            data.classes = classes;

            var inputTemplate = Hdb.compile(KidsInput);
            var inputHtml = inputTemplate(data);
            new Dialog(
                {mode:"confirm",
                    id:"kidsInput",
                    content:inputHtml,
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

        tableInit();
        $("#btn-query").click(function () {
            search();
        })

    }

    return init;
})