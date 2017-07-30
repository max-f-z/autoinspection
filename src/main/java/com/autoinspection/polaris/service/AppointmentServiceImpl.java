package com.autoinspection.polaris.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.autoinspection.polaris.model.entity.AppointmentEntity;
import com.autoinspection.polaris.model.entity.RegistrationDisplayEntity;
import com.autoinspection.polaris.model.entity.RemainEntity;
import com.autoinspection.polaris.model.entity.ReservedEntity;
import com.autoinspection.polaris.model.mapper.AppointmentLimitMapper;
import com.autoinspection.polaris.model.mapper.AppointmentMapper;
import com.autoinspection.polaris.utils.BizException;
import com.autoinspection.polaris.utils.ErrorCode;
import com.autoinspection.polaris.vo.wx.AppointmentRequest;
import com.autoinspection.polaris.vo.wx.ListRegistrationRequest;
import com.autoinspection.polaris.vo.wx.RegisterRequest;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	private AppointmentLimitMapper alMapper;
	
	@Autowired
	private AppointmentMapper appointmentMapper;
	
	@Override
	public List<RemainEntity> listAppointments(AppointmentRequest req) {
		List<RemainEntity> list = alMapper.selectRemainByDate(req.getDate());
		List<ReservedEntity> reservedList = alMapper.selectReserveByDate(req.getDate());
		
		for (ReservedEntity reserved : reservedList) {
			for (RemainEntity remain : list) {
				if (remain.getId() == reserved.getId()) {
					remain.setRemain(remain.getRemain() - reserved.getReserved());
				}
			}
		}
		
		return list;
	}

	@Override
	@Transactional
	public int register(RegisterRequest req) throws BizException {
		int limit = alMapper.getLimitByDate(req.getAppointmentDate());
		Integer reserved = alMapper.getReservedByDateAndSlot(req.getAppointmentDate(), req.getAppointmentSlot());
		
		if ((reserved != null) && (limit - reserved) <= 0){
			throw new BizException(ErrorCode.REGISTRATION_FULL);
		}
		
		AppointmentEntity entity = new AppointmentEntity();
		entity.setAppointmentDate(req.getAppointmentDate());
		entity.setAppointmentSlot(req.getAppointmentSlot());
		entity.setWxUserId(req.getWxid());
		entity.setServiceId(req.getServiceId());
		entity.setStationId(req.getStationId());
		entity.setPlate(req.getPlate());
		
		int regId = appointmentMapper.insertAppointment(entity);
		
		return regId;
	}

	@Override
	public List<RegistrationDisplayEntity> listRegistrations(ListRegistrationRequest req) throws BizException {
		return appointmentMapper.listRegistrations(req.getWxid());
	}

}
