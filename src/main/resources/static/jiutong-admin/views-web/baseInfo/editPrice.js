mui.init({
	swipeBack: true, //开启左滑关闭

});

(function($) {
	$.init();
})(mui);

editPrice = {

	// 事件注册
	event: function() {
		// 取消按钮
	},

	// 表单验证
	validate: function() {

		if(null == $("#editPrice-form #serviceId").val() || 0 == $("#editPrice-form #serviceId").val()) {
			alert("您输入的服务类型为空");
			return false;
		}

		if(null == $("#editPrice-form #customerId").val() || 0 == $("#editPrice-form #customerId").val()) {
			alert("您输入的客户名称为空");
			return false;
		}

		if(null == $("#editPrice-form #price").val() || 0 == $("#editPrice-form #price").val().length) {
			alert("您输入的价格为空");
			return false;
		}

		if(null == $("#editPrice-form #priceDesc").val() || 0 == $("#editPrice-form #priceDesc").val().length) {
			alert("您的输入的价格描述为空");
			return false;
		}
		return true;
	},

	service: {

		doSave: function(callBackFunc) {

			if(!editPrice.validate()) {
				return false;
			}

			var serviceId = $("#editPrice-form #serviceId").val();
			var customerId = $("#editPrice-form #customerId").val();
			var price = $("#editPrice-form #price").val();
			var priceDesc = $("#editPrice-form #priceDesc").val();

			var id = localStorage.getItem("priceId");

			var token = localStorage.getItem("Authorization");
			mui.ajax(GLOBAL.SERVER_URL + "api/serviceprice/prices/update", {
				data: {
					id: id,
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

		doQuery: function(id) {
			var token = localStorage.getItem("Authorization");
			mui.ajax(GLOBAL.SERVER_URL + "api/serviceprice/prices/" + id, {
				headers: {
					'Content-Type': 'application/json',
					'Authorization': token,
				},
				dataType: 'json', //服务器返回json格式数据
				type: 'get', //HTTP请求类型
				error: function(xhr, type, errorThrown) {},
				success: function(data) {
					$("#editPrice-form #serviceId").val(data.data.serviceId);
					$("#editPrice-form #customerId").val(data.data.customerId);
					$("#editPrice-form #price").val(data.data.price);
					$("#editPrice-form #priceDesc").val(data.data.priceDesc);
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
					var tbody = $("#editPrice-form #customerId");
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
					var tbody = $("#editPrice-form #serviceId");

					$.each(data.data, function(index, result) {
						html += "<option value='" + result.id + "'>" + result.name + "-" + result.description + "</option>"
					});
					tbody.append(html);
				}
			});
		},

	},

	dao: {},
	init: function() {
		var id = localStorage.getItem("priceId");
		debugger;
		editPrice.service.getCustomerList();
		editPrice.service.getServiceList();
		editPrice.service.doQuery(id);
		
	}
}
editPrice.init();