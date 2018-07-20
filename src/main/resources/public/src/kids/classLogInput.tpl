<form >
    <input type="hidden" class="kidsClz" name="logId" id="logId" {{#if tClassLog.logId}}value="{{tClassLog.logId}}"{{/if}}>

    <div class="form-group">
        <label for="classNm">班级名称</label>
        <input type="input" class="form-control kidsClz" name="classNm" id="classNm" {{#if tClassLog.classNm}}value="{{tClassLog.classNm}}"{{/if}} placeholder="请输入班级名称">
    </div>
    <div class="form-group">
        <label for="strCrtTime">上课时间</label>
        <input type="hidden" class="kidsClz" name="strCrtTime" id="strCrtTime" {{#if tClassLog.crtTime}}value="{{tClassLog.crtTime}}" {{/if}}>
    </div>
    <label for="kidsList">教师</label>
    <div class="checkbox" id="teacherList">
        {{#each classTeacherList}}
        <label>
            <input type="checkbox" name="classes" class="teacherCheckClz"/>
                   {{#each ../tClassLog.teacherList}}  {{#equal ../teacherId tId}}checked{{/equal}}
            {{/each}}>
            {{classNm}}
        </label>
        {{/each}}
    </div>
    <label for="kidsList">学生</label>
    <div class="checkbox" id="kidsList">
        {{#each classKidsList}}
        <label>
            <input type="checkbox" name="classes" class="kidsCheckClz"/>
                   {{#each ../tClassLog.kidsList}}  {{#equal ../kidsId kId}}checked{{/equal}}
            {{/each}}>
            {{classNm}}
        </label>
        {{/each}}
    </div>
    <div class="form-group">
        <label for="content">日志内容</label>
        <input type="hidden" class="kidsClz" name="content" id="content" {{#if tClassLog.content}}value="{{tClassLog.content}}" {{/if}}>
    </div>
</form>