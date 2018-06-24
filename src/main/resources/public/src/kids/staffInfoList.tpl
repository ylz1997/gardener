<div class="container-fluid" id="containerDiv">
    <form class="form-inline" style="padding:10px;" role="form">
        <div class="row" style="padding: 2px;">
            <div class="form-group col-lg-8"  >
                <div class="input-group">
                    <span class="input-group-addon" >员工姓名</span>
                    <input type="text" class="form-control" id="teacher_nm" placeholder="请输入学生中文名" >
                </div>
                <div class="input-group">
                    <span class="input-group-addon">工号</span>
                    <input type="text" class="form-control" id="teacher_id" placeholder="请输入学生英文名">
                </div>
            </div>
        </div>
    </form>

    <div class="continer">
        <div class="btn-group" role="group" aria-label="...">
            <button type="button" class="btn btn-success">
                <span class="glyphicon glyphicon-plus" id="btn-add" aria-hidden="true">新增咨询表</span>
            </button>
        </div>
        <div class="btn-group" role="group" aria-label="...">
            <button type="button" class="btn btn-info consult-edit"><span class="glyphicon glyphicon-pencil">修改</span></button>
        </div>
        <div class="btn-group" role="group" aria-label="...">
            <button type="button" class="btn btn-success btn-info consult-download">
                <span class="glyphicon glyphicon-download-alt"></span>
                下载模板
            </button>
        </div>
        <div class="btn-group" role="group" aria-label="...">
            <button type="button" class="btn btn-success btn-info consult-delete">
                <span class="glyphicon glyphicon-trash">删除</span></button>
        </div>
    </div>

    <table id="list-contain" class="display">
    </table>
</div>