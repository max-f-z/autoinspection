package com.autoinspection.polaris.service;

import java.util.concurrent.TimeUnit;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.autoinspection.polaris.model.entity.WXUserEntity;
import com.autoinspection.polaris.model.mapper.WXUserMapper;
import com.autoinspection.polaris.utils.BizException;
import com.autoinspection.polaris.utils.Const;
import com.autoinspection.polaris.utils.ErrorCode;
import com.autoinspection.polaris.utils.WXTokenUtils;
import com.autoinspection.polaris.vo.wx.AuthCodeRequest;
import com.autoinspection.polaris.vo.wx.AuthCodeResponse;
import com.autoinspection.polaris.vo.wx.SignInRequest;
import com.autoinspection.polaris.vo.wx.SignInResponse;
import com.autoinspection.polaris.vo.wx.SignUpRequest;
import com.autoinspection.polaris.vo.wx.SignUpResponse;

@Service
public class WXServiceImpl implements WXService {

	@Autowired
	private WXUserMapper wxUserMapper;
	
	@Autowired
	private WXTokenUtils wxTokenUtils;
	
	
	@Autowired
	private StringRedisTemplate redisTemplate;
	
	@Override
	public SignUpResponse signUp(SignUpRequest req) throws BizException {
		WXUserEntity user = new WXUserEntity();
		user.setName(req.getName());
		user.setPhone(req.getPhone());
		user.setPassword(DigestUtils.sha256Hex(req.getPassword()));
		
		Integer exists = wxUserMapper.checkExists(req.getName(), req.getPhone());
		if (exists != null && exists > 0) {
			throw new BizException(ErrorCode.ALREADY_SIGNED_UP);
		}
		
		wxUserMapper.insertWXUser(user);
		
		SignUpResponse resp = new SignUpResponse();
		resp.setId(user.getId());
		resp.setToken(wxTokenUtils.generateToken(user));
		
		return resp;
	}

	@Override
	public AuthCodeResponse authCode(AuthCodeRequest req) throws BizException {
		Integer history = Integer.parseInt(redisTemplate.opsForValue().get(Const.WX_AUTH_CODE_TIMES+req.getPhone()));
		if (history != null && history > 4) {
			throw new BizException(ErrorCode.TOO_MANY_AUTH_CODE);
		}
		
		redisTemplate.opsForValue().set(Const.WX_AUTH_CODE+req.getPhone(), "123456", 15, TimeUnit.MINUTES);
		if (history != null && history > 0) {
			redisTemplate.opsForValue().set(Const.WX_AUTH_CODE_TIMES+req.getPhone(), String.valueOf(history+1));
		} else {
			redisTemplate.opsForValue().set(Const.WX_AUTH_CODE_TIMES+req.getPhone(), "1", 10, TimeUnit.MINUTES);
		}
		
		AuthCodeResponse resp = new AuthCodeResponse();
		resp.setAuthCode("123456");
		return resp;
	}

	@Override
	public SignInResponse signIn(SignInRequest req) throws BizException {
		WXUserEntity user = wxUserMapper.getByNameAndPassword(req.getName(), DigestUtils.sha256Hex(req.getPassword()));
		if (user == null) {
			throw new BizException(ErrorCode.USER_NOTFOUND);
		}
		
		SignInResponse resp = new SignInResponse();
		resp.setId(user.getId());
		resp.setToken(wxTokenUtils.generateToken(user));
		return resp;
	}
}
