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
        'hdb',
        'src/js/kids/checkPermission'],
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
                    Hdb,
                    CheckPermission) {

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
        $("#begin2").click(function () {
            $("#begin").click();
        })

        $("#kidsInfo").click(function () {
            $("#contentMain").removeClass("jumbotron");
            var data = {};
            data.classes = classes;
            $("#contentMain").html(Hdb.compile(kidsTpl)(data));
            new KidsInfo();
        })
        $("#staffInfo").click(function () {
            $("#contentMain").removeClass("jumbotron");
            $("#contentMain").html(staffInfoTpl);
            new KidsStaffInfo();
        })
        $("#classPackageInfo").click(function () {
            $("#contentMain").removeClass("jumbotron");
            $("#contentMain").html(classPackageListTpl);
            new classPackageInfo();
        })
        $("#classInfo").click(function () {
            $("#contentMain").removeClass("jumbotron");
            $("#contentMain").html(classListTpl);
            new classInfo();
        })
        $("#classInfo2").click(function () {
            $("#contentMain").removeClass("jumbotron");
            $("#contentMain").html(classListTpl);
            new classInfo();
        })
        $("#classLogInfo").click(function () {
            $("#contentMain").removeClass("jumbotron");
            var data = {};
            data.classes = classes;

            $("#contentMain").html(Hdb.compile(classLogListTpl)(data));
            new classLogInfo();
        })
        $("#classLogInfo2").click(function () {
            $("#contentMain").removeClass("jumbotron");
            var data = {};
            data.classes = classes;

            $("#contentMain").html(Hdb.compile(classLogListTpl)(data));
            new classLogInfo();
        })

    }

    $(document).ready(function () {
        eventInit();
    })
});