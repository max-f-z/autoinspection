GLOBAL = {
	
	// 服务器地址
	SERVER_URL: "http://116.62.116.204:8080/v1/",
	//SERVER_URL: "http://localhost:8443/v1/",
	
	IS_INSERT_TRUE : "T",
	IS_INSERT_FALSE : "F",
	
	INIT_PAGE : 1,
	INIT_SIZE : 10
	
}

var URL = {
	getRequest: function() {
		    var searchString = window.location.search.substring(1),
		        params = searchString.split("&"),
		        hash = {};
		        
		    if (searchString == "") return {};
		    for (var i = 0; i < params.length; i++) {
		        // 获取等号位置
		        var pos = params[i].indexOf('=');
		        if (pos == -1) { continue; }
		        // 获取name 和 value
		        var paraName = params[i].substring(0, pos),
		            paraValue = params[i].substring(pos + 1);
		        hash[paraName] = paraValue;
		    }
		    return hash;
	},
    isApp:function(){
        var u = URL.getRequest().fromType+'';
        if(u != null && u.indexOf('app') != -1){
            return true;
        }else{
            return false;
        }
    },
    isAndroid:function(){
        var u = URL.getRequest().fromType;
        if(u != null && u=='app_android'){
            return true;
        }else{
            return false;
        }
    },
    isIos:function(){
        var u = URL.getRequest().fromType;
        if(u != null && u=='app_ios'){
            return true;
        }else{
            return false;
        }
    },
    isFirstPresc:function(){
    	var u = URL.getRequest().firstPrescr;
        if(u != null && u=='true'){
            return true;
        }else{
            return false;
        }
    }
    
}