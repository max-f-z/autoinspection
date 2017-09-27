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
		<link rel="stylesheet" href="../css/mui.min.css?v=1.0.0">
		<link rel="stylesheet" href="../css/home.css?v=1.0.0">
		<link rel="stylesheet" type="text/css" href="../css/icons-extra.css" />
		<link rel="stylesheet" type="text/css" href="../css/app.css" />
		<title>玖通轮胎管家-全国轮胎租赁领军企业</title>
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
			
			::-webkit-input-placeholder {
				text-align: right;
			}
			
			:-moz-placeholder {
				text-align: right;
			}
			
			::-moz-placeholder {
				text-align: right;
			}
			
			:-ms-input-placeholder {
				text-align: right;
			}
			
			.mui-input-row label {
				font-family: 'Helvetica Neue', Helvetica, sans-serif;
				line-height: 1.1;
				float: left;
				width: 35%;
				padding: 10px 0px;
			}
			
			.mui-input-row label~input,
			.mui-input-row label~select,
			.mui-input-row label~textarea {}
		</style>
	</head>

	<body>

		<header class="mui-bar mui-bar-nav aui-header b-line">
			<h1 class="mui-title">服务订单</h1>
		</header>
		<div class="mui-content" id="serviceOrderList">
			<div class="mui-table-view-cell mui-card">
				<div class="b-line">
					<p><strong>服务内容</strong><span class="mui-pull-right" id="serviceName"></span></p>
					<p><strong>简介</strong><span class="mui-pull-right" id="describle"></span></p>
					<p><strong>检测站</strong><span class="mui-pull-right" id="station"></span></p>
					<p><strong>检测站地址</strong><span class="mui-pull-right" id="place"></span></p>
					<p><strong>预约检测时间</strong><span class="mui-pull-right" id="orderTime"></span></p>
					<!--<p><strong>车牌号</strong><span class="mui-pull-right" id="plateNo" ></span></p>-->
					<div class="mui-input-row ">
						<label style="color: red; font-size: 18px;">车牌号</label>
						<input type="text" class="mui-input-clear mui-pull-right" placeholder="请输入车牌号" id="plateNo" style=" text-align:right;padding: 0px;0px">
					</div>
				</div>
				<div id="btn" style="color: red;text-align: center; width: 100%; padding-right: 0px;">
					<a onclick='serviceOrderList.service.doRegister()'>预约</a>
				</div>
			</div>
		</div>
		<script src="../js/mui.min.js"></script>
		<script src="../js/core/jquery-2.1.1.min.js"></script>
		<script src="../js/core/global.js"></script>
		<script src="serviceOrderList.js?v=1.0.0"></script>
	</body>

</html>