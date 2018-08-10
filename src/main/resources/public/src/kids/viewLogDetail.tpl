
<div class="container-fluid">
    <form class="form-inline" style="padding:10px;" role="form">
        <div class="form-group" style="width:30%">
            <div class="input-group">
                <span class="input-group-addon">上课类型</span>
                <select id="logType"  class="form-control viewLogsearchParam">
                    <option value="-1">全部</option>
                    <option value="2">正常考勤</option>
                    <option value="3">补课考勤</option>
                    <option value="4">电辅记录</option>
                </select>
            </div>
        </div>
    </form>

    <div class="continer">
        <div class="btn-group" role="group" aria-label="...">
            <button type="button" class="btn btn-success" id="btn-readd-clz">
                <span class="glyphicon glyphicon-plus"  aria-hidden="true">补课</span>
            </button>
            <button type="button" class="btn btn-success" id="btn-telCall-clz">
                <span class="glyphicon glyphicon-plus"  aria-hidden="true">电辅</span>
            </button>
        </div>
    </div>
    <table id="list-contain-history" class="display">
    </table>
</div>