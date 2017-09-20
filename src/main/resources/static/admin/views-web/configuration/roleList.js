mui.init({
	swipeBack: true, //开启左滑关闭

});

(function($) {
	$.init();
})(mui);

roleList = {

	// 事件注册
	event: function() {
		// 取消按钮
		$("#roleList #addBtn").on("click", roleList.service.doAdd);

		$("#roleList #searchBtn").on("click", roleList.service.doSearch);
	},

	// 表单验证
	validate: function() {

	},

	service: {

		doSearch: function() {

			var search = $("#roleList #search").val();

			$("#tbody").empty();
			var token = localStorage.getItem("Authorization");
			mui.ajax(GLOBAL.SERVER_URL + "api/users/admin/search", {
				data: {
					userName: search
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
					debugger
					console.log(data.data);
					var html = "";
					var tbody = $("#roleList #tbody");
					$.each(data.data, function(index, result) {
						var no = index + 1;
						html += '<tr>';
						html += '<td>'+no+'</td>';
						html += '<td>'+result.name+'</td>';
						html += '<td>admin</td>';
						html += '<td>'+result.station+'</td>';
						html += '<td>admin</td>';
						html += '<td>'+result.updateTime.substring(0,19)+'</td>';
						html += '<td>';
						html += '<button type="button" class="btn btn-info" onclick="roleList.service.doEdit('+result.id+')" style="margin-right:5px">修改</button>';
						html += '<button type="button" class="btn btn-info" onclick="roleList.service.doDelete('+result.id+')"  style="margin-left:5px">删除</button>';
						html += '</td>';
						html += '</tr>';
					});
					tbody.append(html);
				}
			});
		},

		doQuery: function() {
			debugger;
			$("#tbody").empty();
			var token = localStorage.getItem("Authorization");
			mui.ajax(GLOBAL.SERVER_URL + "api/users/admin", {
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
					var tbody = $("#roleList #tbody");
					
					$.each(data.data, function(index, result) {
						var no = index + 1;
						html += '<tr>';
						html += '<td>'+no+'</td>';
						html += '<td>'+result.name+'</td>';
						html += '<td>admin</td>';
						html += '<td>'+result.updateTime.substring(0,19)+'</td>';
						html += '<td>';
						html += '<button type="button" class="btn btn-info" onclick="roleList.service.doEdit('+result.id+')" style="margin-right:5px">修改</button>';
						html += '<button type="button" class="btn btn-info" onclick="roleList.service.doDelete('+result.id+')"  style="margin-left:5px">删除</button>';
						html += '</td>';
						html += '</tr>';
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
				mui.ajax(GLOBAL.SERVER_URL + "api/users/delete", {
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
						roleList.service.doQuery();
						swal("删除！", "删除成功", "success");
					}

				});

			});
		},
	
		doAdd: function(callBackFunc) {
			$.ajax({
				type: "get",
				url: "configuration/addRole.html",
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
									addRole.service.doSave(function(rs) {
										if(rs.result == 1) {
											// 重新查询
											//roleList.service.doQuery();
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
			localStorage.setItem("roleId", id);
			$.ajax({
				type: "get",
				url: "configuration/editRole.html",
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
									editRole.service.doSave(function(rs) {

										if(rs.result == 1) {

											// 重新查询
											roleList.service.doQuery();
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
		roleList.event();
		roleList.service.doQuery();
		//storeList.createPage();
	},
}
roleList.init();