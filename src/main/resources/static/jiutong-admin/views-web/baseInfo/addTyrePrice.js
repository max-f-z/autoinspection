mui.init({
	swipeBack: true, //开启左滑关闭

});

(function($) {
	$.init();
})(mui);

addTyrePrice = {

	// 事件注册
	event: function() {
		// 取消按钮
	},

	// 表单验证
	validate: function() {

	},

	service: {

		doSave: function(callBackFunc) {
			var brand = $("#addTyrePrice-form #brand").val();
			var description = $("#addTyrePrice-form #description").val();
			var stripe = $("#addTyrePrice-form #stripe").val();
			var price = $("#addTyrePrice-form #price").val();

			var token = localStorage.getItem("Authorization");
			mui.ajax(GLOBAL.SERVER_URL + "api/tireprice/prices/add", {
				data: {
					brand: brand,
					description: description,
					stripe: stripe,
					price: price
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
		}
	},

	dao: {},
	init: function() {}
}
addTyrePrice.init();