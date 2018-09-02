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

    // 序列化url查询参数
    function serilizeUrl(url) {
        var result = {};
        // url = url.split("?")[1];
        var map = url.split("&");
        for(var i = 0, len = map.length; i < len; i++){
            result[map[i].split("=")[0]] = map[i].split("=")[1];
        }
        return result;
    }

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
            new CheckPermission("kids:add", $("#btn-add-div"));
            new KidsInfo();
        })
        $("#staffInfo").click(function () {
            $("#contentMain").removeClass("jumbotron");
            $("#contentMain").html(staffInfoTpl);
            new CheckPermission("staff:add", $("#btn-add-div"));
            new KidsStaffInfo();
        })
        $("#classPackageInfo").click(function () {
            $("#contentMain").removeClass("jumbotron");
            $("#contentMain").html(classPackageListTpl);
            new CheckPermission("classPackage:add", $("#btn-add-div"));
            new classPackageInfo();
        })
        $("#classInfo").click(function () {
            $("#contentMain").removeClass("jumbotron");
            $("#contentMain").html(classListTpl);
            new CheckPermission("class:add", $("#btn-add-div"));
            new classInfo();
        })
        $("#classInfo2").click(function () {
            $("#contentMain").removeClass("jumbotron");
            $("#contentMain").html(classListTpl);
            new CheckPermission("class:add", $("#btn-add-div"));
            new classInfo();
        })
        $("#classLogInfo").click(function () {
            $("#contentMain").removeClass("jumbotron");
            var data = {};
            data.classes = classes;

            $("#contentMain").html(Hdb.compile(classLogListTpl)(data));
            new CheckPermission("kidsLog:add", $("#btn-add-div"));
            new classLogInfo();
        })
        $("#classLogInfo2").click(function () {
            $("#contentMain").removeClass("jumbotron");
            var data = {};
            data.classes = classes;

            $("#contentMain").html(Hdb.compile(classLogListTpl)(data));
            new CheckPermission("kidsLog:add", $("#btn-add-div"));
            new classLogInfo();
        })

    }

    $(document).ready(function () {
        // 路径查询参数部分
        var searchURL = decodeURI(window.location.search);
        searchURL = searchURL.substring(1, searchURL.length);
        // 参数序列化
        var searchData = serilizeUrl(decodeURI(searchURL));
        $("#userName").html(searchData.loginNm);
        eventInit();
    })
});