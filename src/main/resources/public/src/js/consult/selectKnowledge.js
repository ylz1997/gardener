/**
 * @Description: 知识关联
 * @Date:2017/12/04
 * @Author: duft
 */
 define(['Util','list','public/src/js/dialog/dialog','groupSearchForm','text!modules/knowledgeManage/selectKnowledge.tpl','js/constants/constants','validator'],
 function(Util,List,Dialog,GroupSearchForm,selectKnowledgeTpl,Constants,Validator){
      //系统变量-定义该模块的根节点
     var $el = $(selectKnowledgeTpl);
     this.context = $el;
     var knowledgeManageList=null;
     var validator = null;
     var param = {};//全局参数
     var regnData;
     var trim = function (str) {
         if (str) {
             return str.replace(/(^\s*)|(\s*$)/g, "");
         }
     };

      //关联知识选择
     /**
      * 弹出关联知识选择页面，选中知识后展示到tabid
      *
      * @return targetObj:关联知识对象
      */
     var selectKnowledge = function(targetObj){
          var dialogConfig = {
              title: "关联知识", //弹窗标题，
              content: selectKnowledgeTpl, //弹窗内容，可以是字符串、html代码段,tpl对象。默认为loading状态（由点组成的圈。大段代码建议使用 tpl。
              ok: function(){
                  var selected = knowledgeManageList.getSelected();
                  if(selected){
                      targetObj.val(selected.knwlgNm);
                      targetObj.attr("knwlgId", selected.knwlgId);
                  }
              },
              okValue: '确定', //确定按钮的文本 默认是 ‘ok’
              cancel: function() {
              },
              cancelValue: '取消', //取消按钮的文本 默认是 ‘关闭’
              modal: true ,
              width: 800,
              height: 360
          }
          new Dialog(dialogConfig);
          var validators = {
              el: $("#queryForm"),
              dialog:true,
              msgPos: 'top',
              rules:{
                  catlId:"required"
              },
              messages:{
              }
          }
          validator =  new Validator(validators);
          var buttonGroupConfig = {
              items: [ //按钮配置集合
                  {
                      className: 'search',
                      text: '查询',
                      type: '1',
                      click: function() {
                          if(validator.form()){
                              if(trim($("#queryForm").find("input[name='catlId']").val())){
                                  param.catlId = trim($("#queryForm").find("input[name='catlId']").val());
                              }
                              if(trim($("#queryForm").find("select[name=tmpltId]").val())){
                                  param.tmpltId = trim($("#queryForm").find("select[name=tmpltId]").val());
                              }
                              if(trim($("#queryForm").find("input[name='regnId']").val())){
                                  param.regnId = trim($("#queryForm").find("input[name='regnId']").val());
                              }
                              if(trim($("#queryForm").find("input[name='knwlgNM']").val())){
                                  param.knwlgNm = trim($("#queryForm").find("input[name='knwlgNM']").val());
                              }
                              if(trim($("#queryForm").find("input[name='startDate']").val())){
                                  param.startDate = trim($("#queryForm").find("input[name='startDate']").val());
                              }
                              if(trim($("#queryForm").find("input[name='endDate']").val())){
                                  param.endDate = trim($("#queryForm").find("input[name='endDate']").val());
                              }
                              knowledgeManageList.options.data.url = Constants.AJAXURL+'/docEditPus/getDocEditPusInfoForKnowlgRelation';
                              knowledgeManageList.search(param);
                              param = {};
                          }
                      }
                  },{
                      className: 'reset',
                      text: '重置',
                      type: '0',
                      click: function() {
                          groupSearchForm.reset(); //将所有表单元素重置为初始值
                          //组件有问题导致树组件不会回复默认值
                          $("#queryForm").find("input[name=catlId]").val("0");
                      }
                  }
              ]
          };

          if(!regnData){
              Util.ajax.getJson(Constants.AJAXURL + '/kmConfig/getTKmDistrictConfigListBySuprRegnIdForList?v=' + new Date().getTime(),{}, function(data){
                  console.log(data);
                  if(data.returnCode == "0"){
                      if(data.beans[0].id ==  '000'){
                          data.beans[0].children = null;
                          regnData = data.beans[0];
                      }else{
                          var temp = {name: '全国', id: '000', value: '000', open: true};
                          temp.children = [data.beans[0]];
                          regnData = temp;
                      }
                  }else{
                      new Dialog({
                          mode: 'tips',
                          tipsType: 'error',
                          content: data.responseJSON.message
                      });
                  }
              }, true);
          }

          var groupSearchFormConfig = {
              el: $('#queryForm'),
              className: 'groupSearchForm',
              title: '',
              column: 3,//表单列数
              items: [
                  {
                      element: 'selectTree', //子组件类型（必须）
                      label: '业务树',
                      name: 'catlId',
                      config: {
                          check: false,
                          selectText: false,
                          childNodeOnly: true,
                          expandAll: false,
                          closeSearch: true,
                          checkAllNodes:false,
                          async:{
                              enable: true,//是否开启异步加载模式
                              //以下配置,async.enable=true时生效
                              url: Constants.AJAXURL+"/docCatalog/getCatalog?time="+new Date().getTime(),//Ajax获取数据的地址
                              type: "get",//Ajax的http请求模式
                              autoParam:["id"],//异步加载时需要自动提交父节点属性的参数
                              dataFilter: function (treeId, parentNode, childNodes) {
                                  if (!childNodes){return null};
                                  var tempArr = [];
                                  $(childNodes.beans).each(function(){
                                      var temp = {};
                                      temp.id = this.id;
                                      temp.name = this.name;
                                      temp.isParent = this.isParent;
                                      tempArr.push(temp);
                                  });
                                  return tempArr;
                              }
                          },
                          text: '业务树',
                          value: '0'
                      },
                      url: Constants.AJAXURL + "/docCatalog/getAllCatalogForKnowlgRelation?id=0&time=" + new Date().getTime()
                  },{
                      element: 'select',
                      label: '模板树',
                      name: 'tmpltId',
                      config:{
                          topOption: "所有", //设置最顶部option的text属性
                          value: '',//初始选中项设置 默认是按value，如果你想按id设置 也可以 value:["id",1],这样设置
                          textField:'tmpltNm',
                          valueField:'tmpltId',
                          ajax:{
                              method:'GET'
                          }
                      },
                      url: Constants.AJAXURL + "/TmpltInfo/getAllTmpltInfo?v" + new Date().getTime()
                  },{
                      element: 'selectTree', //子组件类型（必须）
                      label: '地区',
                      name: 'regnId',
                      config: {
                          check: false,
                          selectText: false,
                          childNodeOnly: true,
                          expandAll: false,
                          closeSearch: true,
                          checkAllNodes:false,
                          async: {
                              enable: false
                          }
                      },
                      url: regnData
                      //url: Constants.AJAXURL + '/kmConfig/getRootById?v=' + new Date().getTime()
                  },{
                      element: 'input',
                      label: '知识标题',
                      name: 'knwlgNM',
                      config: { //自定义config,如果element是input，可选值为：className,attr
                          className: '',
                          attr: { //input的属性
                              placeholder: '请输入知识标题'
                          }
                      }
                  },{
                      element: 'doubleDate',
                      label: ['开始时间', '结束时间'],
                      name: ['startDate', 'endDate'],
                      config:[{type:"datetime",format:'yyyy-MM-dd HH:mm:ss'},
                          {type:"datetime",format:'yyyy-MM-dd HH:mm:ss'}]
                  }
              ],
              button: {
                  position: 'right',
                  config: buttonGroupConfig
              }
          };
          var groupSearchForm = new GroupSearchForm(groupSearchFormConfig);
          var listConfig = {
              el: "#listContainer",
              ajax: {
                  method: 'POST'
              },
              field: {
                  boxType: 'radio',
                  key: 'knwlgId',
                  items: [
                      {text: '知识标题', name: 'knwlgNm'}
                  ]
              },
              page: {//分页配置
                  customPages: [5, 10, 20,50],
                  perPage: 10,
                  align: 'right'
              },
              data:{
                  url:Constants.AJAXURL+'/docEditPus/selectKnowledgePus?flag=false'
              }
          };
          knowledgeManageList = new List(listConfig);
         knowledgeManageList.search({catlId: '0'});
         /**
          * 初始化完成改变选中状态
          */
         knowledgeManageList.on("afterBuild", function(result){
             var beans = result.beans;
             var val = targetObj.attr("knwlgId");
             if(val){
                 $(beans).each(function(index){
                     var knwlgId = targetObj.attr("knwlgId");
                     if(knwlgId && this.knwlgId == knwlgId){
                         $("#listContainer").find("table.sn-list-also").find("tr").eq(index).addClass("selected");
                     }
                 });
             }
         });

      }
    return selectKnowledge;

 });
