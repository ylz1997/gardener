
    <div class="t-list">
        <div class="ke-panel-title search-form-title search-has-btn">
            咨询表模板
            <a class="t-btn t-btn-sm t-btn-blue" href="javascript:void(0)">
                <i class="iconfont icon-bianji"></i> 修改
            </a>
        </div>
        <!-- 顶部搜索 -->
        <div class="t-list-search t-columns-3 t-list-search-view">
            <ul class="t-columns-group">
                <li class="width-all">
                    <!-- 必填项为label追加样式: necessary -->
                    <label>模板名称：</label>
                    <div class="big-txt">
                        模板名称模板名称
                    </div>
                </li>
                <li class="width-all">
                    <label>适用地区：</label>
                    <div>
                        云南,甘肃,湖南,贵州,四川,内蒙古
                    </div>
                </li>
            </ul>

        </div>

        <!-- 记录数及列表主操作 -->
        <div class="t-list-btns">
            <p>共<em>123</em>条记录, 13页</p>
            <div>
                <a class="t-btn t-btn-sm t-btn-green" href="javascript:void(0)">
                    <i class="iconfont icon-jiahao"></i> 新增字段
                </a>
            </div>
        </div>

        <!-- 查询结果 -->
        <div class="t-list-result">
            <!-- 列表，请使用list组件实现! -->
            <table class="t-table t-table-striped">
                <thead>
                <tr>
                    <th>字段名称</th>
                    <th>字段类型</th>
                    <th>字段长度</th>
                    <th>必填</th>
                    <th>筛选项</th>
                    <th>字段描述</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>字段1</td>
                    <td>字符串 (固定长度)</td>
                    <td>20</td>
                    <td>是</td>
                    <td>否</td>
                    <td>字段描述信息展示在这里</td>
                    <td>
                        <a class="link-blue" href="#nogo">修改</a>
                        <span class="color-ccc">|</span>
                        <a class="link-blue" href="#nogo">删除</a>
                    </td>
                </tr>
                <!-- 已选行 -->
                <tr class="selected">
                    <td>字段2</td>
                    <td>数字</td>
                    <td>不限</td>
                    <td>否</td>
                    <td>否</td>
                    <td>字段描述信息展示在这里这里这里</td>
                    <td>
                        <a class="link-blue" href="#nogo">修改</a>
                        <span class="color-ccc">|</span>
                        <a class="link-blue" href="#nogo">删除</a>
                    </td>
                </tr>
                <tr>
                    <td>字段3</td>
                    <td>数字 (固定长度)</td>
                    <td>200</td>
                    <td>是</td>
                    <td>是</td>
                    <td>字段描述信息展示在这里这里这里</td>
                    <td>
                        <a class="link-blue" href="#nogo">修改</a>
                        <span class="color-ccc">|</span>
                        <a class="link-blue" href="#nogo">删除</a>
                    </td>
                </tr>
                <tr>
                    <td>字段4</td>
                    <td>日期</td>
                    <td>10</td>
                    <td>是</td>
                    <td>是</td>
                    <td>字段描述信息展示在</td>
                    <td>
                        <a class="link-blue" href="#nogo">修改</a>
                        <span class="color-ccc">|</span>
                        <a class="link-blue" href="#nogo">删除</a>
                    </td>
                </tr>
                <tr>
                    <td>字段5</td>
                    <td>短信</td>
                    <td>400</td>
                    <td>否</td>
                    <td>否</td>
                    <td>字段描述信息展示在</td>
                    <td>
                        <a class="link-blue" href="#nogo">修改</a>
                        <span class="color-ccc">|</span>
                        <a class="link-blue" href="#nogo">删除</a>
                    </td>
                </tr>
                <tr>
                    <td>字段6</td>
                    <td>关联知识</td>
                    <td>不限</td>
                    <td>是</td>
                    <td>是</td>
                    <td>字段描述信息展示在</td>
                    <td>
                        <a class="link-blue" href="#nogo">修改</a>
                        <span class="color-ccc">|</span>
                        <a class="link-blue" href="#nogo">删除</a>
                    </td>
                </tr>
                <tr>
                    <td>字段7</td>
                    <td>字符串</td>
                    <td>不限</td>
                    <td>是</td>
                    <td>是</td>
                    <td>字段描述信息展示在</td>
                    <td>
                        <a class="link-blue" href="#nogo">修改</a>
                        <span class="color-ccc">|</span>
                        <a class="link-blue" href="#nogo">删除</a>
                    </td>
                </tr>
                </tbody>
            </table>

            <!-- 分页（组件中已包含分页），请使用list组件实现! -->
            <div class="t-page">
                <ul>
                    <li class="disabled"><a href="#">&lt;</a></li>
                    <li class="active"><a href="#">1</a></li>
                    <li><a href="#">2</a></li>
                    <li><a href="#">3</a></li>
                    <li><span>...</span></li>
                    <li><a href="#">10</a></li>
                    <li><a href="#">&gt;</a></li>
                </ul>
                <div class="t-page-skip">
                    跳转到第
                    <input type="text">
                    页
                </div>
            </div>

        </div>

    </div>