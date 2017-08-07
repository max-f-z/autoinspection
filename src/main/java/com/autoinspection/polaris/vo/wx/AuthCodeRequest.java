package com.autoinspection.polaris.vo.wx;

public class AuthCodeRequest {
	private String phone;
	// 1 注册登录验证码 2 修改用户信息验证码
	private int type;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
