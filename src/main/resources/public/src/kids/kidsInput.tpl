<form>
    <div class="form-group">
        <label for="ch_nm">中文名</label>
        <input type="input" class="form-control" id="ch_nm" placeholder="请输入中文名">
    </div>
    <div class="form-group">
        <label for="en_nm">英文名</label>
        <input type="input" class="form-control" id="en_nm" placeholder="请输入英文名">
    </div>
<!--    <div class="form-group">
        <label for="exampleInputFile">File input</label>
        <input type="file" id="exampleInputFile">
        <p class="help-block">Example block-level help text here.</p>
    </div>-->
    <div class="radio">
        <label>
            <input type="radio" name="sex" id="male" value="1" checked>
            男
        </label>
    </div>
    <div class="radio">
        <label>
            <input type="radio" name="sex" id="female" value="2">
            女
        </label>
    </div>
    <div class="form-group">
        <label for="exampleInputFile">File input</label>
        <input type="file" id="exampleInputFile">
        <p class="help-block">Example block-level help text here.</p>
    </div>
    <button type="submit" class="btn btn-default">提交</button>
</form>