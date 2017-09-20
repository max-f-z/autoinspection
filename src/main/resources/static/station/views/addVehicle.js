(function($) {
	$.init();
})(mui);

var index = 2;
var tyreArray = new Array();
addVehicle = {

	// 事件注册
	event: function() {

		$("#addBtn").on("click", addVehicle.service.doAdd);

		$("#saveBtn").on("click", addVehicle.service.doSave);

		$("#vehicleType").on("change", addVehicle.service.changeSelect);

	},

	// 表单验证
	validate: function() {

	},

	service: {

		changeSelect: function() {

			debugger
			var selectOption = $('#vehicleType option:selected');
			if(selectOption.val() == 1) {
				$("#first").show();
				$("#second").hide();
			} else {
				$("#first").hide();
				$("#second").show();
			}
			$("#code").text(selectOption.attr("code"));
			$("#tireNum").text(selectOption.attr("tireNum"));
			$("#backUp").text(selectOption.attr("backUp"));

		},

		doQuery: function() {

		},

		addtr: function() {
			var table = $("#tBody");
			var html = "<tr>";
			html += "<td><input style='padding:0px' id='tirePosition' value='" + index + "'></input></td>";
			html += "<td><select class='form-control select' id='tireBrand'>";
			html += "<option value='0'>请选择</option>";
			html += "<option value='1'>普利司通</option>";
			html += "<option value='2'>米其林</option>";
			html += "<option value='3'>固特异</option>";
			html += "<option value='4'>倍耐力</option>";
			html += "<option value='5'>三角集团</option>";
			html += "<option value='6'>住友橡胶</option>";
			html += "<option value='7'>横滨橡胶</option>";
			html += "<option value='8'>韩泰轮胎</option>";
			html += "</select>";
			html += "<input style='display:none'></input></td>";
			html += "<td><select class='form-control select' id='tireType'>";
			html += "<option value='0'>请选择</option>";
			html += "<option value='1'>SH001</option>";
			html += "<option value='2'>SH002</option>";
			html += "<option value='3'>SH003</option>";
			html += "<option value='4'>SH004</option>";
			html += "</select>";
			html += "<input style='display:none'></input></td>";
			html += "<td><input id='tireId'></input ></td>";
			html += "</tr>";
			table.append(html);
			index++;
		},

		getHistoryRecord: function() {

			var data = {
				inspectionId: 3
			}
			var token = localStorage.getItem("Authorization-station");
			mui.ajax(GLOBAL.SERVER_URL + "api/input/maintenance/info", {
				data: data,
				headers: {
					'Content-Type': 'application/json',
					'Authorization': token,
				},
				dataType: 'json', //服务器返回json格式数据
				type: 'POST', //HTTP请求类型
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
				}
			});
		},

		getServiceList: function() {
			var token = localStorage.getItem("Authorization-station");
			mui.ajax(GLOBAL.SERVER_URL + "api/input/services", {
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
					console.log(data);
				}
			});

		},

		getTireType: function() {
			var token = localStorage.getItem("Authorization-station");
			mui.ajax(GLOBAL.SERVER_URL + "api/vehicle/vehicles/types", {
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
						alert(data.msg);
						if(data.code == "1001") {
							window.location.href = "login.html";
						}
						return;
					}
					console.log(data);
				}
			});
		},

		doSave: function() {
			var map = {};
			var tyreArray = new Array();
			$("#tBody tr td input").each(function(index, result) {
				if(index % 4 == 0) {

					map["tirePosition"] = $(this).val();
				}
				$("#tBody tr td select").each(function(index_select, result) {
					if(index_select == index / 3) {
						map["tireBrand"] = $(this).find("option:selected").val();
					}

				});

				if(index % 4 == 2) {
					map["tireType"] = 1;
				}
				
				if(index % 4 == 3) {
					map["tireId"] = $(this).val();
					tyreArray.push(map);
					map = {};
				}

			});

			console.log(tyreArray);
			var plate = $("#plate").val();
			var customerName = $("#customerName").text();
			var customerNameCode = $("#customerId option:selected").val();
			var vehicleType = $("#vehicleType option:selected").text();
			var vehicleTypeCode = $("#vehicleType option:selected").val();
			if(null == plate || "" == plate) {
				alert("请您输入车牌号");
				return false;
			}

			if(null == customerName || "" == $("#customerName").val()) {
				alert("请您输入客户名称");
				return false;
			}

			if(null == vehicleType || 0 == $("#vehicleType").val()) {
				alert("请您输入车辆类型");
				return false;
			}
			var data = {
				plate: plate,
				customerName: customerName,
				vehicleType: vehicleTypeCode,
				tires: tyreArray
			};
			debugger
			var token = localStorage.getItem("Authorization-station");
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
					window.location.href = "tyreList.html";
				}
			});
		},

	},

	dao: {},
	init: function() {

		addVehicle.event();

		addVehicle.service.getTireType();

		addVehicle.service.getHistoryRecord();

		addVehicle.service.getServiceList();
		var date = Format(new Date(), "yyyy-MM-dd HH:mm:ss");
		$("#createTime").text(date);

	}
}
addVehicle.init();

function Format(now, mask) {
	var d = now;
	var zeroize = function(value, length) {
		if(!length) length = 2;
		value = String(value);
		for(var i = 0, zeros = ''; i < (length - value.length); i++) {
			zeros += '0';
		}
		return zeros + value;
	};

	return mask.replace(/"[^"]*"|'[^']*'|\b(?:d{1,4}|m{1,4}|yy(?:yy)?|([hHMstT])\1?|[lLZ])\b/g, function($0) {
		switch($0) {
			case 'd':
				return d.getDate();
			case 'dd':
				return zeroize(d.getDate());
			case 'ddd':
				return ['Sun', 'Mon', 'Tue', 'Wed', 'Thr', 'Fri', 'Sat'][d.getDay()];
			case 'dddd':
				return ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'][d.getDay()];
			case 'M':
				return d.getMonth() + 1;
			case 'MM':
				return zeroize(d.getMonth() + 1);
			case 'MMM':
				return ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'][d.getMonth()];
			case 'MMMM':
				return ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'][d.getMonth()];
			case 'yy':
				return String(d.getFullYear()).substr(2);
			case 'yyyy':
				return d.getFullYear();
			case 'h':
				return d.getHours() % 12 || 12;
			case 'hh':
				return zeroize(d.getHours() % 12 || 12);
			case 'H':
				return d.getHours();
			case 'HH':
				return zeroize(d.getHours());
			case 'm':
				return d.getMinutes();
			case 'mm':
				return zeroize(d.getMinutes());
			case 's':
				return d.getSeconds();
			case 'ss':
				return zeroize(d.getSeconds());
			case 'l':
				return zeroize(d.getMilliseconds(), 3);
			case 'L':
				var m = d.getMilliseconds();
				if(m > 99) m = Math.round(m / 10);
				return zeroize(m);
			case 'tt':
				return d.getHours() < 12 ? 'am' : 'pm';
			case 'TT':
				return d.getHours() < 12 ? 'AM' : 'PM';
			case 'Z':
				return d.toUTCString().match(/[A-Z]+$/);
				// Return quoted strings with the surrounding quotes removed
			default:
				return $0.substr(1, $0.length - 2);
		}
	});
};