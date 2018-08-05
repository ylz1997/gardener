define(['dialog',
    'text!src/kids/staffInput.tpl',
    'jquery',
    'datatables',
    'hdb',
    'text!src/kids/classLogInput.tpl',
    'text!src/kids/viewLogDetail.tpl'
],function (Dialog,
            InputTpl,
            $,
            DataTables,
            Hdb,
            ClassLogInput,
            ViewLogDetail) {
    var table;
    var sexParam = {};
    var classes = {};
    var duties = {};
    var getTeacherByClassId = function (classID) {
        var teachers;
        $.ajax({
            url:"/Staff/listByClassId",
            method:"GET",
            data:{start:0, length:10000, draw:1, classId:classID},
            async:false,
            success:function (callData) {
                callData = JSON.parse(callData);
                teachers = callData.data;
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
        return teachers;
    }

    var getKidsByClassId = function (classId) {
        var allKids;
        //获取所有课时包
        $.ajax({
            url:"/Kids/list",
            method:"GET",
            data:{start:0,length:20000,draw:1,classId:classId},
            async:false,
            success:function (callData) {
                callData = JSON.parse(callData);
                allKids = callData.data;
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
        return allKids;
    }
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
        data:{code:"duties"},
        success:function (callData) {
            callData = JSON.parse(callData);
            if(callData.result == true){
                duties = callData.param;
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
        url:"/class/list",
        method:"GET",
        data:{start:0, length:10000, draw:1},
        success:function (callData) {
            callData = JSON.parse(callData);
            classes = callData.data;
            for(var i in classes){
                classes[i].clzId = classes[i].classId;
                delete classes[i].classId;
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
        var html = "<a class='modifyBtn' href='javascript:void(0)' tId='" + row.teacherId + "'>修改</a> | ";
        html = html + "<a class='deleteBtn' href='javascript:void(0)' tId='" + row.teacherId + "' teacherNm='" + row.teacherNm + "'>删除</a> | "
        html = html + "<a class='history' href='javascript:void(0)' teacherId='" + row.teacherId + "'>上课历史查询</a>";
        return html;
    }
    var genHisOperation = function (row) {
        var html = "<a class='viewLog' href='javascript:void(0)' logId='" + row.logId + "'>查看课堂日志</a> | ";
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
                "url": "/Staff/list",
                "data": function ( d ) {
                    return $.extend( {}, d, getParam() );
                }
            },
            columns: [
                {data: 'teacherId', title:"人员编号"},
                {data: 'teacherNm', title:"中文名"},
                {data: 'teacherEnNm', title:"英文"},
                {data: 'sex', title:"性别",render: function (data, type, row, meta) {
                    return exchangeDataDic(sexParam, data);
                }},
                {data: 'duty', title:"岗位",render: function (data, type, row, meta) {
                    return exchangeDataDic(duties, data);
                }},
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
                var tId = $(this).attr("tId");
                $.ajax({
                    url:"/Staff/get",
                    method:"POST",
                    data:{tId:tId},
                    success:function (data) {
                        data = JSON.parse(data);
                        if(data.result == true){
                            var tmp = Hdb.compile(InputTpl);
                            data.sexParam = sexParam;
                            data.classes = classes;
                            data.duties = duties;
                            var html = tmp(data);
                            new Dialog(
                                {
                                    mode:"confirm",
                                    id:"kidsInput",
                                    content:html,
                                    title:"修改员工信息",
                                    ok:function () {
                                        var params = new Object();
                                        $(".kidsClz").each(function(){
                                            params[$(this).attr("name")] = $(this).val();
                                        })
                                        var classCheck = new Array();
                                        $(".kidsCheckClz").each(function () {
                                            if(this.checked){
                                                var object = new Object();
                                                object.classId = $(this).val();
                                                classCheck.push(object);
                                            }
                                        })
                                        params["classIdArray"] = classCheck;
                                        $.ajax({
                                            url:"/Staff/edit",
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
                var tId = $(this).attr("tId");
                var teacherNm = $(this).attr("teacherNm");
                new Dialog({
                    mode: "confirm",
                    id: "kidsInput",
                    content: "教师姓名："+teacherNm,
                    title: "确认删除？",
                    ok: function () {
                        $.ajax({
                            url: "/Staff/delete",
                            method: "POST",
                            data: {tId: tId},
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
            $(".history").click(function () {
                var teacherId = $(this).attr("teacherId");
                var chargeHtml = ViewLogDetail;
                new Dialog(
                    {
                        mode:"confirm",
                        id:"viewHis",
                        content:chargeHtml,
                        title:"查看课堂历史",
                        callbak:function () {
                            var hisTable = $("#list-contain-history").DataTable( {
                                lengthChange: true,
                                lengthMenu: [ 5, 10],
                                searching: false,
                                serverSide: true,
                                ajax: {
                                    "url": "/KidsLogDetail/list",
                                    "data": function ( d ) {
                                        return $.extend( {}, d, {logObjId:teacherId, logType:1} );
                                    }
                                },
                                columns: [
                                    {data: 'classTime', title:"上课时间"},
                                    {
                                        data: 'logId',
                                        title: "操作",
                                        render: function (data, type, row, meta) {
                                            return genHisOperation(row);
                                        }
                                    }
                                ]
                            });
                            hisTable.on("draw",function () {
                                $(".viewLog").click(function () {
                                    var logId = $(this).attr("logId");
                                    $.ajax({
                                        url:"/kidsLog/get",
                                        method:"get",
                                        data:{logId:logId},
                                        success:function (data) {
                                            data = JSON.parse(data);
                                            if(data.result == true){
                                                var tmp = Hdb.compile(ClassLogInput);
                                                data.classes = classes;
                                                var html = tmp(data);
                                                new Dialog(
                                                    {
                                                        mode:"confirm",
                                                        id:"kidsInput",
                                                        content:html,
                                                        title:"查看课堂日志",
                                                        callbak:function () {
                                                            $(".kidsClzLogChangeClick").change(function () {
                                                                var classId = $(this).val();
                                                                var classKidsList = getKidsByClassId(classId);
                                                                var classTeacherList = getTeacherByClassId(classId);
                                                                var targetKidsHtml = "";
                                                                var targetTeacherHtml = "";

                                                                for(var i=0; i < classKidsList.length; i++){
                                                                    targetKidsHtml += "<label><input type=\"checkbox\" name=\"classes\" checked class=\"kidsCheckClz\" value='"+ classKidsList[i].kId + "'/>" +
                                                                        classKidsList[i].chNm + "</label>";
                                                                }
                                                                if(!targetKidsHtml){
                                                                    targetKidsHtml = "暂无数据...";
                                                                }
                                                                $("#kidsList").html(targetKidsHtml);

                                                                for(var i=0; i < classTeacherList.length; i++){
                                                                    targetTeacherHtml += "<label><input type=\"checkbox\" name=\"classes\" checked class=\"teacherCheckClz\" value='"+ classTeacherList[i].teacherId + "'/>" +
                                                                        classTeacherList[i].teacherNm + "</label>";
                                                                }
                                                                if(!targetTeacherHtml){
                                                                    targetTeacherHtml = "暂无数据...";
                                                                }
                                                                $("#teacherList").html(targetTeacherHtml);
                                                            })

                                                            $(".kidsClzLogChangeClick").change();
                                                            $("#classLogForm").find(".kidsClzLog,.teacherCheckClz,.kidsCheckClz").each(function () {
                                                                $(this).attr("disabled",true);
                                                            })
                                                        },
                                                        ok: function () {

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
                        },
                    });
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
            data.classes = classes;
            data.duties = duties;
            var inputTemplate = Hdb.compile(InputTpl);
            var inputHtml = inputTemplate(data);
            new Dialog(
                {mode:"confirm",
                    id:"kidsInput",
                    content:inputHtml,
                    title:"新增员工录入",
                    ok:function () {
                        var params = new Object();
                        $(".kidsClz").each(function(){
                            params[$(this).attr("name")] = $(this).val();
                        })
                        var classCheck = new Array();
                        $(".kidsCheckClz").each(function () {
                            if(this.checked){
                                var object = new Object();
                                object.classId = $(this).val();
                                classCheck.push(object);
                            }
                        })
                        params["classIdArray"] = classCheck;

                        $.ajax({
                            url:"/Staff/add",
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
        });

        tableInit();
        $("#btn-query").click(function () {
            search();
        })

    }

    return init;
})