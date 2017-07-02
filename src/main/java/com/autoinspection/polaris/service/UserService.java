package com.autoinspection.polaris.service;

import com.autoinspection.polaris.model.entity.UserEntity;

public interface UserService {
	public UserEntity getById(Integer id);
	
	public Boolean validateUser(String uname, String pwd);
}
