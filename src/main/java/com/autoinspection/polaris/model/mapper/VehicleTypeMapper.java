package com.autoinspection.polaris.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.autoinspection.polaris.model.entity.VehicleTypeEntity;

@Mapper
public interface VehicleTypeMapper {
	VehicleTypeEntity getById(@Param("id") Integer id);
	List<VehicleTypeEntity> listVehicleTypes();
	VehicleTypeEntity getByCode(@Param("code") String code,@Param("type") String type);
}
