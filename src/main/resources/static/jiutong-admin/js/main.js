var tyre = {};

tyre.main = {

    init :function () {
    	
    		tyre.main.event();
    		
        if(localStorage.getItem("Authorization") == null){
        		alert("请您登录")
        		window.location.href = "login.html";        	
        }
        tyre.main.initBody("index.html");
        
    },

    bindEvent:function () {
        tyre.main.init();
    },
    
    event:function(){
    		$("#logoutBtn").on("click",tyre.main.logout);
    },
    
    logout:function(){
    	
    		window.localStorage.clear();
    		window.location.href = "login.html";
    	
    },
    
    
    initBody:function (htmlUrl) {
        $("#mainBody").empty();
        $("#mainBody").load(htmlUrl,function(){});
    }
};
$(function(){
    tyre.main.init();
});