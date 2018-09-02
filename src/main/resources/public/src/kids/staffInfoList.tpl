<div class="container-fluid" id="containerDiv">
    <form class="form-inline" style="padding:10px;" role="form">
        <div class="form-group" style="width:30%">
            <div class="input-group">
                <span class="input-group-addon" >员工姓名</span>
                <input type="text" class="form-control searchParam" id="teacherNm" placeholder="请输入员工姓名" >
            </div>
        </div>

        <div class="btn-group" role="group" style="width:30%;margin:10px;" aria-label="...">
            <button type="button" class="btn btn-success"  id="btn-query" >
                <span class="glyphicon glyphicon-plus"aria-hidden="true">查询</span>
            </button>
        </div>
    </form>

    <div id="btn-add-div" class="hideMenu">
    <div class="continer">
        <div class="btn-group" role="group" aria-label="...">
            <button type="button" class="btn btn-success" id="btn-add">
                <span class="glyphicon glyphicon-plus"  aria-hidden="true">新员工档案填写</span>
            </button>
        </div>
    </div>
    </div>
    <table id="list-contain" class="display">
    </table>
</div>