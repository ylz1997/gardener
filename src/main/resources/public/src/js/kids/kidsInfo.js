define(['dialog',
    'text!src/kids/kidsInput.tpl',
    'jquery',
    'datatables',
    'hdb',
    'text!src/kids/chargeClass.tpl',
    'text!src/kids/viewLogDetail.tpl',
    'text!src/kids/classLogInput.tpl',
    'text!src/kids/kidsReAddClass.tpl',
    'datetimepicker',
    'src/js/kids/checkPermission'
],function (Dialog,
            KidsInput,
            $,
            DataTables,
            Hdb,
            ChargeTpl,
            ViewLogDetail,
            ClassLogInput,
            KidsReAddInput,
            datetimepicker,
            CheckPermission) {
    var table;
    var sexParam = {};
    var relation = {};
    var classes = {};
    var consultants = {};
    var classPackage = {};
    var readdRow = {};
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
    });

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
        url:"/class/list",
        method:"GET",
        data:{start:0, length:10000, draw:1},
        success:function (callData) {
            callData = JSON.parse(callData);
            classes = callData.data;
            for(var i = 0; i < classes.length; i++){
                classes[i].clzId = classes[i].classId;
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

    $.ajax({
        url:"/Staff/list",
        method:"GET",
        data:{start:0, length:10000, draw:1, duty:2},
        success:function (callData) {
            callData = JSON.parse(callData);
            consultants = callData.data;
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


    $.ajax({
        url:"/classPackage/list",
        method:"GET",
        data:{start:0, length:10000, draw:1},
        success:function (callData) {
            callData = JSON.parse(callData);
            classPackage = callData.data;
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
        var html = "<a class='modifyBtn hideMenu' href='javascript:void(0)' kId='" + row.kId + "'>修改 | </a>  ";
        html = html + "<a class='deleteBtn hideMenu' href='javascript:void(0)' kId='" + row.kId + "' chNm='" + row.chNm + "'>删除 | </a>  ";
        html = html + "<a class='chargeBtn hideMenu' href='javascript:void(0)' kId='" + row.kId + "'>充值课时 | </a> ";
        html = html + "<a class='history' href='javascript:void(0)' kId='" + row.kId + "'>考勤查询</a>";
        return html;
    }
    var genHisOperation = function (row) {
        var html = "";
        if(row.logId){
            html = "<a class='viewLog' href='javascript:void(0)' logId='" + row.logId + "'>查看课堂日志</a>";
        }else if(row.logType == 3){
            readdRow[row.detailLogId] = row;
            html = "<a class='viewReaddLog' href='javascript:void(0)' detailLogId='" + row.detailLogId + "' logType='reAdd'>查看补课日志</a>";
        }else if(row.logType == 4){
            readdRow[row.detailLogId] = row;
            html = "<a class='viewReaddLog' href='javascript:void(0)' detailLogId='" + row.detailLogId + "' logType='telCall'>查看电辅记录</a>";
        }

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
                {data: 'contractNo', title:"协议编号"},
                {data: 'chNm', title:"中文名"},
                {data: 'enNm', title:"英文名"},
                {data: 'sex', title:"性别",render: function (data, type, row, meta) {
                    return exchangeDataDic(sexParam, data);
                }},
                {data: 'consultantId', title:"顾问",render: function (data) {
                    for(var i=0; i < consultants.length; i++){
                        if(consultants[i].teacherId == data){
                            return consultants[i].teacherEnNm;
                        }
                    }
                    return "未指定";
                }},
                {data: 'phone', title:"联系方式"},
                {data: 'ifAppAccount', title:"是否开通APP账号", render: function (data) {
                    if(data == "1"){
                        return "是";
                    }
                    else if(data == "0"){
                        return "否";
                    }
                    else{
                        return "未指定";
                    }
                }},
                {data: 'amount', title:"剩余课时"},
                {data: 'classId', title:"所在班级", render: function (data) {
                    for(var i=0; i < classes.length; i++){
                        if(classes[i].classId == data){
                            return classes[i].classNm;
                        }
                    }
                    return data;
                }},
                {
                    data: 'cnslColmId',
                    title: "操作",
                    width: "200px",
                    render: function (data, type, row, meta) {
                        return genOperation(row);
                    }
                }
            ]
        });
        table.on( 'draw', function () {
            new CheckPermission("kids:edit", $(".modifyBtn"));
            new CheckPermission("kids:delete", $(".deleteBtn"));
            new CheckPermission("kids:charge", $(".chargeBtn"));

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
                            data.consultants = consultants;
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
            $(".deleteBtn").click(function () {
                var kId = $(this).attr("kId");
                var chNm = $(this).attr("chNm");

                new Dialog({mode:"confirm",
                    id:"kidsInput",
                    content:"学生："+chNm,
                    title:"确认删除？",
                    ok:function () {
                        $.ajax({
                            url:"/Kids/delete",
                            method:"POST",
                            data:{kId:kId},
                            success:function (data) {
                                data = JSON.parse(data);
                                if(data.result == true){
                                    new Dialog({
                                        mode: 'tips',
                                        tipsType: 'success',
                                        content: "删除成功"
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
            })

            $(".history").click(function () {
                var kId = $(this).attr("kId");
                var viewLogHtml = ViewLogDetail;
                new Dialog(
                    {
                        mode:"confirm",
                        id:"viewHis",
                        content:viewLogHtml,
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
                                        return $.extend( {}, d, {logObjId:kId, logType:$("#logType").val()} );
                                    }
                                },
                                columns: [
                                    //{data: '', visible:false},
                                    {data: 'classTime', title:"上课时间"},
                                    {
                                        data: 'logId',
                                        title: "操作",
                                        render: function (data, type, row, meta) {
                                            return genHisOperation(row);
                                        }
                                    },
                                    {data: 'logType', title:"类型", render: function (data, type, row, meta) {
                                        if(data == "2"){
                                            return "正常";
                                        }
                                        if(data == "3"){
                                            return "补课";
                                        }
                                        if(data == "4"){
                                            return "电辅";
                                        }
                                    }}
                                ]
                            });
                            hisTable.on("draw",function () {
                                $("#btn-readd-clz").click(function () {
                                    var data = {};
                                    data.operType = "reAdd";
                                    var reAddTplInput = Hdb.compile(KidsReAddInput)(data);
                                    new Dialog({
                                        mode:"confirm",
                                        id:"kidsInput",
                                        content:reAddTplInput,
                                        title:"补课",
                                        callbak:function () {
                                            $("#strClassTime").datetimepicker({
                                                autoclose:true,
                                                language:"ZH-cn",
                                                format: 'yyyy-mm-dd hh:ii:ss'
                                            });
                                        },
                                        ok:function () {
                                            var params = new Object();
                                            $(".kidsReAddClzLog").each(function(){
                                                params[$(this).attr("name")] = $(this).val();
                                            })
                                            params.logObjId = kId;
                                            params.logType = 3;

                                            $.ajax({
                                                url:"/KidsLogDetail/add",
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
                                                    hisTable.ajax.reload();
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
                                        }
                                    });
                                })

                                $("#btn-telCall-clz").click(function () {
                                    var dataParam = {};
                                    dataParam.operType = "telCall";
                                    var reAddTplInput = Hdb.compile(KidsReAddInput)(dataParam);
                                    new Dialog({
                                        mode:"confirm",
                                        id:"kidsInput",
                                        content:reAddTplInput,
                                        title:"电辅",
                                        callbak:function () {
                                            $("#strClassTime").datetimepicker({
                                                autoclose:true,
                                                language:"ZH-cn",
                                                format: 'yyyy-mm-dd hh:ii:ss'
                                            });
                                        },
                                        ok:function () {
                                            var params = new Object();
                                            $(".kidsReAddClzLog").each(function(){
                                                params[$(this).attr("name")] = $(this).val();
                                            })
                                            params.logObjId = kId;
                                            params.logType = 4;

                                            $.ajax({
                                                url:"/KidsLogDetail/add",
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
                                                    hisTable.ajax.reload();
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
                                        }
                                    });
                                })

                                $("#logType").change(function(){
                                    hisTable.ajax.reload();
                                })
                                $(".viewReaddLog").click(function () {
                                    var detailLogId = $(this).attr("detailLogId");
                                    var logType = $(this).attr("logType");
                                    readdRow[detailLogId].operType = logType;
                                    var reAddTplInput = Hdb.compile(KidsReAddInput)(readdRow[detailLogId]);
                                    new Dialog({
                                        mode:"confirm",
                                        id:"kidsInput",
                                        content:reAddTplInput,
                                        title:"补课或电辅",
                                        callbak:function () {
                                            $("#strClassTime").datetimepicker({
                                                autoclose:true,
                                                language:"ZH-cn",
                                                format: 'yyyy-mm-dd hh:ii:ss'
                                            });
                                        },
                                        ok:function () {
                                        }
                                    });
                                })
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
                                                                            classTeacherList[i].teacherEnNm + "</label>";
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

            $(".chargeBtn").click(function () {
                var kId = $(this).attr("kId");
                var chargeData = {};
                chargeData.classPackage = classPackage;
                var chargeTemplate = Hdb.compile(ChargeTpl);
                var chargeHtml = chargeTemplate(chargeData);

                new Dialog(
                    {
                        mode:"confirm",
                        id:"kidsInput",
                        content:chargeHtml,
                        title:"充值课时包",
                        ok:function () {
                            var classPackageId = $("#classPackageId").val();
                            $.ajax({
                                url:"/Kids/charge",
                                method:"POST",
                                data:{kId:kId, classPackageId:classPackageId},
                                success:function (data) {
                                    data = JSON.parse(data);
                                    if(data.result == true){
                                        new Dialog({
                                            mode: 'tips',
                                            tipsType: 'success',
                                            content: "充值成功"
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
            data.consultants = consultants;
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
                                search();
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