mui.init({
	swipeBack: true, //开启左滑关闭

});

(function($) {
	$.init();
})(mui);

uploadFile = {

	// 事件注册
	event: function() {

	},

	// 表单验证
	validate: function() {

	},

	service: {

		doSave: function(callBackFunc) {
			debugger;
			var token = localStorage.getItem("Authorization");
			$.ajax({
				headers: {
					'Authorization': token,
				},
				url: GLOBAL.SERVER_URL + 'api/vehicle/vehicles/import',
				type: 'POST',
				cache: false,
				data: new FormData($("#fileinfo0")[0]),
				processData: false,
				contentType: false
			}).done(function(res) {
				callBackFunc(res);
			}).fail(function(res) {});

		}

	},

	dao: {},
	init: function() {
		uploadFile.event();

	}
}
uploadFile.init();