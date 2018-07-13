<form >
    <input type="hidden" class="kidsClz" name="kId" id="kId" {{#if tkids.kId}}value="{{tkids.kId}}"{{/if}}>
    <input type="hidden" class="kidsClz" name="strCrtTime" id="strCrtTime" {{#if tkids.crtTime}}value="{{tkids.crtTime}}"{{/if}}>

    <div class="form-group">
        <label for="chNm">中文名</label>
        <input type="input" class="form-control kidsClz" name="chNm" id="chNm" {{#if tkids.chNm}}value="{{tkids.chNm}}"{{/if}} placeholder="请输入中文名">
    </div>
    <div class="form-group">
        <label for="enNm">英文名</label>
        <input type="input" class="form-control kidsClz" name="enNm" id="enNm" {{#if tkids.enNm}}value="{{tkids.enNm}}"{{/if}} placeholder="请输入英文名">
    </div>


    <div class="radio">
        {{#each sexParam}}
        <label>
            <input type="radio" name="sex" class="kidsClz" value="{{value}}" {{#equal ../tkids.sex value}}checked{{/equal}}>
            {{name}}
        </label>
        {{/each}}
    </div>
    <div class="form-group">
        <label for="pNm">监护人姓名</label>
        <input type="input" class="form-control kidsClz" id="pNm" name="pNm" {{#if tkids.pNm}}value="{{tkids.pNm}}"{{/if}} placeholder="请输入监护人姓名">
    </div>
    <div class="form-group">
        <label for="pRelation">监护人关系</label>
        <select class="form-control kidsClz" id="pRelation" name="pRelation" {{#if tkids.pRelation}}value="{{tkids.pRelation}}"{{/if}} >
        {{#each relation}}
            <option value="{{value}}" {{#equal ../tkids.pRelation value}}selected{{/equal}}>{{name}}</option>
        {{/each}}
        </select>
    </div>

    <div class="form-group">
        <label for="phone">联系方式</label>
        <input type="input" class="form-control kidsClz" id="phone"  name="phone" {{#if tkids.phone}}value="{{tkids.phone}}"{{/if}} placeholder="请输入联系方式">
    </div>
    <div class="form-group">
        <label for="address">家庭住址</label>
        <input type="textarea" class="form-control kidsClz" id="address" name="address" {{#if tkids.address}}value="{{tkids.address}}"{{/if}} placeholder="请输入家庭住址">
    </div>
    <div class="form-group">
        <label for="classId">班级</label>
        <select class="form-control kidsClz" id="classId" name="classId" {{#if tkids.classId}}value="{{tkids.classId}}"{{/if}} >
            {{#each classes}}
            <option value="{{classId}}" {{#equal ../tkids.classId classId}}selected{{/equal}}>{{classNm}}</option>
            {{/each}}
        </select>
    </div>
    <div class="form-group">
        <label for="address">剩余课时</label>
        <input type="input" readonly class="form-control kidsClz" id="amount" name="amount" {{#if tkids.amount}}value="{{tkids.amount}}"{{else}}value=0{{/if}}>
    </div>
</form>