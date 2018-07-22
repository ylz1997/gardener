define(['dialog',
    'text!src/kids/classLogInput.tpl',
    'jquery',
    'datatables',
    'hdb',
    'datetimepicker'
],function (Dialog,
            InputTpl,
            $,
            DataTables,
            Hdb,
            datetimepicker) {
    var table;
    var classes;
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
        var html = "<a class='viewBtn' href='javascript:void(0)' logId='" + row.logId + "' classId='" + row.classId + "'>查看明细</a> | ";
/*
        html = html + "<a class='deleteBtn' href='javascript:void(0)' logId='" + row.logId + "'>删除</a>"
*/
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
                "url": "/kidsLog/list",
                "data": function ( d ) {
                    return $.extend( {}, d, getParam() );
                }
            },
            columns: [
                //{data: 'classId', title:"日志id"},
                //{data: 'teacherNm', title:"教师姓名"},
                {data: 'classNm', title:"班级"},
                {data: 'content', title:"课堂日志"},
                {data: 'crtTime', title:"日志时间"},
                {data: 'classTime', title:"上课时间"},
                {data: 'logId' ,title:"操作",
                    render:
                        function (data, type, row, meta) {
                            return genOperation(row);
                }}
            ]
        });
        table.on( 'draw', function () {
            $(".viewBtn").click(function () {
                var logId = $(this).attr("logId");
                $.ajax({
                    url:"/kidsLog/get",
                    method:"get",
                    data:{logId:logId},
                    success:function (data) {
                        data = JSON.parse(data);
                        if(data.result == true){
                            var tmp = Hdb.compile(InputTpl);
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
    }

    var init = function () {
        $("#btn-add").click(function () {
            var data = {};
            data.classes = classes;
            var inputTemplate = Hdb.compile(InputTpl);
            var inputHtml = inputTemplate(data);
            new Dialog(
                {mode:"confirm",
                    id:"kidsInput",
                    content:inputHtml,
                    title:"新增课堂日志",
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
                        $("#strClassTime").datetimepicker({
                            autoclose:true,
                            language:"ZH-cn",
                            format: 'yyyy-mm-dd hh:ii:ss'
                        });
                    },
                    ok:function () {
                        var params = new Object();
                        $(".kidsClzLog").each(function(){
                            params[$(this).attr("name")] = $(this).val();
                        })

                        var kidsCheckClz = new Array();
                        $(".kidsCheckClz").each(function () {
                            if(this.checked){
                                var object = new Object();
                                object.logObjId = $(this).val();
                                kidsCheckClz.push(object);
                            }
                        })
                        params["kidsList"] = kidsCheckClz;

                        var teacherCheckClz = new Array();
                        $(".teacherCheckClz").each(function () {
                            if(this.checked){
                                var object = new Object();
                                object.logObjId = $(this).val();
                                teacherCheckClz.push(object);
                            }
                        })
                        params["teacherList"] = teacherCheckClz;


                        $.ajax({
                            url:"/kidsLog/add",
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