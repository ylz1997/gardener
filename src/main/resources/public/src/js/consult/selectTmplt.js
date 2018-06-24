define(['text!src/consult/selectTmplt.tpl',
            'dialog','jquery','bootstrap', 'datatables'],function(tpl, Dialog, $, Bootstrap, DataTb){
    var trim = function (str) {
        if (str) {
            return str.replace(/(^\s*)|(\s*$)/g, "");
        }else{
            return "";
        }
    };

    var initialize = function(targetObj){
        var dialogConfig = {
            id: 'selectTmpltDialog',
            title: '选择模板',
            content: tpl,
            ok: function() {
                return true;
                //todo 逻辑放到列后面去做
            },
            cancel: function() {
                return true;
            }
        };

        var confirmDialog = new Dialog(dialogConfig);

        var listConfig = {
            "paging": false, // 禁止分页
/*            "lengthChange": false,
            "lengthMenu": [ 10, 25, 50, 75, 100 ],*/
            "searching": false,
            "serverSide": true,
            "ajax": {
                "url": '/consultTmplt/getTmpltListNoPage',
                "data": function ( d ) {
                    return $.extend( {}, d, {tmpltNm: trim($("input[name=tmpltNm]").val())} );
                }
            },
            "columns": [
                { data: 'tmpltNm' ,title: "模板名称"},
                { data: 'tmpltId', title: "操作", render:function (data, type, row, meta) {
                    if(row){
                        return genOperation(row);
                    }
                }}
            ]
        };

        var genOperation = function (row) {
            var html = "<a class='changeBtn' href='javascript:void(0)' tmpltid='" + row.tmpltId + "' tmpltnm = '" + row.tmpltNm + "'>选中</a>";
            return html;
        }
/*        var listConfig = {
            el: $("#tmplt-contain"),
            ajax:{
                method:'GET'
            },
            field:{
                boxType:'radio',
                key:'tmpltId',
                items:[
                    {
                        text: '模板名称',
                        name: 'tmpltNm',
                        width: '99%',
                        title: 'tmpltNm'
                    }
                ]
            },
            data: {
                url: '/consultTmplt/getTmpltListNoPage'
            }
        };*/
        //var list = new List(listConfig);
        var list = $("#tmplt-contain").DataTable(listConfig);

        list.on( 'draw', function () {
            $(".changeBtn").click(function () {
                var tmpltNm = $(this).attr("tmpltnm");
                var tmpltId = $(this).attr("tmpltid");

                targetObj.val(tmpltNm);
                targetObj.attr("tmpltId", tmpltId);
                confirmDialog.hide();
            })
        } );


        $("#selectTmplt").click(function(){
                list.ajax.reload(null,false);
        });
    }

    return initialize;
});