(function($) {
	$.init();
})(mui);

var index = 2;
var tyreArray = new Array();
addMotor = {

	// 事件注册
	event: function() {

		$("#searchBtn").on("click", addMotor.service.doQuery);

		$("#addBtn").on("click", addMotor.service.doAdd);

		$("#saveBtn").on("click", addMotor.service.doSave);

		$("#vehicleType").on("change", addMotor.service.chageTheSelect);

//		$("#tireId").on("blur", addMotor.service.checkNum);

		$(".tireTd").on("blur", ".tireBlur",addMotor.service.checkNum);

	},

	// 表单验证
	validate: function(tireId) {

	},

	service: {

		checkNum: function(tireId) {
			if( $(this).val() == ""){
				return;
			}
			var token = localStorage.getItem("Authorization");
			var _this = $(this);
			mui.ajax(GLOBAL.SERVER_URL + "api/vehicle/vehicles/tire", {
				data: {
					tireId: $(this).val()
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
							alert("登录超时");
							window.location.href = "login.html";
						} else {
							alert(data.msg);
							return
						}
						return;
					}
					if(data.data.exists == true){
						alert("您输入的轮胎编号已存在");
						_this.val("");
						
					}
					console.log(data);
					debugger;
				}
			});
		},

		chageTheSelect: function() {
			debugger;
			if($("#vehicleType option:selected").val() == 1) {
				$("#firstPanel").show();
				$("#secondPanel").hide();
			} else {
				$("#firstPanel").hide();
				$("#secondPanel").show();
			}

		},

		doQuery: function() {

		},

		addtr: function() {
			var table = $("#tBody");
			var html = "<tr>";
			html += "<td><input style='padding:0px' id='tirePosition' value='" + index + "'></input></td>";
			html += "<td><select class='form-control select' id='brand'>";
			html += "<option value='0'>请选择</option>";
			html += "<option value='1'>普利司通</option>";
			html += "<option value='2'>米其林</option>";
			html += "<option value='3'>固特异</option>";
			html += "<option value='4'>倍耐力</option>";
			html += "<option value='5'>三角集团</option>";
			html += "<option value='6'>住友橡胶</option>";
			html += "<option value='7'>横滨橡胶</option>";
			html += "<option value='8'>韩泰轮胎</option>";
			html += "</select><input style='display:none;'></input></td>";
			html += "<td><input id='tireId' class='tireBlur'></input></td>";

			table.append(html);
			index++;
		},

		doSave: function(callBackFunc) {
			var map = {};
			var tyreArray = new Array();
			$("#tBody tr td input").each(function(index, result) {
				debugger
				if(index % 3 == 0) {

					map["tirePosition"] = $(this).val();
				}
				$("#tBody tr td select").each(function(index_select, result) {
					debugger
					if(index_select == index / 3) {
						map["tireBrand"] = $(this).find("option:selected").val();
					}

				});
				if(index % 3 == 2) {
					map["tireId"] = $(this).val();
					tyreArray.push(map);
					map = {};
				}

			});

			console.log(tyreArray);
			var plate = $("#plate").val();
			var customerName = $("#customerId option:selected").text();
			var customerNameCode = $("#customerId option:selected").val();
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

					if(data.result != 1) {
						mui.toast(data.msg);
						if(data.code == "1001") {
							alert("登录超时");
							window.location.href = "login.html";
						} else {
							alert(data.msg);
							return
						}

						return;
					}
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