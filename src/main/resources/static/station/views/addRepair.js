(function($) {
	$.init();
})(mui);

var tyreArray = new Array();
var plate = "";
var inspectionId = 0;
var index = 2;
var id = 0;

var testHtml = "";
addRepair = {

	// 事件注册
	event: function() {

		$("#searchBtn").on("click", addRepair.service.doQuery);

		$("#addBtn").on("click", addRepair.service.doAdd);

		$("#saveBtn").on("click", addRepair.service.doSave);

	},

	// 表单验证
	validate: function() {

	},

	service: {

		doQuery: function() {

		},
		
		addtr: function() {
			var table = $("#tBody");
			var html = "<tr>";
			html += "<td><input style='padding:0px' id='tirePosition' value='" + index + "'></input></td>";
			html += "<td><select class='form-control select' id='serviceId'><option value='0'>请选择</option>";
			var inh = addRepair.service.getServiceList();
			html += testHtml;
			debugger;
			html += "</select><input style='display:none'></input></td>";
			html += "<td><input id='serviceDesc'></input></td>";
			html += "<td><input id='num' type='number'></input></td>";
			html += "<td><input id='startTime' class ='datepicker' ></input></td>";
			html += "<td><input id='endTime'  class ='datepicker'></input></td>";
			table.append(html);
			index++;
			$(".datepicker").datepicker({
					format: "yyyy-mm-dd",
					timeFormat: 'hh:mm:ss',
				}

			);
		},

		doSave: function() {

			if($("#driverPhone").val() == "") {
				alert("请您输入手机号码");
				return;
			}
			var map = {};
			var tyreArray = new Array();
			$("#tBody tr td input").each(function(index, result) {
				if(index % 6 == 0) {

					map["tirePosition"] = $(this).val();
				}
				if(index % 6 == 1) {
					//map["serviceName"] = $(this).val();
				}

				$("#tBody tr td select").each(function(index_select, result) {
					if(index_select == index / 6) {
						map["serviceId"] = $(this).find("option:selected").val();
						map["serviceName"] = $(this).find("option:selected").text();
						debugger;
					}
				});

				if(index % 6 == 2) {
					map["serviceDesc"] = $(this).val();
				}
				if(index % 6 == 3) {
					map["num"] = $(this).val();
				}
				if(index % 6 == 4) {
					map["startTime"] = $(this).val();
				}
				if(index % 6 == 5) {
					map["endTime"] = $(this).val();
					tyreArray.push(map);
					map = {};
				}
			});

			console.log(tyreArray);
			debugger
			debugger
			var data = {
				inspectionId: inspectionId,
				plate: plate,
				driverPhone: $("#driverPhone").val(),
				details: tyreArray
			};

			var token = localStorage.getItem("Authorization-station");
			mui.ajax(GLOBAL.SERVER_URL + "api/input/maintenance/add", {
				data: data,
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
					alert("添加成功");
					if(data.result == 1) {
						window.location.href = "tyreHistory.html?plate=" + plate + "&id=" + id;
					}
				}
			});
		},

	
		getServiceList: function() {
			debugger
			var token = localStorage.getItem("Authorization-station");
			mui.ajax(GLOBAL.SERVER_URL + "/api/input/services", {
				headers: {
					'Content-Type': 'application/json',
					'Authorization': token
				},
				dataType: 'json', //服务器返回json格式数据
				type: 'get', //HTTP请求类型
				async: false,
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
					$.each(data.data, function(index, result) {
						html += "<option value='"+result.id+"'>" + result.name + "-" + result.description + "</option>";
						debugger
					});
					testHtml = html;
					return html;
				}
			});
		},
		
		getServiceListInit: function() {
			debugger
			var token = localStorage.getItem("Authorization-station");
			mui.ajax(GLOBAL.SERVER_URL + "/api/input/services", {
				headers: {
					'Content-Type': 'application/json',
					'Authorization': token
				},
				dataType: 'json', //服务器返回json格式数据
				type: 'get', //HTTP请求类型
				async: false,
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
					testHtml = "";
					$.each(data.data, function(index, result) {
						html += "<option value='"+result.id+"'>" + result.name + "-" +result.description + "</option>";
					});
					debugger
					$("#serviceIdinit").append(html);
					return html;
				}
			});
		}


	},

	dao: {},
	init: function() {

		plate = decodeURIComponent(URL.getRequest().plate);
		id = decodeURIComponent(URL.getRequest().id);
		inspectionId = decodeURIComponent(URL.getRequest().inspectionId);
		addRepair.service.getServiceListInit();
		
		
		$("#plate").text(plate);
		addRepair.event();
	}
}
addRepair.init();