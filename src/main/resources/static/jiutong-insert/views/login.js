mui.init({
	swipeBack: true, //开启左滑关闭

});

(function($) {
	$.init();
})(mui);

login = {

	// 事件注册
	event: function() {
		// 登陆按钮事件
		$("#loginBtn").on("click", login.service.doLogin);

	},

	// 表单验证
	validate: function() {

		if($("#uname").val() == "") {
			mui.toast("您输入的手机号码为空");
			return false;
		}

		if($("#pwd").val() == "") {
			mui.toast("您输入的密码为空");
			return false;
		}
		return true;
	},

	service: {

		// 登陆

		doLogin: function() {

			var uname = $("#uname").val();
			var pwd = $("#pwd").val();
			var phoneObj = $("#phone").val();
			if(!login.validate()) {
				return false;
			}

			mui.ajax(GLOBAL.SERVER_URL + "auth/signin", {
				data: {
					uname: uname,
					pwd: pwd
				},
				headers: {
					'Content-Type': 'application/json',
				},
				dataType: 'json', //服务器返回json格式数据
				type: 'post', //HTTP请求类型
				error: function(xhr, type, errorThrown) {
					mui.alert("登录失败");
				},
				success: function(data) {
					if(data.result != 1) {
						mui.toast(data.msg);
						return;
					}
					mui.toast("登录成功");
					window.location.href = "index.html"
				}
			});

		}
	},

	dao: {},
	init: function() {

		login.event();
	}
}
login.init();