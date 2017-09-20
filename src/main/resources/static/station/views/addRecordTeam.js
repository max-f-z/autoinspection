(function($) {
	$.init();
})(mui);

var index = 2;
var plate = "";
var tyreArray = new Array();

var id = 0;
addRecordTeam = {

	// 事件注册
	event: function() {

		$("#addBtn").on("click", addRecordTeam.service.doAdd);

		$("#saveBtn").on("click", addRecordTeam.service.doSave);

		$("#tyreType").on("change", addRecordTeam.service.changeSelect);

	},

	// 表单验证
	validate: function() {

	},

	service: {

		changeSelect: function() {

			var selectOption = $('#tyreType option:selected');
			$("#code").text(selectOption.attr("code"));
			$("#tireNum").text(selectOption.attr("tireNum"));
			$("#backUp").text(selectOption.attr("backUp"));

		},

		doQuery: function(id) {
			var token = localStorage.getItem("Authorization-station");
			mui.ajax(GLOBAL.SERVER_URL + "api/vehicle/vehicles/" + id, {
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
					debugger
					$("#customerName").text(data.data.customerName);
					$("#plate").text(data.data.plate);
					var tyreNum = 0;
					var firstParm = parseInt(data.data.vehicleModel.substring(0, 1));
					var secondParm = parseInt(data.data.vehicleModel.substring(1, 2));
					if(data.data.vehicleType == "牵引车") {
						$("#first").show();
						$("#second").hide();
						tyreNum = firstParm * secondParm;
						debugger
					} else {
						$("#first").hide();
						$("#second").show();
						tyreNum = firstParm * secondParm * 2;
					}
					debugger;
					$("#typeName").text(data.data.vehicleType);
					$("#typeCode").text(data.data.vehicleModel);
					$("#tireNum").text(tyreNum);
					$("#backUp").text(2);
					$("#tBody").empty();
					var table = $("#tBody");
					debugger;
					var html = "";
					$.each(data.data.tires, function(index, result) {
						html += "<tr>";
						html += "<td><input style='padding:0px' id='tirePosition' value='" + result.tirePosition + "'></input></td>";
						html += "<td><input id='tireId' value='" + result.tireId + "'></input ></td>";
						html += "<td><select class='form-control select' id='tireBrand' >";
						html += "<option value='0' >请选择</option>";
						debugger
						if(result.tireBrand == "普利司通") {
							html += "<option value='1'  selected = 'selected'>普利司通</option>";
							html += "<option value='2'>米其林</option>";
							html += "<option value='3'>固特异</option>";
							html += "<option value='4'>倍耐力</option>";
							html += "<option value='5'>三角集团</option>";
							html += "<option value='6'>住友橡胶</option>";
							html += "<option value='7'>横滨橡胶</option>";
							html += "<option value='8'>韩泰轮胎</option>";

						}
						if(result.tireBrand == "米其林") {
							html += "<option value='1'>普利司通</option>";
							html += "<option value='2' selected = 'selected'>米其林</option>";
							html += "<option value='3'>固特异</option>";
							html += "<option value='4'>倍耐力</option>";
							html += "<option value='5'>三角集团</option>";
							html += "<option value='6'>住友橡胶</option>";
							html += "<option value='7'>横滨橡胶</option>";
							html += "<option value='8'>韩泰轮胎</option>";

						}
						if(result.tireBrand == "固特异") {
							html += "<option value='1'>普利司通</option>";
							html += "<option value='2'>米其林</option>";
							html += "<option value='3' selected = 'selected'>固特异</option>";
							html += "<option value='4'>倍耐力</option>";
							html += "<option value='5'>三角集团</option>";
							html += "<option value='6'>住友橡胶</option>";
							html += "<option value='7'>横滨橡胶</option>";
							html += "<option value='8'>韩泰轮胎</option>";

						}
						if(result.tireBrand == "倍耐力") {
							html += "<option value='1'  >普利司通</option>";
							html += "<option value='2'>米其林</option>";
							html += "<option value='3'>固特异</option>";
							html += "<option value='4' selected = 'selected'>倍耐力</option>";
							html += "<option value='5'>三角集团</option>";
							html += "<option value='6'>住友橡胶</option>";
							html += "<option value='7'>横滨橡胶</option>";
							html += "<option value='8'>韩泰轮胎</option>";

						}
						if(result.tireBrand == "三角集团") {
							html += "<option value='1'>普利司通</option>";
							html += "<option value='2'>米其林</option>";
							html += "<option value='3'>固特异</option>";
							html += "<option value='4'>倍耐力</option>";
							html += "<option value='5'   selected = 'selected'>三角集团</option>";
							html += "<option value='6'>住友橡胶</option>";
							html += "<option value='7'>横滨橡胶</option>";
							html += "<option value='8'>韩泰轮胎</option>";

						}
						if(result.tireBrand == "住友橡胶") {
							html += "<option value='1'  >普利司通</option>";
							html += "<option value='2'>米其林</option>";
							html += "<option value='3'>固特异</option>";
							html += "<option value='4'>倍耐力</option>";
							html += "<option value='5'>三角集团</option>";
							html += "<option value='6' selected = 'selected'>住友橡胶</option>";
							html += "<option value='7'>横滨橡胶</option>";
							html += "<option value='8'>韩泰轮胎</option>";

						}
						if(result.tireBrand == "横滨橡胶") {
							html += "<option value='1'  >普利司通</option>";
							html += "<option value='2'>米其林</option>";
							html += "<option value='3'>固特异</option>";
							html += "<option value='4'>倍耐力</option>";
							html += "<option value='5'>三角集团</option>";
							html += "<option value='6'>住友橡胶</option>";
							html += "<option value='7' selected = 'selected'>横滨橡胶</option>";
							html += "<option value='8'>韩泰轮胎</option>";

						}
						if(result.tireBrand == "韩泰轮胎") {
							html += "<option value='1'  >普利司通</option>";
							html += "<option value='2'>米其林</option>";
							html += "<option value='3'>固特异</option>";
							html += "<option value='4'>倍耐力</option>";
							html += "<option value='5'>三角集团</option>";
							html += "<option value='6'>住友橡胶</option>";
							html += "<option value='7'>横滨橡胶</option>";
							html += "<option value='8' selected = 'selected'>韩泰轮胎</option>";

						}
						html += "</select>";
						html += "<input style='display:none'></input></td>";
						html += "<td><input id='stripe'></input></td>";
						html += "<td><input id='pressure'></input></td>";
						html += "<td><input id='depth'></input></td>";
						html += "<td><input id='brake'></input></td>";
						html += "</tr>";

					});
					table.append(html);

				}
			});

		},

		addtr: function() {
			var table = $("#tBody");
			var html = "<tr>";
			html += "<td><input style='padding:0px' id='tirePosition' value='" + index + "'></input></td>";
			html += "<td><input id='tireId'></input ></td>";
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
			html += "<td><input id='stripe'></input></td>";
			html += "<td><input id='pressure'></input></td>";
			html += "<td><input id='depth'></input></td>";
			html += "<td><input id='brake'></input></td>";
			html += "</tr>";
			table.append(html);
			index++;
		},

		doSave: function() {
			debugger;
			var map = {};
			var tyreArray = new Array();
			$("#tBody tr td input").each(function(index, result) {
				if(index % 7 == 0) {

					map["tirePosition"] = $(this).val();
				}
				if(index % 7 == 1) {
					map["tireId"] = $(this).val();
				}

				$("#tBody tr td select").each(function(index_select, result) {
					debugger
					if(index_select == index / 3) {
						map["tireBrand"] = $(this).find("option:selected").val();
					}

				});

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

			if($("#milometer").val() <= 0) {
				alert("请您输入正确的里程表读数");
				return false;
			}

			if($("#serviceMile").val() <= 0) {
				alert("请您输入正确的服务里程");
				return false;
			}

			var data = {
				plate: plate,
				milometer: $("#milometer").val(),
				serviceMile: $("#serviceMile").val(),
				details: tyreArray
			};

			console.log(data);
			debugger;
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
						alert(data.msg);
						if(data.code == "1001") {
							window.location.href = "login.html";
						}
						return;
					}
					alert("添加成功");
					window.location.href = "tyreHistory.html?plate=" + plate + "&id=" + id;
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
		}

	},

	dao: {},
	init: function() {

		plate = decodeURIComponent(URL.getRequest().plate);

		var type = decodeURIComponent(URL.getRequest().type);

		id = decodeURIComponent(URL.getRequest().id);
		
		if(type == 1) {
			$("#first").show();
			$("#second").hide();
		} else {
			$("#first").hide();
			$("#second").show();
		}
		addRecordTeam.event();
		addRecordTeam.service.doQuery(id);
		var date = Format(new Date(), "yyyy-MM-dd HH:mm:ss");
		$("#createTime").text(date);

	}
}

addRecordTeam.init();

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