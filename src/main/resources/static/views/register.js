mui.init({
	swipeBack: true, //开启左滑关闭

});

(function($) {
	$.init();
})(mui);
var issend = true;
register = {

	// 事件注册
	event: function() {
		// 登陆按钮事件
		$("#registerBtn").on("click", register.service.doRegister);

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
		doRegister: function() {
			var phone = $("#phone").val();
			var password = $("#password").val();
			var codeBtn = "1";
			//window.location.href = "http://uri.amap.com/navigation?from=116.478346,39.997361,startpoint&to=116.3246,39.966577,endpoint&via=116.402796,39.936915,midwaypoint&mode=car&policy=1&src=mypage&coordinate=gaode&callnative=0"
			if(!register.validate()) {
				return false;
			}
			mui.ajax(GLOBAL.SERVER_URL + "wx/signup", {
				data: {
					phone: phone,
					password: password,
					authCode: codeBtn
				},
				headers: {
					'Content-Type': 'application/json',
				},
				dataType: 'json', //服务器返回json格式数据
				type: 'post', //HTTP请求类型
				error: function(xhr, type, errorThrown) {
					mui.alert("注册失败");
				},
				success: function(data) {
					if(data.result != 1) {
						mui.toast(data.msg);
						return;
					}
					mui.toast("注册成功");
					console.log(data.data.token);
					localStorage.setItem("Authorization", data.data.token);
					window.location.href = "login.html";
				}
			});
		},

		getCode: function() {
			var phone = $("#phone").val();
			mui.ajax(GLOBAL.SERVER_URL + "wx/authCode", {
				data: {
					phone: phone,
					authCode:1
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

		senmobile: function(t) {
			if(issend) {
				//验证电话号码手机号码 
				var phoneObj = document.getElementById('phone');
				var pw = document.getElementById('password');
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
						register.service.getCode();
						issend = false;
						for(i = 1; i <= 60; i++) {
							window.setTimeout("register.service.update_a(" + i + "," + 60 + ")", i * 1000);
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
	},

	dao: {},
	init: function() {

		register.event();
	}
}
register.init();