<form >
    <input type="hidden" class="kidsClz" name="teacherId" id="teacherId" {{#if tStaff.teacherId}}value="{{tStaff.teacherId}}"{{/if}}>
    <input type="hidden" class="kidsClz" name="strCrtTime" id="strCrtTime" {{#if tStaff.crtTime}}value="{{tStaff.crtTime}}" {{/if}}>

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
<!--
    <div class="form-group">
        <label for="personalCode">所带班级</label>
        <input type="input" class="form-control kidsClz" id="classId" name="classId" {{#if tStaff.classId}}value="{{tStaff.classId}}"{{/if}}>
    </div>
-->

    <label for="classes">所在班级</label>
    <div class="radio" id="classes">
        {{#each classes}}
        <label>
            <input type="checkbox" name="classes" class="kidsCheckClz" value="{{clzId}}"
                  {{#each ../tStaff.classIdArray}}  {{#equal ../clzId classId}}checked{{/equal}}
                  {{/each}}>
            {{classNm}}
        </label>
        {{/each}}
    </div>



</form>