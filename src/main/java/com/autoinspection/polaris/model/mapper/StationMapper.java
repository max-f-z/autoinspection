package com.autoinspection.polaris.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.autoinspection.polaris.model.entity.StationEntity;

@Mapper
public interface StationMapper {
	StationEntity getById(@Param("id") Integer id);
	List<StationEntity> listAllStations();
	int insertStation(StationEntity entity);
	int updateStation(StationEntity entity);
	int deleteStation(@Param("id") Integer id, @Param("operatorId") Integer operatorId);
}
