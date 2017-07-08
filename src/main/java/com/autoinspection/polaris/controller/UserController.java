package com.autoinspection.polaris.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.autoinspection.polaris.interceptor.Permission;
import com.autoinspection.polaris.interceptor.PermissionEnum;
import com.autoinspection.polaris.model.entity.UserEntity;
import com.autoinspection.polaris.service.UserService;
import com.autoinspection.polaris.utils.BizException;
import com.autoinspection.polaris.utils.ErrorCode;


@RestController
@RequestMapping(path = "${api.path}")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(path = "/users/{id}", method = RequestMethod.GET)
	@Permission( permissionTypes = { PermissionEnum.ADMIN })
    public UserEntity getUsers(@PathVariable Integer id) throws BizException {
		UserEntity user = userService.getById(id);
		if (user == null) {
			throw new BizException(ErrorCode.USER_NOTFOUND);
		}
		
		return user;
	}
}
