(function($) {
	$.init();
})(mui);

tyreList = {

	// 事件注册
	event: function() {

		$("#searchBtn").on("click", tyreList.service.doSearch);

		$("#addBtn").on("click", tyreList.service.doAdd);

	},

	// 表单验证
	validate: function() {

	},

	service: {

		doSearch: function() {
			var search = $("#search").val();
			var token = localStorage.getItem("Authorization-station");
			$("#tyreListForm #labelTBody").empty();
			mui.ajax(GLOBAL.SERVER_URL + "api/input/searchVehicles", {
				data: {
					page: 1,
					pageSize: 10,
					search: search
				},
				headers: {
					'Content-Type': 'application/json',
					'Authorization': token,
				},
				dataType: 'json', //服务器返回json格式数据
				type: 'POST', //HTTP请求类型
				error: function(xhr, type, errorThrown) {
					mui.alert("登录失败");
				},
				success: function(data) {
					if(data.result != 1) {
						mui.toast(data.msg);
						if(data.code == "1001") {
							window.location.href = "login.html";
						}

						return;
					}
					debugger
					if(data.data.length == 0) {
						$("#tyreForNoList").show();
						return
					}
					$("#tyreForNoList").hide();

					var html = "";
					var tbody = $("#tyreListForm #labelTBody");
					debugger
					$.each(data.data, function(index, result) {
						debugger
						html += "<table class='table datatable' onclick='tyreList.service.doRecord(" + JSON.stringify(result.plate) + "," + result.id + ")'>";
						html += "<tbody>";
						html += "<tr>";
						html += "<td width='300'>编号:<span>" + result.id + "</span></td>";
						html += "<td width='300'>车队/用户名称:<span>" + result.customerName + "</span></td>";
						html += "<td width='300'>车牌号:<span>" + result.plate + "</span>";
						if(result.regStatus == true) {
							html += "<span class='pull-right' style='color:red' >已预约</span>";
						} else {
							html += "<span class='pull-right' style='color:red' ></span>";
						}
						html += "</td>";
						html += "</tr>";
						html += "<tr style='margin-bottom: 20px;'>";
						if(result.regDate != null) {
							html += "<td width='300'>预约检测时间:<span>" + result.regDate + '&nbsp' + result.regTime + "</span></td>";
						} else {
							html += "<td width='300'>预约检测时间:<span></span></td>";
						}

						if(null != result.lastInspector) {
							html += "<td><span>" + result.lastInspector + "</span></td>";
						} else {
							html += "<td><span></span></td>";
						}
						if(null != result.lastInspectTime) {
							html += "<td><span>" + result.lastInspectTime.substring(0, 19) + "</span></td>";
						} else {
							html += "<td><span></span></td>";
						}

						html += "</tr>";
						html += "</tbody>";
						html += "</table>";
					});
					debugger
					tbody.append(html);
				}
			});
		},

		doQuery: function() {
			var search = $("#search").val();
			var token = localStorage.getItem("Authorization-station");
			$("#tyreListForm #labelTBody").empty();
			mui.ajax(GLOBAL.SERVER_URL + "api/input/listVehicles", {
				headers: {
					'Content-Type': 'application/json',
					'Authorization': token,

				},
				data: {
					page: 1,
					pageSize: 10,
					search: search
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
							window.location.href = "login.html";
						}
						return;
					}
					if(data.data.length == 0) {
						$("#tyreForNoList").show();
						return
					}
					$("#tyreForNoList").hide();
					var html = "";
					var tbody = $("#tyreListForm #labelTBody");
					debugger
					$.each(data.data, function(index, result) {
						debugger
						html += "<table class='table datatable' onclick='tyreList.service.doRecord(" + JSON.stringify(result.plate) + "," + result.id + ")'>";
						html += "<tbody>";
						html += "<tr>";
						html += "<td width='300'>编号:<span>" + result.id + "</span></td>";
						html += "<td width='300'>车队/用户名称:<span>" + result.customerName + "</span></td>";
						html += "<td width='300'>车牌号:<span>" + result.plate + "</span>";
						if(result.regStatus == true) {
							html += "<span class='pull-right' style='color:red' >已预约</span>";
						} else {
							html += "<span class='pull-right' style='color:red' ></span>";
						}
						html += "</td>";
						html += "</tr>";
						html += "<tr style='margin-bottom: 20px;'>";
						if(result.regDate != null) {
							html += "<td width='300'>预约检测时间:<span>" + result.regDate + '&nbsp' + result.regTime + "</span></td>";
						} else {
							html += "<td width='300'>预约检测时间:<span></span></td>";
						}

						if(null != result.lastInspector) {
							html += "<td><span>" + result.lastInspector + "</span></td>";
						} else {
							html += "<td><span></span></td>";
						}
						if(null != result.lastInspectTime) {
							html += "<td><span>" + result.lastInspectTime.substring(0, 19) + "</span></td>";
						} else {
							html += "<td><span></span></td>";
						}

						html += "</tr>";
						html += "</tbody>";
						html += "</table>";
					});
					debugger
					tbody.append(html);
				}
			});
		},
		doAdd: function() {
			window.location.href = "addVehicle.html";
		},

		doRecord: function(plate, id) {
			debugger;
			window.location.href = "tyreHistory.html?plate=" + plate + "&id=" + id;
		}

	},

	dao: {},
	init: function() {

		tyreList.event();

		tyreList.service.doQuery();

		tyreList.createPage(1, 10, 100);
	},

	createPage: function(page, size, total) {
		$(".pagination").jBootstrapPage({
			pageSize: size,
			total: total,
			maxPageButton: page,
			onPageClicked: function(obj, pageIndex) {
				debugger;
				//courseList.service.doSearch(pageIndex + 1, GLOBAL.INIT_SIZE);
			}
		});
	}

}

tyreList.init()