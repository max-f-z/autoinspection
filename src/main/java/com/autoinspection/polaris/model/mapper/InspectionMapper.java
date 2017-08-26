package com.autoinspection.polaris.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.autoinspection.polaris.model.entity.InspectionEntity;

@Mapper
public interface InspectionMapper {
	List<InspectionEntity> listInspections(@Param("skip") Integer skip, @Param("pageSize") Integer pageSize);
	InspectionEntity getById(@Param("id") Integer id);
	int insertInspection(@Param("en") InspectionEntity entity, @Param("operatorId") Integer operatorId);
	int deleteInspection(@Param("id") Integer id);
	List<InspectionEntity> search(@Param("skip") Integer skip, @Param("pageSize") Integer pageSize, @Param("search") String search);
}
