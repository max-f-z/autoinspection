(function($) {
	$.init();
})(mui);

var tyreArray = new Array();
addMotor = {

	// 事件注册
	event: function() {

		$("#searchBtn").on("click", addMotor.service.doQuery);

		$("#addBtn").on("click", addMotor.service.doAdd);

		$("#saveBtn").on("click", addMotor.service.doSave);

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
			html += "<td><input style='padding:0px' id='tirePosition'></input></td>";
			html += "<td><input id='tireBrand'></input ></td>";
			html += "<td><input id='tireId'></input></td>";

			table.append(html);
		},

		doSave: function(callBackFunc) {
			var map = {};
			var tyreArray = new Array();
			$("#tBody tr td input").each(function(index, result) {
				debugger
				if(index % 3 == 0) {

					map["tirePosition"] = $(this).val();
				}
				if(index % 3 == 1) {
					map["tireBrand"] = $(this).val();
				}
				if(index % 3 == 2) {
					map["tireId"] = $(this).val();
					tyreArray.push(map);
					map = {};
				}

			});

			console.log(tyreArray);
			var plate = $("#plate").val();
			var customerName = $("#customerName option:selected").text();
			var vehicleType = $("#vehicleType option:selected").text();
			var vehicleTypeCode = $("#vehicleType option:selected").val();
			if(null == plate || "" == plate) {
				alert("请您输入车牌号");
				return false;
			}

			if(null == customerName || 0 == $("#customerName").val()) {
				alert("请您输入客户名称");
				return false;
			}
			
			if(null == vehicleType || 0 == $("#vehicleType").val()) {
				alert("请您输入车牌号");
				return false;
			}
			var data = {
				plate: plate,
				customerName: customerName,
				vehicleType: vehicleTypeCode,
				tires: tyreArray
			};
			debugger
			var token = localStorage.getItem("Authorization");
			mui.ajax(GLOBAL.SERVER_URL + "api/vehicle/vehicles/add", {
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
					console.log(data);
					debugger;
					callBackFunc(data);
				}
			});
		},

		getCustomerList: function() {
			var token = localStorage.getItem("Authorization");
			mui.ajax(GLOBAL.SERVER_URL + "api/customer/customers", {
				data: {},
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
					var tbody = $("#addMoter-list #customerId");
					$.each(data.data, function(index, result) {
						html += "<option value='" + result.id + "'>" + result.name + "</option>"
					});
					tbody.append(html);
				}
			});
		},

	},

	dao: {},
	init: function() {

		addMotor.event();
		
		addMotor.service.getCustomerList();
	}
}
addMotor.init();