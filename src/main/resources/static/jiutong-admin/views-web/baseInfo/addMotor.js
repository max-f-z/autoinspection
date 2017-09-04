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
			var customerName = $("#customerName").val();
			var vehicleType = $("#vehicleType").val();
			var data = {
				plate: plate,
				customerName: customerName,
				vehicleType: vehicleType,
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

	},

	dao: {},
	init: function() {

		addMotor.event();
	}
}
addMotor.init();