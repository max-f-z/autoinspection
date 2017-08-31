package com.autoinspection.polaris.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.autoinspection.polaris.model.entity.AppointmentEntity;
import com.autoinspection.polaris.model.entity.RegistrationDisplayEntity;
import com.autoinspection.polaris.model.entity.RemainEntity;
import com.autoinspection.polaris.model.entity.ReservedEntity;
import com.autoinspection.polaris.model.entity.VehicleInfoEntity;
import com.autoinspection.polaris.model.entity.WXUserEntity;
import com.autoinspection.polaris.model.mapper.AppointmentLimitMapper;
import com.autoinspection.polaris.model.mapper.AppointmentMapper;
import com.autoinspection.polaris.model.mapper.VehicleInfoMapper;
import com.autoinspection.polaris.model.mapper.WXUserMapper;
import com.autoinspection.polaris.utils.BizException;
import com.autoinspection.polaris.utils.ErrorCode;
import com.autoinspection.polaris.vo.wx.AppointmentRequest;
import com.autoinspection.polaris.vo.wx.RegisterRequest;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	private AppointmentLimitMapper alMapper;
	
	@Autowired
	private AppointmentMapper appointmentMapper;
	
	@Autowired
	private VehicleInfoMapper vehicleMapper;
	
	@Autowired
	private WXUserMapper wxUserMapper;
	
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
	public int register(RegisterRequest req, int wxid) throws BizException {
		
		VehicleInfoEntity en = vehicleMapper.getByPlate(req.getPlate());
		if (en == null) {
			en = new VehicleInfoEntity();
			en.setPlate(req.getPlate());
			WXUserEntity entity = wxUserMapper.getById(wxid);
			en.setCustomerName(entity.getName());
			vehicleMapper.insertVehicle(en, wxid);
		}
		
		
		int limit = alMapper.getLimitByDate(req.getAppointmentDate());
		Integer reserved = alMapper.getReservedByDateAndSlot(req.getAppointmentDate(), req.getAppointmentSlot());
		
		if ((reserved != null) && (limit - reserved) <= 0){
			throw new BizException(ErrorCode.REGISTRATION_FULL);
		}
		
		List<AppointmentEntity> exists = appointmentMapper.getByPlateAndDate(req.getPlate(), req.getAppointmentDate());
		
		if (exists != null && exists.size() > 0) {
			throw new BizException(ErrorCode.ONCE_PER_DAY);
		}
		
		AppointmentEntity entity = new AppointmentEntity();
		entity.setAppointmentDate(req.getAppointmentDate());
		entity.setAppointmentSlot(req.getAppointmentSlot());
		entity.setWxUserId(wxid);
		entity.setServiceId(req.getServiceId());
		entity.setStationId(req.getStationId());
		entity.setPlate(req.getPlate());
		
		appointmentMapper.insertAppointment(entity);
		
		return entity.getId();
	}

	@Override
	public List<RegistrationDisplayEntity> listRegistrations(int wxid) throws BizException {
		return appointmentMapper.listRegistrations(wxid);
	}
	
	@Override
	public List<RegistrationDisplayEntity> listRegistrationsForEndUser(int stationId, String regDate) throws BizException {
		return appointmentMapper.listRegistrationsForEndUser(stationId, regDate);
	}

	@Override
	public void cancelRegistration(int regId, int wxid) throws BizException {
		List<AppointmentEntity> exists = appointmentMapper.getById(regId, wxid);
		if (exists == null || exists.size() > 1) {
			throw new BizException(ErrorCode.INVALID_PARAM);
		}
		
		appointmentMapper.deleteAppointment(regId, wxid);
	}

	@Override
	public List<RegistrationDisplayEntity> search(int stationId, String regDate, String search) throws BizException {
		return appointmentMapper.search(stationId, regDate, search);
	}

}
