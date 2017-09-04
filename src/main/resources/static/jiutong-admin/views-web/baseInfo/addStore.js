mui.init({
	swipeBack: true, //开启左滑关闭

});

(function($) {
	$.init();
})(mui);

addStore = {

	// 事件注册
	event: function() {
		// 取消按钮
	},

	// 表单验证
	validate: function() {
		debugger;
		if(null == $("#addStore-form #name").val() || 0 == $("#addStore-form #name").val().length) {
			alert("您输入的名称为空");
			return false;
		}

		if(null == $("#addStore-form #district").text()||  0 == $("#addStore-form #district").text().length) {
			alert("您输入的地区为空");
			return false;
		}

		if(null == $("#addStore-form #address").val()||  0 == $("#addStore-form #address").val().length) {
			alert("您输入的详细地址为空");
			return false;
		}

		if(null == $("#addStore-form #latitude").val()||  0 == $("#addStore-form #latitude").val().length) {
			alert("您输入的精度为空");
			return false;
		}

		if(null == $("#addStore-form #phone").val()||  0 == $("#addStore-form #phone").val().length) {
			alert("您输入的纬度为空");
			return false;
		}

		if(null == $("#addStore-form #principal").val()|| 0 == $("#addStore-form #principal").val().length) {
			alert("您输入的检测中心电话为空");
			return false;
		}


		if(null == $("#addStore-form #principalPhone").val()|| 0 == $("#addStore-form #principal").val().length) {
			alert("您输入的负责电话为空");
			return false;
		}
		
		return true;

	},

	service: {

		doSave: function(callBackFunc) {
			debugger;
			if(!addStore.validate()){
				return false;
			}
			
			var name = $("#addStore-form #name").val();
			var district = $("#addStore-form #district option:selected").text();
			var address = $("#addStore-form #address").val();
			var latitude = $("#addStore-form #latitude").val();
			var longitude = $("#addStore-form #longitude").val();
			var phone = $("#addStore-form #phone").val();
			var principal = $("#addStore-form #principal").val();
			var principalPhone = $("#addStore-form #principalPhone").val()
			var token = localStorage.getItem("Authorization");

			mui.ajax(GLOBAL.SERVER_URL + "api/station/stations/add", {
				data: {
					"name": name,
					"district": district,
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
					console.log(data);
					debugger;
					callBackFunc(data);
				}
			});
		},
	},

	dao: {},
	init: function() {}
}
addStore.init();