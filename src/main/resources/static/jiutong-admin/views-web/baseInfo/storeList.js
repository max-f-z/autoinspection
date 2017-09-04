mui.init({
	swipeBack: true, //开启左滑关闭

});

(function($) {
	$.init();
})(mui);

storeList = {

	// 事件注册
	event: function() {
		// 取消按钮
		$("#storeList #addBtn").on("click", storeList.service.doAdd);

		$("#storeList #searchBtn").on("click", storeList.service.doSearch);
	},

	// 表单验证
	validate: function() {

	},

	service: {

		doSearch: function() {

			var search = $("#storeList #search").val();
			var district = $("#storeList #district").val();
			debugger;
			$("#tbody").empty();
			var token = localStorage.getItem("Authorization");
			mui.ajax(GLOBAL.SERVER_URL + "api/station/stations", {
				data: {
					search: search,
					district: district
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
							window.location.href = "login.html";
						}
						return;
					}
					console.log(data.data);
					var html = "";
					var tbody = $("#storeList #tbody");
					$.each(data.data, function(index, result) {
						html += "<tr>";
						var no = index + 1;
						html += "<td>" + no + "</td>";
						html += "<td>" + result.name + "</td>";
						html += "<td>" + result.district + "</td>";
						html += "<td>" + result.address + "</td>";
						html += "<td>" + result.longitude + "</td>";
						html += "<td>" + result.latitude + "</td>";
						html += "<td>" + result.phone + "</td>";
						html += "<td>" + result.principal + "</td>";
						html += "<td>" + result.principalPhone + "</td>";
						html += "<td><button type='button' class='btn btn-info' onclick='storeList.service.doDelete(" + result.id + ")'>删除</button><button type='button' class='btn btn-info' onclick='storeList.service.doClose(" + result.id + ")'>查看</button></td>";
						html += "</tr>";
					});
					tbody.append(html);
				}
			});
		},

		doQuery: function() {
			debugger;
			$("#tbody").empty();
			var token = localStorage.getItem("Authorization");
			mui.ajax(GLOBAL.SERVER_URL + "api/station/stations", {
				data: {},
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
							window.location.href = "login.html";
						}
						return;
					}
					console.log(data.data);
					var html = "";
					var tbody = $("#storeList #tbody");
					$.each(data.data, function(index, result) {
						html += "<tr>";
						var no = index + 1;
						html += "<td>" + no + "</td>";
						html += "<td>" + result.name + "</td>";
						html += "<td>" + result.district + "</td>";
						html += "<td>" + result.address + "</td>";
						html += "<td>" + result.longitude + "</td>";
						html += "<td>" + result.latitude + "</td>";
						html += "<td>" + result.phone + "</td>";
						html += "<td>" + result.principal + "</td>";
						html += "<td>" + result.principalPhone + "</td>";
						html += "<td><button type='button' class='btn btn-info' onclick='storeList.service.doDelete(" + result.id + ")'>删除</button><button type='button' class='btn btn-info' onclick='storeList.service.doEdit(" + result.id + ")'>查看</button></td>";
						html += "</tr>";
					});
					tbody.append(html);
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
				mui.ajax(GLOBAL.SERVER_URL + "api/station/stations/delete", {
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
						storeList.service.doQuery();
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
				mui.ajax(GLOBAL.SERVER_URL + "api/station/stations/delete", {
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
						storeList.service.doQuery();
						swal("禁用！", "您的店面已经被禁用", "success");
					}

				});

			});
		},

		doAdd: function(callBackFunc) {
			$.ajax({
				type: "get",
				url: "baseInfo/addStore.html",
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
									debugger;
									// 保存
									debugger
									addStore.service.doSave(function(rs) {

										if(rs.result == 1) {

											// 重新查询
											storeList.service.doQuery();
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
			localStorage.setItem("storeId",id);
			$.ajax({
				type: "get",
				url: "baseInfo/editStore.html",
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
								debugger;
									// 保存
									editStore.service.doSave(function(rs) {

										if(rs.result == 1) {

											// 重新查询
											storeList.service.doQuery();
											// 关闭对话框
											dialog.modal("hide");
											
											swal("修改", "修改成功", "success");
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
		storeList.event();
		storeList.service.doQuery();
	}
}
storeList.init();