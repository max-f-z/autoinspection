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
		$("#orderList #searchBtn").on("click", orderList.service.doSearch);

	},

	// 表单验证
	validate: function() {

	},

	service: {

		doQuery: function(status) {

			if(status == -1) {
				$("#allOrder").addClass("active");
				$("#noPayOrder").removeClass("active");
				$("#payOrder").removeClass("active");
				status = '';
			}
			if(status == 0) {
				$("#allOrder").removeClass("active");
				$("#noPayOrder").addClass("active");
				$("#payOrder").removeClass("active");
			}
			if(status == 1) {
				$("#allOrder").removeClass("active");
				$("#noPayOrder").removeClass("active");
				$("#payOrder").addClass("active");
			}
			$("#tbody").empty();
			var token = localStorage.getItem("Authorization");
			mui.ajax(GLOBAL.SERVER_URL + "api/order/search", {
				data: {
					status: status
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
					var html = '';
					var tbody = $("#orderList #tbody");
					html += '<div class="table-responsive">';
					$.each(data.data, function(index, result) {
						html += '<table class="table datatable">';
						html += '<tbody >';
						html += '<tr>';
						html += '<td><span style="color: #990000">' + result.plate + '</span></td>';
						html += '<td>&nbsp;</td>';
						html += '<td>&nbsp;</td>';
						html += '<td>&nbsp;</td>';
						html += '<td class="text-right">状态：<span style="color: #990000">已完成</span></td>';
						html += '</tr>';
						html += '<tr>';
						html += '<td>订单编号：<span>' + result.orderNo + '</span></td>';
						html += '<td>下单日期：<span>' + result.createDate + '</span></td>';
						html += '<td>客户类型：<span>' + result.customerTypeName + '</span></td>';
						html += '<td>客户名称：<span>' + result.customerName + '</span></td>';
						html += '<td>客户电话：<span>' + result.customerPhone + '</span></td>';
						html += '</tr>';
						html += '</tbody>';
						html += '</table>';
						html += '<table class="table table-bordered table-striped table-actions " style="text-align: center">';
						html += '<thead>';
						html += '<tr>';
						html += '<th style="text-align: center">轮位</th>';
						html += '<th style="text-align: center">轮胎编号</th>';
						html += '<th style="text-align: center">服务类型</th>';
						html += '<th style="text-align: center">描述</th>';
						html += '<th style="text-align: center">单价</th>';
						html += '<th style="text-align: center">数量</th>';
						html += '<th style="text-align: center">金额</th>';
						html += '</tr>';
						html += '</thead>';
						html += '<tbody id="tbody">';
						$.each(result.maintenanceList, function(index, result) {
							html += '<tr>';
							html += '<td style="text-align: center">' + result.tireposition + '</th>';
							html += '<td style="text-align: center">' + result.tireposition + '</th>';
							html += '<td style="text-align: center">' + result.servicePriceName + '</th>';
							html += '<td style="text-align: center">' + result.servicePriceDesc + '</th>';
							html += '<td style="text-align: center">' + result.servicePriceDesc + '</th>';
							html += '<td style="text-align: center">' + result.num + '</th>';
							html += '<td style="text-align: center">' + result.servicePrice + '</th>';
							html += '</tr>';
						});

						html += '</tbody>';
						html += '</table>';
					});
					html += '</div>';
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
		//pageLoadingFrame("show");
		orderList.event();
		orderList.service.doQuery();
		$(".datepicker").datepicker({
			format: "yyyy-mm-dd",
			timeFormat: 'hh:mm:ss',
		});
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
orderList.init();