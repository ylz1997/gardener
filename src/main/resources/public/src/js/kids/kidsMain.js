define(['text!src/kids/kidsInfoList.tpl',
        'text!src/kids/staffInfoList.tpl',
        'text!src/kids/classPackageList.tpl',
        'text!src/kids/classList.tpl',
        'src/js/kids/kidsInfo',
        'src/js/kids/kidsStaffInfo',
        'src/js/kids/classPackageInfo',
        'src/js/kids/classInfo',
        'jquery',
        'dialog',
        'hdb'],
        function (kidsTpl,
                    staffInfoTpl,
                    classPackageListTpl,
                    classListTpl,
                    KidsInfo,
                    KidsStaffInfo,
                    classPackageInfo,
                    classInfo,
                    $,
                    Dialog,
                    Hdb) {

    var table;
    var classes;
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
    }

    $(document).ready(function () {
        eventInit();
    })
});