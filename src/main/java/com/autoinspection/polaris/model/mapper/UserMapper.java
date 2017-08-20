package com.autoinspection.polaris.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.autoinspection.polaris.model.entity.UserEntity;

@Mapper
public interface UserMapper {
	UserEntity getById(@Param("id") Integer id);
	
	UserEntity getByNameAndPassword(@Param("name") String name, @Param("pwd") String pwd);
	
	List<UserEntity> listAllUsers();
	
	int insertUser(UserEntity user);
	int updateUser(UserEntity user);
	int deleteUser(@Param("id") Integer id, @Param("operatorId") Integer operatorId);
}
