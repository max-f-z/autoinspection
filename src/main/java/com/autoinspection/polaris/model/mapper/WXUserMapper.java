package com.autoinspection.polaris.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.autoinspection.polaris.model.entity.WXUserEntity;

@Mapper
public interface WXUserMapper {
	public WXUserEntity getById(@Param("id") Integer id);
	public int insertWXUser(WXUserEntity user);
	public Integer checkExists(@Param("phone") String phone);
	public WXUserEntity getByNameAndPassword(@Param("name") String name, @Param("password") String password);
}
