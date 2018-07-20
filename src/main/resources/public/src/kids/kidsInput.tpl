<form >
    <input type="hidden" class="kidsClz" name="kId" id="kId" {{#if tKids.kId}}value="{{tKids.kId}}"{{/if}}>
    <input type="hidden" class="kidsClz" name="strCrtTime" id="strCrtTime" {{#if tKids.crtTime}}value="{{tKids.crtTime}}"{{/if}}>

    <div class="form-group">
        <label for="chNm">中文名</label>
        <input type="input" class="form-control kidsClz" name="chNm" id="chNm" {{#if tKids.chNm}}value="{{tKids.chNm}}"{{/if}} placeholder="请输入中文名">
    </div>
    <div class="form-group">
        <label for="enNm">英文名</label>
        <input type="input" class="form-control kidsClz" name="enNm" id="enNm" {{#if tKids.enNm}}value="{{tKids.enNm}}"{{/if}} placeholder="请输入英文名">
    </div>


    <div class="radio">
        {{#each sexParam}}
        <label>
            <input type="radio" name="sex" class="kidsClz" value="{{value}}" {{#equal ../tKids.sex value}}checked{{/equal}}>
            {{name}}
        </label>
        {{/each}}
    </div>
    <div class="form-group">
        <label for="pNm">监护人姓名</label>
        <input type="input" class="form-control kidsClz" id="pNm" name="pNm" {{#if tKids.pNm}}value="{{tKids.pNm}}"{{/if}} placeholder="请输入监护人姓名">
    </div>
    <div class="form-group">
        <label for="pRelation">监护人关系</label>
        <select class="form-control kidsClz" id="pRelation" name="pRelation" {{#if tKids.pRelation}}value="{{tKids.pRelation}}"{{/if}} >
        {{#each relation}}
            <option value="{{value}}" {{#equal ../tKids.pRelation value}}selected{{/equal}}>{{name}}</option>
        {{/each}}
        </select>
    </div>

    <div class="form-group">
        <label for="phone">联系方式</label>
        <input type="input" class="form-control kidsClz" id="phone"  name="phone" {{#if tKids.phone}}value="{{tKids.phone}}"{{/if}} placeholder="请输入联系方式">
    </div>
    <div class="form-group">
        <label for="address">家庭住址</label>
        <input type="textarea" class="form-control kidsClz" id="address" name="address" {{#if tKids.address}}value="{{tKids.address}}"{{/if}} placeholder="请输入家庭住址">
    </div>
    <div class="form-group">
        <label for="classId">班级</label>
        <select class="form-control kidsClz" id="classId" name="classId" {{#if tKids.classId}}value="{{tKids.classId}}"{{/if}} >
            {{#each classes}}
            <option value="{{classId}}" {{#equal ../tKids.classId classId}}selected{{/equal}}>{{classNm}}</option>
            {{/each}}
        </select>
    </div>
    <div class="form-group">
        <label for="address">剩余课时</label>
        <input type="input" readonly class="form-control kidsClz" id="amount" name="amount" {{#if tKids.amount}}value="{{tKids.amount}}"{{else}}value=0{{/if}}>
    </div>
</form>