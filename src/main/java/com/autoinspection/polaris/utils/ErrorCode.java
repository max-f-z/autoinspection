package com.autoinspection.polaris.utils;

public class ErrorCode {
	public static final ErrorCode TOKEN_INVALID = new ErrorCode("1001", "token失效");
	public static final ErrorCode USER_NOTFOUND = new ErrorCode("1002", "找不到用户");
	public static final ErrorCode INVALID_USR_OR_PWD = new ErrorCode("1003", "错误的用户名或密码");
	
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
