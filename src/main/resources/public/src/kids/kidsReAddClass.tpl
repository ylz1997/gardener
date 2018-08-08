<div class="form-group">
    <label for="strCrtTime">上课时间</label>
    <input type="input" class="form-control kidsReAddClzLog" name="strClassTime" id="strClassTime" {{#if tClassLog.classTime}}value="{{tClassLog.classTime}}"{{/if}} placeholder="请按照yyyy-MM-dd HH:mm:ss填写日期">
</div>
<div class="form-group">
    <label for="content">备注</label>
    <textarea class="form-control kidsReAddClzLog" name="rmk" id="rmk" rows="5">{{#if tClassLog.rmk}}{{tClassLog.rmk}}{{/if}}</textarea>
</div>