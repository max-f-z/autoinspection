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

		if($("#phone").val() == "") {
			mui.toast("您输入的手机号码为空");
			return false;
		}

		if($("#password").val() == "") {
			mui.toast("您输入的密码为空");
			return false;
		}
		return true;
	},

	service: {

		// 登陆

		doLogin: function() {
			var phone = $("#phone").val();
			var password = $("#password").val();
			var phoneObj = $("#phone").val();
			if(!login.validate()) {
				return false;
			}
			if(phoneObj != "") {
				var phoneVal = phoneObj;
				var p1 = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
				var me = false;
				if(p1.test(phoneVal)) me = true;
				if(!me) {
					phoneObj = '';
					mui.toast('请输入正确的手机号码');
					return false;
				} else {
					var token = localStorage.getItem("Authorization");
					var phone = $("#phone").val();
					mui.ajax(GLOBAL.SERVER_URL + "wx/signin", {
						data: {
							phone: phone,
							password: password
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
							if(data.result != 1){
								mui.toast(data.msg);
								return;
							}
							mui.toast("登录成功");
							console.log(data.data.token);
							localStorage.setItem("Authorization", data.data.token);
							window.location.href = "serviceCenter.html";
						}
					});
				}
			} else {
				mui.toast('手机号码不能为空！', {
					verticalAlign: 'center'
				});
				return false;
			}

		}
	},

	dao: {},
	init: function() {

		login.event();
	}
}
login.init();