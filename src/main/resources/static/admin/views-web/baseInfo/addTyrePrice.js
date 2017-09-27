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

		if(null == $("#addTyrePrice-form #brand").text() || 0 == $("#addTyrePrice-form #brand").val()) {
			alert("您输入的品牌为空");
			return false;
		}

		if(null == $("#addTyrePrice-form #description").val() || 0 == $("#addTyrePrice-form #description").val().length) {
			alert("您输入的描述为空");
			return false;
		}

		if(null == $("#addTyrePrice-form #stripe").val() || 0 == $("#addTyrePrice-form #stripe").val().length) {
			alert("您输入的花纹为空");
			return false;
		}

		if(null == $("#addTyrePrice-form #price").val() || 0 == $("#addTyrePrice-form #price").val().length) {
			alert("您输入的价格为空");
			return false;
		}

		return true;
	},

	service: {

		doSave: function(callBackFunc) {

			if(!addTyrePrice.validate()) {
				return false;
			}
			
			if($("#addTyrePrice-form #price").val() <= 0 ){
				alert("请您输入正确的价格");
				return false
			}
			var brand = $("#addTyrePrice-form #brand option:selected").val();
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
		}
	},

	dao: {},
	init: function() {}
}
addTyrePrice.init();