(function($) {
	$.init();
})(mui);

var index = 2;
var tyreArray = new Array();

var plate = "";

var id = 0;

tyreHistory = {

	// 事件注册
	event: function() {

		$("#saveBtn").on("click", tyreHistory.service.doSave);

		$("#tyreType").on("change", tyreHistory.service.changeSelect);

		$("#addBtn").on("click", tyreHistory.service.addRecord);

	},

	// 表单验证
	validate: function() {

	},

	service: {

		addRecord: function() {

			debugger
			if($("#typeName").text() == "牵引车") {
				window.location.href = "addRecordTeam.html?plate=" + plate + "&type=" + 1 + "&id=" + id;
			} else {
				window.location.href = "addRecordTeam.html?plate=" + plate + "&type=" + 2 + "&id=" + id;
			}

		},

		changeSelect: function() {

			var selectOption = $('#tyreType option:selected');
			$("#code").text(selectOption.attr("code"));
			$("#tireNum").text(selectOption.attr("tireNum"));
			$("#backUp").text(selectOption.attr("backUp"));

		},

		doQuery: function() {

		},

		getHistoryInfo: function(id) {
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
					tyreHistory.service.getHistoryList();
				}
			});
		},

		getHistoryList: function() {
			var token = localStorage.getItem("Authorization-station");
			mui.ajax(GLOBAL.SERVER_URL + "api/input/inspection/inspections", {
				data: {
					"page": 1,
					"pageSize": 100,
					plate: plate
				},
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
					var list = $("#historyList");
					var html = "";
					$.each(data.data, function(index, result) {
						html += '<div class="panel panel-default">';
						html += '<div class="panel-body" onclick="tyreHistory.service.showHisPrepair(' + result.id + ')">';
						html += '<div class="col-xs-12">';
						console.log(result);
						debugger
						if(result.maintained == '0') {
							html += '<button onclick="tyreHistory.service.addPrepair(' + result.id + ')" type="button" class="btn btn-info pull-right" style="margin-bottom: 10px;" >新增维修记录</button>';
						}
						html += '</div>';

						html += '<table class="table table-bordered table-striped table-action">';
						html += '<tr>';
						html += '<td width="200">里程表读书</td>';
						html += '<td class="text-center"><span id="milometer">' + result.milometer + '</span>';
						html += '</td>';
						html += '<td width="200">服务里程</td>';
						html += '<td class="text-center"><span id="serviceMile">' + result.serviceMile + '</span>';
						html += '</td>';
						html += '<td width="200">检测人</td>';
						html += '<td class="text-center"><span id="operatorName">' + result.operatorName + '</span>';
						html += '</td>';
						html += '<td width="200">日期</td>';
						html += '<td class="text-center"><span id="createTime">' + result.createTime.substring(0, 19) + '<span>';
						html += '</td>';
						html += '</tr>';
						html += '</table>';
						html += '<table class="table table-bordered table-striped table-actions " style="text-align: center">';
						html += '<thead>';
						html += '<tr>';
						html += '<th style="text-align: center">轮位</th>';
						html += '<th style="text-align: center;">轮胎条码</th>';
						html += '<th style="text-align: center;">品牌</th>';
						html += '<th style="text-align: center;">花纹</th>';
						html += '<th style="text-align: center;">胎压</th>';
						html += '<th style="text-align: center;">磨损</th>';
						html += '<th style="text-align: center;">刹车</th>';
						html += '</tr>';
						html += '</thead>';
						html += '<tbody id="tBody">';

						$.each(result.details, function(index, result) {

							html += '<tr>';
							html += '<td><span id="tirePosition" value="1">' + result.tirePosition + '</span></td>';
							html += '<td><span id="tireId">' + result.tireId + '</span></td>';
							html += '<td style="width: 200;">';

							html += '<span id="tireBrand" >' + result.tireBrand + '</span>';

							html += '</td>';
							if(result.stripe != null) {
								html += '<td><span id="stripe">' + result.stripe + '</span>';
							} else {
								html += '<td><span id="stripe">--</span>';
							}
							html += '</td>';
							if(result.pressure != null) {
								html += '<td><span id="pressure">' + result.pressure + '</span>';
							} else {
								html += '<td><span id="pressure">--</span>';
							}
							html += '</td>';
							if(result.depth != null) {
								html += '<td><span id="depth">' + result.depth + '</span>';
							} else {
								html += '<td><span id="depth">--</span>';
							}
							html += '</td>';
							if(result.brake != null) {
								html += '<td><span id="brake">' + result.brake + '</span>';
							} else {
								html += '<td><span id="brake">--</span>';
							}
							html += '</td>';
							html += '</tr>';
						});
						html += '</tbody>';
						html += '</table>';
						html += '</div>';
						html += '</div>';
						html += '<div style="color:#000000;height:10px;width:100%"></div>'
					});

					list.append(html);
				}
			});
		},

		showHisPrepair: function(inspectionId) {
			if($("#typeName").text() == "牵引车") {
				window.location.href = "repairInfo.html?inspectionId=" + inspectionId + "&type=" + 1 + "&id=" + id;
			} else {
				window.location.href = "repairInfo.html?inspectionId=" + inspectionId + "&type=" + 2 + "&id=" + id;
			}

		},

		addPrepair: function(inspectionId) {
			event.stopPropagation();
			window.location.href = "addRepair.html?plate=" + plate + "&inspectionId=" + inspectionId + "&id=" + id;
		}

	},

	dao: {},
	init: function() {

		plate = decodeURIComponent(URL.getRequest().plate);

		id = decodeURIComponent(URL.getRequest().id);

		debugger

		tyreHistory.event();

		tyreHistory.service.getHistoryInfo(id);
	}
}
tyreHistory.init();

function getTyrePic(first, second) {
	var platePlate = $("#platePlate");
	var html = "";
	debugger;
	for(var i = 0; i < 4; i++) {
		html += '<div class="row" style="style">';
		for(var j = 0; j < first; j++) {
			var no = j * 3 + (i + 1);
			html += '<div class="col-xs-2"><button class="btn btn btn-primary">' + (i + 1) + '</button></div>';
		}
		html += '</div>';
	}
	platePlate.append(html);

};

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