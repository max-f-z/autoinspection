package com.autoinspection.polaris.service;

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
}
