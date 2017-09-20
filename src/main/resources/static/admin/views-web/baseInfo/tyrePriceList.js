mui.init({
	swipeBack: true, //开启左滑关闭

});

(function($) {
	$.init();
})(mui);

tyrePriceList = {

	// 事件注册
	event: function() {
		// 取消按钮

		$("#tyrePriceList #addBtn").on("click", tyrePriceList.service.doAdd);

		$("#tyrePriceList #searchBtn").on("click", tyrePriceList.service.doSearch);

	},

	// 表单验证
	validate: function() {

	},

	service: {

		doSearch: function() {
			$("#tbody").empty();
			var search = $("#tyrePriceList #search").val();

			var token = localStorage.getItem("Authorization");
			mui.ajax(GLOBAL.SERVER_URL + "api/tireprice/search", {
				headers: {
					'Content-Type': 'application/json',
					'Authorization': token,
				},
				data: {
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
					console.log(data.data);
					if(data.data.length == 0) {
						$("#tyrePriceNoList").show();
						return
					}
					$("#tyrePriceNoList").hide();
					var html = "";
					var tbody = $("#tyrePriceList #tbody");
					$.each(data.data, function(index, result) {
						console.log(result);
						html += "<tr>";
						var no = index + 1;
						html += "<td>" + no + "</td>";
						if(result.brand == "1") {
							html += "<td>普利司通</td>";
						}
						if(result.brand == "2") {
							html += "<td>米其林</td>";
						}
						if(result.brand == "3") {
							html += "<td>固特异</td>";
						}
						if(result.brand == "4") {
							html += "<td>倍耐力</td>";
						}
						if(result.brand == "5") {
							html += "<td>三角集团</td>";
						}
						if(result.brand == "6") {
							html += "<td>住友橡胶</td>";
						}
						if(result.brand == "7") {
							html += "<td>横滨橡胶</td>";
						}
						if(result.brand == "8") {
							html += "<td>韩泰轮胎</td>";
						}
						html += "<td>" + result.description + "</td>";
						html += "<td>" + result.stripe + "</td>";
						html += "<td>" + result.price + "</td>";
						html += "<td><button type='button' style='margin-right:5px' class='btn btn-info' onclick='tyrePriceList.service.doDelete(" + result.id + ")'>删除</button><button style='margin-left:5px' type='button' class='btn btn-info' onclick='tyrePriceList.service.doEdit(" + result.id + ")'>查看</button></td>";
						html += "</tr>";
					});
					tbody.append(html);
				}
			});
		},

		doQuery: function() {
			$("#tbody").empty();
			var token = localStorage.getItem("Authorization");
			mui.ajax(GLOBAL.SERVER_URL + "api/tireprice/prices", {
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
					if(data.data.length == 0) {
						$("#tyrePriceNoList").show();
						return
					}
					$("#tyrePriceNoList").hide();
					var html = "";
					var tbody = $("#tyrePriceList #tbody");
					$.each(data.data, function(index, result) {
						console.log(result);
						html += "<tr>";
						var no = index + 1;
						html += "<td>" + no + "</td>";
						if(result.brand == "1") {
							html += "<td>普利司通</td>";
						}
						if(result.brand == "2") {
							html += "<td>米其林</td>";
						}
						if(result.brand == "3") {
							html += "<td>固特异</td>";
						}
						if(result.brand == "4") {
							html += "<td>倍耐力</td>";
						}
						if(result.brand == "5") {
							html += "<td>三角集团</td>";
						}
						if(result.brand == "6") {
							html += "<td>住友橡胶</td>";
						}
						if(result.brand == "7") {
							html += "<td>横滨橡胶</td>";
						}
						if(result.brand == "8") {
							html += "<td>韩泰轮胎</td>";
						}

						html += "<td>" + result.description + "</td>";
						html += "<td>" + result.stripe + "</td>";
						html += "<td>" + result.price + "</td>";
						html += "<td><button type='button' class='btn btn-info' style='margin-right:5px' onclick='tyrePriceList.service.doDelete(" + result.id + ")'>删除</button><button type='button' style='margin-left:5px' class='btn btn-info' onclick='tyrePriceList.service.doEdit(" + result.id + ")'>查看</button></td>";
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
				mui.ajax(GLOBAL.SERVER_URL + "api/tireprice/prices/delete", {
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
						tyrePriceList.service.doQuery();
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
				mui.ajax(GLOBAL.SERVER_URL + "api/tireprice/prices/delete", {
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
						tyrePriceList.service.doQuery();
						swal("禁用！", "您的店面已经被禁用", "success");
					}

				});

			});
		},
		doAdd: function(callBackFunc) {
			$.ajax({
				type: "get",
				url: "baseInfo/addTyrePrice.html",
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
									addTyrePrice.service.doSave(function(rs) {

										if(rs.result == 1) {

											// 重新查询
											tyrePriceList.service.doQuery();

											// 关闭对话框
											dialog.modal("hide");

											swal("添加!", "添加成功", "success");
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
			localStorage.setItem("tyrePriceId", id);
			$.ajax({
				type: "get",
				url: "baseInfo/editTyrePrice.html",
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
									editTyrePrice.service.doSave(function(rs) {

										if(rs.result == 1) {

											// 重新查询
											tyrePriceList.service.doQuery();
											// 关闭对话框
											dialog.modal("hide");

											swal("修改!", "修改成功", "success");
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
		tyrePriceList.event();
		tyrePriceList.service.doQuery();
		//tyrePriceList.createPage();
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
tyrePriceList.init();