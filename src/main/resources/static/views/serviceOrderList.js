mui.init({
	swipeBack: true, //开启左滑关闭

});

(function($) {
	$.init();
})(mui);

serviceOrderList = {

	// 事件注册
	event: function() {
		// 取消按钮

	},

	service: {

		doRegister: function() {
			var token = localStorage.getItem("Authorization");

			var appointmentDate = localStorage.getItem("date");
			var appointmentSlot = localStorage.getItem("appointmentSlot");
			var plate = "辽B 12312";
			var serviceId = localStorage.getItem("serviceId");
			var stationId = localStorage.getItem("stationId");

			mui.ajax(GLOBAL.SERVER_URL + "wx/api/register", {
				data: {
					appointmentDate: appointmentDate,
					appointmentSlot: appointmentSlot,
					plate: plate,
					serviceId: serviceId,
					stationId: stationId
				},
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
						return;
					}
					console.log(data.data);
					var html = "";
					$.each(data.data, function(index, result) {
						mui.alert("恭喜您预约成功");
						window.location.href = "orderList.html";
					});
					$("#time").append(html);
				}
			});

		},

	},

	dao: {},
	init: function() {

		if(localStorage.getItem("Authorization") == null) {
			window.location.href = "login.html";
		}

		serviceOrderList.event();

		$("#serviceName").text(localStorage.getItem("serviceName"));
		$("#describle").text(localStorage.getItem("serviceDescription"));
		$("#station").text(localStorage.getItem("stationName"));
		$("#place").text(localStorage.getItem("stationAddress"));
		$("#orderTime").text(localStorage.getItem("date"));
	}
}
serviceOrderList.init();