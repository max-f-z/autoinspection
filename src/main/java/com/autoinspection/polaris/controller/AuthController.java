package com.autoinspection.polaris.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.autoinspection.polaris.model.auth.AuthRequest;
import com.autoinspection.polaris.model.auth.AuthResponse;
import com.autoinspection.polaris.model.entity.UserEntity;
import com.autoinspection.polaris.service.UserService;
import com.autoinspection.polaris.utils.BizException;
import com.autoinspection.polaris.utils.ErrorCode;
import com.autoinspection.polaris.utils.TokenUtils;
import com.mysql.jdbc.StringUtils;

@RestController
@RequestMapping(path = "/v1/auth")
public class AuthController {
	@Autowired
	private UserService userService;
	
	@Autowired
    private TokenUtils tokenUtils;
	
	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public AuthResponse login(@RequestBody AuthRequest request) throws BizException {
		if (request == null || StringUtils.isNullOrEmpty(request.getUname()) || StringUtils.isNullOrEmpty(request.getPwd()) || !userService.validateUser(request.getUname(), request.getPwd())) {
			throw new BizException(ErrorCode.INVALID_USR_OR_PWD);
		}
		
		UserEntity user = userService.getByUnamePwd(request.getUname(), request.getPwd());
		AuthResponse resp = new AuthResponse();
		resp.setToken(tokenUtils.generateToken(user));
		return resp;
	}
}
