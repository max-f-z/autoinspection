(function($) {
	$.init();
})(mui);

var inspectionId = 0;
var tyreArray = new Array();
repairInfo = {

	// 事件注册
	event: function() {

		$("#searchBtn").on("click", editRepair.service.doQuery);

	},

	// 表单验证
	validate: function() {

	},

	service: {

		doQuery: function() {
			var token = localStorage.getItem("Authorization-station");
			mui.ajax(GLOBAL.SERVER_URL + "api/input/maintenance/info", {
				data: {
					inspectionId: inspectionId
				},
				headers: {
					'Content-Type': 'application/json',
					'Authorization': token
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
					console.log(data);
					debugger;
					$("#plate").text(data.data.plate);
					$("#driverPhone").text(data.data.driverPhone);
					var list = $("#tableList");
					var html = "";
					html += '<table class="table table-bordered table-striped table-actions " style="text-align: center">';
					html += '<thead>';
					html += '<tr>';
					html += '<th style="text-align: center;">轮位</th>';
					html += '<th style="text-align: center;">服务类型</th>';
					html += '<th style="text-align: center;">描述</th>';
					html += '<th style="text-align: center;">数量</th>';
					html += '<th style="text-align: center;">开始时间</th>';
					html += '<th style="text-align: center;">结束时间</th>';
					html += '</tr>';
					html += '</thead>';
					html += '<tbody id="tBody">';

					$.each(data.data.details, function(index, result) {
						html += '<tr>';
						html += '<td>';
						html += '<span id="tirePosition">' + result.tirePosition + '</span>';
						html += '</td>';
						html += '<td>';
						html += '<span id="serviceName">' + result.serviceName + '</span>';
						html += '</td>';
						html += '<td>';
						html += '<span id="serviceDesc">' + result.serviceDesc + '</span>';
						html += '</td>';
						html += '<td>';
						html += '<span id="num">' + result.num + '</span>';
						html += '</td>';
						html += '<td>';
						html += '<span id="startTime">' + result.startTime + '</span>';
						html += '</td>';
						html += '<td>';
						html += '<span id="endTime">' + result.endTime + '</span>';
						html += '</td>';
					});

					html += "</tbody>";
					html += "</table>";
					html += "</div>";

					list.append(html);
				}

			});
		},

	},

	dao: {},
	init: function() {

		inspectionId = decodeURIComponent(URL.getRequest().inspectionId);
		
		var type = decodeURIComponent(URL.getRequest().type);
		
		if(type == 1){
			$("#first").show();
			$("#second").hide();
		}else{
			$("#second").show(); 
			$("#first").hide();
		}

		repairInfo.service.doQuery();

	}
}
repairInfo.init();