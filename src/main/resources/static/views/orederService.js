mui.init({
	swipeBack: true, //开启左滑关闭

});

(function($) {
	$.init();
})(mui);

orederService = {

	// 事件注册
	event: function() {
		// 取消按钮

	},

	// 表单验证
	validate: function() {

	},

	service: {

		doQuery: function() {
			var token = localStorage.getItem("Authorization");
			mui.ajax(GLOBAL.SERVER_URL + "wx/api/appointments", {
				data: {
					date: "2017-08-02"
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

						if(result.remain == 1) {
							html += "<li class='mui-table-view-cell' style='border-bottom:  1px solid #f3f3f3;' id='showSexPickerMask' onclick='orederService.service.addOrder(" + result.id + ")'><span class='mui-navigate-right'>" + result.name + "</span></li>"
						} else {
							html += "<li class='mui-table-view-cell' style='border-bottom:  1px solid #f3f3f3;' ><span class='mui-navigate-right'>" + result.name + "</span><span style='color:red'>(约满)</span></li>"
						}

					});
					$("#time").append(html);
				}
			});
		},

		addOrder: function(id) {
			localStorage.setItem("appointmentSlot", id);
			localStorage.setItem("date", "2017-08-02");

			window.location.href = "serviceOrderList.html";
		}

	},

	dao: {},
	init: function() {
		if(localStorage.getItem("Authorization") == null) {
			window.location.href = "login.html";
		}
		orederService.event();
		orederService.service.doQuery();
	}
}
orederService.init();