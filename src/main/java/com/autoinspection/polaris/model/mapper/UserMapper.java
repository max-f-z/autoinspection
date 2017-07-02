package com.autoinspection.polaris.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.autoinspection.polaris.model.entity.UserEntity;

@Mapper
public interface UserMapper {
	@Select("select * from user u where u.id=#{id}")
	UserEntity getById(@Param("id") Integer id);
	
	@Select("select * from user u where u.NAME=#{name} and u.PASSWORD=#{pwd}")
	UserEntity getByNameAndPassword(@Param("name") String name, @Param("pwd") String pwd);
}
