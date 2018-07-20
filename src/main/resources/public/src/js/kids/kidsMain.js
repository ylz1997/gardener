define(['text!src/kids/kidsInfoList.tpl',
        'text!src/kids/staffInfoList.tpl',
        'text!src/kids/classPackageList.tpl',
        'text!src/kids/classList.tpl',
        'text!src/kids/classLogList.tpl',
        'src/js/kids/kidsInfo',
        'src/js/kids/kidsStaffInfo',
        'src/js/kids/classPackageInfo',
        'src/js/kids/classInfo',
        'src/js/kids/classLogInfo',
        'jquery',
        'dialog',
        'hdb'],
        function (kidsTpl,
                    staffInfoTpl,
                    classPackageListTpl,
                    classListTpl,
                    classLogListTpl,
                    KidsInfo,
                    KidsStaffInfo,
                    classPackageInfo,
                    classInfo,
                    classLogInfo,
                    $,
                    Dialog,
                    Hdb) {

    var classes;
    $.ajax({
        url:"/class/list",
        method:"GET",
        data:{start:0, length:10000, draw:1},
        success:function (callData) {
            callData = JSON.parse(callData);
            classes = callData.data;
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

    var eventInit = function(){
        $("#kidsInfo").click(function () {
            var data = {};
            data.classes = classes;
            $("#contentMain").html(Hdb.compile(kidsTpl)(data));
            new KidsInfo();
        })
        $("#staffInfo").click(function () {
            $("#contentMain").html(staffInfoTpl);
            new KidsStaffInfo();
        })
        $("#classPackageInfo").click(function () {
            $("#contentMain").html(classPackageListTpl);
            new classPackageInfo();
        })
        $("#classInfo").click(function () {
            $("#contentMain").html(classListTpl);
            new classInfo();
        })
        $("#classLogInfo").click(function () {
            $("#contentMain").html(classLogListTpl);
            new classLogInfo();
        })

    }

    $(document).ready(function () {
        eventInit();
    })
});