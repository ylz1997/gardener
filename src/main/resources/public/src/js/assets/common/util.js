define([
	'ajax',
	'hdb'
], function(ajax, hdb) {
    // 序列化url查询参数
    var serilizeUrl = function() {
        // 路径查询参数部分
        var searchURL = decodeURI(window.location.search);
        searchURL = searchURL.substring(1, searchURL.length);

        var result = {};
        var map = searchURL.split("&");
        for(var i = 0, len = map.length; i < len; i++){
            result[map[i].split("=")[0]] = map[i].split("=")[1];
        }
        return result;
    }

	return {
		ajax: ajax,
		hdb: hdb,
        serilizeUrl: serilizeUrl
	}
});
