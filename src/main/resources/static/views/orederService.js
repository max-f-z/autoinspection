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
			$("#time").empty();
			var token = localStorage.getItem("Authorization");
			mui.ajax(GLOBAL.SERVER_URL + "wx/api/appointments", {
				data: {
					date: localStorage.getItem("checkDay")
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
						console.log(result);
						if(result.remain == 1) {

							html += "<li class='mui-table-view-cell' style='border-bottom:  1px solid #f3f3f3;' id='showSexPickerMask' onclick='orederService.service.addOrder(" + JSON.stringify(result).replace(/"/g, '&quot;') + ")'><span class='mui-navigate-right'>" + result.name + "</span></li>"
						} else {
							html += "<li class='mui-table-view-cell' style='border-bottom:  1px solid #f3f3f3;' ><span class='mui-navigate-right'>" + result.name + "</span><span style='color:red'>(约满)</span></li>"
						}

					});
					$("#time").append(html);
				}
			});
		},

		addOrder: function(result) {
			localStorage.setItem("appointmentSlot", result.id);
			localStorage.setItem("appointmentName", result.name);
			localStorage.setItem("date", localStorage.getItem("checkDay"));

			window.location.href = "serviceOrderList.jsp";
		}

	},

	dao: {},
	init: function() {

		var date_selected = new Date();
		var checkDate = "";
		var month = ""
		var dayDate = ""
		if((date_selected.getMonth() + 1) < 10) {
			month = "0" + (date_selected.getMonth() + 1);
		} else {
			month = (date_selected.getMonth() + 1);
		}
		if(date_selected.getDate() < 10) {
			dayDate = "0" + (date_selected.getDate() + 1);
		} else {
			dayDate = date_selected.getDate() + 1;
		}

		checkDate = date_selected.getFullYear() + "-" + month + "-" + dayDate;
		localStorage.setItem("checkDay", checkDate);
		orederService.event();
		orederService.service.doQuery();
	}
}
orederService.init();