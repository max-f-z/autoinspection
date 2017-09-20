mui.init({
	swipeBack: true, //开启左滑关闭

});

(function($) {
	$.init();
})(mui);

var totalPage;

var totalCount;

courseList = {

	event: function() {

		$("#doExpBtn").on("click", courseList.service.doExpload);

	},

	// 表单验证doSave
	validate: function() {

	},

	service: {

		doQueryDetail: function(page, size) {
	
			$("#tbody").empty();
			var token = localStorage.getItem("Authorization");
			var barCode = $("#barCode").val();
			var brand = $("#brand").val();
			var monthStart = $("#monthStart").val();
			var monthEnd = $("#monthEnd").val();
			var plateLike = $("#plateLike").val();

			var data = {};
			data["page"] = page;
			data["size"] = size;
			debugger
			if(monthStart != null && monthStart != '') {
				var time = new Date(monthStart).getTime();
				data["monthStart"] = monthStart;
			}
			if(monthEnd != null && monthEnd != '') {
				var time = new Date(monthEnd).getTime();
				data["monthEnd"] = monthEnd;
			}
			data["plateLike"] = plateLike;
			mui.ajax(GLOBAL.SERVER_URL + "api/vehicle/mileages", {
				data:data,
				headers: {
					'Content-Type': 'application/x-www-form-urlencoded',
					'Authorization': token,
				},
				dataType: 'json', //服务器返回json格式数据
				type: 'get', //HTTP请求类型
				error: function(xhr, type, errorThrown) {
					mui.alert("登录失败");
				},
				success: function(data) {
					console.log(data.data.pagination);
					if(data.result != 1) {
						mui.toast(data.msg);
						if(data.code == "1001") {
							window.location.href = "login.html";
						}
						return;
					}
					var html = "";
					var tbody = $("#courseList #tbody");
					if(data.data.length == 0) {
						$("#courseNoList").show();
						return
					} else {
						$("#courseNoList").hide();
					}

					totalPage = data.data.pagination.totalPage;
					totalCount = data.data.pagination.totalCount;
					debugger;
					$.each(data.data.data, function(index, result) {
						var datetime = new Date();
						datetime.setTime(result.month);
						var month = datetime.getMonth() + 1;
						var year = datetime.getFullYear();
						var no = index + 1;
						console.log(result);
						debugger;
						html += '<tr>';
						html += '<td style="text-align: center" width="100">' + no + '</td>';
						html += '<td style="text-align: center" width="100">' + result.plate + '</td>';
						html += '<td style="text-align: center" width="200">' + year + "年" + month + "月" + '</td>';
						html += '<td style="text-align: center" width="200">' + result.mile + '</td>';
						html += '<td style="text-align: center" width="200">' + result.period + '</td>';
						html += '<td style="text-align: center" width="200">' + result.averageSpeed + '</td>';
						html += '<td style="text-align: center" width="200">' + result.begMile + '</td>';
						html += '<td style="text-align: center" width="200">' + result.endMile + '</td>';
						html += '</tr>';

					});
					tbody.append(html);
					pageLoadingFrame("hide");
					courseList.createPage(totalPage, GLOBAL.INIT_SIZE, totalCount);
				}
			});
		},

		doSearch: function(page, size) {
			debugger;
			$("#courseList #tbody").empty();
			var token = localStorage.getItem("Authorization");
			var barCode = $("#barCode").val();
			var brand = $("#brand").val();
			var monthStart = $("#monthStart").val();
			var monthEnd = $("#monthEnd").val();
			var plateLike = $("#plateLike").val();
			var data = {};
			data["page"] = page;
			data["size"] = size;
			if(monthStart != null && monthStart != '') {
				var time = new Date(monthStart).getTime();
				data["monthStart"] = monthStart;
			}
			if(monthEnd != null && monthEnd != '') {
				var time = Date.parse(new Date(monthEnd));
				data["monthEnd"] = monthEnd;
			}
			data["plateLike"] = plateLike;

			mui.ajax(GLOBAL.SERVER_URL + "api/vehicle/mileages", {
				data: data,
				headers: {
					'Content-Type': 'application/x-www-form-urlencoded',
					'Authorization': token,
				},
				dataType: 'json', //服务器返回json格式数据
				type: 'get', //HTTP请求类型
				error: function(xhr, type, errorThrown) {
					mui.alert("登录失败");
				},
				success: function(data) {
					console.log(data.data.pagination);
					if(data.result != 1) {
						mui.toast(data.msg);
						if(data.code == "1001") {
							window.location.href = "login.html";
						}
						return;
					}
					var html = "";
					var tbody = $("#courseList #tbody");
					if(data.data.length == 0) {
						$("#courseNoList").show();
						return
					} else {
						$("#courseNoList").hide();
					}

					totalPage = data.data.pagination.totalPage;
					totalCount = data.data.pagination.totalCount;

					$.each(data.data.data, function(index, result) {
						var datetime = new Date();
						datetime.setTime(result.month);
						var month = datetime.getMonth() + 1;
						var year = datetime.getFullYear();
						var no = (data.data.pagination.page - 1) * 10 + index + 1;
						html += '<tr>';
						html += '<td style="text-align: center" width="50">' + no + '</td>';
						html += '<td style="text-align: center" width="100">' + result.plate + '</td>';
						html += '<td style="text-align: center" width="100">' + year + "年" + month + "月" + '</td>';
						html += '<td style="text-align: center" width="100">' + result.mile + '</td>';
						html += '<td style="text-align: center" width="100">' + result.period + '</td>';
						html += '<td style="text-align: center" width="100">' + result.averageSpeed + '</td>';
						html += '<td style="text-align: center" width="100">' + result.begMile + '</td>';
						html += '<td style="text-align: center" width="100">' + result.endMile + '</td>';
						html += '</tr>';

					});
					tbody.append(html);
					//courseList.createPage(totalPage, GLOBAL.INIT_SIZE, totalCount);	
				}
			});
		},

		doQuery: function(page, size) {
			debugger;
			$("#tbody").empty();
			var token = localStorage.getItem("Authorization");
			var barCode = $("#barCode").val();
			var brand = $("#brand").val();
			var monthStart = $("#monthStart").val();
			var monthEnd = $("#monthEnd").val();
			var plateLike = $("#plateLike").val();

			var data = {};
			data["page"] = page;
			data["size"] = size;
			if(monthStart != null && monthStart != '') {
				data["monthStart"] = monthStart;
			}
			if(monthEnd != null && monthEnd != '') {
				data["monthEnd"] = monthEnd;
			}
			data["plateLike"] = plateLike;
			mui.ajax(GLOBAL.SERVER_URL + "api/vehicle/mileages", {
				headers: {
					'Content-Type': 'application/x-www-form-urlencoded',
					'Authorization': token,
				},
				dataType: 'json', //服务器返回json格式数据
				type: 'get', //HTTP请求类型
				error: function(xhr, type, errorThrown) {
					mui.alert("登录失败");
				},
				success: function(data) {
					console.log(data.data.pagination);
					if(data.result != 1) {
						mui.toast(data.msg);
						if(data.code == "1001") {
							window.location.href = "login.html";
						}
						return;
					}
					var html = "";
					var tbody = $("#courseList #tbody");
					if(data.data.length == 0) {
						$("#courseNoList").show();
						return
					} else {
						$("#courseNoList").hide();
					}

					totalPage = data.data.pagination.totalPage;
					totalCount = data.data.pagination.totalCount;

					$.each(data.data.data, function(index, result) {
						var datetime = new Date();
						datetime.setTime(result.month);
						var month = datetime.getMonth() + 1;
						var year = datetime.getFullYear();
						var no = index + 1;
						html += '<tr>';
						html += '<td style="text-align: center" width="50">' + no + '</td>';
						html += '<td style="text-align: center" width="100">' + result.plate + '</td>';
						html += '<td style="text-align: center" width="100">' + year + "年" + month + "月" + '</td>';
						html += '<td style="text-align: center" width="100">' + result.mile + '</td>';
						html += '<td style="text-align: center" width="100">' + result.period + '</td>';
						html += '<td style="text-align: center" width="100">' + result.averageSpeed + '</td>';
						html += '<td style="text-align: center" width="100">' + result.begMile + '</td>';
						html += '<td style="text-align: center" width="100">' + result.endMile + '</td>';
						html += '</tr>';

					});
					tbody.append(html);
					pageLoadingFrame("hide");
					courseList.createPage(totalPage, GLOBAL.INIT_SIZE, totalCount);
				}
			});
		},

		doExpload: function() {

			$.ajax({
				type: "get",
				url: "baseInfo/uploadFileCourse.html",
				success: function(data) {
					debugger;
					var dialog = bootbox.dialog({
						size: "large",
						title: "上传excel",
						message: data,
						buttons: {
							save: {
								label: "上传",
								className: "btn-success",
								callback: function() {
									uploadFileCourse.service.doSave(function(rs) {

										if(rs.result == 1) {

											// 重新查询
											courseList.service.doSearch(GLOBAL.INIT_PAGE, GLOBAL.INIT_SIZE);

											// 关闭对话框
											dialog.modal("hide");

											swal("上传", "上传成功", "success");
										} else {
											alert("保存失败");
										}
									});
									return false;
								}
							},
							cancel: {
								label: "取消",
								className: "btn-default",
							}
						}
					});
				}
			});
		},

	},

	dao: {},
	init: function() {
		pageLoadingFrame("show");
		courseList.event();
		courseList.service.doQuery(GLOBAL.INIT_PAGE, GLOBAL.INIT_SIZE);
		$(".datepicker").datepicker({
			format: "yyyy-mm-dd",
			timeFormat: 'hh:mm:ss',
		});

	},

	createPage: function(page, size, total) {
		$(".pagination").jBootstrapPage({
			pageSize: size,
			total: total,
			maxPageButton: GLOBAL.INIT_PAGE_MAX_BTN,
			onPageClicked: function(obj, pageIndex) {
				debugger;
				courseList.service.doSearch(pageIndex + 1, GLOBAL.INIT_SIZE);
			}
		});
	}

}
courseList.init();