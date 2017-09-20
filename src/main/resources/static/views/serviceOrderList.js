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

		doRegister: function(){
			
			if($("#plateNo").val() == null || $("#plateNo").val() == "" || $("#plateNo").val() == undefined){
				mui.toast("请输入车牌号");
				return
			}
			var token = localStorage.getItem("Authorization");
			var appointmentDate = localStorage.getItem("date");
			var appointmentSlot = localStorage.getItem("appointmentSlot");
			var plate = $("#plateNo").val();
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
						if(data.code == "1001") {
							window.location.href = "login.jsp";
						}
						return;
					}
					console.log(data.data);
					var html = "";
					$.each(data.data, function(index, result) {
						mui.alert("恭喜您预约成功");
						window.location.href = "orderList.jsp";
					});
					$("#time").append(html);
				}
			});

		},

	},

	dao: {},
	init: function() {

		serviceOrderList.event();

		$("#serviceName").text(localStorage.getItem("serviceName"));
		$("#describle").text(localStorage.getItem("serviceDescription"));
		$("#station").text(localStorage.getItem("stationName"));
		$("#place").text(localStorage.getItem("stationAddress"));
		$("#orderTime").text(localStorage.getItem("date") + " " + localStorage.getItem("appointmentName"));
	}
}
serviceOrderList.init();