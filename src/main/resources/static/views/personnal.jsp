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
		<link rel="stylesheet" href="../css/base.css">
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
		</style>
	</head>

	<body>

		<header class="mui-bar mui-bar-nav aui-header b-line">
			<a class="mui-action-back mui-icon mui-icon-arrowleft mui-pull-left"></a>
			<h1 class="mui-title">个人信息</h1>
			<!--<a class="mui-icon mui-icon-paperplane mui-pull-right"></a>-->
		</header>
		<div class="mui-content">
			<div class="devider b-line"></div>
			<form class="mui-input-group">
				<div class="mui-input-row b-line">
					<label>姓名：</label>
					<input type="text" placeholder="请输入您的用户名" value="小强" disabled="disabled" id="name">
				</div>
				<div class="mui-input-row b-line">
					<label>手机号码：</label>
					<input type="text" class="mui-input-clear" placeholder="请输入您的手机号码" data-input-clear="5" id="phone" disabled="disabled">
				</div>
				<div class="mui-input-row b-line" id="newCode" hidden="hidden">
					<label>验证码<span class="must-input"></span>:</label>
					<input type="text" class="mui-input-clear " placeholder="请输入验证码" data-input-clear="5" id="code"   style="width: 35%; float: left;">
					<a class="mui-btn mui-btn-primary mui-btn-block mui-pull-left" onclick="personnal.service.senmobile(60);" id="codeBtn" disabled="true" style="font-size: 15px; margin-top: 5px;width:90px;margin-right:10px;height: 40px;text-align: center;">验证码</a>
				</div>

				<div class="mui-button-row" style="padding-top:20px; padding-bottom:10px; height:auto">
					<div id="saveBtn" hidden="hidden">
						<div type="button" class="mui-btn mui-btn-primary circle-btn" style="width:90%; padding:10px 0">保存</div>
					</div>
					<div id="editBtn">
						<div type="button" class="mui-btn mui-btn-primary circle-btn" style="width:90%; padding:10px 0">编辑</div>
					</div>
				</div>
			</form>

		</div>
		<script src="../js/mui.min.js"></script>

		<script src="../js/core/jquery-2.1.1.min.js"></script>
		<script src="../js/core/global.js"></script>
		<script src="personnal.js?v=1.0.0"></script>
	</body>

</html>