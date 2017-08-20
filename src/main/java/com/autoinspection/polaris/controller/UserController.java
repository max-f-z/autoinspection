package com.autoinspection.polaris.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.autoinspection.polaris.interceptor.Permission;
import com.autoinspection.polaris.interceptor.PermissionEnum;
import com.autoinspection.polaris.model.entity.UserEntity;
import com.autoinspection.polaris.resolver.CurrentUser;
import com.autoinspection.polaris.service.UserService;
import com.autoinspection.polaris.utils.BizException;
import com.autoinspection.polaris.utils.ErrorCode;
import com.autoinspection.polaris.vo.UserVo;
import com.autoinspection.polaris.vo.tireprice.DeleteUserResponse;
import com.autoinspection.polaris.vo.user.AddUserRequest;
import com.autoinspection.polaris.vo.user.AddUserResponse;
import com.autoinspection.polaris.vo.user.DeleteUserRequest;
import com.autoinspection.polaris.vo.user.UpdateUserRequest;
import com.autoinspection.polaris.vo.user.UpdateUserResponse;


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
	
	@RequestMapping(path = "/users", method = RequestMethod.GET)
	@Permission( permissionTypes = { PermissionEnum.ADMIN })
	public List<UserEntity> listUsers() {
		return userService.listAllUsers();
	}
	
	@RequestMapping(path = "/users/add", method = RequestMethod.POST)
	@Permission( permissionTypes = { PermissionEnum.ADMIN })
	public AddUserResponse addUser(@RequestBody AddUserRequest request, @CurrentUser UserVo user) {
		AddUserResponse resp = new AddUserResponse();
		
		resp.setId(userService.addUser(request, user.getUid()));
		
		return resp;
	}
	
	@RequestMapping(path = "/users/update", method = RequestMethod.POST)
	@Permission( permissionTypes = { PermissionEnum.ADMIN })
	public UpdateUserResponse updateUser(@RequestBody UpdateUserRequest request, @CurrentUser UserVo user) {
		UpdateUserResponse resp = new UpdateUserResponse();
		
		int rows = userService.updateUser(request, user.getUid());
		resp.setAffectedRows(rows);
		return resp;
	}
	
	@RequestMapping(path = "/users/delete", method = RequestMethod.DELETE)
	@Permission( permissionTypes = { PermissionEnum.ADMIN })
	public DeleteUserResponse deleteUser(@RequestBody DeleteUserRequest request, @CurrentUser UserVo user) {
		DeleteUserResponse resp = new DeleteUserResponse();
		int rows = userService.deleteUser(request, user.getUid());
		resp.setAffectedRows(rows);
		return resp;
	}
}
