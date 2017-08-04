mui.init({
	swipeBack: true, //开启左滑关闭

});

(function($) {
	$.init();
})(mui);

personnal = {

	// 事件注册
	event: function() {
		// 登陆按钮事件
		$("#loginBtn").on("click", personnal.service.doQuery);

		$("#saveBtn").on("click", personnal.service.doSave);

	},
	service: {

		// 登陆
		doQuery: function() {
			var token = localStorage.getItem("Authorization");

			mui.ajax(GLOBAL.SERVER_URL + "wx/api/userinfo", {
				headers: {
					Authorization: token,
				},
				dataType: 'json', //服务器返回json格式数据
				type: 'get', //HTTP请求类型
				error: function(xhr, type, errorThrown) {
					mui.alert("登录失败");
				},
				success: function(data) {
					$("#phone").val(data.data.phone);
				}
			});
		},

		doSave: function() {
			var phoneObj = $("#phone").val();
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
					mui.ajax(GLOBAL.SERVER_URL + "wx/api/updateUser", {
						headers: {
							'Authorization': token,
							'Content-Type': 'application/json',
						},
						data: {
							phone: phone
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
							$("#phone").val(data.data.phone);
							location.reload();
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

		if(localStorage.getItem("Authorization") == null) {
			window.location.href = "login.html";
		}

		personnal.event();

		personnal.service.doQuery();
	}
}
personnal.init();