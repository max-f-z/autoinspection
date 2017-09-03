mui.init({
	swipeBack: true, //开启左滑关闭

});

(function($) {
	$.init();
})(mui);

addMail = {

	// 事件注册
	event: function() {
		// 取消按钮
	},

	// 表单验证
	validate: function() {

		if(null == $("#addMail-form #name").val() ||  0 == $("#addMail-form #name").val().length) {
			alert("您输入的客户名称为空");
			return false;
		}

		if(null == $("#addMail-form #code").val()||  0 == $("#addMail-form #code").val().length) {
			alert("您输入的客户代码为空");
			return false;
		}

		if(null == $("#addMail-form #contactName").val() ||  0 == $("#addMail-form #contactName").val().length ) {
			alert("您输入的联系人为空");
			return false;
		}

		if(null == $("#addMail-form #contactPhone").val()||  0 == $("#addMail-form #contactPhone	").val().length) {
			alert("您的输入的联系电话为空");
			return false;
		}

		if(null == $("#addMail-form #address").val() ||  0 == $("#addMail-form #address").val().length ) {
			alert("您的输入的地址为空");
			return false;
		}

		if(null == $("#addMail-form #salesman").val() ||  0 == $("#addMail-form #salesman").val().length ) {
			alert("您的输入的销售员为空");
			return false;
		}

	},

	service: {

		doSave: function(callBackFunc) {
			
			if(!addMail.validate()){
				return false;
			}
			
			
			var name = $("#addMail-form #name").val();
			var code = $("#addMail-form #code").val();
			var contactName = $("#addMail-form #contactName").val();
			var contactPhone = $("#addMail-form #contactPhone").val();
			var address = $("#addMail-form #address").val();
			var salesman = $("#addMail-form #salesman").val();

			var token = localStorage.getItem("Authorization");
			mui.ajax(GLOBAL.SERVER_URL + "api/customer/customers/add", {
				data: {
					name: name,
					code: code,
					contactName: contactName,
					contactPhone: contactPhone,
					address: address,
					salesman: salesman
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
addMail.init();