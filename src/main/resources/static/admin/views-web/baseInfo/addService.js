mui.init({
	swipeBack: true, //开启左滑关闭

});

(function($) {
	$.init();
})(mui);

addService = {

	// 事件注册
	event: function() {
		// 取消按钮
	},

	// 表单验证
	validate: function() {
		if(null == $("#addMail-form #name").val() || 0 == $("#addMail-form #name").val()) {
			alert("您输入的服务类型为空");
			return false;
		}

		if(null == $("#addMail-form #description").val() || 0 == $("#addMail-form #description").val().length) {
			alert("您输入的描述为空");
			return false;
		}
		return true;
	},

	service: {

		doSave: function(callBackFunc) {

			if(!addService.validate()) {
				return false;
			}

			var name = $("#addMail-form #name option:selected").val();
			var description = $("#addMail-form #description").val();

			var token = localStorage.getItem("Authorization");
			mui.ajax(GLOBAL.SERVER_URL + "api/service/services/add", {
				data: {
					name: name,
					description: description
				},
				headers: {
					'Content-Type': 'application/json',
					'Authorization': token,
				},
				dataType: 'json', //服务器返回json格式数据
				type: 'post', //HTTP请求类型
				error: function(xhr, type, errorThrown) {},
				success: function(data) {
					if(data.result != 1) {
						mui.toast(data.msg);
						if(data.code == "1001") {
							alert("登录超时");
							window.location.href = "login.html";
						}
						return;
					}
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
addService.init();