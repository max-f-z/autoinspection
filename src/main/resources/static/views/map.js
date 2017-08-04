mui.init({
	swipeBack: true, //开启左滑关闭

});

(function($) {
	$.init();
})(mui);

map = {

	// 事件注册
	event: function() {},

	service: {
		doMapInit: function() {
			var map = new AMap.Map('map', {
				zoom: 10,
				center: [116.39, 39.9]
			});

			var drive = new AMap.Driving();

			var ptStart = new AMap.LngLat(116.379018, 39.865026);

			var ptEnd = new AMap.LngLat(116.321139, 39.896028);

			drive.search(ptStart, ptEnd, driveRouteResult);
		}
	},

	dao: {},
	init: function() {

		map.event();
		map.service.doMapInit();
	}
}
map.init();