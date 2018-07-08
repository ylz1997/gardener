<form >
    <input type="hidden" class="kidsClz" name="classId" id="classId" {{#if tClassVO.classId}}value="{{tClassVO.classId}}"{{/if}}>
    <input type="hidden" class="kidsClz" name="crtTime" id="crtTime" {{#if tClassVO.crtTime}}value={{tClassVO.crtTime}} {{/if}}>

    <div class="form-group">
        <label for="classNm">班级名称</label>
        <input type="input" class="form-control kidsClz" name="classNm" id="classNm" {{#if tClassVO.classNm}}value="{{tClassVO.classNm}}"{{/if}} placeholder="请输入班级名称">
    </div>
    <div class="form-group">
        <label for="classPackageId">课时包</label>
        <select class="form-control kidsClz" id="classPackageId" name="classPackageId" {{#if tClassVO.classPackageId}}value="{{tClassVO.classPackageId}}"{{/if}} >
        {{#each allClassPackage}}
        <option value="{{classPackageId}}" {{#equal ../tClassVO.classPackageId value}}selected{{/equal}}>{{classPackageNm}}</option>
        {{/each}}
        </select>
    </div>
    <div class="form-group">
        <div class="radio">
            {{#each classCycle}}
            <label>
                <input type="radio" name="cycle" class="kidsClz" value="{{value}}" {{#equal ../tClassVO.cycle value}}checked{{/equal}}>
                {{name}}
            </label>
            {{/each}}
        </div>
        <button type="button" class="btn btn-success"  id="btn-query"  id="addClassSchdule">
            <span class="glyphicon glyphicon-plus" aria-hidden="true" title="添加一个上课时间">添加</span>
        </button>
        <div class="schdule">
        </div>
    </div>
</form>