package com.autoinspection.polaris.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.autoinspection.polaris.model.entity.AppointmentEntity;
import com.autoinspection.polaris.model.entity.RegistrationDisplayEntity;

@Mapper
public interface AppointmentMapper {
	int insertAppointment(AppointmentEntity entity);
	List<RegistrationDisplayEntity> listRegistrations(@Param("wxid") Integer wxid);
	int deleteAppointment(@Param("regId") Integer regId, @Param("wxid") Integer wxid);
	List<AppointmentEntity> getById(@Param("id") Integer regId, @Param("wxid") Integer wxid);
	List<AppointmentEntity> getByPlateAndDate(@Param("plate") String plate, @Param("appoDate") String appoDate);
	List<RegistrationDisplayEntity> listRegistrationsForEndUser(@Param("stationId") Integer stationId, @Param("regDate") String regDate);
	List<RegistrationDisplayEntity> search(@Param("stationId") Integer stationId, @Param("regDate") String regDate, @Param("search") String search);
}
