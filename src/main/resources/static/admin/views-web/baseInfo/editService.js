mui.init({
	swipeBack: true, //开启左滑关闭

});

(function($) {
	$.init();
})(mui);

editService = {

	// 事件注册
	event: function() {
		// 取消按钮
	},

	// 表单验证
	validate: function() {

		if(null == $("#editService-form #name").val() || 0 == $("#editService-form #name").val().length) {
			alert("您输入的服务类型为空");
			return false;
		}

		if(null == $("#editService-form #description").val() || 0 == $("#editService-form #description").val().length) {
			alert("您输入的描述为空");
			return false;
		}
		return true;

	},

	service: {

		doSave: function(callBackFunc) {

			if(!editService.validate()) {
				return false;
			}

			var name = $("#editService-form #name option:selected").val();
			var description = $("#editService-form #description").val();
			var id = localStorage.getItem("serviceId");
			var token = localStorage.getItem("Authorization");
			mui.ajax(GLOBAL.SERVER_URL + "api/service/services/update", {
				data: {
					id: id,
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
		},

		doQuery: function(id) {
			var token = localStorage.getItem("Authorization");
			mui.ajax(GLOBAL.SERVER_URL + "api/service/services/" + id, {
				headers: {
					'Content-Type': 'application/json',
					'Authorization': token,
				},
				dataType: 'json', //服务器返回json格式数据
				type: 'get', //HTTP请求类型
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
					$("#editService-form #name").val(data.data.name);
					$("#editService-form #description").val(data.data.description);
				}
			});
		}
	},

	dao: {},
	init: function() {
		var id = localStorage.getItem("serviceId");
		editService.service.doQuery(id);
	}
}
editService.init();