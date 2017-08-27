package com.autoinspection.polaris.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.autoinspection.polaris.model.entity.MaintenanceEntity;

@Mapper
public interface MaintenanceMapper {
	int insertMaintenance(@Param("en") MaintenanceEntity entity, @Param("operatorId") Integer operatorId);
	int updateMaintenance(@Param("en") MaintenanceEntity entity, @Param("operatorId") Integer operatorId);
	MaintenanceEntity getByInspectionId(@Param("inspectionId") Long inspectionId);
}
