<form >
    <input type="hidden" class="kidsClz" name="kId" id="kId" {{#if kId}}value={{kId}}{{/if}}>
    <input type="hidden" class="kidsClz" name="crtTime" id="crtTime" {{#if crtTime}}value={{crtTime}}{{/if}}>

    <div class="form-group">
        <label for="chNm">中文名</label>
        <input type="input" class="form-control kidsClz" name="chNm" id="chNm" {{#if chNm}}value="{{chNm}}"{{/if}} placeholder="请输入中文名">
    </div>
    <div class="form-group">
        <label for="enNm">英文名</label>
        <input type="input" class="form-control kidsClz" name="enNm" id="enNm" {{#if enNm}}value="{{enNm}}"{{/if}} placeholder="请输入英文名">
    </div>
    <div class="radio">
        <label>
            <input type="radio" name="sex" class="kidsClz" value="1" {{#equal sex 1}}checked{{/equal}}>
            男
        </label>
        <label>
            <input type="radio" name="sex" class="kidsClz" value="2" {{#equal sex 2}}checked{{/equal}}>
            女
        </label>
    </div>

    <div class="form-group">
        <label for="pNm">监护人姓名</label>
        <input type="input" class="form-control kidsClz" id="pNm" name="pNm" {{#if pNm}}value="{{pNm}}"{{/if}} placeholder="请输入监护人姓名">
    </div>
    <div class="form-group">
        <label for="pRelation">监护人关系</label>
        <select class="form-control kidsClz" id="pRelation" name="pRelation" {{#if pRelation}}value="{{pRelation}}"{{/if}} placeholder="请输入监护人关系">
            <option value="1" {{#equal pRelation 1}}selected{{/equal}}>爸爸</option>
            <option value="2" {{#equal pRelation 2}}selected{{/equal}}>妈妈</option>
            <option value="3" {{#equal pRelation 3}}selected{{/equal}}>爷爷</option>
            <option value="4" {{#equal pRelation 4}}selected{{/equal}}>奶奶</option>
            <!--todo 需要更换字典-->
        </select>
    </div>

    <div class="form-group">
        <label for="phone">联系方式</label>
        <input type="input" class="form-control kidsClz" id="phone"  name="phone" {{#if phone}}value="{{phone}}"{{/if}} placeholder="请输入联系方式">
    </div>
    <div class="form-group">
        <label for="address">家庭住址</label>
        <input type="textarea" class="form-control kidsClz" id="address" name="address" {{#if address}}value="{{address}}"{{/if}} placeholder="请输入家庭住址">
    </div>
    <div class="form-group">
        <label for="classId">班级</label>
        <select class="form-control kidsClz" id="classId" name="classId" {{#if classId}}value="{{classId}}"{{/if}} placeholder="选择班级">
        <option value="1" {{#equal classId 1}} selected{{/equal}}>brown</option>
        <option value="2" {{#equal classId 2}} selected{{/equal}}>harverd</option>
        </select>
    </div>
</form>