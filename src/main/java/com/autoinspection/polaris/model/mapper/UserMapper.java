package com.autoinspection.polaris.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.autoinspection.polaris.model.entity.UserEntity;

@Mapper
public interface UserMapper {
	UserEntity getById(@Param("id") Integer id);
	
	UserEntity getByNameAndPassword(@Param("name") String name, @Param("pwd") String pwd);
	
	
}
