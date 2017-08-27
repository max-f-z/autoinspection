package com.autoinspection.polaris.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.autoinspection.polaris.model.entity.InspectionDetailEntity;

@Mapper
public interface InspectionDetailMapper {
	List<InspectionDetailEntity> listDetails(@Param("inspectionId") Long inspectionId);
	int insertInspectionDetail(@Param("en") InspectionDetailEntity entity, @Param("operatorId") Integer operatorId);
	int updateInspectionDetail(@Param("en") InspectionDetailEntity entity, @Param("operatorId") Integer operatorId);
	int deleteInspectionDetail(@Param("id") Long id);
}
