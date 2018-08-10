<form >
    <input type="hidden" class="kidsClz" name="teacherId" id="teacherId" {{#if tStaff.teacherId}}value="{{tStaff.teacherId}}"{{/if}}>
    <input type="hidden" class="kidsClz" name="strCrtTime" id="strCrtTime" {{#if tStaff.crtTime}}value="{{tStaff.crtTime}}" {{/if}}>

    <div class="form-group">
        <label for="teacherNm">中文名</label>
        <input type="input" class="form-control kidsClz" name="teacherNm" id="teacherNm" {{#if tStaff.teacherNm}}value="{{tStaff.teacherNm}}"{{/if}} placeholder="请输入中文名">
    </div>
    <div class="form-group">
        <label for="teacherNm">英文名</label>
        <input type="input" class="form-control kidsClz" name="teacherEnNm" id="teacherEnNm" {{#if tStaff.teacherEnNm}}value="{{tStaff.teacherEnNm}}"{{/if}} placeholder="请输入英文名">
    </div>
    <div class="radio">
        {{#each sexParam}}
        <label>
            <input type="radio" name="sex" class="kidsClz" value="{{value}}" {{#equal ../tStaff.sex value}}checked{{/equal}}>
            {{name}}
        </label>
        {{/each}}
    </div>
    <div class="form-group">
        <label for="cdNumber">身份证号</label>
        <input type="input" class="form-control kidsClz" id="cdNumber" name="cdNumber" {{#if tStaff.cdNumber}}value="{{tStaff.cdNumber}}"{{/if}} placeholder="请输入身份证号">
    </div>
    <div class="form-group">
        <label for="duty">岗位</label>
        <select class="form-control kidsClz" id="duty" name="duty" {{#if tStaff.classId}}value="{{tStaff.classId}}"{{/if}} >
            {{#each duties}}
            <option value="{{value}}" {{#equal ../tStaff.duty value}}selected{{/equal}}>{{name}}</option>
            {{/each}}
        </select>
    </div>
<!--    <label for="classes">所在班级</label>
    <div class="checkbox" id="classes">
        {{#each classes}}
        <label>
            <input type="checkbox" name="classes" class="kidsCheckClz" value="{{clzId}}"
                  {{#each ../tStaff.classIdArray}}  {{#equal ../clzId classId}}checked{{/equal}}
                  {{/each}}>
            {{classNm}}
        </label>
        {{/each}}
    </div>-->



</form>