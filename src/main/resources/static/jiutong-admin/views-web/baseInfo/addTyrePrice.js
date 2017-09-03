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

		if(null == $("#addTyrePrice-form #brand").val() || 0 == $("#addTyrePrice-form #brand").val().length) {
			alert("您输入的名称为空");
			return false;
		}

		if(null == $("#addTyrePrice-form #description").val() || 0 == $("#addTyrePrice-form #description").val().length) {
			alert("您输入的地区为空");
			return false;
		}

		if(null == $("#addTyrePrice-form #stripe").val() || 0 == $("#addTyrePrice-form #stripe").val().length) {
			alert("您输入的详细地址为空");
			return false;
		}

		if(null == $("#addTyrePrice-form #price").val() || 0 == $("#addTyrePrice-form #price").val().length) {
			alert("您输入的精度为空");
			return false;
		}
	},

	service: {

		doSave: function(callBackFunc) {

			if(!addTyrePrice.validate()) {
				return false;
			}
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