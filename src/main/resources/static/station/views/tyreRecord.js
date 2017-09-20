(function($) {
	$.init();
})(mui);

var tyreArray = new Array();
tyreRecord = {

	// 事件注册
	event: function() {

		$("#searchBtn").on("click", tyreRecord.service.doQuery);

		$("#addBtn").on("click", tyreRecord.service.doAdd);

		$("#saveBtn").on("click", tyreRecord.service.doSave);
		
	},

	// 表单验证
	validate: function() {

	},

	service: {

	doQuery: function() {
			$("#tyreRecordInfo #tbody").empty();
			var token = localStorage.getItem("Authorization-station");
			mui.ajax(GLOBAL.SERVER_URL + "api/input/inspection/inspections", {
				data: {
					page: 1,
					pageSize: 100,
					plate: "辽B 88888"
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
						alert(data.msg);
						if(data.code == "1001") {
							window.location.href = "login.html";
						}
						return;
					}
					var html = "";
					var tbody = $("#tyreRecordInfo #tbody");

				
					var html = "";
					$.each(data.data, function(index, result) {
						html += '<table class="table table-bordered table-striped table-action">';
						html += '<tr>';
						html += '<td width="200">里程表读书</td>';
						html += '<td class="text-center"><strong id="milometer">xxxxx</strong></td>';
						html += '<td>服务里程</td>';
						html += '<td class="text-center"><strong id="serviceMile">xxxxx</strong>></td>';
						html += '<td><strong>检测人</strong></td>';
						html += '<td class="text-center"><strong id="operatorName">xxxxx</strong></td>';
						html += '<td><strong id="createTime">日期</strong></td>';
						html += '<td class="text-center"><strong>xxxxx</strong></td>';
						html += '</tr>';
						html += '</table>';
						html += '<div class="" >';
						html += '<table class="table table-bordered table-striped table-actions " style="text-align: center">';
						html += '<thead>';
						html += '<tr>';
						html += '<th style="text-align: center;">轮位</th>';
						html += '<th style="text-align: center;">轮胎条码</th>';
						html += '<th style="text-align: center;">品牌</th>';	
						html += '<th style="text-align: center;">花纹</th>';			
						html += '<th style="text-align: center;">胎压</th>';			
						html += '<th style="text-align: center;">磨损</th>';			
						html += '<th style="text-align: center;">刹车</th>';	
						html += '</tr>';	
						html += '</thead>';		
						html += '<tbody id="tBody">';	
						
						$.each(result.tires, function(index, result) {
							html += '<tr>';
							html += '<td><input id="tirePosition"></input></td>';
							html += '<td><input id="tireId"></input></td>';
							html += '<td><input id="tireBrand"></input></td>';
							html += '<td><input id="stripe"></input></td>';
							html += '<td><input id="pressure"></input></td>';
							html += '<td><input id="depth"></input></td>';
							html += '<td><input id="brake"></input></td>';
							html += '</tr>';
						});
						html += '</tbody>';
						html += '</table>';
						html += '</div>';
					});
					tbody.append(html);
				}
			});
		},
		addtr: function() {
			var table = $("#tBody");
			var html = "<tr>";
			html += "<td><input style='padding:0px' id='tirePosition'></input></td>";
			html += "<td><input id='tireId'></input ></td>";
			html += "<td><input id='tireBrand'></input></td>";
			html += "<td><input id='stripe'></input></td>";
			html += "<td><input id='pressure'></input></td>";
			html += "<td><input id='depth'></input></td>";
			html += "<td><input id='brake'></input></td>";
			table.append(html);
		},

		doSave: function() {
			var map = {};
			var tyreArray = new Array();
			$("#tBody tr td input").each(function(index, result) {
				if(index % 7 == 0) {

					map["tirePosition"] = $(this).val();
				}
				if(index % 7 == 1) {
					map["tireId"] = $(this).val();
				}
				if(index % 7 == 2) {
					map["tireBrand"] = $(this).val();
				}
				if(index % 7 == 3) {
					map["stripe"] = $(this).val();
				}
				if(index % 7 == 4) {
					map["pressure"] = $(this).val();
				}
				if(index % 7 == 5) {
					map["depth"] = $(this).val();
				}
				if(index % 7 == 6) {
					map["brake"] = $(this).val();
					tyreArray.push(map);
					map = {};
				}
			});

			console.log(tyreArray);

			var data = {
				plate: "辽B 12312",
				milometer: $("#milometer").val(),
				serviceMile: $("#serviceMile").val(),
				details: tyreArray
			};

			var token = localStorage.getItem("Authorization-station");
			mui.ajax(GLOBAL.SERVER_URL + "api/input/inspection/add", {
				data: data,
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
						return;
					}
					alert("添加成功");
					if(data.result == 1) {
						window.location.href = "tyreHistory.html"
					}
				}
			});
		},

	},

	dao: {},
	init: function() {

		tyreRecord.event();
		
		tyreRecord.service.doQuery();
	}
}
tyreRecord.init();