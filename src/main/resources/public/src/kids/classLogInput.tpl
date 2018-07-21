<form >
    <input type="hidden" class="kidsClzLog" name="logId" id="logId" {{#if tClassLog.logId}}value="{{tClassLog.logId}}"{{/if}}>
    <input type="hidden" class="kidsClzLog" name="strCrtTime" id="strCrtTime" {{#if tClassLog.crtTime}}value="{{tClassLog.crtTime}}"{{/if}}>

    <div class="form-group">
        <label for="classId">班级</label>
        <select class="form-control kidsClzLog kidsClzLogChangeClick" id="classId" name="classId" {{#if tClassLog.classId}}value="{{tClassLog.classId}}"{{/if}} >
        <option value="-1">请选择</option>
        {{#each classes}}
        <option value="{{clzId}}" {{#equal ../tClassLog.classId clzId}}selected{{/equal}}>{{classNm}}</option>
        {{/each}}
        </select>
    </div>

    <div class="form-group">
        <label for="strCrtTime">上课时间</label>
        <input type="input" class="form-control kidsClzLog" name="strClassTime" id="strClassTime" {{#if tClassLog.classTime}}value="{{tClassLog.classTime}}"{{/if}} placeholder="请按照yyyy-MM-dd HH:mm:ss填写日期">
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
        <textarea class="form-control kidsClzLog" name="content" id="content" rows="5" {{#if tClassLog.content}}value="{{tClassLog.content}}" {{/if}}></textarea>
    </div>

</form>