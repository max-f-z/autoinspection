package com.autoinspection.polaris.service;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.autoinspection.polaris.interceptor.PermissionEnum;
import com.autoinspection.polaris.model.entity.WXUserEntity;
import com.autoinspection.polaris.model.mapper.WXUserMapper;
import com.autoinspection.polaris.utils.BizException;
import com.autoinspection.polaris.utils.Const;
import com.autoinspection.polaris.utils.ErrorCode;
import com.autoinspection.polaris.utils.TokenUtils;
import com.autoinspection.polaris.vo.Result;
import com.autoinspection.polaris.vo.wx.AuthCodeRequest;
import com.autoinspection.polaris.vo.wx.SignInRequest;
import com.autoinspection.polaris.vo.wx.SignInResponse;
import com.autoinspection.polaris.vo.wx.SignUpRequest;
import com.autoinspection.polaris.vo.wx.SignUpResponse;
import com.autoinspection.polaris.vo.wx.UpdateUserRequest;
import com.autoinspection.polaris.vo.wx.UserInfoResponse;
import com.mysql.jdbc.StringUtils;

@Service
public class WXServiceImpl implements WXService {

	@Autowired
	private WXUserMapper wxUserMapper;
	
	@Autowired
	private TokenUtils wxTokenUtils;
	
	@Autowired
	private SmsService smsService;
	
	@Autowired
	private StringRedisTemplate redisTemplate;
	
	@Override
	public SignUpResponse signUp(SignUpRequest req) throws BizException {
		if (StringUtils.isNullOrEmpty(req.getAuthCode())) {
			throw new BizException(ErrorCode.EMPTY_AUTH_CODE);
		}
		
		if (!redisTemplate.opsForValue().get(Const.WX_AUTH_CODE + req.getPhone()).equals(req.getAuthCode())) {
			throw new BizException(ErrorCode.INVALID_AUTH_CODE);
		}
		
		WXUserEntity user = new WXUserEntity();
		user.setPhone(req.getPhone());
		user.setPassword(DigestUtils.sha256Hex(req.getPassword()));
		user.setRole(PermissionEnum.WXUSER.ordinal());
		
		Integer exists = wxUserMapper.checkExists(req.getPhone());
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
	public Result<String> authCode(AuthCodeRequest req) throws BizException {
		
		String code = smsService.generateCode();
		
		smsService.sendSms(req.getPhone(), code);
		
		if (req.getType() == 1) {
			Integer history = null;
			if (redisTemplate.opsForValue().get(Const.WX_AUTH_CODE_TIMES+req.getPhone()) != null ) {
				history = Integer.parseInt(redisTemplate.opsForValue().get(Const.WX_AUTH_CODE_TIMES+req.getPhone()));
				if (history != null && history > 4) {
					throw new BizException(ErrorCode.TOO_MANY_AUTH_CODE);
				}
			}
			redisTemplate.opsForValue().set(Const.WX_AUTH_CODE+req.getPhone(), code, 15, TimeUnit.MINUTES);
			if (history != null && history > 0) {
				Long expire = redisTemplate.getExpire(Const.WX_AUTH_CODE_TIMES+req.getPhone(), TimeUnit.MICROSECONDS);
				redisTemplate.opsForValue().set(Const.WX_AUTH_CODE_TIMES+req.getPhone(), String.valueOf(history+1), expire, TimeUnit.MICROSECONDS);
			} else {
				redisTemplate.opsForValue().set(Const.WX_AUTH_CODE_TIMES+req.getPhone(), "1", 10, TimeUnit.MINUTES);
			}
		}
		
		if (req.getType() == 2){
			Integer history = null;
			if (redisTemplate.opsForValue().get(Const.WX_AUTH_CODE_UPDATE_USER+req.getPhone()) != null ) {
				history = Integer.parseInt(redisTemplate.opsForValue().get(Const.WX_AUTH_CODE_UPDATE_USER_TIMES+req.getPhone()));
				if (history != null && history > 4) {
					throw new BizException(ErrorCode.TOO_MANY_AUTH_CODE);
				}
			}
			redisTemplate.opsForValue().set(Const.WX_AUTH_CODE_UPDATE_USER+req.getPhone(), code, 15, TimeUnit.MINUTES);
			if (history != null && history > 0) {
				Long expire = redisTemplate.getExpire(Const.WX_AUTH_CODE_UPDATE_USER_TIMES+req.getPhone(), TimeUnit.MICROSECONDS);
				redisTemplate.opsForValue().set(Const.WX_AUTH_CODE_UPDATE_USER_TIMES+req.getPhone(), String.valueOf(history+1), expire, TimeUnit.MICROSECONDS);
			} else {
				redisTemplate.opsForValue().set(Const.WX_AUTH_CODE_UPDATE_USER_TIMES+req.getPhone(), "1", 10, TimeUnit.MINUTES);
			}
		}

		return new Result<>("");
	}

	@Override
	public SignInResponse signIn(SignInRequest req) throws BizException {
		WXUserEntity user = wxUserMapper.getByPhoneAndPassword(req.getPhone(), DigestUtils.sha256Hex(req.getPassword()));
		if (user == null) {
			throw new BizException(ErrorCode.INVALID_USR_OR_PWD);
		}
		
		SignInResponse resp = new SignInResponse();
		resp.setId(user.getId());
		resp.setToken(wxTokenUtils.generateToken(user));
		return resp;
	}

	@Override
	@Transactional
	public Result<String> updateUser(UpdateUserRequest req, int wxid) throws BizException {
		if (StringUtils.isNullOrEmpty(req.getAuthCode())) {
			throw new BizException(ErrorCode.EMPTY_AUTH_CODE);
		}
		
		if (!redisTemplate.opsForValue().get(Const.WX_AUTH_CODE_UPDATE_USER + req.getPhone()).equals(req.getAuthCode())) {
			throw new BizException(ErrorCode.INVALID_AUTH_CODE);
		}
		
		List<WXUserEntity> exists = wxUserMapper.getByPhone(req.getPhone(), wxid);
		if (exists != null && exists.size() > 0) {
			throw new BizException(ErrorCode.ALREADY_SIGNED_UP);
		}
		
		int rows = wxUserMapper.updateUser(wxid, req.getName(), req.getPhone());
		if (rows != 1) {
			throw new BizException(ErrorCode.INVALID_PARAM);
		}
		return new Result<>("");
	}

	@Override
	public UserInfoResponse getUserInfo(int wxid) throws BizException {
		UserInfoResponse resp = new UserInfoResponse();
		WXUserEntity entity = wxUserMapper.getById(wxid);
		resp.setName(entity.getName());
		resp.setPhone(entity.getPhone());
		return resp;
	}
}
