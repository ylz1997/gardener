define(['dialog',
    'text!src/kids/kidsInput.tpl'
],function (DiaLog,
            KidsInput) {

    var init = function () {
        $("#btn-add").click(function () {
            new DiaLog({mode:"confirm",id:"kidsInput",content:KidsInput,title:"新增学生录入"});
        });

    }

    return init;
})