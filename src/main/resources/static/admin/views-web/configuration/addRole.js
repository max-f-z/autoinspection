mui.init({
	swipeBack: true, //开启左滑关闭

});

(function($) {
	$.init();
})(mui);

addRole = {

	// 事件注册
	event: function() {
		// 取消按钮
	},

	// 表单验证
	validate: function() {

		if(null == $("#addRole-form #userName").val() || 0 == $("#addRole-form #userName").val().length) {
			alert("您输入的用户名为空");
			return false;
		}

		if(null == $("#addRole-form #password").val() || 0 == $("#addRole-form #password").val().length) {
			alert("您输入的密码为空");
			return false;
		}

		return true;

	},

	service: {

		doSave: function(callBackFunc) {
			if(!addRole.validate()) {
				return false;
			}
			var token = localStorage.getItem("Authorization");
			var role = 1;
			var userName = $("#addRole-form #userName").val();
			var password = $("#addRole-form #password").val();
			var station = $("#addRole-form #station option:selected").text();
			var stationId = ("#addRole-form #station option:selected").val();
			mui.ajax(GLOBAL.SERVER_URL + "api/users/add", {

				data: {
					role: role,
					userName: userName,
					password: password,
					station: station,
					stationId: stationId
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
					debugger
					if(data.result != 1) {
						mui.toast(data.msg);
						if(data.code == "1001") {
							alert("登录超时");
							window.location.href = "login.html";
						}
						return;
					}
					if(data.data.id == 0) {
						alert("您输入的用户名已经存在");
						return;
					}
					console.log(data);
					callBackFunc(data);
				}
			});
		},

		doGetStation: function() {
			var token = localStorage.getItem("Authorization");
			mui.ajax(GLOBAL.SERVER_URL + "xxx", {
				headers: {
					'Content-Type': 'application/json',
					'Authorization': token,
				},
				dataType: 'json', //服务器返回json格式数据
				type: 'get', //HTTP请求类型
				error: function(xhr, type, errorThrown) {},
				success: function(data) {
					console.log(data);
					debugger
					if(data.result != 1) {
						mui.toast(data.msg);
						if(data.code == "1001") {
							alert("登录超时");
							window.location.href = "login.html";
						}
						return;
					}
					var select = $("#station");
					var html = "";
					$.each(data.data, function(index, result) {
						html += '<option value="8">韩泰轮胎</option>';
					});
					select.append(html)
				}
			});

		}
	},

	dao: {},
	init: function() {}
}
addStore.init();

function checkPhone(phone) {
	if(!(/^1[34578]\d{9}$/.test(phone))) {
		alert("手机号码有误，请重填");
		return false;
	}
	return true;
}