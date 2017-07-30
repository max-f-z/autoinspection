package com.autoinspection.polaris.service;

import com.autoinspection.polaris.utils.BizException;
import com.autoinspection.polaris.vo.Result;
import com.autoinspection.polaris.vo.wx.AuthCodeRequest;
import com.autoinspection.polaris.vo.wx.SignInRequest;
import com.autoinspection.polaris.vo.wx.SignInResponse;
import com.autoinspection.polaris.vo.wx.SignUpRequest;
import com.autoinspection.polaris.vo.wx.SignUpResponse;
import com.autoinspection.polaris.vo.wx.UpdateUserRequest;

public interface WXService {
	public SignUpResponse signUp(SignUpRequest req) throws BizException;
	public Result<String> authCode(AuthCodeRequest req) throws BizException;
	public SignInResponse signIn(SignInRequest req) throws BizException;
	public Result<String> updateUser(UpdateUserRequest req) throws BizException;
}
