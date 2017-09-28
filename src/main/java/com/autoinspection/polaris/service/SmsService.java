package com.autoinspection.polaris.service;

import com.autoinspection.polaris.utils.BizException;

public interface SmsService {
	public void sendSms(String phoneNo, String code) throws BizException;
	public String generateCode();
}
