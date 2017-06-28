package com.autoinspection.polaris.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.autoinspection.polaris.interceptor.Permission;
import com.autoinspection.polaris.interceptor.PermissionEnum;
import com.autoinspection.polaris.model.entity.UserEntity;
import com.autoinspection.polaris.service.RedisService;
import com.autoinspection.polaris.service.UserService;


@RestController
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private RedisService redisService;
	
	@RequestMapping(path = "/users/{id}", method = RequestMethod.GET)
	@Permission( permissionTypes = { PermissionEnum.ADMIN })
    public UserEntity getUsers(@PathVariable Integer id) {
		UserEntity user = userService.getById(id);
		
		redisService.set("haha", "123");
		
		return user;
	}
}
