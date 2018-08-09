define(['dialog',
    'text!src/kids/classInput.tpl',
    'jquery',
    'datatables',
    'hdb'
],function (Dialog,
            InputTpl,
            $,
            DataTables,
            Hdb) {
    var classLevel;
    var table;
    $.ajax({
        url:"/SysPara/getParamByCode",
        method:"GET",
        data:{code:"classLevel"},
        success:function (callData) {
            callData = JSON.parse(callData);
            if(callData.result == true){
                classLevel = callData.param;
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
    });

    var getAllPackage = function () {
        var allClassPackage;
        //获取所有课时包
        $.ajax({
            url:"/classPackage/list",
            method:"GET",
            data:{start:0,length:20000,draw:1},
            async:false,
            success:function (callData) {
                callData = JSON.parse(callData);
                allClassPackage = callData.data;
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
        return allClassPackage;
    }

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
        var html = "<a class='modifyBtn' href='javascript:void(0)' classId='" + row.classId + "'>修改</a> | ";
        html = html + "<a class='deleteBtn' href='javascript:void(0)' classId='" + row.classId + "' classNm='" + row.classNm + "'>删除</a>"
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
                "url": "/class/list",
                "data": function ( d ) {
                    return $.extend( {}, d, getParam() );
                }
            },
            columns: [
                {data: 'classId', title:"班级id"},
                {data: 'classNm', title:"班级名称"},
                {data: 'crtTime', title:"创建时间"},
                {data: 'modfTime', title:"更新时间"},
                {
                    data: 'classId',
                    title: "操作",
                    render: function (data, type, row, meta) {
                        return genOperation(row);
                    }
                }
            ]
        });
        table.on( 'draw', function () {
            $(".modifyBtn").click(function () {
                var classId = $(this).attr("classId");
                $.ajax({
                    url:"/class/get",
                    method:"POST",
                    data:{classId:classId},
                    success:function (data) {
                        data = JSON.parse(data);
                        if(data.result == true){
                            var tmp = Hdb.compile(InputTpl);
                            data.allClassPackage = getAllPackage();
                            data.classLevel = classLevel;
                            var html = tmp(data);
                            new Dialog(
                                {
                                    mode:"confirm",
                                    id:"kidsInput",
                                    content:html,
                                    title:"修改班级信息",
                                    ok:function () {
                                        var params = new Object();
                                        $(".kidsClz").each(function(){
                                            params[$(this).attr("name")] = $(this).val();
                                        })
                                        $.ajax({
                                            url:"/class/edit",
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
            $(".deleteBtn").click(function () {
                var classId = $(this).attr("classId");
                var classNm = $(this).attr("classNm");
                new Dialog({
                    mode: "confirm",
                    id: "kidsInput",
                    content: "班级名称："+classNm,
                    title: "确认删除？",
                    ok: function () {
                        $.ajax({
                            url: "/class/delete",
                            method: "POST",
                            data: {classId: classId},
                            success: function (data) {
                                data = JSON.parse(data);
                                if (data.result == true) {
                                    new Dialog({
                                        mode: 'tips',
                                        tipsType: 'success',
                                        content: "删除成功"
                                    });
                                    search();
                                }
                            },
                            error: function (data) {
                                new Dialog({
                                    mode: 'tips',
                                    tipsType: 'error',
                                    content: data.responseJSON.error
                                });
                                return;
                            }
                        })
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
            data.allClassPackage = getAllPackage();
            data.classLevel = classLevel;
            var inputTemplate = Hdb.compile(InputTpl);
            var inputHtml = inputTemplate(data);
            new Dialog(
                {mode:"confirm",
                    id:"kidsInput",
                    content:inputHtml,
                    title:"新增班级",
                    ok:function () {
                        var params = new Object();
                        $(".kidsClz").each(function(){
                            params[$(this).attr("name")] = $(this).val();
                        })
                        $.ajax({
                            url:"/class/add",
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
                //添加课程时间表
                $("#addClassSchdule").click(function () {
                    $(".schdule").append(dynClassSchdule);
                })
        });

        tableInit();
        $("#btn-query").click(function () {
            search();
        })
    }

    return init;
})