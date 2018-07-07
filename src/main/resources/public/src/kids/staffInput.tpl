<form >
    <input type="hidden" class="kidsClz" name="teacherId" id="teacherId" {{#if tStaff.teacherId}}value="{{tStaff.teacherId}}"{{/if}}>
    <input type="hidden" class="kidsClz" name="crtTime" id="crtTime" {{#if tStaff.crtTime}}value={{tStaff.crtTime}} {{/if}}>

    <div class="form-group">
        <label for="teacherNm">姓名</label>
        <input type="input" class="form-control kidsClz" name="teacherNm" id="teacherNm" {{#if tStaff.teacherNm}}value="{{tStaff.teacherNm}}"{{/if}} placeholder="请输入中文名">
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
        <label for="age">年龄</label>
        <input type="input" class="form-control kidsClz" id="age" name="age" {{#if tStaff.age}}value="{{tStaff.age}}"{{/if}} placeholder="请输入年龄">
    </div>
    <div class="form-group">
        <label for="personalCode">身份证号</label>
        <input type="input" class="form-control kidsClz" id="personalCode" name="personalCode" {{#if tStaff.personalCode}}value="{{tStaff.personalCode}}"{{/if}} placeholder="请输入身份证号">
    </div>
</form>