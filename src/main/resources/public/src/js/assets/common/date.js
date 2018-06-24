define(['datepicker'], function (datePicker) {
    var initilize = function (options) {
        if(options && options.el)
        {
            if("yyyy-mm-dd" == options.format){
                options.el.datepicker({
                    autoclose:true,
                    language:"ZH-cn",
                    format: options.format
                });
            }
/*            else if("yyyy-mm-dd hh:ii:ss" == options.format){
                options.el.datetimepicker({
                    format: options.format
                });
            }*/
        }
    }
    return initilize();
})