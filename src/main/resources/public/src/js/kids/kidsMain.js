define(['text!src/kids/kidsInfoList.tpl',
        'text!src/kids/staffInfoList.tpl',
        'Util',
        'text!src/kids/kidsInput.tpl',
        'dialog'],
        function (kidsTpl,
                    staffInfoTpl,
                    util,
                    kidsInput,
                    Dialog) {

    var eventInit = function(){
        $("#kidsInfo").click(function () {
            $("#contentMain").html(kidsTpl);
        })
        $("#staffInfo").click(function () {
            $("#contentMain").html(staffInfoTpl);
        })
        $("#btn-add").click(function () {
            new Dialog({mode:confirm,id:"kidsInput",content:kidsInput,title:"新增学生录入"})
        })
    }


    $(document).ready(function () {
/*        var searchData = util.serilizeUrl();
        var titleName = searchData.titleName;
        console.log(decodeURIComponent(titleName));*/
        eventInit.call();
/*        ($("ul.nav").children()).click(function () {
            $(this).addClass("active");
            $(this).siblings("li").removeClass("active");
        })*/
    })
});