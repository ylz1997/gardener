<form>
    <div class="form-group">
        <label for="chNm">中文名</label>
        <input type="input" class="form-control" id="chNm" placeholder="请输入中文名">
    </div>
    <div class="form-group">
        <label for="enNm">英文名</label>
        <input type="input" class="form-control" id="enNm" placeholder="请输入英文名">
    </div>
    <div class="radio">
        <label>
            <input type="radio" name="sex" id="male" value="1" checked>
            男
        </label>
        <label>
            <input type="radio" name="sex" id="female" value="1">
            女
        </label>
    </div>

    <div class="form-group">
        <label for="pNm">监护人姓名</label>
        <input type="input" class="form-control" id="pNm" placeholder="请输入监护人姓名">
    </div>
    <div class="form-group">
        <label for="pRelation">监护人关系</label>
        <select class="form-control" id="pRelation" placeholder="请输入监护人关系">
            <option value="1">爸爸</option>
            <option value="2">妈妈</option>
            <option value="3">爷爷</option>
            <option value="4">奶奶</option>
            <!--todo 需要更换字典-->
        </select>
    </div>

    <div class="form-group">
        <label for="phone">联系方式</label>
        <input type="input" class="form-control" id="phone" placeholder="请输入联系方式">
    </div>
    <div class="form-group">
        <label for="address">家庭住址</label>
        <input type="textarea" class="form-control" id="address" placeholder="请输入家庭住址">
    </div>
    <div class="form-group">
        <label for="classId">班级</label>
        <select class="form-control" id="classId" placeholder="选择班级">
        <option value="brown">brown</option>
        </select>
    </div>
</form>