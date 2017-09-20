<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8"%> 
<%@ page pageEncoding="UTF-8" %>
<html lang="zh-cn">
<html lang="en">

	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<link rel="stylesheet" href="../css/mui.min.css?v=1.0.0">
		<link rel="stylesheet" href="../css/home.css?v=1.0.0">
		<link rel="stylesheet" type="text/css" href="../css/icons-extra.css" />
		<link rel="stylesheet" type="text/css" href="../css/app.css?v=1.0.0" />
		<title>轮胎</title>
		<link href="favicon.ico" type="image/x-icon" rel="icon">
		<link href="iTunesArtwork@2x.png" sizes="114x114" rel="apple-touch-icon-precomposed">
		<style>
			html,
			body {
				background-color: #efeff4;
			}
			
			.mui-bar .mui-pull-left .mui-icon {
				padding-right: 5px;
				font-size: 28px;
			}
			
			.mui-bar .mui-btn {
				font-weight: normal;
				font-size: 17px;
			}
			
			.mui-bar .mui-btn-link {
				top: 1px;
			}
			
			.mui-content img {
				width: 100%;
			}
			
			.hm-description {
				margin: 15px;
			}
			
			.hm-description>li {
				font-size: 14px;
				color: #8f8f94;
			}
			
			.mui-checkbox input[type=checkbox],
			.mui-radio input[type=radio] {
				top: 10px;
			}
		</style>
	</head>

	<body>

		<header class="mui-bar mui-bar-nav aui-header b-line">
			<a class="mui-action-back mui-icon mui-icon-arrowleft mui-pull-left"></a>
			<h1 class="mui-title">地图导航</h1>
		</header>
		<div class="mui-content">
			<div style="width: 400px;height: 400px;" id="map">

			</div>
		</div>
		<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=HR3RRyTsXYdQ3QzzG5yUkg0q"></script>
		<script src="../js/mui.min.js"></script>
		<script src="../js/core/jquery-2.1.1.min.js"></script>
		<script src="map.js?v=1.0.0"></script>
	</body>

</html>