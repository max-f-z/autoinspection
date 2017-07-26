package com.autoinspection.polaris.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.autoinspection.polaris.service.WXService;
import com.autoinspection.polaris.utils.BizException;
import com.autoinspection.polaris.vo.wx.SignUpRequest;
import com.autoinspection.polaris.vo.wx.SignUpResponse;

@RestController
@RequestMapping(path = "/v1/wx")
public class WXController {
	
	@Autowired
	private WXService wxService;
	
	@RequestMapping(path = "/signup", method = RequestMethod.POST)
	public SignUpResponse signUp(@RequestBody SignUpRequest req) throws BizException {
		return wxService.signUp(req);
	}
}
