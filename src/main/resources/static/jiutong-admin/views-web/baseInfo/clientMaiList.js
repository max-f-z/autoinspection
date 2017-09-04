mui.init({
	swipeBack: true, //开启左滑关闭

});

(function($) {
	$.init();
})(mui);

clientMaiList = {

	// 事件注册
	event: function() {
		// 取消按钮

		$("#clientMailList #addBtn").on("click", clientMaiList.service.doAdd);

		$("#clientMailList #searchBtn").on("click", clientMaiList.service.doSearch);

	},

	// 表单验证
	validate: function() {
		
		
		

	},

	service: {

		doSearch: function() {

			var search = $("#clientMailList #search").val();
			debugger;
			$("#tbody").empty();
			var token = localStorage.getItem("Authorization");
			mui.ajax(GLOBAL.SERVER_URL + "api/customer/customers", {
				data: {
					search: search
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
					var html = "";
					var tbody = $("#clientMailList #tbody");
					$.each(data.data, function(index, result) {
						html += "<tr>";
						var no = index + 1;
						html += "<td>" + result.id + "</td>";
						html += "<td>" + result.name + "</td>";
						html += "<td>" + result.code + "</td>";
						html += "<td>" + result.contactName + "</td>";
						html += "<td>" + result.contactPhone + "</td>";
						html += "<td>" + result.address + "</td>";
						html += "<td>" + result.salesman + "</td>";
						html += "<td><button type='button' class='btn btn-info' onclick='clientMaiList.service.doDelete(" + result.id + ")'>删除</button><button type='button' class='btn btn-info' onclick='clientMaiList.service.doEdit(" + result.id + ")'>查看</button></td>";
						html += "</tr>";
					});
					tbody.append(html);
				}
			});
		},
		doQuery: function() {
			$("#tbody").empty();
			var token = localStorage.getItem("Authorization");
			mui.ajax(GLOBAL.SERVER_URL + "api/customer/customers", {
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
					var html = "";
					var tbody = $("#clientMailList #tbody");
					$.each(data.data, function(index, result) {
						html += "<tr>";
						var no = index + 1;
						html += "<td>" + result.id + "</td>";
						html += "<td>" + result.name + "</td>";
						html += "<td>" + result.code + "</td>";
						html += "<td>" + result.contactName + "</td>";
						html += "<td>" + result.contactPhone + "</td>";
						html += "<td>" + result.address + "</td>";
						html += "<td>" + result.salesman + "</td>";
						html += "<td><button type='button' class='btn btn-info' onclick='clientMaiList.service.doDelete(" + result.id + ")'>删除</button><button type='button' class='btn btn-info' onclick='clientMaiList.service.doEdit(" + result.id + ")'>查看</button></td>";
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
				mui.ajax(GLOBAL.SERVER_URL + "api/customer/customers/delete", {
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
						clientMaiList.service.doQuery();
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
				mui.ajax(GLOBAL.SERVER_URL + "api/customer/customers/delete", {
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
						clientMaiList.service.doQuery();
						swal("禁用！", "您的店面已经被禁用", "success");
					}

				});

			});
		},

		doAdd: function(callBackFunc) {
			$.ajax({
				type: "get",
				url: "baseInfo/addMail.html",
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
									debugger
									// 保存
									addMail.service.doSave(function(rs) {

										if(rs.result == 1) {

											// 重新查询
											clientMaiList.service.doQuery();

											// 关闭对话框
											dialog.modal("hide");

											swal("添加", "添加成功", "success");
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
			localStorage.setItem("mailId", id);
			$.ajax({
				type: "get",
				url: "baseInfo/editMail.html",
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

									// 保存
									editMail.service.doSave(function(rs) {

										if(rs.result == 1) {

											// 重新查询
											clientMaiList.service.doQuery();
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

		clientMaiList.event();
		clientMaiList.service.doQuery();
	}
}
clientMaiList.init();