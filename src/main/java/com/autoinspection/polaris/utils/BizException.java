package com.autoinspection.polaris.utils;

public class BizException extends Exception {
	private static final long serialVersionUID = 1L;

	private String msg;
	private String code;
	
	public BizException(String msg) {
        super();
        this.msg = msg;
    }
		
	public BizException(String code,String msg){
		super();
		this.msg = msg;
		this.code = code;
	}
	
	public BizException(ErrorCode error){
		super();
		this.msg = error.getMsg();
		this.code = error.getCode();
	}
	
	public void setErrorMessage(String errorMessage) {
		this.msg = errorMessage;
	}

	public String getErrorMessage() {
		return msg;
	}
	public void setErrorCode(String errorCode) {
		this.code = errorCode;
	}
	public String getErrorCode() {
		return code;
	}
}
