define(['Util', 'dialog',  'datatables',
    'src/js/consult/addConsultData', 'src/js/consult/batchUpdateConsultData', 'select', 'src/js/consult/importConsultRecord',
        'datepicker'],
    function(Util, Dialog, DateTables, AddConsult, BatchUpdate, Select, ImportConsultRecord, DatePicker){
    var searchParam = {};
    //字段select组件
    var atomSelectList = [];
    //字段select组件的选中值
    var atomSelectVal = {};
    //字段类型常量
    var NUM = "num";
    var DATE = "date";
    var REL = "rel";
    var _root = window.location.protocol + "//" + window.location.host;
    //查询条件计数器
    var count = 0;
    //模板下的原子集合
    var keys;
    //用于存放查询条件的数组
    var paramList = [];
    var table;
    var searchParams = [];

        /**
     * 用于修剪字符串 兼容ie8
     *
     * @param str
     * @returns {XML|string|void|*}
     */
    var trim = function (str) {
        if (str) {
            return str.replace(/(^\s*)|(\s*$)/g, "");
        }else{
            return "";
        }
    };

    /**
     * 获取查询参数  主要是模板id  咨询表id
     */
    var getTmpltId = function(){
        var url = decodeURI(window.location.search);
        if(url){
            url = url.substring(1);
        }
        var linkParams;
        if(url){
            linkParams = url.split("&");
        }
        if(linkParams){
            $(linkParams).each(function(){
                searchParam[this.split("=")[0]] = this.split("=")[1];
            });
        }
    };

    /*var checkDelete = function(recIds){
        var flag = false;
        Util.ajax.postJson(Constants.AJAXURL + "/consultData/deleteConsultData", {recIds: recIds.join(",")}, function(data){
            if(data.returnCode == "0"){
                if(data.beans.length){
                    new Dialog({
                        mode: 'tips',
                        tipsType: 'error',
                        content: '选中记录中的其中一条或多条已被删除'
                    });
                }else{
                    flag = true;
                }
            }else{
                new Dialog({
                    mode: 'tips',
                    tipsType: 'error',
                    content: data.responseJSON.message
                });
            }
        }, true);

        return flag;
    };
*/
    /**
     * list组件配置文件
     *
     * @type {{el: (jQuery|HTMLElement), ajax: {method: string}, field: {boxType: string, key: string, button: {width: number, text: string, items: [null]}}, page: {customPages: [number,number,number,number,number,number], perPage: number, total: boolean, align: string, checkedCount: string, button: {items: [null,null,null]}}, data: {url: string}}}
     */

    var tableInit = function (tableConfig) {
        table = $("#list-contain").DataTable(tableConfig);

        table.on('draw', function () {
/*            $(".deleteBtn").click(function () {
                deleteConfirm($(this).attr("cnslid"));
            })
            $(".downloadTmpBtn").click(function () {
                window.location.href = "/kmConsult/downldConsult?cnsultTmpltId=" + $(this).attr("tmpltId") + "&v=" + new Date().getTime();
            });
            $(".modifyBtn").click(function (item) {
                new AddConsult(search, keys, searchParam.tmpltId, searchParam.cnslId, item.data.recId);
            });
            $(".link-consult").click(function () {
                var cnslId = $(this).attr("cnslId");
                var tmpltId = $(this).attr("tmpltId");
                window.open('/src/consult/consultDataManageList.html?cnslId=' + cnslId + '&tmpltId=' + tmpltId);
            });*/
        });
    }

    var genOperation = function (row) {
        var html = "<a class='modifyBtn' href='javascript:void(0)' cnslid='" + row.cnslColmId + "'>修改</a>";
        return html;
    }

        /* var listConfig = {
             el: $("#list-contain"),
             ajax: {
                 method:'POST'
             },
             field: {
                 boxType:'checkbox',
                 key:'recId',
                 button:{
                     width:80,
                     text: '操作',
                     locked: 1,
                     items: [
                         {
                             text: '修改',
                             click: function(e,item){
                                 new AddConsult(search, keys, searchParam.tmpltId, searchParam.cnslId, item.data.recId);
                             }
                         }
                     ]
                 }
             },
             page: {
                 customPages: [5, 10, 20, 50],
                 perPage: 10,
                 total:true,
                 align: 'right',
                 checkedCount: 'currentPage',
                 /!*button: {
                     items: [
                         {
                             text: '导出',
                             click: function(){
                                 var tmpltId=searchParam.tmpltId;
                                 //获取选中行对象
                                 debugger
                                 var checkedData=  list.getAllCheckedRows();
                                 if(!checkedData || checkedData.length == 0){
                                     new Dialog({
                                         mode: 'tips',
                                         content: '请选择要导出的记录'
                                     });
                                 }else{
                                     var recIds = [];
                                     $(checkedData).each(function(){
                                         recIds.push(this.recId);
                                     });
                                     window.location.href = "/consultData/exportConsult?cnsultTmpltId="+tmpltId+"&recIds="+recIds.join(",");
                                 }
                             }
                         },{
                             text: '删除',
                             click: function(){
                                 var selected = list.getCheckedRows();
                                 if(!selected || selected.length == 0){
                                     new Dialog({
                                         mode: 'tips',
                                         content: '请选择要删除的记录'
                                     });
                                 }else{
                                     var deleteDialog = {
                                         title: '删除提示',
                                         content: '确认删除?',
                                         mode: 'confirm',
                                         modal: true,
                                         ok: function(){
                                             var recIds = [];
                                             $(selected).each(function(){
                                                 recIds.push(this.recId);
                                             });

                                             Util.ajax.postJson("/consultData/deleteConsultData", {recIds: recIds.join(",")}, function(data){
                                                 if(data.returnCode == '0'){
                                                     new Dialog({
                                                         mode: 'tips',
                                                         tipsType: 'success',
                                                         content: '删除成功,同步索引中,请稍后查询'
                                                     });

                                                     search();
                                                 }else{
                                                     new Dialog({
                                                         mode: 'tips',
                                                         tipsType: 'error',
                                                         content: data.responseJSON.message
                                                     });
                                                 }
                                                 return true;
                                             },true);
                                         },
                                         okValue: '确定',
                                         cancel: function(){
                                             return true;
                                         },
                                         cancelValue: '取消'
                                     };

                                     new Dialog(deleteDialog);
                                 }
                             }
                         },{
                             text: '批量修改',
                             click: function(){
                                 var selected = list.getCheckedRows();
                                 if(!selected || selected.length == 0){
                                     new Dialog({
                                         mode: 'tips',
                                         content: '请选择更新记录'
                                     });
                                 }else{
                                     var recIds = [];
                                     $(selected).each(function(){
                                         recIds.push(this.recId);
                                     });


                                     new BatchUpdate(search, keys, searchParam.tmpltId, searchParam.cnslId, recIds.join(","));
                                 }
                             }
                         }
                     ]
                 }*!/
             },
             data: {
                 url: ''
             }
         };*/

    /**
     * 查询方法
     */
    var search = function(){
        searchParams = [];
        $("#form-contain").find("li.width-all").each(function(){
            var columnId = $(this).find("select[name=colmId]").val();
            var fieldType = getType(columnId);
            var colmId;

/*            if(fieldType == "num"){
                colmId = "consult_num_" + columnId;
            }/!*else if(fieldType == "date"){
                colmId = "consult_date_" + columnId;
            }*!/else{
                colmId = "keyValue_" + columnId;
            }*/
            var type = $(this).find("select[name=type]").val();
            var value;

            if($(this).find("input[name=value]").eq(0).css("display") === "none"){
                value = trim($(this).find("input[name=value]").eq(1).val());
            }else{
                value = trim($(this).find("input[name=value]").eq(0).val());
                var knwlgId = $(this).find("input[name=value]").eq(0).attr("knwlgId");

                if(value && knwlgId){
                    value = knwlgId;
                }
            }

           /* if(value && getType($(this).find("select[name=colmId]").val()) == "date"){
                value = new Date(value).getTime();
            }*/

            if(colmId && type && value){
                var temp = {};
                temp.columnId = colmId;
                temp.CalculateType = type;
                temp.value = value;
                temp.fieldType = fieldType;

                searchParams.push(temp);
            }
        });

        //发送后台请求;
        if(searchParams.length > 0){
            table.ajax.reload();
           /* list.search({param: JSON.stringify(searchParams), cnslId: searchParam.cnslId});*/
        }else{
            table.ajax.reload();
            /*list.search({cnslId: searchParam.cnslId});*/
        }
    };

    /**
     * 重置方法
     */
    var reset = function(){
        $(paramList).each(function(){
            this.colm.setValue(0);
            this.type.setValue(0);
        });

        $("#form-contain").find("input[name=value]").val("");
    };

    /**
     * 事件初始化
     */
    var eventInit = function(){
        $("#btn-import").click(function(){
            new ImportConsultRecord(searchParam.tmpltId, searchParam.cnslId, table);
        });
        $("#btn-add").click(function(){
            new AddConsult(search, keys, searchParam.tmpltId, searchParam.cnslId);
        });

        $("#btn-batchUpdate").click(function(){

        });

        //查询按钮点击事件
        $("#btn-search").click(function(){
            search();
        });
        //重置按钮点击事件
        $("#btn-reset").click(function(){
            reset();
        });
    };

    /**
     * 获取模板字段值
     */
    var getTmpltKeys = function(){
        Util.ajax.getJson("/consultTmplt/getTmpltColumnNoPage", {tmpltId: searchParam.tmpltId}, function(data){
            if(!data.exception){
                var items = [{
                    data: 'cnslColmId',
                    title: "操作",
                    render: function (data, type, row, meta) {
                        return genOperation(row);
                        }
                    }];

                keys = data.beans;

/*                for(var i=0; i<keys.length; i++){
                    var temp = {};
                    //text: '咨询表名称', name: 'cnslNm', width: '20%', title: 'cnslNm'
                    temp.title = keys.colmNm;
                    temp.data = keys.colmCode;
                    temp.width = '12%';

                    items.push(temp);
                }*/

                $(keys).each(function(){
                    var temp = {};
                    //text: '咨询表名称', name: 'cnslNm', width: '20%', title: 'cnslNm'
                    temp.title = this.colmNm;
                    temp.data = this.colmCode;
                    temp.width = '12%';
/*                    //关联知识类型的   增加格式化方法
                    if(this.colmTypeCd == "rel"){
                        temp.render = function(item,val,$src){
                            $src.on('click', 'a', function(){
                                crossAPI.destroyTab("采编预览");
                                crossAPI.createTab({title:"采编预览",url:"/src/modules/knowledgeManage/knowledgeDetail.html?knwlgId=" + $(this).attr("knwlgId") + "&isPublished=1"});
                            });
                            Util.ajax.getJson("/docEditPus/getNmById", {knwlgId: val}, function(data){
                                if(data.returnCode == "0" && data.object){
                                    $src.html("<a class='link-blue' title='" + data.object + "' href='javascript:void(0);' knwlgId='" + val + "'>" + data.object + "</a>");
                                }else{
                                    $src.html(val);
                                    $src.title = val;
                                }
                            });
                        }
                    }else{
                        if(this.colmTypeCd == "date"){
                            temp.render = function(item,val,$src){
                                //对日期类型的值进行转换
                                try{
                                    if(val && val.match("^[1-9]\\d*$")){
                                        var datetimeType = "";
                                        var date = new Date(parseInt(val));
                                        datetimeType+= date.getFullYear();  //年
                                        datetimeType += "-";
                                        datetimeType += date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : (date.getMonth() + 1);
                                        datetimeType += "-";
                                        datetimeType += date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
                                        datetimeType += " ";
                                        datetimeType += date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
                                        datetimeType += ":";
                                        datetimeType += date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
                                        datetimeType += ":";
                                        datetimeType += date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
                                        $src.attr("title", datetimeType);
                                        return datetimeType;
                                    }else{
                                        return "";
                                    }
                                }catch(e) {
                                    return val;
                                }
                            };
                        }else{
                            temp.title = this.colmId;
                        }*/
                   /* }*/

                    this.name = this.colmNm;
                    this.value = this.colmId;

                    items.push(temp);
                });


                if(items.length == 0){
                    new Dialog({
                        mode: 'confirm',
                        content: '当前模板下没有字段',
                        cancelDisplay: false
                    });
                    return false;
                }


                var listConfig =
                {
                    lengthChange: true,
                    lengthMenu: [5,10,20,50],
                    searching: false,
                    serverSide: true,
                    ajax: {
                        "url": "",
                        "data": function (d) {
                        return $.extend({}, d, searchData());
                        }
                     },
                    columns: [
                    ]
                }

                //构造list配置
                listConfig.columns = items;
                listConfig.ajax = {
                    type:"POST",
                    url: "/consultData/getConsultList",
                    contentType:"application/json",
                    data: function (d) {
                        return JSON.stringify($.extend({}, d, {cnslId:searchParam.cnslId,searchParams:searchParams}));
                    }
                };

                tableInit(listConfig);

                buildSearchForm();
            }else{
                new Dialog({
                    mode: 'tips',
                    tipsType:'error',
                    content: data.message
                });
            }
        }, true);
    };

    /**
     * 查询字段类型
     *
     * @param value
     * @returns {*}
     */
    var getType = function(value){
        for(var i = 0, j = keys.length; i < j;i ++){
            if(keys[i].value == value){
                return keys[i].colmTypeCd;
            }
        }
    };

    /**
     * 构建查询的form
     */
    var buildSearchForm = function(){
        var length = $("#form-contain").find("li.width-all").length;
        count ++;
        var tempHtml = "<li class='width-all'><label>查询条件" + count + "</label><div><span class='search-consult-span-first' id='select" + count + "' argesqo='" + count + "'></span><span class='search-consult-span-second' id='type" + count + "'></span><input class='zxb-f2 search-consult-input' name='value' type='text' disabled><!--<span id='date" + count + "' class='consult-data-date hide'></span>--><span class='zxb-f3'><a href=\"#nogo\" class=\"clk-add\" title=\"添加\"></a><a href=\"#nogo\" class=\"clk-del\" argesqo='" + count + "' title=\"删除\"></a></span></div></li>";
        $("#form-contain").find("li.width-all").eq(length - 1).before(tempHtml);
        var selectConfig1 = {
            el: $("#select" + count),
            name: 'colmId',
            textField:"name",
            valueField:"value",
            topOption:"--请选择--",
            value: 0,
            datas: keys
        };

        var typeData = [
            {
                name: '等于',
                value: "equal"
            },{
                name: '不等于',
                value: "notEqual"
            },{
                name: '包含',
                value: "contain"
            },{
                name: '不包含',
                value: "notContain"
            },{
                name: '大于',
                value: "gt"
            },{
                name: '小于',
                value: "lt"
            }
        ];

        var selectConfig2 = {
            el: $("#type" + count),
            name: 'type',
            textField:"name",
            valueField:"value",
            topOption:"--请选择--",
            value: 0,
            datas: typeData
        };

  /*      var dateConfig = {
            el: $("#date" + count),
            type:"datetime",
            name: 'value'
        };

        var date = new MyDate(dateConfig);*/
/*        $("#date" + count).datepicker({
            autoclose: true,
            language: "ZH-cn",
            format: 'yyyy-mm-dd'
        })*/

        var select1 = new Select(selectConfig1);

        atomSelectVal[count] = "0";

        $(atomSelectList).each(function(){
            if(this.getSelected("value") && this.getSelected("value") != 0){
                select1.disabled(this.getSelected("value"));
            }
        });
        atomSelectList.push(select1);
        var select2 = new Select(selectConfig2);
        select2.disabled();
        select1.on("change", function(e,valueObj){

            select2.enable();
            select2.setValue(0);
            //重置input的属性值
            select2.$el.next().attr("knwlgId", "");
            var typeCode = getType(valueObj.value);
            if(valueObj.value && valueObj.value != "0"){
                $(atomSelectList).each(function(){
                    if(this.$el != select1.$el){
                        this.disabled(valueObj.value);
                    }
                });
            }

            if(atomSelectVal[select1.$el.attr("argesqo")] && atomSelectVal[select1.$el.attr("argesqo")] != "0"){
                $(atomSelectList).each(function(){
                    this.enable(atomSelectVal[select1.$el.attr("argesqo")]);
                });
            }
            atomSelectVal[select1.$el.attr("argesqo")] = valueObj.value;

            if(typeCode === NUM){
                select2.enable("gt");
                select2.enable("lt");
                select2.disabled("contain");
                select2.disabled("notContain");

                //显示隐藏内容
                select2.$el.nextAll(".consult-data-date").addClass("hide");
                select2.$el.next().removeClass("hide");
            }else if(typeCode === DATE){
                select2.enable("gt");
                select2.enable("lt");
                select2.disabled("contain");
                select2.disabled("notContain");

                //显示隐藏内容
                select2.$el.next().addClass("hide");
                select2.$el.nextAll(".consult-data-date").removeClass("hide");

                //禁用组件
                $("#date" + count).disabled();
            }else if(typeCode === REL){
                select2.disabled("gt");
                select2.disabled("lt");
                select2.disabled("contain");
                select2.disabled("notContain");

                //显示隐藏内容
                select2.$el.nextAll(".consult-data-date").addClass("hide");
                select2.$el.next().removeClass("hide");
            }else if(!typeCode){
                select2.disabled();
                //显示隐藏内容
                select2.$el.nextAll(".consult-data-date").addClass("hide");
                select2.$el.next().removeClass("hide");

                //禁用输入框
                select2.$el.next().attr("disabled", true);
                select2.$el.next().val("");
            }else{
                select2.disabled("gt");
                select2.disabled("lt");
                select2.enable("contain");
                select2.enable("notContain");

                //显示隐藏内容
                select2.$el.nextAll(".consult-data-date").addClass("hide");
                select2.$el.next().removeClass("hide");
            }
        });

        select2.on("change", function(e,valueObj){
            select2.$el.next().unbind("click");
            select2.$el.next().attr("readonly", false);
            if(!valueObj.value || valueObj.value == "0"){
               //禁用内容
                select2.$el.next().attr("disabled", true);
/*                $("#date" + count).disabled();*/
                //清空值
                select2.$el.next().val("");
/*                $("#date" + count).$el.find("input").val("");*/
            }else{
                //启用组件
                select2.$el.next().attr("disabled", false);
/*
                $("#date" + count).enable();
*/
            }

/*            //关联知识类型的  继续禁用组件 但是要绑定点击事件
            if(getType(select1.getSelected("value")) == REL && valueObj.value && valueObj.value != "0"){
                select2.$el.next().attr("readonly", true);
                select2.$el.next().click(function(e){
                    var maxX = $(this).offset().left + $(this).innerWidth() - 25;
                    if(e.pageX > maxX&&$(this).hasClass('clear-bg')) {
                        $(this).val('');
                        $(this).attr("data", "");
                        return;
                    }
                    new SelectKnowledge($(this));
                });
            }*/
        });

        var temp = {};
        temp.colm = select1;
        temp.type = select2;

        paramList.push(temp);
        //绑定点击事件之前  先解除之前的绑定
        $(".clk-add").unbind("click");
        $(".clk-del").unbind("click");
        //给添加删除按钮绑定事件
        $(".clk-add").click(function(){
            buildSearchForm();
        });
        $(".clk-del").click(function(){
            length = $("#form-contain").find("li.width-all").length;
            if(length > 2){
                $(this).parents("li.width-all").remove();
                var argesqo = $(this).attr("argesqo");
                $(atomSelectList).each(function(i){
                    if(this.$el.attr("argesqo") == argesqo){
                        var temp = this;
                        $(atomSelectList).each(function(){
                            this.enable(temp.getSelected("value"));
                        });
                        atomSelectList.splice(i, 1);
                    }
                });
            }
        });
    };

    /**
     * 初始化方法
     */
    var initialize = function(){
        getTmpltId();
        if(!searchParam.tmpltId){
            new Dialog({
                mode: 'tips',
                tipsType:'error',
                content: "模板Id为空"
            });
            return;
        }
        getTmpltKeys();
        eventInit();
    };

    return initialize();
});