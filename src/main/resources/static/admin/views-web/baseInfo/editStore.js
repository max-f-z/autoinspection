mui.init({
	swipeBack: true, //开启左滑关闭

});

(function($) {
	$.init();
})(mui);

editStore = {

	// 事件注册
	event: function() {
		// 取消按钮
	},

	// 表单验证
	validate: function() {
		debugger;
		if(null == $("#editStore-form #name").val() || 0 == $("#editStore-form #name").val().length) {
			alert("您输入的名称为空");
			return false;
		}

		if(null == $("#editStore-form #district").text() || 0 == $("#editStore-form #district").val()) {
			alert("您输入的地区为空");
			return false;
		}

		if(null == $("#editStore-form #address").val() || 0 == $("#editStore-form #address").val().length) {
			alert("您输入的详细地址为空");
			return false;
		}

		if(null == $("#editStore-form #latitude").val() || 0 == $("#editStore-form #latitude").val().length) {
			alert("您输入的经度为空");
			return false;
		}

		if(null == $("#editStore-form #longitude").val() || 0 == $("#editStore-form #longitude").val().length) {
			alert("您输入的纬度为空");
			return false;
		}

		if(null == $("#editStore-form #phone").val() || 0 == $("#editStore-form #phone").val().length) {
			alert("您输入的检测中心电话为空");
			return false;
		} else {
			if(!checkPhone($("#editStore-form #phone").val())) {
				return false;
			}
		}

		if(null == $("#editStore-form #principal").val() || 0 == $("#editStore-form #principal").val().length) {
			alert("您输入的负责人为空");
			return false;
		}

		if(null == $("#editStore-form #principalPhone").val() || 0 == $("#editStore-form #principalPhone").val().length) {
			alert("您输入的负责人电话为空");
			return false;
		} else {
			if(!checkPhone($("#editStore-form #principalPhone").val())) {
				return false;
			}
		}

		return true;

	},
	service: {

		doSave: function(callBackFunc) {
			debugger;
			if(!editStore.validate()) {
				return false;
			}

			var id = localStorage.getItem("storeId");
			var name = $("#editStore-form #name").val();
			var district = $("#editStore-form #district option:selected").val();
			var address = $("#editStore-form #address").val();
			var latitude = $("#editStore-form #latitude").val();
			var longitude = $("#editStore-form #longitude").val();
			var phone = $("#editStore-form #phone").val();
			var principal = $("#editStore-form #principal").val();
			var principalPhone = $("#editStore-form #principalPhone").val()
			var token = localStorage.getItem("Authorization");

			mui.ajax(GLOBAL.SERVER_URL + "api/station/stations/update", {
				data: {
					id: id,
					name: name,
					district: district,
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
			mui.ajax(GLOBAL.SERVER_URL + "api/station/stations/" + id, {
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
					$("#editStore-form #name").val(data.data.name);
					//$("#editStore-form #district").val(data.data.district);
					debugger

					$("#editStore-form #district").val(data.data.district);
					$("#editStore-form #address").val(data.data.address);
					$("#editStore-form #latitude").val(data.data.latitude);
					$("#editStore-form #longitude").val(data.data.longitude);
					$("#editStore-form #phone").val(data.data.phone);
					$("#editStore-form #principal").val(data.data.principal);
					$("#editStore-form #principalPhone").val(data.data.principalPhone);
				}
			});
		}
	},

	dao: {},
	init: function() {
		var id = localStorage.getItem("storeId");
		editStore.service.doQuery(id);
	}
}
editStore.init();

function checkPhone(phone) {
	if(!(/^1[34578]\d{9}$/.test(phone))) {
		alert("手机号码有误，请重填");
		return false;
	}
	return true;
}