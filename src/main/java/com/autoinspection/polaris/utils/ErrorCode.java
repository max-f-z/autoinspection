package com.autoinspection.polaris.utils;

public class ErrorCode {
	public static final ErrorCode TOKEN_INVALID = new ErrorCode("1001", "tokenʧЧ");
	
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
