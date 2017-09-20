mui.init({
	swipeBack: true, //开启左滑关闭

});

(function($) {
	$.init();
})(mui);

editMail = {

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

		if(null == $("#addMail-form #contactPhone").val()||  0 == $("#addMail-form #contactPhone").val().length) {
			alert("您的输入的联系电话为空");
			return false;
		}else{
			if(!checkPhone($("#addMail-form #contactPhone").val())){
				return false;
			}
		}

		if(null == $("#addMail-form #address").val() ||  0 == $("#addMail-form #address").val().length ) {
			alert("您的输入的地址为空");
			return false;
		}

		if(null == $("#addMail-form #salesman").val() ||  0 == $("#addMail-form #salesman").val().length ) {
			alert("您的输入的销售员为空");
			return false;
		}
		return true;
	},
	service: {

		doSave: function(callBackFunc) {
			
			if(!editMail.validate()){
				return;
			}
			var name = $("#editMail-form #name").val();
			var code = $("#editMail-form #code").val();
			var contactName = $("#editMail-form #contactName").val();
			var contactPhone = $("#editMail-form #contactPhone").val();
			var address = $("#editMail-form #address").val();
			var salesman = $("#editMail-form #salesman").val();

			var id = localStorage.getItem("mailId");
			var token = localStorage.getItem("Authorization");
			mui.ajax(GLOBAL.SERVER_URL + "api/customer/customers/update", {
				data: {
					id: id,
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
					callBackFunc(data);
				}
			});
		},

		doQuery: function(id) {
			var token = localStorage.getItem("Authorization");
			debugger
			mui.ajax(GLOBAL.SERVER_URL + "api/customer/customers/" + id, {
				headers: {
					'Content-Type': 'application/json',
					'Authorization': token,
				},
				dataType: 'json', //服务器返回json格式数据
				type: 'get', //HTTP请求类型
				error: function(xhr, type, errorThrown) {},
				success: function(data) {
					$("#editMail-form #name").val(data.data.name);
					$("#editMail-form #code").val(data.data.code);
					$("#editMail-form #contactName").val(data.data.contactName);
					$("#editMail-form #contactPhone").val(data.data.contactPhone);
					$("#editMail-form #address").val(data.data.address);
					$("#editMail-form #salesman").val(data.data.salesman);
				}
			});
		}
	},

	dao: {},
	init: function() {
		var id = localStorage.getItem("mailId");
		debugger
		editMail.service.doQuery(id);
	}
}
editMail.init();

function checkPhone(phone) {
	if(!(/^1[34578]\d{9}$/.test(phone))) {
		alert("手机号码有误，请重填");
		return false;
	}
	return true;
}