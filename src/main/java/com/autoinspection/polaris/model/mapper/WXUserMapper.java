package com.autoinspection.polaris.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.autoinspection.polaris.model.entity.WXUserEntity;

@Mapper
public interface WXUserMapper {
	public WXUserEntity getById(@Param("id") Integer id);
	public int insertWXUser(WXUserEntity user);
	public Integer checkExists(@Param("phone") String phone);
	public WXUserEntity getByPhoneAndPassword(@Param("phone") String phone, @Param("password") String password);
	public int updateUser(@Param("id") Integer id, @Param("name") String name, @Param("phone") String phone);
	public List<WXUserEntity> getByPhone(@Param("phone") String phone, @Param("wxid") int wxid);
}
