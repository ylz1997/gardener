<div class="container-fluid" id="containerDiv">
    <form class="form-inline" style="padding:10px;" role="form">
        <div class="form-group" style="width:30%">
            <div class="input-group">
                <span class="input-group-addon" >中文名</span>
                <input type="text" class="form-control searchParam" id="chNm" placeholder="请输入学生中文名" >
            </div>
        </div>
        <div class="form-group" style="width:30%">
            <div class="input-group">
                <span class="input-group-addon">英文名</span>
                <input type="text" class="form-control searchParam" id="enNm" placeholder="请输入学生英文名">
            </div>
        </div>
        <div class="form-group" style="width:30%">
            <div class="input-group">
                <span class="input-group-addon">班级</span>
                <select id="classId"  class="form-control searchParam">
                    <option value="1">Brown</option>
                    <option value="2">harvard</option>
                </select>
            </div>

        </div>

        <div class="btn-group" role="group" style="width:30%;margin:10px;" aria-label="...">
            <button type="button" class="btn btn-success">
                <span class="glyphicon glyphicon-plus" id="btn-query" aria-hidden="true">查询</span>
            </button>
        </div>
    </form>

<!--    <div id="successDialog" class="alert alert-success" role="alert" style="display: none">操作成功！</div>
    <div id="errorDialog" class="alert alert-danger" role="alert" style="display: none">操作失败！</div>
    <div id="confirmDialog" class="alert alert-danger" role="alert" style="display: none">确认删除吗？
        <a href="javascript:void(0)" class="alert-link confirm-delete-btn">确认</a>
        <a href="javascript:void(0)" class="alert-link cancel-delete-btn">取消</a>
    </div>-->
    <div class="continer">
        <div class="btn-group" role="group" aria-label="...">
            <button type="button" class="btn btn-success">
                <span class="glyphicon glyphicon-plus" id="btn-add" aria-hidden="true">新生档案填写</span>
            </button>
        </div>
        <!--
        <div class="btn-group" role="group" aria-label="...">
            <button type="button" class="btn btn-success btn-info consult-download">
                <span class="glyphicon glyphicon-download-alt"></span>
                下载模板
            </button>
        </div>
        <div class="btn-group" role="group" aria-label="...">
            <button type="button" class="btn btn-success btn-info consult-delete">
                <span class="glyphicon glyphicon-trash">删除</span></button>
        </div>-->
    </div>
    <table id="list-contain" class="display">
    </table>
</div>