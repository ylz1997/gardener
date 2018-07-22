<div >
    <input type="hidden" class="kidsClz" name="classPackageId" id="classPackageId" {{#if tClassPackageVO.classPackageId}}value="{{tClassPackageVO.classPackageId}}"{{/if}}>
    <input type="hidden" class="kidsClz" name="crtTime" id="crtTime" {{#if tClassPackageVO.crtTime}}value={{tClassPackageVO.crtTime}} {{/if}}>

    <div class="form-group">
        <label for="classPackageNm">课时包名称</label>
        <input type="input" class="form-control kidsClz" name="classPackageNm" id="classPackageNm" {{#if tClassPackageVO.classPackageNm}}value="{{tClassPackageVO.classPackageNm}}"{{/if}} placeholder="请输入课时包名称">
    </div>
    <div class="form-group">
        <label for="strStartTime">生效时间</label>
        <input type="input" class="form-control kidsClz" id="strStartTime" name="strStartTime" {{#if tClassPackageVO.strStartTime}}value="{{tClassPackageVO.strStartTime}}"{{/if}} placeholder="请输入生效时间">
    </div>
    <div class="form-group">
        <label for="strEndTime">生效时间</label>
        <input type="input" class="form-control kidsClz" id="strEndTime" name="strEndTime" {{#if tClassPackageVO.strEndTime}}value="{{tClassPackageVO.strEndTime}}"{{/if}} placeholder="请输入失效时间">
    </div>
    <div class="form-group">
        <label for="amount">课时数</label>
        <input type="input" class="form-control kidsClz" id="amount" name="amount" {{#if tClassPackageVO.amount}}value="{{tClassPackageVO.amount}}"{{/if}} placeholder="请输入课时数">
    </div>
    <div class="form-group">
        <label for="price">价格</label>
        <input type="input" class="form-control kidsClz" id="price" name="price" {{#if tClassPackageVO.price}}value="{{tClassPackageVO.price}}"{{/if}} placeholder="请输入价格">
    </div>
</div>