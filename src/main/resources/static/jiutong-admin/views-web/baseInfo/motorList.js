(function($) {
	$.init();
})(mui);

motorList = {

	// 事件注册
	event: function() {

		$("#motorList #addBtn").on("click", motorList.service.doAdd);

		$("#motorList #searchBtn").on("click", motorList.service.doSearch);

		$("#motorList #doExpBtn").on("click", motorList.service.doExpload);

	},

	// 表单验证
	validate: function() {

	},

	service: {

		doSearch: function() {
			$("#tbody").empty();
			var search = $("#search").val();
			var token = localStorage.getItem("Authorization");
			mui.ajax(GLOBAL.SERVER_URL + "api/vehicle/vehicles/search", {
				data: {
					page: 1,
					pageSize: 100,
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
					var tbody = $("#motorList #tbody");

					var html = "";
					$.each(data.data, function(index, result) {
						html += '<table class="table table-striped">';
						html += '<tr>';
						html += '<td width="200">编号</td>';
						html += '<td id="id">' + result.id + '</td>';
						html += '<td>车牌号</td>';
						html += '<td class="text-right" id="plateNo">' + result.plate + '</td>';
						html += '<td><strong>客户</strong></td>';
						html += '<td class="text-right" id="costermName"><strong>' + result.customerName + '</strong></td>';
						html += '<td><strong>车辆类型编号</strong></td>';
						html += '<td class="text-right"><strong><span id="tyreNo">' + result.vehicleType + '</span></strong></td>';
						html += '<td><strong>操作</strong></td>';
						html += "<td><button type='button' class='btn btn-info' onclick='motorList.service.doDelete(" + result.id + ")'>删除</button></td>";
						html += '</tr>';
						html += '</table>';
						html += '<div class="" >';
						$.each(result.tires, function(index, result) {

							if(index % 4 == 0) {
								html += '<div class="col-xs-4 pull-left" >';
								html += '<table class="table table-bordered table-striped table-actions " style="text-align: center">';
								html += '<thead>';
								html += '<tr>';
								html += '<th style="text-align: center;width: 33.33%;">轮位</th><th style="text-align: center;width: 33.33%;">轮胎品牌</th><th style="text-align: center;width: 33.33%;">轮胎编号</th></tr></thead>';
								html += '<tbody>';
							}
							html += '<tr>';
							html += '<td>' + result.tirePosition + '</td>';
							html += '<td>' + result.tireBrand + '</td>';
							html += '<td>' + result.tireId + '</td>';
							html += '</tr>';
							if(index % 4 == 3) {
								html += '</tbody>';
								html += '</table>';
								html += '</div>';
							}
						});
						html += '</tbody>';
						html += '</table>';
						html += '</div>';
					});
					tbody.append(html);
				}
			});
		},

		doQuery: function() {
			$("#tbody").empty();
			var token = localStorage.getItem("Authorization");
			mui.ajax(GLOBAL.SERVER_URL + "api/vehicle/vehicles", {
				data: {
					page: 1,
					pageSize: 100
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
					var tbody = $("#motorList #tbody");

					var html = "";
					$.each(data.data, function(index, result) {
						console.log(result);
						html += '<table class="table table-striped">';
						html += '<tr>';
						html += '<td width="200">编号</td>';
						html += '<td id="id">' + result.id + '</td>';
						html += '<td>车牌号</td>';
						html += '<td class="text-right" id="plateNo">' + result.plate + '</td>';
						html += '<td><strong>客户</strong></td>';
						html += '<td class="text-right" id="costermName"><strong>' + result.customerName + '</strong></td>';
						html += '<td><strong>车辆类型编号</strong></td>';
						if(result.vehicleType == 1){
							html += '<td class="text-right"><strong><span id="tyreNo">牵引车</span></strong></td>';
						}else{
							html += '<td class="text-right"><strong><span id="tyreNo">挂车</span></strong></td>';
						}
						
						html += '<td><strong>操作</strong></td>';
						html += "<td><button type='button' class='btn btn-info' onclick='motorList.service.doDelete(" + result.id + ")'>删除</button></td>";
						html += '</tr>';
						html += '</table>';
						html += '<div class="" >';

						$.each(result.tires, function(index, result) {

							if(index % 4 == 0) {
								html += '<div class="col-xs-4 pull-left" >';
								html += '<table class="table table-bordered table-striped table-actions " style="text-align: center">';
								html += '<thead>';
								html += '<tr>';
								html += '<th style="text-align: center;width: 33.33%;">轮位</th><th style="text-align: center;width: 33.33%;">轮胎品牌</th><th style="text-align: center;width: 33.33%;">轮胎编号</th></tr></thead>';
								html += '<tbody>';
							}
							html += '<tr>';
							html += '<td>' + result.tirePosition + '</td>';
							html += '<td>' + result.tireBrand + '</td>';
							html += '<td>' + result.tireId + '</td>';
							html += '</tr>';
							if(index % 4 == 3) {
								html += '</tbody>';
								html += '</table>';
								html += '</div>';
							}
						});
						html += '</tbody>';
						html += '</table>';
						html += '</div>';
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
				mui.ajax(GLOBAL.SERVER_URL + "api/vehicle/vehicles/delete", {
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
						motorList.service.doQuery();
						swal("删除！", "删除成功", "success");
					}

				});

			});
		},

		//新建
		doExpload: function() {
			//			var htmlUrl = "baseInfo/uploadFile.html";
			//			$("#mainBody").empty();
			//			$("#mainBody").load(htmlUrl, function() {});

			$.ajax({
				type: "get",
				url: "baseInfo/uploadFile.html",
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
									uploadFile.service.doSave(function(rs) {

										if(rs.result == 1) {

											// 重新查询
											motorList.service.doQuery();

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

		doAdd: function(callBackFunc) {
			debugger
			$.ajax({
				type: "get",
				url: "baseInfo/addMotor.html",
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
									addMotor.service.doSave(function(rs) {

										if(rs.result == 1) {

											// 重新查询
											motorList.service.doQuery();

											swal("添加！", "添加成功", "success");
											// 关闭对话框
											dialog.modal("hide");
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

		motorList.event();
		motorList.service.doQuery();

	}
}
motorList.init();