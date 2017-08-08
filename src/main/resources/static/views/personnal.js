mui.init({
	swipeBack: true, //开启左滑关闭

});

(function($) {
	$.init();
})(mui);
var issend = true;
personnal = {

	// 事件注册
	event: function() {
		// 登陆按钮事件
		$("#loginBtn").on("click", personnal.service.doQuery);

		$("#saveBtn").on("click", personnal.service.doSave);

		$("#editBtn").on("click", personnal.service.doEdit);
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
								if(data.code == "1001") {
									window.location.href = "login.html";
								}
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
		},

		doEdit: function() {
			$("#saveBtn").removeAttr("hidden");
			$("#editBtn").attr("hidden", "hidden");

			$("#name").removeAttr("disabled");
			$("#phone").removeAttr("disabled");
			
			$("#newCode").removeAttr("hidden");
		},

		senmobile: function(t) {
			if(issend) {
				//验证电话号码手机号码 
				var phoneObj = document.getElementById('phone');
				var get_code = document.getElementById('codeBtn');
				if(phoneObj.value != "") {
					var phoneVal = phoneObj.value;
					var p1 = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
					var me = false;
					if(p1.test(phoneVal)) me = true;
					if(!me) {
						phoneObj.value = '';
						mui.toast('请输入正确的手机号码');
						phoneObj.focus();
						return false;
					} else {
						personnal.service.getCode();
						issend = false;
						for(i = 1; i <= 60; i++) {
							window.setTimeout("personnal.service.update_a(" + i + "," + 60 + ")", i * 1000);
						}
					}
				} else {
					mui.toast('手机号码不能为空！', {
						verticalAlign: 'center'
					});
					return false;
				}
			}
		},

		update_a: function(num, t) {
			var get_code = document.getElementById('codeBtn');
			if(num == t) {
				get_code.innerHTML = " 重新发送 ";
				issend = true;
			} else {
				var printnr = t - num;
				get_code.innerHTML = printnr + " 秒后重发";
			}
		},

		getCode: function() {
			var phone = $("#phone").val();
			mui.ajax(GLOBAL.SERVER_URL + "wx/authCode", {
				data: {
					phone: phone,
					authCode:2
				},
				headers: {
					'Content-Type': 'application/json',
				},
				dataType: 'json', //服务器返回json格式数据
				type: 'post', //HTTP请求类型
				error: function(xhr, type, errorThrown) {
					mui.alert("获取验证码失败");
				},
				success: function(data) {

				}
			});
		},
	},

	dao: {},
	init: function() {

		personnal.event();

		personnal.service.doQuery();
	}
}
personnal.init();