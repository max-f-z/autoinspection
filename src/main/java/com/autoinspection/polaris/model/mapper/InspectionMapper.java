package com.autoinspection.polaris.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.autoinspection.polaris.model.entity.InspectionEntity;

@Mapper
public interface InspectionMapper {
	List<InspectionEntity> listInspections(@Param("skip") Integer skip, @Param("pageSize") Integer pageSize, @Param("plate") String plate);
	InspectionEntity getById(@Param("id") Long id);
	long insertInspection(@Param("en") InspectionEntity entity, @Param("operatorId") Integer operatorId);
	int updateInspection(@Param("en") InspectionEntity entity, @Param("operatorId") Integer operatorId);
	int deleteInspection(@Param("id") Long id);
	List<InspectionEntity> search(@Param("skip") Integer skip, @Param("pageSize") Integer pageSize, @Param("search") String search);
}
