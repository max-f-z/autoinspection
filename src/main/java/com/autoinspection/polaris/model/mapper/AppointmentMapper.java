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
}
