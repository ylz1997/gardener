<form >
    <input type="hidden" class="kidsClz" name="classPackageId" id="classPackageId" {{#if tClassPackage.classPackageId}}value="{{tClassPackage.classPackageId}}"{{/if}}>
    <input type="hidden" class="kidsClz" name="crtTime" id="crtTime" {{#if tClassPackage.crtTime}}value={{tClassPackage.crtTime}} {{/if}}>

    <div class="form-group">
        <label for="classPackageNm">课时包名称</label>
        <input type="input" class="form-control kidsClz" name="classPackageNm" id="classPackageNm" {{#if tClassPackage.classPackageNm}}value="{{tClassPackage.classPackageNm}}"{{/if}} placeholder="请输入课时包名称">
    </div>
    <div class="form-group">
        <label for="startTime">生效时间</label>
        <input type="input" class="form-control kidsClz" id="startTime" name="startTime" {{#if tClassPackage.startTime}}value="{{tClassPackage.startTime}}"{{/if}} placeholder="请输入生效时间">
    </div>
    <div class="form-group">
        <label for="EndTime">生效时间</label>
        <input type="input" class="form-control kidsClz" id="EndTime" name="EndTime" {{#if tClassPackage.EndTime}}value="{{tClassPackage.EndTime}}"{{/if}} placeholder="请输入失效时间">
    </div>
    <div class="form-group">
        <label for="amount">课时数</label>
        <input type="input" class="form-control kidsClz" id="amount" name="amount" {{#if tClassPackage.amount}}value="{{tClassPackage.amount}}"{{/if}} placeholder="请输入课时数">
    </div>
    <div class="form-group">
        <label for="price">价格</label>
        <input type="input" class="form-control kidsClz" id="price" name="price" {{#if tClassPackage.price}}value="{{tClassPackage.price}}"{{/if}} placeholder="请输入价格">
    </div>
</form>