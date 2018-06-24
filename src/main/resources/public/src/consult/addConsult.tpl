<form style="padding:10px;" role="form" id="form">
    <input name="cnslId" type="hidden">
    <div class="form-group"  >
        <div class="input-group">
            <span class="input-group-addon">咨询表名称</span>
            <input type="text" class="form-control" id="fm01" name="cnslNm" placeholder="请输入咨询表名称">
        </div>
    </div>
    <div class="form-group"  >
        <div class="input-group">
            <span class="input-group-addon">模板名称</span>
            <input id="fm02" class="form-control" readonly name="tmpltId" type="text" placeholder="请选择模板...">
            <span class="input-group-btn">
                <button class="btn btn-default" type="button" href="javascript:void(0);" id="comsult-tmplt">选择</button>
            </span>
        </div>
    </div>
    <div class="form-group"  >
        <div class="input-group">
            <span class="input-group-addon">咨询表描述</span>
            <textarea id="fm03" class="form-control" name="rmk"></textarea>
        </div>
    </div>
</form>