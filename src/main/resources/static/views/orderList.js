mui.init({
	swipeBack: true, //开启左滑关闭

});

(function($) {
	$.init();
})(mui);

orderList = {

	// 事件注册
	event: function() {
		// 取消按钮

	},

	// 表单验证
	validate: function() {

	},

	service: {

		doQuery: function() {

			var list = $("#orderList");
			orderList.service.doDraw(list, null);
		},

		// 生成列表
		doDraw: function(list, rows) {
			// 加载模板
			mui.get("orderList-tmpl.html", function(data) {
				var container = $("<div style=''></div>");
				container.html(data);

				mui.ajax(GLOBAL.SERVER_URL + "wx/api/registrations", {
					headers: {
						'Content-Type': 'application/json',
						'Authorization': localStorage.getItem("Authorization")
					},
					dataType: 'json', //服务器返回json格式数据
					type: 'get', //HTTP请求类型
					error: function(xhr, type, errorThrown) {
						mui.alert("获取失败");
					},
					success: function(data) {
						if(data.result != 1) {
							mui.toast(data.msg);
							return;
						}

						console.log(data);
						if(data.length == 0) {
							return
						}

						$.each(data.data, function(index, result) {
							var li = "";
							li = $("<div></div>");
							li.attr("data-id", result.regId);
							container.find("span[id=date]").text(result.appointmentDate);
							container.find("span[id=station]").text(result.stationName);
							container.find("span[id=orderTime]").text(result.slotName);
							container.find("span[id=serviceType]").html(result.serviceName);
							container.find("div[id=btn]").html("<a onclick='orderList.service.doCancle(" + result.regId + ")'>取消预约</a>	");
							// 模板渲染
							li.html(container.html());
							list.append(li);
						});
					}
				});

			}, "html");
		},

		doCancle: function(regId) {
			var btnArray = ['取消', '确定'];
			mui.confirm('是否取消预约？', '提示', btnArray, function(e) {
				if(e.index == 1) {

					mui.ajax(GLOBAL.SERVER_URL + "wx/api/cancelRegistration", {
						data: {
							regId: regId
						},
						headers: {
							'Content-Type': 'application/json',
							'Authorization': localStorage.getItem("Authorization")
						},
						dataType: 'json', //服务器返回json格式数据
						type: 'post', //HTTP请求类型
						error: function(xhr, type, errorThrown) {
							mui.alert("取消失败");
						},
						success: function(data) {
							if(data.result != 1) {
								mui.toast(data.msg);
								return;
							}
							mui.toast("取消成功");
							location.reload();
						}
					});

				} else {}
			})
		},
	},

	dao: {},
	init: function() {
		if(localStorage.getItem("Authorization") == null) {
			window.location.href = "login.html";
		}
		orderList.event();
		orderList.service.doQuery();
	}
}

orderList.init();