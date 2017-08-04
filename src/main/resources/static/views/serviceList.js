mui.init({
	swipeBack: true, //开启左滑关闭

});

(function($) {
	$.init();
})(mui);

serviceList = {

	// 事件注册
	event: function() {
		// 取消按钮

	},

	// 表单验证
	validate: function() {

	},

	service: {

		doQuery: function() {

			var list = $("#serviceList");
			serviceList.service.doDraw(list, null);
		},

		// 生成列表
		doDraw: function(list, rows) {
			// 加载模板
			mui.get("serviceList-tmpl.html", function(data) {
				var container = $("<div ></div>");
				container.html(data);
				var authToken = localStorage.getItem("Authorization");
				mui.ajax(GLOBAL.SERVER_URL + "wx/api/services", {

					dataType: 'json', //服务器返回json格式数据
					type: 'get', //HTTP请求类型
					timeout: 10000, //超时时间设置为10秒；
					error: function(xhr, type, errorThrown) {
						mui.alert("获取失败");
					},
					success: function(data) {
						if(data.result != 1) {
							mui.toast(data.msg);
							return;
						}
						$.each(data.data, function(index, result) {
							var li = "";
							var serviceResult = result;
							li = $("<div  onclick='serviceList.service.showServiceInfo(" + JSON.stringify(serviceResult).replace(/"/g, '&quot;') + ")'></div>");
							li.attr("data-id", result.id);
							container.find("span[id=serviceName]").text(result.name);
							container.find("span[id=describle]").text(result.description);

							li.html(container.html());
							list.append(li);
						});

					},
					headers: {
						'Authorization': authToken
					},
				});
			}, "html");
		},

		showServiceInfo: function(serviceResult) {
			localStorage.setItem("serviceId", serviceResult.id);
			localStorage.setItem("serviceName", serviceResult.name);
			localStorage.setItem("serviceDescription", serviceResult.description);
			window.location.href = "orderService.html";
		}
	},

	dao: {},
	init: function() {
		if(localStorage.getItem("Authorization") == null) {
			window.location.href = "login.html";
		}
		serviceList.event();
		serviceList.service.doQuery();
	}
}
serviceList.init();