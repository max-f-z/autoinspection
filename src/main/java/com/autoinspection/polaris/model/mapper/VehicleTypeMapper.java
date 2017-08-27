package com.autoinspection.polaris.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.autoinspection.polaris.model.entity.VehicleTypeEntity;

@Mapper
public interface VehicleTypeMapper {
	VehicleTypeEntity getById(@Param("id") Integer id);
}
