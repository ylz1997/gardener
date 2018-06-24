require(['src/js/consult/addConsult',
            'datatables',
            'datepicker'],
    function(AddConsult, dataTables, datePicker){
        var table;
        var trim = function (str) {
            if (str) {
                return str.replace(/(^\s*)|(\s*$)/g, "");
            } else {
                return "";
            }
        }

        var searchData = function(){
            var param = {};
            var cnslNm = trim($("#cnslNm").val());
            if(cnslNm){
                param.cnslNm = cnslNm;
            }
            var regnId = trim($("#regnId").val());
            if(regnId){
                param.regnId = regnId;
            }
            var opPrsnId = trim($("#opPrsnId").val());
            if(opPrsnId){
                param.opPrsnId = $("#opPrsnId").val();
            }
            var startTime = trim($("#startTime").val());
            if(startTime){
                param.startTime = startTime+" 00:00:00";
            }
            var endTime = trim($("#endTime").val());
            if(endTime){
                param.endTime = endTime + " 23:59:59";
            }

            /*        param.start = 0;
                    param.length = 12;*/
            return param;
        };

        var formatUserId = function(userId){
            if(userId == 000) return "测试";
            else return "未知";
            /*        Util.ajax.postJson(Constants.AJAXURL + '/kmConfig/getRegnNmsByIds', {ids: regnId}, function (data) {
                        if (data.returnCode == 0) {
                            if (data.object && data.object != "null" && JSON.stringify(data.object) != "{}") {
                                regnId = data.object;
                                $src.html('<span title="' + regnId + '">' + regnId + '</span>');
                            }else{
                                $src.html('<span title="' + regnId + '">' + regnId + '</span>');
                            }
                        }
                    });*/
        };

        var genLinkToConsultData = function(row, data){
            var html = "<a class='link-consult' href='javascript:void(0)' cnslid='" + row.cnslId + "' tmpltId='" + row.tmpltId + "'>"+data+"</a>";
            return html;
        }
        var genOperation = function (row) {
            var html = "<a class='modifyBtn' href='javascript:void(0)' cnslid='" + row.cnslId + "'>修改</a>" +
                '<span class="color-ccc">|</span>' +
                "<a class='downloadTmpBtn' href='javascript:void(0)' tmpltId='" + row.tmpltId + "'>下载模板</a>" +
                '<span class="color-ccc">|</span>' +
                "<a class='deleteBtn' href='javascript:void(0)' cnslid='" + row.cnslId + "'>删除</a>";
            return html;
        }

        var deleteConfirm = function (cnslId) {
            $("#confirmDialog").attr("cnslid", cnslId);
            $("#confirmDialog").show();
        }
        var showErrorMsg = function(msg){
            $("#errorDialog").html("操作失败！"+msg);
            $("#errorDialog").show();
        }
        $(".confirm-delete-btn").click(function () {
            $("#confirmDialog").hide();
            var cnslId = $(this).parent().attr("cnslid");
            $.ajax({
                url:"/kmConsult/deleteConsult",
                method:"POST",
                async:false,
                data:{cnslId: cnslId},
                success:function (data) {
                    $("#successDialog").show();
                    window.setTimeout(function(){
                        $("#successDialog").hide();
                    },2000);

                    table.ajax.reload(null,false);
                },
                error:function (data) {
                    showErrorMsg(data.responseJSON.message);
                    window.setTimeout(function(){
                        $("#errorDialog").hide();
                    },2000);
                }
            })
        })
        $(".cancel-delete-btn").click(function () {
            $("#confirmDialog").hide();
        })


        var tableInit = function () {
            table = $("#list-contain").DataTable( {
                lengthChange: true,
                lengthMenu: [ 10, 25, 50, 75, 100 ],
                searching: false,
                serverSide: true,
                ajax: {
                    "url": "/kmConsult/getConsultList",
                    "data": function ( d ) {
                        return $.extend( {}, d, searchData() );
                    }
                },
                columns: [
                    { data: 'cnslNm' ,title: "咨询表名称" , render:function (data, type, row, meta) {
                        return genLinkToConsultData(row,data);
                    }},
                    //function render( data, type, row, meta )
                    { data: 'regnId',title:"用户" ,render:function (data, type, row, meta) {
                        return formatUserId(data);
                    }},
                    { data: 'tmpltId', title:"模板id"},
                    { data: 'modfTime', title:"修改时间"},
                    { data: 'opPrsnId', title:"操作人"},
                    { data: 'cnslId', title: "操作", render:function (data, type, row, meta) {
                        return genOperation(row);
                    }}
                ]
            });

            table.on( 'draw', function () {
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
            });
        }

        var searchList = function () {
            table.ajax.reload();
        }

        $(document).ready(function () {
            tableInit();


            $("#startTime").datepicker({
                autoclose:true,
                language:"ZH-cn",
                format: 'yyyy-mm-dd'
            });
            $("#endTime").datepicker({
                autoclose:true,
                language:"ZH-cn",
                format: 'yyyy-mm-dd'
            });



            $("#searchBtn").click(function () {
                table.ajax.reload(null,false);
                return;
            })
            $("#btn-add").click(function(){
                new AddConsult(searchList);
            });


        })
    });