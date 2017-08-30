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

	},

	service: {

		doSave: function(callBackFunc) {
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
		
		
	},

	dao: {},
	init: function() {}
}
addPrice.init();