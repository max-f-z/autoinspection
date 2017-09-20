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

		$("#serviceList #addBtn").on("click", serviceList.service.doAdd);

		$("#serviceList #searchBtn").on("click", serviceList.service.doSearch);

	},

	// 表单验证
	validate: function() {

	},

	service: {

		doSearch: function() {
			$("#tbody").empty();
			var search = $("#serviceList #search").val();
			var level1 = "";
			if($("#serviceList #level1").val() != '0'){
				level1 = $("#serviceList #level1").val();
			}
			
			var token = localStorage.getItem("Authorization");
			mui.ajax(GLOBAL.SERVER_URL + "api/service/services/search", {
				data: {
					search: search,
					level1:level1
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

					if(data.data.length == 0) {
						$("#serviceNoList").show();
						return
					}
					$("#serviceNoList").hide();

					var html = "";
					var tbody = $("#serviceList #tbody");
					$.each(data.data, function(index, result) {
						console.log(result);
						html += "<tr>";
						var no = index + 1;
						html += "<td>" + no + "</td>";
						if(result.name == "1") {
							html += "<td>检测服务</td>";
						}
						if(result.name == "2") {
							html += "<td>产品销售</td>";
						}
						if(result.name == "3") {
							html += "<td>租赁服务</td>";
						}
						if(result.name == "4") {
							html += "<td>救援服务</td>";
						}
						if(result.name == "5") {
							html += "<td>维保服务</td>";
						}
						if(result.name == "6") {
							html += "<td>检测服务</td>";
						}
						html += "<td>" + result.description + "</td>";
						html += "<td><button type='button' class='btn btn-info' style='margin-right:5px' onclick='serviceList.service.doDelete(" + result.id + ")'>删除</button><button type='button' style='margin-left:5px' class='btn btn-info' onclick='serviceList.service.doEdit(" + result.id + ")'>查看</button></td>";
						html += "</tr>";
					});
					tbody.append(html);
				}
			});
		},

		doQuery: function() {
			$("#tbody").empty();
			var token = localStorage.getItem("Authorization");
			mui.ajax(GLOBAL.SERVER_URL + "api/service/services", {
				headers: {
					'Content-Type': 'application/json',
					'Authorization': token,
				},
				dataType: 'json', //服务器返回json格式数据
				type: 'get', //HTTP请求类型
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
					var html = "";
					var tbody = $("#serviceList #tbody");
					$.each(data.data, function(index, result) {

						if(data.data.length == 0) {
							$("#serviceNoList").show();
							return
						}
						$("#serviceNoList").hide();
						html += "<tr>";
						var no = index + 1;
						html += "<td>" + no + "</td>";

						if(result.name == "1") {
							html += "<td>检测服务</td>";
						}
						if(result.name == "2") {
							html += "<td>产品销售</td>";
						}
						if(result.name == "3") {
							html += "<td>租赁服务</td>";
						}
						if(result.name == "4") {
							html += "<td>救援服务</td>";
						}
						if(result.name == "5") {
							html += "<td>维保服务</td>";
						}
						if(result.name == "6") {
							html += "<td>检测服务</td>";
						}

						html += "<td>" + result.description + "</td>";
						html += "<td><button type='button' class='btn btn-info' style='margin-right:5px' onclick='serviceList.service.doDelete(" + result.id + ")'>删除</button><button type='button' style='margin-left:5px' class='btn btn-info' onclick='serviceList.service.doEdit(" + result.id + ")'>查看</button></td>";
						html += "</tr>";
					});
					tbody.append(html);
					pageLoadingFrame("hide");
				}
			});
		},

		doDelete: function(id) {
			swal({
				title: "您确定要删除吗？",
				text: "您确定要删除这条数据？",
				type: "warning",
				showCancelButton: true,
				closeOnConfirm: false,
				confirmButtonText: "是的",
				confirmButtonColor: "#ec6c62"
			}, function() {
				var token = localStorage.getItem("Authorization");
				mui.ajax(GLOBAL.SERVER_URL + "api/service/services/delete", {
					data: {
						id: id
					},
					headers: {
						'Content-Type': 'application/json',
						'Authorization': token,
					},
					dataType: 'json', //服务器返回json格式数据
					type: 'delete', //HTTP请求类型
					error: function(xhr, type, errorThrown) {},
					success: function(data) {
						serviceList.service.doQuery();
						swal("删除！", "删除成功", "success");
					}

				});

			});
		},

		doClose: function(id) {
			swal({
				title: "您确定要禁用吗？",
				text: "您确定要禁用这条数据？",
				type: "warning",
				showCancelButton: true,
				closeOnConfirm: false,
				confirmButtonText: "是的",
				confirmButtonColor: "#ec6c62"
			}, function() {
				var token = localStorage.getItem("Authorization");
				mui.ajax(GLOBAL.SERVER_URL + "api/service/services/delete", {
					data: {
						id: id
					},
					headers: {
						'Content-Type': 'application/json',
						'Authorization': token,
					},
					dataType: 'json', //服务器返回json格式数据
					type: 'delete', //HTTP请求类型
					error: function(xhr, type, errorThrown) {},
					success: function(data) {
						serviceList.service.doQuery();
						swal("禁用！", "您的服务已经被禁用", "success");
					}

				});

			});
		},

		doAdd: function(callBackFunc) {
			$.ajax({
				type: "get",
				url: "baseInfo/addService.html",
				success: function(data) {
					debugger;
					var dialog = bootbox.dialog({
						size: "large",
						title: "新建",
						message: data,
						buttons: {
							save: {
								label: "保存",
								className: "btn-success",

								callback: function() {

									// 保存
									addService.service.doSave(function(rs) {

										if(rs.result == 1) {

											// 重新查询
											serviceList.service.doQuery();

											// 关闭对话框
											dialog.modal("hide");

											swal("添加！", "添加成功", "success");
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

		doEdit: function(id) {
			localStorage.setItem("serviceId", id);
			$.ajax({
				type: "get",
				url: "baseInfo/editService.html",
				success: function(data) {
					debugger;
					var dialog = bootbox.dialog({
						size: "large",
						title: "查看详情",
						message: data,
						buttons: {
							save: {
								label: "保存",
								className: "btn-success",

								callback: function() {
									debugger
									// 保存
									editService.service.doSave(function(rs) {
										debugger
										if(rs.result == 1) {

											// 重新查询
											serviceList.service.doQuery();
											// 关闭对话框
											dialog.modal("hide");

											swal("修改！", "修改成功", "success");
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
		}
	},

	dao: {},
	init: function() {
		pageLoadingFrame("show");
		serviceList.event();
		serviceList.service.doQuery();
		//serviceList.createPage();
	},

//	createPage: function() {
//		$(".pagination").jBootstrapPage({
//			pageSize: 10,
//			total: 100,
//			maxPageButton: 5,
//			onPageClicked: function(obj, pageIndex) {
//				alert((pageIndex + 1) + '页');
//			}
//		});
//	}
}
serviceList.init();