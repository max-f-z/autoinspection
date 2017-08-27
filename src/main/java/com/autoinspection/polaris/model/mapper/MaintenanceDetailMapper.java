package com.autoinspection.polaris.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.autoinspection.polaris.model.entity.MaintenanceDetailEntity;

@Mapper
public interface MaintenanceDetailMapper {
	List<MaintenanceDetailEntity> listDetails(@Param("MaintenanceId") Long MaintenanceId);
	int insertMaintenanceDetail(@Param("en") MaintenanceDetailEntity entity, @Param("operatorId") Integer id);
	int updateMaintenanceDetail(@Param("en") MaintenanceDetailEntity entity, @Param("operatorId") Integer id);
}
