<div class="form-group">
    <label for="strClassTime">{{#equal operType "telCall"}}电辅时间{{/equal}}{{#equal operType "reAdd"}}补课时间{{/equal}}</label>
    <input type="input" class="form-control kidsReAddClzLog" name="strClassTime" id="strClassTime" autocomplete="off" {{#if classTime}}value="{{classTime}}" disabled="disabled"{{/if}} placeholder="请按照yyyy-MM-dd HH:mm:ss填写日期">
</div>
<div class="form-group">
    <label for="rmk">备注</label>
    <textarea class="form-control kidsReAddClzLog" name="rmk" id="rmk" rows="5"{{#if rmk}}disabled="disabled"{{/if}}>{{#if rmk}}{{rmk}}{{/if}}</textarea>
</div>