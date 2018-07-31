<form >
    <input type="hidden" class="kidsClz" name="classId" id="classId" {{#if tClass.classId}}value="{{tClass.classId}}"{{/if}}>
    <input type="hidden" class="kidsClz" name="strCrtTime" id="strCrtTime" {{#if tClass.crtTime}}value="{{tClass.crtTime}}" {{/if}}>

    <div class="form-group">
        <label for="classNm">班级名称</label>
        <input type="input" class="form-control kidsClz" name="classNm" id="classNm" {{#if tClass.classNm}}value="{{tClass.classNm}}"{{/if}} placeholder="请输入班级名称">
    </div>
<!--    <div class="form-group">
        <div class="radio">
            {{#each classCycle}}
            <label>
                <input type="radio" name="cycle" class="kidsClz" value="{{value}}" {{#equal ../tClass.cycle value}}checked{{/equal}}>
                {{name}}
            </label>
            {{/each}}
        </div>
        <button type="button" class="btn btn-success"  id="btn-query"  id="addClassSchdule">
            <span class="glyphicon glyphicon-plus" aria-hidden="true" title="添加一个上课时间">添加</span>
        </button>
        <div class="schdule">
        </div>
    </div>-->
</form>