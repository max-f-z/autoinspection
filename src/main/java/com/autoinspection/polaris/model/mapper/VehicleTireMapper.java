package com.autoinspection.polaris.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.autoinspection.polaris.model.entity.VehicleTireEntity;

@Mapper
public interface VehicleTireMapper {
	List<VehicleTireEntity> listTires(@Param("vid") Integer vid);
	VehicleTireEntity getById(@Param("id") Long id);
	VehicleTireEntity getByTireId(@Param("tId") String tid);
	VehicleTireEntity getByVehiclePosition(@Param("vid") Integer vid, @Param("tirePosition") Integer tirePosition);
	int insertVehicleTire(@Param("en") VehicleTireEntity entity, @Param("operatorId") Integer operatorId);
	int updateVehicleTire(@Param("en") VehicleTireEntity entity, @Param("operatorId") Integer operatorId);
	int deleteVehicleTire(@Param("id") Long id);
}
