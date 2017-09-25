package com.autoinspection.polaris.vo.payment;

public class AccessTokenReq {
	private String code;
	private String appSecret;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getAppSecret() {
		return appSecret;
	}
	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}
}
