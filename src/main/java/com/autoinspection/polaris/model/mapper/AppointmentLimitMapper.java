package com.autoinspection.polaris.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.autoinspection.polaris.model.entity.AppointmentLimitEntity;
import com.autoinspection.polaris.model.entity.RemainEntity;
import com.autoinspection.polaris.model.entity.ReservedEntity;

@Mapper
public interface AppointmentLimitMapper {
	int getLimitByDate(@Param("reqDate") String reqDate, @Param("stationId") Integer stationId);
	int getReservedByDateAndSlot(@Param("reqDate") String reqDate, @Param("slotId") Integer slotId, @Param("stationId") Integer stationId);
	int insertLimit(AppointmentLimitEntity entity);
	int updateLimit(AppointmentLimitEntity entity);
	int deleteLimit(@Param("id") Integer id, @Param("operatorId") Integer operatorId);
	List<ReservedEntity> selectReserveByDate(@Param("reqDate") String reqDate, @Param("stationId") Integer stationId);
	List<RemainEntity> selectRemainByDate(@Param("reqDate") String reqDate, @Param("stationId") Integer stationId);
	AppointmentLimitEntity check(@Param("reqDate") String reqDate, @Param("stationId") Integer stationId);
}
