define(['text!src/kids/kidsInfoList.tpl',
        'text!src/kids/staffInfoList.tpl',
        'src/js/kids/kidsInfo'],
        function (kidsTpl,
                    staffInfoTpl,
                  KidsInfo) {

    var table;

    var eventInit = function(){
        $("#kidsInfo").click(function () {
            $("#contentMain").html(kidsTpl);
            new KidsInfo();
        })
        $("#staffInfo").click(function () {
            $("#contentMain").html(staffInfoTpl);
        })

    }

    $(document).ready(function () {
        eventInit();

    })
});