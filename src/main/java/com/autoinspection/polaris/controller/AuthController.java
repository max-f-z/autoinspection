package com.autoinspection.polaris.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.autoinspection.polaris.model.auth.AuthRequest;
import com.autoinspection.polaris.model.auth.AuthResponse;
import com.autoinspection.polaris.service.UserService;
import com.autoinspection.polaris.utils.BizException;
import com.autoinspection.polaris.utils.ErrorCode;
import com.mysql.jdbc.StringUtils;

@RestController
public class AuthController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public AuthResponse login(@RequestBody AuthRequest request) throws BizException {
		if (request == null || StringUtils.isNullOrEmpty(request.getUname()) || StringUtils.isNullOrEmpty(request.getPwd()) || !userService.validateUser(request.getUname(), request.getPwd())) {
			throw new BizException(ErrorCode.INVALID_USR_OR_PWD);
		}
		
		AuthResponse resp = new AuthResponse();
		resp.setToken("123");
		return resp;
	}
}
