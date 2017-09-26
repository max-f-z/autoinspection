package com.autoinspection.polaris.service;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autoinspection.polaris.model.entity.UserEntity;
import com.autoinspection.polaris.model.mapper.UserMapper;
import com.autoinspection.polaris.vo.user.AddUserRequest;
import com.autoinspection.polaris.vo.user.DeleteUserRequest;
import com.autoinspection.polaris.vo.user.UpdateUserRequest;

@Service
public class UserServiceImpl implements UserService {
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

	@Override
	public List<UserEntity> listAllUsers() {
		return userMapper.listAllUsers();
	}

	@Override
	public List<UserEntity> listUsersAdmin() {
		return userMapper.listUsersAdmin();
	}

	@Override
	public List<UserEntity> listUsersAdminSearch(String name) {
		return userMapper.listUsersAdminSearch(name);
	}
	
	@Override
	public int addUser(AddUserRequest request, int uid) {
		UserEntity entity = new UserEntity();
		entity = userMapper.getByName(request.getUserName());
		if(null == entity){
			entity = new UserEntity();
			entity.setName(request.getUserName());
			entity.setPassword(DigestUtils.sha256Hex(request.getPassword()));
			entity.setStation(request.getStation());
			entity.setStationId(request.getStationId());
			entity.setRole(request.getRole());
			entity.setOperatorId(uid);
			userMapper.insertUser(entity);
			return entity.getId();
		}
		return 0;

	}

	@Override
	public int updateUser(UpdateUserRequest request, int uid) {
		UserEntity entity = new UserEntity();
		entity.setName(request.getUserName());
		entity.setPassword(DigestUtils.sha256Hex(request.getPassword()));
		entity.setStation(request.getStation());
		entity.setStationId(request.getStationId());
		entity.setRole(request.getRole());
		entity.setOperatorId(uid);
		entity.setId(request.getId());
		return userMapper.updateUser(entity);
	}

	@Override
	public int deleteUser(DeleteUserRequest request, int uid) {
		return userMapper.deleteUser(request.getId(), uid);
	}
}
