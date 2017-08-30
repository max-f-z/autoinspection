mui.init({
	swipeBack: true, //开启左滑关闭

});

(function($) {
	$.init();
})(mui);

addMail = {

	// 事件注册
	event: function() {
		// 取消按钮
	},

	// 表单验证
	validate: function() {

	},

	service: {

		doSave: function(callBackFunc) {
			var name = $("#addMail-form #name").val();
			var code = $("#addMail-form #code").val();
			var contactName = $("#addMail-form #contactName").val();
			var contactPhone = $("#addMail-form #contactPhone").val();
			var address = $("#addMail-form #address").val();
			var salesman = $("#addMail-form #salesman").val();

			var token = localStorage.getItem("Authorization");
			mui.ajax(GLOBAL.SERVER_URL + "api/customer/customers/add", {
				data: {
					name: name,
					code: code,
					contactName: contactName,
					contactPhone: contactPhone,
					address: address,
					salesman: salesman
				},
				headers: {
					'Content-Type': 'application/json',
					'Authorization': token,
				},
				dataType: 'json', //服务器返回json格式数据
				type: 'post', //HTTP请求类型
				error: function(xhr, type, errorThrown) {},
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
addMail.init();