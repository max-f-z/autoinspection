mui.init({
	swipeBack: true, //开启左滑关闭

});

(function($) {
	$.init();
})(mui);

skuList = {

	event: function() {

		$("#doExpBtn").on("click", skuList.service.doExpload);

		$("#searchBtn").on("click", skuList.service.doSearch);
	},

	// 表单验证doSave
	validate: function() {

	},

	service: {

		doSearch: function(page,size) {
			$("#tbody").empty();
			var token = localStorage.getItem("Authorization");
			var barCode = $("#barCode").val();
			var brand = $("#brand").val();
			if(brand == '0') {
				brand = '';
			}
			debugger
			mui.ajax(GLOBAL.SERVER_URL + "api/instock/tyre/pageList?barCode=" + barCode + "&brand=" + brand + "&page=" + page + "&size=" + size, {
				headers: {
					'Content-Type': 'application/json',
					'Authorization': token,
				},
				dataType: 'json', //服务器返回json格式数据
				type: 'GET', //HTTP请求类型
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
					var tbody = $("#skuList #tbody");
					if(data.data.data.length == 0) {
						$("#skuNoList").show();
						return
					} else {
						$("#skuNoList").hide();
					}

					totalPage = data.data.pagination.totalPage;
					totalCount = data.data.pagination.totalCount;

					$.each(data.data.data, function(index, result) {
						console.log(result);
						debugger
						var no = index + 1;
						html += '<tr>';
						html += '<td style="text-align: center" width="50">' + no + '</td>';
						html += '<td style="text-align: center" width="100">' + result.tyreBrand + '</td>';
						html += '<td style="text-align: center" width="100">' + result.tyreType + '</td>';
						html += '<td style="text-align: center" width="100">' + result.barCode + '</td>';
						html += '<td style="text-align: center" width="100">' + result.figure + '</td>';
						html += '</tr>';
					});
					tbody.append(html);
				}
			});
		},

		doQuery: function(page, size) {
			$("#tbody").empty();
			var token = localStorage.getItem("Authorization");
			var barCode = $("#barCode").val();
			var brand = $("#brand").val();
			if(brand == '0') {
				brand = '';
			}
			debugger
			mui.ajax(GLOBAL.SERVER_URL + "api/instock/tyre/pageList?barCode=" + barCode + "&brand=" + brand + "&page=" + page + "&size=" + size, {
				headers: {
					'Content-Type': 'application/json',
					'Authorization': token,
				},
				dataType: 'json', //服务器返回json格式数据
				type: 'GET', //HTTP请求类型
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
					var tbody = $("#skuList #tbody");
					if(data.data.data.length == 0) {
						$("#skuNoList").show();
						return
					} else {
						$("#skuNoList").hide();
					}

					totalPage = data.data.pagination.totalPage;
					totalCount = data.data.pagination.totalCount;

					$.each(data.data.data, function(index, result) {
						console.log(result);
						debugger
						var no = index + 1;
						html += '<tr>';
						html += '<td style="text-align: center" width="50">' + no + '</td>';
						html += '<td style="text-align: center" width="200">' + result.tyreBrand + '</td>';
						html += '<td style="text-align: center" width="200">' + result.tyreType + '</td>';
						html += '<td style="text-align: center" width="200">' + result.barCode + '</td>';
						html += '<td style="text-align: center" width="200">' + result.figure + '</td>';
						html += '</tr>';
					});
					tbody.append(html);
					skuList.createPage(totalPage, GLOBAL.INIT_SIZE, totalCount);
				}
			});
		},

		doExpload: function() {

			$.ajax({
				type: "get",
				url: "baseInfo/uploadFileSku.html",
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
									uploadFileSku.service.doSave(function(rs) {

										if(rs.result == 1) {

											// 重新查询
											skuList.service.doQuery();

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
		skuList.event();
		skuList.service.doQuery(GLOBAL.INIT_PAGE, GLOBAL.INIT_SIZE);
	},

	createPage: function(page, size, total) {
		$(".pagination").jBootstrapPage({
			pageSize: size,
			total: total,
			maxPageButton: GLOBAL.INIT_PAGE_MAX_BTN,
			onPageClicked: function(obj, pageIndex) {
				debugger;
				skuList.service.doSearch(pageIndex + 1, GLOBAL.INIT_SIZE);
			}
		});
	}
}
skuList.init();