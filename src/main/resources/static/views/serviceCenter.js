mui.init({
	swipeBack: true, //开启左滑关闭

});

(function($) {
	$.init();
})(mui);

serviceCenter = {

	// 事件注册
	event: function() {
		// 取消按钮

	},

	// 表单验证
	validate: function() {

	},

	service: {

		doQuery: function() {
			var list = $("#serviceCenter");
			serviceCenter.service.doDraw(list, null);
		},

		// 生成列表
		doDraw: function(list, rows) {
			// 加载模板
			mui.get("serviceCenter-tmpl.html", function(data) {
				var container = $("<div style=''></div>");
				container.html(data);
				var authToken = localStorage.getItem("Authorization");
				console.log(authToken);
				mui.ajax(GLOBAL.SERVER_URL + "wx/api/stations", {

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
							li = $("<div></div>");
							li.attr("data-id", result.id);
							container.find("span[id=name]").text(result.name);
							container.find("span[id=place]").text(result.address);
							container.find("span[id=phone]").text(result.phone);
							container.find("div[id=mapBtn]").html("<a onclick='serviceCenter.service.doMap()'>导航</a>");
							var serviceResult = result;
							container.find("div[id=addServiceBtn]").html("<a onclick='serviceCenter.service.doAddService(" + JSON.stringify(serviceResult).replace(/"/g, '&quot;') + ")'>预约线下服务</a>");
							// 模板渲染
							li.html(container.html());
							list.append(li);
						});
					},
					headers: {
						'Authorization': authToken,

					},
				});

			}, "html");
		},
		doMap: function() {
			window.location.href = "http://uri.amap.com/navigation?from=116.478346,39.997361,startpoint&to=116.3246,39.966577,endpoint&via=116.402796,39.936915,midwaypoint&mode=car&policy=1&src=mypage&coordinate=gaode&callnative=0"
		},

		doAddService: function(serviceResult) {

			localStorage.setItem("stationId", serviceResult.id);
			localStorage.setItem("stationName", serviceResult.name);
			localStorage.setItem("stationAddress", serviceResult.address);
			window.location.href = "serviceList.html"
		}

	},

	dao: {},
	init: function() {

		if(localStorage.getItem("Authorization") == null) {
			window.location.href = "login.html";
		}
		serviceCenter.event();
		serviceCenter.service.doQuery();
	}
}

serviceCenter.init();