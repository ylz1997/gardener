function urlArgs(){
    return function (name, path) {
        if (this.paths[name] && this.paths[name].indexOf("?v=") > -1) {
            return '';
        }
        return "?v=1523848215327";
    }
}

var require = {
	baseUrl : "/",
    map: {
      	'*': { 'style': 'assets/lib/requirejs/css.min' }
    },
	paths : {
        "jquery" : 'src/js/assets/jquery/jquery-3.3.1.min',
        "datatables": 'src/DataTables-1.10.16/js/jquery.dataTables.min',
        "datepicker": 'src/js/assets/datepicker/bootstrap-datepicker.min',
        "bootstrap" : 'src/js/assets/bootstrap/bootstrap.min',
        "hdb" : 'src/js/assets/handlebars/handlebars',
        "dialog" : 'src/js/assets/dialog/dialog',
        'text' : 'src/js/requirejs/text',
        'css' : 'src/js/requirejs/css.min',
        'toastr' : 'src/js/assets/toastr/toastr.min',
        'ajax' : 'src/js/assets/common/ajax_amd',
        'Util' : 'src/js/assets/common/util',
        'select':'src/js/assets/common/select',
        'eventTarget':'src/js/assets/common/eventTarget',
        'date':'src/js/assets/common/date',
        'encrypt' :'src/js/assets/common/encrypt'
    },
	waitSeconds:0,
	shim:{
	        "bootstrap" :{
	            "deps" :['jquery', 'css!src/css/bootstrap/bootstrap.css']
	        },
            "datatables" :{
	            "deps" :['jquery', 'css!src/css/datatable/datatables.min.css']
            },
            'ajax': { 'deps': ['jquery'] }
        },
    urlArgs: urlArgs()

};
//var requireConfig = require;

/*requirejs.config(require);*/
