<form >
    <input type="hidden" class="kidsClz" name="classId" id="classId" {{#if tClass.classId}}value="{{tClass.classId}}"{{/if}}>
    <input type="hidden" class="kidsClz" name="strCrtTime" id="strCrtTime" {{#if tClass.crtTime}}value="{{tClass.crtTime}}" {{/if}}>

    <div class="form-group">
        <label for="classNm">班级名称</label>
        <input type="input" class="form-control kidsClz" name="classNm" id="classNm" {{#if tClass.classNm}}value="{{tClass.classNm}}"{{/if}} placeholder="请输入班级名称">
    </div>
    <div class="form-group">
        <label for="classLevel">课程级别</label>
        <select class="form-control kidsClz" id="classLevel" name="classLevel" {{#if tClass.classLevel}}value="{{tClass.classLevel}}"{{/if}} >
        {{#each classLevel}}
        <option value="{{value}}" {{#equal ../tClass.classLevel value}}selected{{/equal}}>{{name}}</option>
        {{/each}}
        </select>
    </div>
    <div class="form-group">
        <label for="classTime">上课时间</label>
        <input type="input" class="form-control kidsClz" name="classTime" id="classTime" {{#if tClass.classTime}}value="{{tClass.classTime}}"{{/if}} placeholder="请输入上课时间">
    </div>
</form>