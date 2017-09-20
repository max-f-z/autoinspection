mui.init({
	swipeBack: true, //开启左滑关闭

});

(function($) {
	$.init();
})(mui);

uploadFile = {

	// 事件注册
	event: function() {

	},

	// 表单验证
	validate: function() {

	},

	service: {
		doSave: function(callBackFunc) {
			debugger;
			pageLoadingFrame("show");
			var token = localStorage.getItem("Authorization");
			var formData = new FormData($("#fileinfo0")[0]);
			var customer = $("#customers").val();
			if(!customer) {
				alert("请选择客户");
			}
			formData.append("customerName", customer);
			$.ajax({
				headers: {
					'Authorization': token,
				},
				url: GLOBAL.SERVER_URL + 'api/vehicle/vehicles/import',
				type: 'POST',
				cache: false,
				data: formData,
				processData: false,
				contentType: false
			}).done(function(res) {
				pageLoadingFrame("hide");
				callBackFunc(res);
			}).fail(function(res) {});

		},

		doGetCustomers: function() {
			pageLoadingFrame("show");
			var token = localStorage.getItem("Authorization");
			$.ajax({
				headers: {
					'Authorization': token,
				},
				url: GLOBAL.SERVER_URL + 'api/customer/customers',
				type: 'POST',
				contentType: "application/json",
				cache: false,
				data: '{"search":""}'
			}).done(function(res) {
				pageLoadingFrame("hide");
				console.log(res.data);
				$("#customers").empty();
				if(res.data && res.data.length > 0) {
					for(var i = 0; i < res.data.length; i++) {
						$("#customers").append("<option value='" + res.data[i].code + "'>" + res.data[i].name + "</option>");
					}
				}
			}).fail(function(res) {});
		}

	},

	dao: {},
	init: function() {
		uploadFile.event();
		uploadFile.service.doGetCustomers();
	}
}
uploadFile.init();