package com.autoinspection.polaris.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.autoinspection.polaris.model.entity.VehicleInfoEntity;

@Mapper
public interface VehicleInfoMapper {
	List<VehicleInfoEntity> listVehicles(@Param("skip") Integer skip, @Param("pageSize") Integer pageSize);
	VehicleInfoEntity getById(@Param("id") Integer id);
	int insertVehicle(@Param("en") VehicleInfoEntity entity, @Param("operatorId") Integer operatorId);
	int updateVehicle(@Param("en") VehicleInfoEntity entity, @Param("operatorId") Integer operatorId);
	int deleteVehicle(@Param("id") Integer id);
	List<VehicleInfoEntity> search(@Param("skip") Integer skip, @Param("pageSize") Integer pageSize, @Param("search") String search);
}
