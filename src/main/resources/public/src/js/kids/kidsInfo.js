define(['dialog',
    'text!src/kids/kidsInput.tpl',
    'jquery',
    'datatables',
],function (Dialog,
            KidsInput,
            $,
            DataTables) {
    var table;
    var tableInit = function () {
        table = $("#list-contain").DataTable( {
            lengthChange: true,
            lengthMenu: [ 10, 25, 50, 75, 100 ],
            searching: false,
            serverSide: true,
            ajax: {
                "url": "/Kids/list",
                "data": function ( d ) {
                    return $.extend( {}, d, /*todo 添加参数*/{} );
                }
            },
            columns: [
                {data: 'kId', title:"人员编号"},
                {data: 'chNm', title:"中文名"},
                {data: 'enNm', title:"英文名"},
                {data: 'sex', title:"性别"}

                /* { data: 'cnslNm' ,title: "咨询表名称" , render:function (data, type, row, meta) {
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
                 }}*/
            ]
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
            new Dialog(
                {mode:"confirm",
                    id:"kidsInput",
                    content:KidsInput,
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

    }

    return init;
})