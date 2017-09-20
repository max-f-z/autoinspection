mui.init({
	swipeBack: true, //开启左滑关闭

});

(function($) {
	$.init();
})(mui);

editTyrePrice = {

	// 事件注册
	event: function() {
		// 取消按钮
	},

	// 表单验证
	validate: function() {

		if(null == $("#editTyrePrice-form #brand").text() || 0 == $("#editTyrePrice-form #brand").val()) {
			alert("您输入的品牌为空");
			return false;
		}

		if(null == $("#editTyrePrice-form #description").val() || 0 == $("#editTyrePrice-form #description").val().length) {
			alert("您输入的描述为空");
			return false;
		}

		if(null == $("#editTyrePrice-form #stripe").val() || 0 == $("#editTyrePrice-form #stripe").val().length) {
			alert("您输入的花纹为空");
			return false;
		}

		if(null == $("#editTyrePrice-form #price").val() || 0 == $("#editTyrePrice-form #price").val().length) {
			alert("您输入的价格为空");
			return false;
		}
		return true;
	},

	service: {

		doSave: function(callBackFunc) {

			if(!editTyrePrice.validate()) {
				return false;
			}
			
			if($("#editTyrePrice-form #price").val() <= 0 ){
				alert("请您输入正确的价格");
				return false;
			}
			
			var brand = $("#editTyrePrice-form #brand option:selected").val();
			var description = $("#editTyrePrice-form #description").val();
			var stripe = $("#editTyrePrice-form #stripe").val();
			var price = $("#editTyrePrice-form #price").val();
			var id = localStorage.getItem("tyrePriceId");
			var token = localStorage.getItem("Authorization");
			mui.ajax(GLOBAL.SERVER_URL + "api/tireprice/prices/update", {
				data: {
					id: id,
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
			debugger
			var token = localStorage.getItem("Authorization");
			mui.ajax(GLOBAL.SERVER_URL + "api/tireprice/prices/" + id, {
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
					debugger
					$("#editTyrePrice-form #brand").val(data.data.brand);
					$("#editTyrePrice-form #description").val(data.data.description);
					$("#editTyrePrice-form #stripe").val(data.data.stripe);
					$("#editTyrePrice-form #price").val(data.data.price);

				}
			});
		}
	},

	dao: {},
	init: function() {
		var id = localStorage.getItem("tyrePriceId");
		debugger
		editTyrePrice.service.doQuery(id);
	}
}
editTyrePrice.init();