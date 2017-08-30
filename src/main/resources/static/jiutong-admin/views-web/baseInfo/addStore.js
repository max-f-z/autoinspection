mui.init({
	swipeBack: true, //开启左滑关闭

});

(function($) {
	$.init();
})(mui);

addStore = {

	// 事件注册
	event: function() {
		// 取消按钮
	},

	// 表单验证
	validate: function() {

	},

	service: {

		doSave: function(callBackFunc) {
			var name = $("#addStore-form #name").val();
			var district = $("#addStore-form #district").val();
			var address = $("#addStore-form #address").val();
			var latitude = $("#addStore-form #latitude").val();
			var longitude = $("#addStore-form #longitude").val();
			var phone = $("#addStore-form #phone").val();
			var principal = $("#addStore-form #principal").val();
			var principalPhone = $("#addStore-form #principalPhone").val()
			var token = localStorage.getItem("Authorization");

			mui.ajax(GLOBAL.SERVER_URL + "api/station/stations/add", {
				data: {
					"name": name,
					"district": district,
					address: address,
					latitude: latitude,
					longitude: longitude,
					phone: phone,
					principal: principal,
					principalPhone: principalPhone
				},
				headers: {
					'Content-Type': 'application/json',
					'Authorization': token,
				},
				dataType: 'json', //服务器返回json格式数据
				type: 'post', //HTTP请求类型
				error: function(xhr, type, errorThrown) {
				},
				success: function(data) {
					console.log(data);
					debugger;
					callBackFunc(data);
				}
			});

		}
	},

	dao: {},
	init: function() {}
}
addStore.init();