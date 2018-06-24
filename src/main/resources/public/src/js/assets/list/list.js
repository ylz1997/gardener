define(['jquery','datatable'],function (jqy, dt) {
    var initilize = function (config) {
        debugger;

        if(config.el){
            return el.DataTable(config);
        }
        else{
            console.log("el 不能为空！");
        }
    }

    return initilize();
})