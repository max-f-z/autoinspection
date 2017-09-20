mui.init({
	swipeBack: true, //开启左滑关闭

});

(function($) {
	$.init();
})(mui);

priceList = {

	// 事件注册
	event: function() {
		// 取消按钮
		$("#priceList #searchBtn").on("click", priceList.service.doSearch);

		$("#priceList #addBtn").on("click", priceList.service.doAdd);
	},

	// 表单验证
	validate: function() {

	},

	service: {

		doSearch: function() {
			var search = $("#priceList #seacrh").val();
			
				var level1 = "";
			if($("#priceList #level1").val() != '0'){
				level1 = $("#priceList #level1").val();
			}
			$("#tbody").empty();
			var token = localStorage.getItem("Authorization");
			mui.ajax(GLOBAL.SERVER_URL + "api/serviceprice/prices/search", {
				data: {
					search:search,
					level1:level1
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
					pageLoadingFrame("hide");
					if(data.result != 1) {
						mui.toast(data.msg);
						if(data.code == "1001") {
							window.location.href = "login.html";
						}
						return;
					}

					if(data.data.length == 0) {
						$("#priceNoList").show();
						return
					}
					$("#priceNoList").hide();

					var html = "";
					var tbody = $("#priceList #tbody");
					$.each(data.data, function(index, result) {
						console.log(result);
						html += "<tr>";
						var no = index + 1;
						html += "<td>" + no + "</td>";
						if(result.serviceName == "1") {
							html += "<td>检测服务</td>";
						}
						if(result.serviceName == "2") {
							html += "<td>产品销售</td>";
						}
						if(result.serviceName == "3") {
							html += "<td>租赁服务</td>";
						}
						if(result.serviceName == "4") {
							html += "<td>救援服务</td>";
						}
						if(result.serviceName == "5") {
							html += "<td>维保服务</td>";
						}
						if(result.serviceName == "6") {
							html += "<td>检测服务</td>";
						}
						html += "<td>" + result.priceDesc + "</td>";
						html += "<td>" + result.customerName + "</td>";
						html += "<td>" + result.price + "</td>";
						html += "<td>" + result.priceDesc + "</td>";
						html += "<td><button type='button' class='btn btn-info' style='margin-right:5px' onclick='priceList.service.doDelete(" + result.id + ")'>删除</button><button type='button' style='margin-left:5px' class='btn btn-info' onclick='priceList.service.doEdit(" + result.id + ")'>查看</button></td>";
						html += "</tr>";
					});
					tbody.append(html);
					
				}
			});
		},

		doQuery: function() {
			$("#tbody").empty();
			var token = localStorage.getItem("Authorization");
			mui.ajax(GLOBAL.SERVER_URL + "api/serviceprice/prices", {
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
					pageLoadingFrame("hide");
					if(data.result != 1) {
						mui.toast(data.msg);
						if(data.code == "1001") {
							window.location.href = "login.html";
						}
						return;
					}
					if(data.data.length == 0) {
						$("#priceNoList").show();
						return
					}
					$("#priceNoList").hide();
					var html = "";
					var tbody = $("#priceList #tbody");
					$.each(data.data, function(index, result) {
						console.log(result);

						html += "<tr>";
						var no = index + 1;
						html += "<td>" + no + "</td>";
						if(result.serviceName == "1") {
							html += "<td>检测服务</td>";
						}
						if(result.serviceName == "2") {
							html += "<td>产品销售</td>";
						}
						if(result.serviceName == "3") {
							html += "<td>租赁服务</td>";
						}
						if(result.serviceName == "4") {
							html += "<td>救援服务</td>";
						}
						if(result.serviceName == "5") {
							html += "<td>维保服务</td>";
						}
						if(result.serviceName == "6") {
							html += "<td>检测服务</td>";
						}
						html += "<td>" + result.priceDesc + "</td>";
						html += "<td>" + result.customerName + "</td>";
						html += "<td>" + result.price + "</td>";
						html += "<td>" + result.priceDesc + "</td>";
						html += "<td><button type='button' class='btn btn-info' style='margin-right:5px' onclick='priceList.service.doDelete(" + result.id + ")'>删除</button><button type='button' style='margin-left:5px' class='btn btn-info' onclick='priceList.service.doEdit(" + result.id + ")'>查看</button></td>";
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
				mui.ajax(GLOBAL.SERVER_URL + "api/serviceprice/prices/delete", {
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
						priceList.service.doQuery();
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
				mui.ajax(GLOBAL.SERVER_URL + "api/serviceprice/prices/delete", {
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
						priceList.service.doQuery();
						swal("禁用！", "您的店面已经被禁用", "success");
					}

				});

			});
		},

		doAdd: function(callBackFunc) {
			debugger
			$.ajax({
				type: "get",
				url: "baseInfo/addPrice.html",
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
									addPrice.service.doSave(function(rs) {

										if(rs.result == 1) {

											// 重新查询
											priceList.service.doQuery();

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
			localStorage.setItem("priceId", id);
			$.ajax({
				type: "get",
				url: "baseInfo/editPrice.html",
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
									editPrice.service.doSave(function(rs) {

										if(rs.result == 1) {

											// 重新查询
											priceList.service.doQuery();
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
		pageLoadingFrame("show");
		priceList.event();
		priceList.service.doQuery();
		//priceList.createPage();
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
priceList.init();