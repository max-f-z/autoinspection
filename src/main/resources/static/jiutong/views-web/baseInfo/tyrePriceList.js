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
				data:{
					search:search	
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
					var tbody = $("#tyrePriceList #tbody");
					$.each(data.data, function(index, result) {
						console.log(result);
						html += "<tr>";
						var no = index + 1;
						html += "<td>" + result.id + "</td>";
						html += "<td>" + result.brand + "</td>";
						html += "<td>" + result.description + "</td>";
						html += "<td>" + result.stripe + "</td>";
						html += "<td>" + result.price + "</td>";
						html += "<td><button type='button' class='btn btn-info' onclick='tyrePriceList.service.doDelete(" + result.id + ")'>删除</button><button type='button' class='btn btn-info' onclick='tyrePriceList.service.doClose(" + result.id + ")'>禁用</button></td>";
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
					console.log(data.data);
					var html = "";
					var tbody = $("#tyrePriceList #tbody");
					$.each(data.data, function(index, result) {
						console.log(result);
						html += "<tr>";
						var no = index + 1;
						html += "<td>" + result.id + "</td>";
						html += "<td>" + result.brand + "</td>";
						html += "<td>" + result.description + "</td>";
						html += "<td>" + result.stripe + "</td>";
						html += "<td>" + result.price + "</td>";
						html += "<td><button type='button' class='btn btn-info' onclick='tyrePriceList.service.doDelete(" + result.id + ")'>删除</button><button type='button' class='btn btn-info' onclick='tyrePriceList.service.doClose(" + result.id + ")'>禁用</button></td>";
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
						swal("删除！", "您的店面已经被删除", "success");
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
				url: "../views/baseInfo/addTyrePrice.html",
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

											swal("成功！", "您的服务添加成功", "success");
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
		}
	},

	dao: {},
	init: function() {
		tyrePriceList.event();
		tyrePriceList.service.doQuery();
	}
}
tyrePriceList.init();