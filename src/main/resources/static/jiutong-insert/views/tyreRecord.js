(function($) {
	$.init();
})(mui);

tyreRecord = {

	// 事件注册
	event: function() {

		$("#searchBtn").on("click", tyreRecord.service.doQuery);

		$("#addBtn").on("click", tyreRecord.service.doAdd);

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
			html += "<td><input style='padding:0px'></input></td>";
			html += "<td><input></input></td>";
			html += "<td><input></input></td>";
			html += "<td><input></input></td>";
			html += "<td><input></input></td>";
			html += "<td><input></input></td>";
			table.append(html);
		}

	},

	dao: {},
	init: function() {

		tyreRecord.event();
	}
}
tyreRecord.init();