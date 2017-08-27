package com.autoinspection.polaris.service;

import java.util.List;

import com.autoinspection.polaris.model.entity.RegistrationDisplayEntity;
import com.autoinspection.polaris.model.entity.RemainEntity;
import com.autoinspection.polaris.utils.BizException;
import com.autoinspection.polaris.vo.wx.AppointmentRequest;
import com.autoinspection.polaris.vo.wx.RegisterRequest;

public interface AppointmentService {
	public List<RemainEntity> listAppointments(AppointmentRequest req);
	public int register(RegisterRequest req, int wxid) throws BizException;
	public List<RegistrationDisplayEntity> listRegistrations(int wxid) throws BizException;
	public void cancelRegistration(int regId, int wxid) throws BizException;
	public List<RegistrationDisplayEntity> listRegistrationsForEndUser(int stationId, String regDate) throws BizException;
}
