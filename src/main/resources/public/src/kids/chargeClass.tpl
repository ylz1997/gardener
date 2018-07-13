<div class="form-group">
    <label for="classPackageId">课时</label>
    <select class="form-control kidsClz" id="classPackageId" name="classPackageId">
    {{#each classPackage}}
    <option value="{{classPackageId}}" >{{classPackageNm}}(费用:{{price}}/课时:{{amount}})</option>
    {{/each}}
    </select>
</div>