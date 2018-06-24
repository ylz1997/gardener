<div class="t-columns-2">
<form id="form">
  <ul class="t-columns-group" style="padding-top: 10px">
    {{#each this}}
        {{#equal colmTypeCd 'char'}}
            <li class="width-all mt-5">
              <label for="{{colmId}}" title="{{colmNm}}" {{#if requiredFlag}}class="necessary"{{/if}}>
                {{colmNm}}
              </label>
              <div title="{{hintCntt}}">
                <input id="{{colmId}}" type="text" name="{{colmId}}" placeholder="">
              </div>
            </li>
        {{/equal}}
        {{#equal colmTypeCd 'num'}}
            <li class="width-all mt-5">
              <label for="{{colmId}}" title="{{colmNm}}" {{#if requiredFlag}}class="necessary"{{/if}}>
                {{colmNm}}
              </label>
              <div title="{{hintCntt}}">
                <input id="{{colmId}}" type="text" name="{{colmId}}" placeholder="">
              </div>
            </li>
        {{/equal}}

        {{#equal colmTypeCd 'mes'}}
            <li class="width-all mt-5">
              <label for="{{colmId}}" title="{{colmNm}}" {{#if requiredFlag}}class="necessary"{{/if}}>
                {{colmNm}}
              </label>
              <div title="{{hintCntt}}">
                <textarea id="{{colmId}}" class="h80" name="{{colmId}}"></textarea>
              </div>
            </li>
        {{/equal}}

        {{#equal colmTypeCd 'date'}}
            <li class="width-all mt-5">
              <label for="{{colmId}}" title="{{colmNm}}" {{#if requiredFlag}}class="necessary"{{/if}}>
                {{colmNm}}
              </label>
              <div title="{{hintCntt}}" class="date-contain" id="{{colmId}}">
              </div>
            </li>
        {{/equal}}

        {{#equal colmTypeCd 'rel'}}
            <li class="mt-5 width-all form-has-link">
                <label for="fm01" title="{{colmNm}}" {{#if requiredFlag}}class="necessary"{{/if}}>
                    {{colmNm}}
                  </label>
                  <div title="{{hintCntt}}">
                    <input knwlgId="" readonly id="{{colmId}}" type="text" name="{{colmId}}" placeholder="">
                    <p>
                      <a class="t-btn t-btn-sm" href="#nogo" id="add-knwlg">选择</a>
                    </p>
                  </div>
            </li>
        {{/equal}}
    {{/each}}
  </ul>
  </form>
</div>