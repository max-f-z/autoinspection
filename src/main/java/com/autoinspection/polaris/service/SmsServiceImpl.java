package com.autoinspection.polaris.service;

import org.springframework.stereotype.Service;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.autoinspection.polaris.utils.BizException;
import com.autoinspection.polaris.utils.ErrorCode;

@Service
public class SmsServiceImpl implements SmsService {

	final String product = "Dysmsapi";
	final String domain = "dysmsapi.aliyuncs.com";
	final String accessKeyId = "LTAIVN6oLnM51LNy";
	final String accessKeySecret = "XOJ52fzuY23Pp74zqWoneEjEggW38f";
	
	@Override
	public void sendSms(String phoneNo, String code) throws BizException {
		// TODO Auto-generated method stub
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,accessKeySecret);
		try {
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		IAcsClient acsClient = new DefaultAcsClient(profile);
		
		SendSmsRequest request = new SendSmsRequest();
		request.setMethod(MethodType.POST);
		//必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
		request.setPhoneNumbers(phoneNo);
		//必填:短信签名-可在短信控制台中找到
		request.setSignName("玖通轮胎安装保养中心");
		request.setTemplateCode("SMS_100030049");
		request.setTemplateParam("{\"code\":\"" + code +"\"}");
		
		try {
			SendSmsResponse resp = acsClient.getAcsResponse(request);
			if (resp.getCode() == null || !resp.getCode().equals("OK")) {
				throw new BizException(ErrorCode.SEND_SMS_FAILED);
			}
			
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@Override
	public String generateCode() {
		return String.valueOf((int)((Math.random()*9+1)*100000)); 
	}
	
}
