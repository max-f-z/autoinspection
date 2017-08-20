package com.autoinspection.polaris.service;

import java.util.List;

import com.autoinspection.polaris.model.entity.UserEntity;
import com.autoinspection.polaris.vo.user.AddUserRequest;
import com.autoinspection.polaris.vo.user.DeleteUserRequest;
import com.autoinspection.polaris.vo.user.UpdateUserRequest;

public interface UserService {
	public UserEntity getById(Integer id);
	
	public Boolean validateUser(String uname, String pwd);
	
	public UserEntity getByUnamePwd(String uname, String pwd);

	public List<UserEntity> listAllUsers();
	
	public int addUser(AddUserRequest request, int uid);
	
	public int updateUser(UpdateUserRequest request, int uid);
	
	public int deleteUser(DeleteUserRequest request, int uid);
}
