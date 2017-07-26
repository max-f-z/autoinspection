package com.autoinspection.polaris.utils;

public class ErrorCode {
	public static final ErrorCode TOKEN_INVALID = new ErrorCode("1001", "token失效");
	public static final ErrorCode USER_NOTFOUND = new ErrorCode("1002", "找不到用户");
	public static final ErrorCode INVALID_USR_OR_PWD = new ErrorCode("1003", "错误的用户名或密码");
	public static final ErrorCode NOT_AUTHORIZED = new ErrorCode("1004", "权限不足");
	public static final ErrorCode INVALID_PARAM = new ErrorCode("1005", "非法参数");
	public static final ErrorCode NOT_FOUND = new ErrorCode("1006", "未找到资源");
	public static final ErrorCode TOO_MANY_AUTH_CODE = new ErrorCode("1007", "发送短信太多，请稍后再试");
	public static final ErrorCode ALREADY_SIGNED_UP = new ErrorCode("1008", "已经注册，请尝试登陆");
	
	private String code;
	private String msg;
	
	public ErrorCode(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public String getMsg() {
		return msg;
	}
	
	public String getCode() {
		return code;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
