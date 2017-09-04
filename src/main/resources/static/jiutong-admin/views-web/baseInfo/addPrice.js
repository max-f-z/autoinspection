mui.init({
	swipeBack: true, //开启左滑关闭

});

(function($) {
	$.init();
})(mui);

addPrice = {

	// 事件注册
	event: function() {
		// 取消按钮
	},

	// 表单验证
	validate: function() {

		if(null == $("#addPrice-form #serviceId").val() || 0 == $("#addPrice-form #serviceId").val()) {
			alert("您输入的服务类型为空");
			return false;
		}

		if(null == $("#addPrice-form #customerId").val() || 0 == $("#addPrice-form #customerId").val()) {
			alert("您输入的客户名称为空");
			return false;
		}

		if(null == $("#addPrice-form #price").val() || 0 == $("#addPrice-form #price").val().length) {
			alert("您输入的价格为空");
			return false;
		}

		if(null == $("#addPrice-form #priceDesc").val() || 0 == $("#addPrice-form #priceDesc").val().length) {
			alert("您的输入的价格描述为空");
			return false;
		}

		return true;

	},

	service: {

		doSave: function(callBackFunc) {

			if(!addPrice.validate()) {
				return false;
			}

			var serviceId = $("#addPrice-form #serviceId").val();
			var customerId = $("#addPrice-form #customerId").val();
			var price = $("#addPrice-form #price").val();
			var priceDesc = $("#addPrice-form #priceDesc").val();

			var token = localStorage.getItem("Authorization");
			mui.ajax(GLOBAL.SERVER_URL + "api/serviceprice/prices/add", {
				data: {
					serviceId: serviceId,
					customerId: customerId,
					price: price,
					priceDesc: priceDesc
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

		getCustomerList: function() {
			var token = localStorage.getItem("Authorization");
			mui.ajax(GLOBAL.SERVER_URL + "api/customer/customers", {
				data: {},
				headers: {
					'Content-Type': 'application/json',
					'Authorization': token,
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
					var html = "";
					var tbody = $("#addPrice-form #customerId");
					$.each(data.data, function(index, result) {
						html += "<option value='" + result.id + "'>" + result.name + "</option>"
					});
					tbody.append(html);
				}
			});
		},

		getServiceList: function() {
			var token = localStorage.getItem("Authorization");
			mui.ajax(GLOBAL.SERVER_URL + "api/service/services", {
				headers: {
					'Content-Type': 'application/json',
					'Authorization': token,
				},
				dataType: 'json', //服务器返回json格式数据
				type: 'get', //HTTP请求类型
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
					debugger;
					var html = "";
					var tbody = $("#addPrice-form #serviceId");

					$.each(data.data, function(index, result) {
						html += "<option value='" + result.id + "'>" + result.name + "</option>"
					});
					tbody.append(html);
				}
			});
		},

	},

	dao: {},
	init: function() {
		debugger
		addPrice.service.getServiceList();
		addPrice.service.getCustomerList();
	}
}
addPrice.init();