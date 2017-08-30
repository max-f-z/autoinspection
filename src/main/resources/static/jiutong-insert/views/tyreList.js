
(function($) {
	$.init();
})(mui);

tyreList = {

	// 事件注册
	event: function() {
		
		$("#searchBtn").on("click", tyreList.service.doQuery);
		
		$("#addBtn").on("click", tyreList.service.doAdd);
		

	},

	// 表单验证
	validate: function() {

	
	},

	service: {
		
		doQuery:function(){
			
		},
		
		doAdd:function(){
			window.location.href = "tyreEdit.html";
		},
		
		doRecord:function(){
			window.location.href = "tyreRecord.html";
		}


	
	},

	dao: {},
	init: function() {

		tyreList.event();
	}
}
tyreList.init();