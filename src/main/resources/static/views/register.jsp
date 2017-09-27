<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8"%> 
<%@ page pageEncoding="UTF-8" %>
<html lang="zh-cn">
<html>

	<head>
		<meta charset="UTF-8">
		<title></title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link href="../css/mui.min.css" rel="stylesheet" />
		<link href="../css/plugin/bootstrap/bootstrap.min.css" rel="stylesheet">
		<link href="../css/plugin/bootstrap/register.css" rel="stylesheet">
		<link href="../css/base.css" rel="stylesheet">
		<title>玖通轮胎管家-全国轮胎租赁领军企业</title>
	</head>

	<body>
		<form>
			<div class="registerBg">
				<div class="container top-50">
					
					<div class="input-group top-50 login_btn" style="margin-top: 150px;">
						<span class="input-group-addon addon-border" style="background: rgba(255,255,255,0.30);border: 0 solid #FFFFFF"><img alt="登录" src="../images/login/account.png"></span>
						<input type="text" class="input-border form-control " placeholder="请输入用户名" id="phone" style="background: rgba(255,255,255,0.30);border: 0 solid #FFFFFF">
					</div>
					<div class="input-group login_btn">
						<span class="input-group-addon addon-border" style="background: rgba(255,255,255,0.30);border: 0 solid #FFFFFF"><img  alt="密码" src="../images/login/password.png"></span>
						<input type="password" class="form-control input-border" placeholder="请输入密码" id="password" style="background: rgba(255,255,255,0.30);border: 0 solid #FFFFFF">
					</div>

					<div class="input-group login_btn">
						<span class="input-group-addon addon-border" style="background: rgba(255,255,255,0.30);border: 0 solid #FFFFFF"><img  alt="密码" src="../images/login/password.png"></span>
						<input type="text" class="form-control input-border" placeholder="请输入验证码" style="background: rgba(255,255,255,0.30);border: 0 solid #FFFFFF">
						<span class="input-group-addon right-bored " id="codeBtn" onclick="register.service.senmobile(60);" style="background: rgba(255,255,255,0.30);border: 0 solid #FFFFFF">获取验证码</span>
					</div>
					<div class="input-group login_btn_word" >
						<a href="login.html"><span class="once">登录</span></a>
					</div>
					<button type="button" id="registerBtn" class="btn btn-default btn-block btn-border red_btn login_btn btn_color" >注册</button>
				</div>
			</div>
		</form>
		<script src="../js/mui.min.js"></script>
		<script src="../js/core/jquery-2.1.1.min.js"></script>
		<script src="register.js?v=1.0.0"></script>
		<script src="../js/core/global.js"></script>
	</body>

</html>