<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8"%> 
<%@ page pageEncoding="UTF-8" %>
<html lang="zh-cn">
	<script>
		if(localStorage.getItem("Authorization") == null) {
			window.location.href = "login.html";
		}
	</script>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<link rel="stylesheet" href="../css/mui.min.css">
		<link rel="stylesheet" href="../css/home.css">
		<link rel="stylesheet" type="text/css" href="../css/icons-extra.css" />
		<link rel="stylesheet" type="text/css" href="../css/app.css" />
		<title>玖通轮胎管家-全国轮胎租赁领军企业</title>
		<link href="favicon.ico" type="image/x-icon" rel="icon">
		<link href="iTunesArtwork@2x.png" sizes="114x114" rel="apple-touch-icon-precomposed">
	</head>

	<body>

		<header class="mui-bar mui-bar-nav aui-header b-line">
			<a class="mui-action-back mui-icon mui-icon-arrowleft mui-pull-left"></a>
			<h1 class="mui-title">服务内容</h1>
		</header>
		<div class="mui-content">

			<div id="container">

			</div>
			<div class="divider b-line">

			</div>

			<ul class="mui-table-view" id="time">

			</ul>

		</div>

		<script src="../js/mui.min.js"></script>
		<script src="../js/core/jquery-2.1.1.min.js"></script>
		<script src="../js/core/global.js"></script>
		<script src="orederService.js?v=1.0.0"></script>
		<link href="../css/MCalendar.css" rel="stylesheet" />
		<script src="../js/MCalendar.js?v=1.0.0"></script>
		<script>
			var test = function() {
				var nextDay = mui.DateUtil.addDate(mui.DateUtil.getToday(), 1);
				var MC = mui("#container").MCalendar({
					date: nextDay
				});

			};

			mui.ready(test)
		</script>

	</body>

</html>