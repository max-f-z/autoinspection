mui.init({
	swipeBack: true, //开启左滑关闭

});

(function($) {
	$.init();
})(mui);

uploadFileSku = {

	// 事件注册
	event: function() {

	},

	// 表单验证
	validate: function() {

	},

	service: {

		doSave: function(callBackFunc) {
			pageLoadingFrame("show");
			var token = localStorage.getItem("Authorization");
			$.ajax({
				headers: {
					'Authorization': token,
				},
				url: GLOBAL.SERVER_URL + 'api/instock/tyre/import',
				type: 'POST',
				cache: false,
				data: new FormData($("#fileinfo0")[0]),
				processData: false,
				contentType: false
			}).done(function(res) {
				pageLoadingFrame("hide");
				callBackFunc(res);
			}).fail(function(res) {});

		}

	},

	dao: {},
	init: function() {
		uploadFileSku.event();
	}
}
uploadFileSku.init();