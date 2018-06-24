require(['Util', 'list', 'simpleTree', 'groupSearchForm', 'public/src/js/dialog/dialog', 'js/constants/constants', 'crossAPI', 'js/knowledgeManage/docRespPrsn','js/consult/addConsultTmplt'],
    function (Util, List, SimpleTree, GroupSearchForm, Dialog, Constants, crossAPI, docRespPrsn,AddConsultTmplt) {
        var dataList = null;//数据列表
        var maxLevel = 3;
        var zTreeObj = null;
        var catlId = null;//当前选中的目录ID
        var oldName = null; //用于记录重命名时该节点之前的名称
        var isUpdate = false; //用于判断保存目录名称时执行的是新增操作还是修改操作
        var _root = window.location.protocol + "//" + window.location.host;
        var queryParam;
        var trim = function (str) {//注册去除空格方法
            if (str) {//要去除空格的字符串
                return str.replace(/(^\s*)|(\s*$)/g, "");//去除空格
            }
        }//注册去除空格方法 结束

        //组合表单
        var formInit = function () {
            var config = {
                el: '.t-list-search', // * 要绑定的容器
                className: 'groupSearchForm', //组件外围的className
                title: '咨询表模板列表', //查询表单表头
                column: 3, //表单文本框的列数(每一行表单项的个数)，枚举值：2、3、4，默认4。
                items: [ // 表单属性信息 及页面显示顺序
                    {
                        element: 'input', //子组件类型（必须）
                        label: '模板名称', // 输入框label（必须）
                        name: 'tmpltNm' //该表单元素的name，对应getDate方法获取到的json数据的名（必须）

                    },
                    {
                        element: 'selectTree',
                        //子组件类型（必须）
                        label: '适用地区',
                        name: 'authRegn',
                        config: {
                            check: false,
                            selectText: false,
                            childNodeOnly: true,
                            expandAll: false,
                            closeSearch: true,
                            checkAllNodes: false,
                            async: {
                                enable: true,
                                url: Constants.AJAXURL + '/kmConfig/getDistrictBySuprRegnId?maxLevel=' + maxLevel,//路径
                                autoParam: ['id']
                            }
                        },
                        url: Constants.AJAXURL + '/kmConfig/getRootById?v=' + new Date().getTime()//路径
                    },
                    {
                        element: 'input',
                        label: '操作人',
                        name: 'opPrsnId',
                        config: {
                            attr: {
                                placeholder: '请输入采编人',
                                readonly: "readonly"
                            }
                        }
                    },
                    {
                        element: 'doubleDate',
                        label: ['开始时间', '结束时间'],
                        name: ['startTime', 'endTime'],
                        config: [
                            {
                                type: "datetime",
                                format: 'yyyy-MM-dd HH:mm:ss',
                                clearFunc: function () {
                                    groupSearchForm.options.items[3].compt[1].options.min = '2000-01-01 00:00:00';
                                },
                                done: function (dates) {
                                    groupSearchForm.options.items[3].compt[1].options.min = dates;
                                }
                            },
                            {
                                type: "datetime",
                                format: 'yyyy-MM-dd HH:mm:ss',
                                clearFunc: function () {
                                    groupSearchForm.options.items[3].compt[0].options.max = '2099-01-01 00:00:00';
                                },
                                done: function (dates) {
                                    groupSearchForm.options.items[3].compt[0].options.max = dates;
                                }
                            }
                        ]
                    }
                ],
                button: {
                    position: 'right', //按钮位置。枚举值：left、right、center、absolute,默认left
                    config: { //应用的buttonGropu组件
                        items: [ //按钮配置集合
                            {
                                text: '查询', //按钮上的文本
                                type: '1', //按钮类型  0普通按钮(默认灰)|1焦点按钮(蓝)|2特殊按钮(红)
                                click: function (e) { //按钮点击时触发的回调函数
                                    tmpltSearch();
                                }
                            },
                            {
                                text: '重置',
                                type: '0',
                                click: function (e) {
                                    groupSearchForm.reset(); //将所有表单元素重置为初始值
                                }
                            }
                        ]
                    }
                }
            }
            var groupSearchForm = new GroupSearchForm(config);
        }

        //查询
        var tmpltSearch = function () {
            queryParam = {};
            if (trim($("input[name='tmpltNm']").val()) != "") {//模板标题
                queryParam.tmpltNm = trim($("input[name='tmpltNm']").val());
            }
            if (trim($("input[name='authRegn']").val())) {//适用地区
                queryParam.authRegn = trim($("input[name='authRegn']").val());
            }
            if (trim($("input[name='opPrsnId']").val())) {//操作人员
                queryParam.opPrsnId = trim($("input[name='opPrsnId']").val());
            }
            if (trim($("input[name='startTime']").val())) {//开始时间
                queryParam.startTime = trim($("input[name='startTime']").val());
            }
            if (trim($("input[name='endTime']").val())) {//结束时间
                queryParam.endTime = trim($("input[name='endTime']").val());
            }
            if (catlId != null && catlId != 0) {
                queryParam.catlId = catlId;
            }
            dataList.search(queryParam);
        }

        var eventInit = function () {
            $("#addNewTmpltBtn").click(function (e) {
                if (catlId == ''||catlId == 0) {
                    new Dialog({
                        mode: 'tips',
                        content: '请选择需要的模板目录'
                    });
                    return;
                }

                var transParam = {
                    catlId: catlId,
                    isUpdate: false,
                    list: dataList,
                    dataParam: queryParam
                };
                new AddConsultTmplt(transParam);

            });

            $("input[name=opPrsnId]").click(function (e) {
                //点击清空时  不触发弹出事件
                var maxX = $(this).offset().left + $(this).innerWidth() - 25;
                if (e.pageX > maxX && $(this).hasClass('clear-bg')) {
                    $(this).val('');
                    $(this).attr("data", "");
                    return;
                }
                new docRespPrsn(this);
            });
            var content = "";
            var flag = true;
            $('#catl-add').on('click', function () { //新增节点点击事件
                var flag = true;
                var selectedNode = zTreeObj.getSelectedNodes()[0];//获取选中节点
                if (selectedNode == null && flag == true) {
                    content = "请先选择目录";
                    flag = false;
                }
                if (selectedNode.isParent) {
                    zTreeObj.expandNode(selectedNode, true, false, true, true);
                }
                if (selectedNode != null && selectedNode.level >= 5 && flag == true) {
                    zTreeObj.selectNode(selectedNode);
                    content = "不能再建更深层级的目录了";
                    flag = false;
                }
                if (flag != true) {
                    new Dialog({//提示
                        mode: 'tips',
                        tipsType: 'error',
                        content: content
                    });
                    return;
                }
                var lz = zTreeObj.addNodes(selectedNode, -1, [
                    {
                        name: "新建目录"
                    }
                ]);
                zTreeObj.editName(lz[0]);
                isUpdate = false;
            });
            $('#catl-edit').on('click', function () { //重命名点击事件
                var flag = true;
                var selectedNode = zTreeObj.getSelectedNodes()[0];
                if (selectedNode == null && flag == true) {
                    content = "请先选择目录";
                    flag = false;
                }
                var node = zTreeObj.getSelectedNodes();
                if ($('#catl-edit').hasClass("disabled")) {
                    content = "该录不能重命名";
                    flag = false;
                }
                if (flag != true) {
                    new Dialog({//提示
                        mode: 'tips',
                        content: content
                    });
                    return;
                }
                zTreeObj.editName(node[0]); //编辑节点
                isUpdate = true;
                oldName = node[0].name;
            });
            //点击删除目录
            $("#catl-del").click(function () {
                var flag = true;
                var selectedNode = zTreeObj.getSelectedNodes()[0];
                if (selectedNode == null && flag == true) {
                    content = "请先选择目录";
                    flag = false;
                }
                if (selectedNode.isParent == true && flag == true) {
                    content = "父目录不允许删除";
                    flag = false;
                }
                if($("#catl-del").hasClass("disabled") && flag == true){
                    content = "该录不允许删除";
                    flag = false;
                }
                if (flag != true) {
                    new Dialog({//提示
                        mode: 'tips',
                        content: content
                    });
                    return;
                }
                delete_catalog();
            });
            //点击上移
            $("#catl-up").click(function () {
                move("prev");
            });
            //点击下移
            $("#catl-down").click(function () {
                move("next");
            });
        }

        var delete_catalog = function () {
            var selectedNode = zTreeObj.getSelectedNodes()[0];//获取选中的目录
            Util.ajax.ajax({
                type: 'POST',//请求方式
                data: {
                    'catlId': selectedNode.id//目录ID
                },
                url: Constants.AJAXURL + '/consultTmplt/getConsultList',//获取目录下的知识
                success: function(json) {//请求成功返回的数据
                    if (json.bean.total != 0) {//如果目录下有知识
                        new Dialog({//提示'该目录下有存量知识，无法直接删除'
                            mode: 'tips',
                            tipsType: 'error',
                            content: '该目录下有存量模板，无法直接删除！'
                        });
                        return false;//返回,方法结束
                    } else {//如果目录没有知识
                        var config = {//提示'确定要删除该知识目录吗'
                            mode: 'confirm',
                            id: 'confirmId',
                            content: '确定要删除该知识目录吗？'
                        }
                        var dialog = new Dialog(config);//注册提示框
                        dialog.on('confirm',
                        function() {//回调函数
                            zTreeObj.removeNode(selectedNode);//删除页面上的目录
                            var id = selectedNode.id;//要删除的目录id
                            var condition = {
                                catlId: id//请求参数
                            };
                            Util.ajax.postJson(Constants.AJAXURL + '/consultCatl/deleteConsultCatl', condition,//发送请求
                            function(date) {//请求回调函数
                                if (date.returnCode == "0") {//判断返回信息
                                    new Dialog({//提示'删除成功'
                                        mode: 'tips',
                                        tipsType: 'success',
                                        content: '删除成功'
                                    });
                                }
                            });
                        });
                    }
                },
                error: function() {//请求失败后调用
                    return;//返回,方法结束
                }
            });
        }

        var move = function (type) {
            var node = zTreeObj.getSelectedNodes()[0];
            var content = "";
            if (node == null) {
                content= '请先选择目录';
            }
            var targetNode = null;
            if(type=="prev"){
                targetNode = node.getPreNode();
                if(targetNode == null){
                    content= '已无法上移';
                }
            }else{
                targetNode = node.getNextNode();
                if(targetNode == null){
                    content= '已无法下移';
                }
            }
            if(content != ""){
                new Dialog({//提示'目录名字不能为空'
                    mode: 'tips',
                    content: content
                });
                return false;
            }
            zTreeObj.moveNode(targetNode, node, type);//调用zTreeAPI
            var param={
                catlId:node.id,
                argeSeqno:node.seqno,
                targetCatlId:targetNode.id,
                targetSeqno:targetNode.seqno
            }
            Util.ajax.ajax({
                type: "GET",//请求方式
                async: false,//是否异步
                data: param,//参数
                url: Constants.AJAXURL + '/consultCatl/updateConsultCatl?v=' + new Date().getTime(),//路径
                success: function(data) {
                    if(data.returnCode == "0"){
                        var nodeSeqno = node.seqno;
                        node.seqno = targetNode.seqno;
                        targetNode.seqno = nodeSeqno;
                        buttonStatus(zTreeObj.getSelectedNodes()[0]);
                    }else{
                        if(type == "prev"){
                            zTreeObj.moveNode(targetNode, node, "next");
                        }else{
                            zTreeObj.moveNode(targetNode, node, "prev");
                        }
                    }
                },
                error: function(datas) {}//请求失败
        });
        }

        var buttonStatus = function(treeNode){
            if(treeNode.id==0){
                $("#catl-edit").addClass("disabled");
                $("#catl-del").addClass("disabled");
            }else{
                if($("#catl-edit").hasClass("disabled")){
                    $("#catl-edit").removeClass("disabled");
                }
                if($("#catl-del").hasClass("disabled")){
                    $("#catl-del").removeClass("disabled");
                }
            }
            if(treeNode.getNextNode() == null){
                $("#catl-down").addClass("disabled");
            }else{
                if($("#catl-down").hasClass("disabled")){
                    $("#catl-down").removeClass("disabled");
                }
            }
            if(treeNode.getPreNode() == null){
                $("#catl-up").addClass("disabled");
            }else{
                if($("#catl-up").hasClass("disabled")){
                    $("#catl-up").removeClass("disabled");
                }
            }
        }
        //保存树数据
        var save_catalog = function (event, treeId, treeNode) {
            var name = trim(treeNode.name);//去空格
            if (name == "undefined" || name == "" || name == null || name.length == 0) {//检验名称
                if (isUpdate) {//修改
                    treeNode.name = oldName;
                    zTreeObj.updateNode(treeNode);
                    zTreeObj.selectNode(treeNode);
                } else {//新增
                    zTreeObj.removeNode(treeNode);
                    zTreeObj.selectNode(treeNode.getParentNode());
                }
                new Dialog({//提示'目录名字不能为空'
                    mode: 'tips',
                    tipsType: 'error',
                    content: '目录名字不能为空'
                });
                return;//返回
            }
            if (name.indexOf("<") >= 0 || name.indexOf(">") >= 0) {//校验非法字符
                if (isUpdate) {//修改
                    treeNode.name = oldName;
                    zTreeObj.updateNode(treeNode);
                    zTreeObj.selectNode(treeNode);
                } else {//新增
                    zTreeObj.removeNode(treeNode);
                    zTreeObj.selectNode(treeNode.getParentNode());
                }
                new Dialog({//提示'目录名字不能包含"<"、">"'
                    mode: 'tips',
                    tipsType: 'error',
                    content: '目录名字不能包含"<"、">"'
                });
                return;//返回
            }
            if (name.length >= 50) {//检验长度
                if (isUpdate) {//修改
                    treeNode.name = oldName;
                    zTreeObj.updateNode(treeNode);
                    zTreeObj.selectNode(treeNode);
                } else {//新增
                    zTreeObj.removeNode(treeNode);
                    zTreeObj.selectNode(treeNode.getParentNode());
                }
                new Dialog({//提示'保存失败，目录名称过长'
                    mode: 'tips',
                    tipsType: 'error',
                    content: '保存失败，目录名称过长'
                });
                return false;//返回
            }
            //先做判断，同级节点名称是否相同
            var nodes = treeNode.getParentNode().children;//父节点
            var count = 0;
            for(var i=0;i<nodes.length;i++){
                if(isUpdate == true){
                    if(name==nodes[i].name && treeNode.id != nodes[i].id){
                        new Dialog({//保存失败，目录名称重复
                            mode: 'tips',
                            tipsType: 'error',
                            content: '保存失败，目录名称重复'
                        });
                        treeNode.name = oldName;//名字
                        zTreeObj.updateNode(treeNode);//ztreeAPI
                        zTreeObj.selectNode(treeNode);//ztreeAPI
                        return false;//返回
                    }
                }else{
                    if(name==nodes[i].name){
                        count += 1;
                        if(count > 1){
                            new Dialog({//提示'保存失败，目录名称过长'
                                mode: 'tips',
                                tipsType: 'error',
                                content: '保存失败，目录名称重复'
                            });
                            zTreeObj.removeNode(treeNode);//ztreeAPI
                            zTreeObj.selectNode(treeNode.getParentNode());//ztreeAPI
                            return false;//返回
                        }
                    }
                }
            }
            var params;
            var requestUrl;
            params = {
                "catlId": treeNode.id,
                "catlNm": name
            };
            if(isUpdate == false){
                params.suprCatlId = treeNode.getParentNode().id;
                requestUrl = Constants.AJAXURL + '/consultCatl/saveConsultCatl?v=' + new Date().getTime();//新增路径
            }else{
                requestUrl = Constants.AJAXURL + '/consultCatl/updateConsultCatl?v=' + new Date().getTime();//修改路径
            }
            Util.ajax.ajax({
                type: "GET",//请求方式
                async: false,//是否异步
                data: params,//参数
                url: requestUrl,//路径
                success: function (object) {//返回信息
                    if (object.returnCode == "0") {
                        new Dialog({//提示
                            mode: 'tips',
                            tipsType: 'success',
                            content: '保存成功'
                        });
                        if (isUpdate == false) {
                            treeNode.id = object.bean.catlId;//赋值
                            treeNode.seqno = object.bean.seqno;
                        }
                        treeNode.name = name;//赋值
                        buttonStatus(treeNode);//判断按钮状态
                        zTreeObj.getSelectedNodes(treeNode);
                    } else {
                        if (isUpdate) {//修改
                            treeNode.name = oldName;
                            zTreeObj.updateNode(treeNode);
                            zTreeObj.selectNode(treeNode);
                        } else {//新增
                            zTreeObj.removeNode(treeNode);
                            zTreeObj.selectNode(treeNode.getParentNode());
                        }
                        new Dialog({//提示
                            mode: 'tips',
                            tipsType: 'error',
                            content: '保存失败'
                        });
                        return false;
                    }
                },
                error: function (datas) {//请求失败
                    return;//返回
                }
            });

        }

        //点击树
        var zTreeOnClick = function () {
            catlId = zTreeObj.getSelectedNodes()[0].id;
            buttonStatus(zTreeObj.getSelectedNodes()[0]);
            tmpltSearch();
        }

        //树初始化
        var zTreeInit = function () {
            $("#treeDemo").html("");
            var treeConfig = {
                hasLine: true,//是否有节点连线
                async: {
                    enable: true, //是否开启异步加载模式
                    //以下配置,async.enable=true时生效
                    url: Constants.AJAXURL + "/consultCatl/getConsultCatl?v=" + new Date().getTime(),//路径 //Ajax获取数据的地址
                    type: "get", //Ajax的http请求模式
                    autoParam: ["id=catlId"], //异步加载时需要自动提交父节点属性的参数
                    dataFilter: function (treeId, parentNode, datas) {//数据
                        var childrenDatas = [];
                        for(var i = 0; i < datas.beans.length; i++){
                            var childrenData = {
                                "id":datas.beans[i].catlId,
                                "name":datas.beans[i].catlNm,
                                "pId":datas.beans[i].suprCatlId,
                                "seqno":datas.beans[i].argeSeqno,
                                "isParent":datas.beans[i].isParent
                            }
                            childrenDatas.push(childrenData);
                        }
                        return childrenDatas;
                    }
                },
                view: {
                    showTitle: true
                },
                callback: {
                    onClick: zTreeOnClick,//点击事件
                    onRename: save_catalog
                },
                data: {
                    simpleData: {
                        enable: true,
                        idKey: "id",
                        pIdKey: "pId",
                        rootPId: 0/*,
                        name:"catlNm",
                        seqno:"argeSeqno",
                        isParent:"isParent"*/
                    }
                }
            };
            Util.ajax.ajax({
                type: "GET",//请求方式
                url: Constants.AJAXURL + "/consultCatl/getConsultCatl?catlId=0&v=" + new Date().getTime(),//路径
                async: false,//是否异步
                success: function (datas) {//返回消息
                      //var treeDatas = datas.beans;
                    var treeDatas = [];
                    for (var i = 0; i<datas.beans.length;i++){
                        var treeData = {
                            "id":datas.beans[i].catlId,
                            "name":datas.beans[i].catlNm,
                            "pId":datas.beans[i].suprCatlId,
                            "seqno":datas.beans[i].argeSeqno,
                            "isParent":datas.beans[i].isParent
                        };
                        if(datas.beans[i].catlId == 0){
                            treeData.open = true;
                            var childrenDatas = [];
                            for(var j=0;j<datas.beans[i].children.length;j++){
                                var data = datas.beans[i].children[j];
                                var childrenData = {
                                    "id":data.catlId,
                                    "name":data.catlNm,
                                    "pId":data.suprCatlId,
                                    "children":data.children,
                                    "seqno":data.argeSeqno,
                                    "isParent":data.isParent
                                }
                                childrenDatas.push(childrenData);
                            }
                            treeData.children=childrenDatas;
                        }
                        treeDatas.push(treeData);
                    }
                    zTreeObj = $.fn.zTree.init($('#treeDemo'), treeConfig, treeDatas);//初始化
                    selctTreeNode = zTreeObj.getNodesByParam("id", "0", null)[0];//获取根节点
                    zTreeObj.selectNode(selctTreeNode);//选中
                    zTreeOnClick(null, "treeDemo", selctTreeNode);
                },
                error: function () {//请求失败
                    console.log("==================================加载业务树失败！");
                }
            });
        }

        //数据列表内的删除方法
        var deleteTemplet = function(item){
            var  deleteTempl = function(){
                var tmpltId = item.data.tmpltId;
                Util.ajax.postJson(Constants.AJAXURL + "/consultTmplt/deleteConsultTmpltInfo", {tmpltId: tmpltId}, function (data) {
                    if (data.returnCode == 0) {
                        new Dialog({
                            mode: 'tips',
                            tipsType: 'success',
                            content: '模板已成功删除!'
                        });
                        tmpltSearch();
                    } else {
                        new Dialog({
                            mode: 'tips',
                            tipsType: 'error',
                            content: data.responseJSON.message
                        });
                    }
                });
            }
            var config = {
                mode: 'confirm',
                id: 'confirmId',
                content: '确定要删除该模板吗？'
            }
            var dialog = new Dialog(config);
            dialog.on('confirm',deleteTempl);
        }

        //添加相似操作方法
        var extendTemplet = function(item){

            var transParam = {
                authRegn: item.authRegn,
                catlId : item.catlId,
                tmpltId : item.tmpltId,
                tmpltNm: item.tmpltNm,
                list : dataList,
                dataParam : queryParam
            };
            new AddConsultTmplt(transParam);
        }
        //数据列表的修改方法
        var updateTempl = function(item){

            var transParam = {
                isUpdate:true,
                authRegn: item.authRegn,
                tmpltId : item.tmpltId,
                tmpltNm: item.tmpltNm,
                list : dataList,
                dataParam : queryParam
            };
            new AddConsultTmplt(transParam);
        }

        //数据列表
        var listInit = function () {
            var config = {
                el: '.t-list-result',  //要绑定的容器
                field: {     //字段设置
                    boxType: 'checkbox',     //行类型，checkbox复选框|radio单选(以选中行特殊颜色标识单选，无单选框)
                    key: 'tmpltId',           //主键，必须设置该项
                    items: [     //列设置，必须设置该项
                        {
                            text: '模板名称', name: 'tmpltNm', width: '15%', title: 'tmpltNm',
                            render: function (item, val, $src) {
                               $src.on('click', 'a', function (e) {
                                   crossAPI.destroyTab("编辑咨询表模板");
                                   crossAPI.createTab({title:"编辑咨询表模板",url:_root+Constants.PREAJAXURL+"/src/modules/consult/consultTmpltDetail.html?tmpltId="+item.tmpltId});
                                })
                                return "<a class='knwlgLink' href='javascript:;' id='" + item.tmpltId + "'>" + val + "</a>";
                            }
                        },
                        {
                            text: '适用地区',
                            width:'20%',
                            title:'authRegnNm',
                            name: 'authRegnNm'
                        },
                        {
                            text: '更新时间',
                            name: 'modfTime'
                        },
                        {
                            text: '操作人',
                            name: 'opPrsnId',
                            render: function (item, val, $src) {
                                var userName = val;
                                if (!userName) {
                                    return "";
                                }
                                Util.ajax.getJson(Constants.AJAXURL + '/kmGroup/getTramsName', {//路径
                                        emapvPrsnId: userName
                                    },
                                    function (data) {
                                        if (data.returnCode == 0) {
                                            if (data.bean && data.bean != "null") {
                                                userName = data.bean;
                                                $src.html('<span title="' + userName + '">' + userName + '</span>');
                                            } else {
                                                $src.html('<span title="' + userName + '">' + userName + '</span>');
                                            }
                                        }
                                    });
                            }
                        }
                    ],
                    button: {
                        text: '操作',
                        width: "20%",
                        items: [ //操作区域按钮设置
                            {
                                text: '修改',  //按钮文本
                                name: 'editor',  //按钮名称
                                click: function (e, item) {     //按钮点击时处理函数
                                    updateTempl(item.data)
                                }
                            },
                            {
                                text: '导出',  //按钮文本
                                name: 'editor',  //按钮名称
                                click: function (e, item) {     //按钮点击时处理函数
                                    window.location.href = Constants.AJAXURL + "/kmConsult/downldConsult?cnsultTmpltId="+item.data.tmpltId+"&v=" + new Date().getTime();
                                }
                            },
                            {
                                text: '添加相似',  //按钮文本
                                name: 'editor',  //按钮名称
                                click: function (e, item) {     //按钮点击时处理函数
                                    extendTemplet(item.data);
                                }
                            },
                            {
                                text: '删除',  //按钮文本
                                name: 'editor',  //按钮名称
                                click: function (e, item) {     //按钮点击时处理函数
                                    deleteTemplet(item);
                                }
                            }
                        ]
                    }
                },
                page: {//分页设置
                    // noPages: true, // 设置是否配置分页器，枚举值true | false;设为true时，不配置分页器，默认值false
                    customPages: [5,10,20,50],     //可选择每页显示多少条
                    perPage: 10,     //默认每页显示多少条记录
                    total: true     //是否显示总记录数
                },
                data: {  //数据源设置
                    url: Constants.AJAXURL + '/consultTmplt/getConsultList'//路径
                }
            }
            dataList = new List(config);
        }

        var initialize = function () {
            formInit.call(this);
            listInit.call(this);
            eventInit.call(this);
            zTreeInit.call(this);

        }

        $(function () {
            new initialize();
        });
    });