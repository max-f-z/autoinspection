mui.init({
	swipeBack: true, //开启左滑关闭

});

(function($) {
	$.init();
})(mui);

motorList = {

	// 事件注册
	event: function() {

		$("#motorList #addBtn").on("click", motorList.service.doAdd);
		
		$("#motorList #searchBtn").on("click", motorList.service.doSearch);
		

	},

	// 表单验证
	validate: function() {

	},

	service: {
		
		
		doSearch: function() {
			var search = $("#motorList #search").val();
			$("#tbody").empty();
			var token = localStorage.getItem("Authorization");
			mui.ajax(GLOBAL.SERVER_URL + "api/vehicle/vehicles/search", {
				data: {
					page: 1,
					pageSize: 10,
					search:search
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
						html += '<td class="text-right"><strong><span id="tyreNo">' <button type='button' class='btn btn-info' onclick='clientMaiList.service.doDelete(" + result.id + ")'>删除</button> '</span></strong></td>';
						html += '</tr>';
						html += '</table>';
						html += '<div class="panel-body" >';

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
					pageSize: 10
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
						html += '</tr>';
						html += '</table>';
						html += '<div class="panel-body" >';

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

		//新建
		doAdd: function() {
			$.ajax({
				type: "get",
				url: "../views/baseInfo/uploadFile.html",
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

		$("#excelFile").fileinput({
			uploadUrl: GLOBAL.SERVER_URL + "api/vehicle/vehicles/import", //上传的地址
			uploadAsync: true,

			language: "zh", //设置语言
			showCaption: true, //是否显示标题
			showUpload: true, //是否显示上传按钮
			browseClass: "btn btn-primary", //按钮样式 
			allowedFileExtensions: ["xls", "xlsx"], //接收的文件后缀
			maxFileCount: 10, //最大上传文件数限制
			uploadAsync: true,
			previewFileIcon: '<i class="glyphicon glyphicon-file"></i>',
			allowedPreviewTypes: null,
			previewFileIconSettings: {
				'docx': '<i class="glyphicon glyphicon-file"></i>',
				'xlsx': '<i class="glyphicon glyphicon-file"></i>',
				'pptx': '<i class="glyphicon glyphicon-file"></i>',
				'jpg': '<i class="glyphicon glyphicon-picture"></i>',
				'pdf': '<i class="glyphicon glyphicon-file"></i>',
				'zip': '<i class="glyphicon glyphicon-file"></i>',
			},
			uploadExtraData: function() {
				var extraValue = null;
				var radios = document.getElementsByName('excelType');
				for(var i = 0; i < radios.length; i++) {
					if(radios[i].checked) {
						extraValue = radios[i].value;
					}
				}
				return {
					"excelType": extraValue
				};
			}
		});
	}
}
motorList.init();