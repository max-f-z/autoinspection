package com.autoinspection.polaris.controller;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.autoinspection.polaris.utils.wxpay.utils.WXPayUtil;
import com.autoinspection.polaris.vo.Result;
import com.autoinspection.polaris.vo.payment.AccessTokenReq;

@Controller
@RequestMapping(value = "/v1/pay/auth")
@Transactional
public class WXAuthController {
	
	@Value("${wx.appid}")
	private String appId;
	@Value("${wx.redirect}")
	private String redirect;
	@Value("${wx.secret}")
	private String secret;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	@RequestMapping( value = "/authorize", method = RequestMethod.GET)
	public void redirectWxOauth(@RequestParam(value = "state") String state, HttpServletResponse response) throws IOException {
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
	
		try {
			url = url.replace("APPID", appId);
			url = url.replace("REDIRECT_URI",java.net.URLEncoder.encode(redirect, "UTF-8"));
			url = url.replace("STATE", state);
			response.sendRedirect(url);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping( value = "/accessToken")
	public Result<String> requestAccessToken(@RequestParam(value = "code")String code, @RequestParam(value="state") String state) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		AccessTokenReq req = new AccessTokenReq();
		req.setCode(code);
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=APPSECRET&code=CODE&grant_type=authorization_code";
		
		url = url.replace("APPID", appId);
		url = url.replace("APPSECRET", secret);
		url = url.replace("CODE", code);
		
		String respStr = restTemplate.getForObject(url, String.class);
		Map<String, String> map = WXPayUtil.xmlToMap(respStr);
		
		
		return new Result<>("");
	}
}
