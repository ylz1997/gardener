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
                    {{#each classes}}
                    <option value="{{this.value}}">{{this.name}}</option>
                    {{/each}}
                </select>
            </div>

        </div>

        <div class="btn-group" role="group" style="width:30%;margin:10px;" aria-label="...">
            <button type="button" class="btn btn-success">
                <span class="glyphicon glyphicon-plus" id="btn-query" aria-hidden="true">查询</span>
            </button>
        </div>
    </form>

    <div class="continer">
        <div class="btn-group" role="group" aria-label="...">
            <button type="button" class="btn btn-success">
                <span class="glyphicon glyphicon-plus" id="btn-add" aria-hidden="true">新生档案填写</span>
            </button>
        </div>
    </div>
    <table id="list-contain" class="display">
    </table>
</div>