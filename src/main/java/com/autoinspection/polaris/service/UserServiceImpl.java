package com.autoinspection.polaris.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autoinspection.polaris.model.entity.UserEntity;
import com.autoinspection.polaris.model.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public UserEntity getById(Integer id) {
		return userMapper.getById(id);
	}
	
	@Override
	public Boolean validateUser(String uname, String pwd) {
		UserEntity user = userMapper.getByNameAndPassword(uname, DigestUtils.sha256Hex(pwd));
		if (user != null) {
			return true;
		}
		return false;
	}

	@Override
	public UserEntity getByUnamePwd(String uname, String pwd) {
		UserEntity user = userMapper.getByNameAndPassword(uname, DigestUtils.sha256Hex(pwd));
		return user;
	}
}
