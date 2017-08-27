package com.autoinspection.polaris.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.autoinspection.polaris.model.entity.MaintenanceDetailEntity;
import com.autoinspection.polaris.model.entity.MaintenanceEntity;
import com.autoinspection.polaris.model.entity.UserEntity;
import com.autoinspection.polaris.model.mapper.MaintenanceDetailMapper;
import com.autoinspection.polaris.model.mapper.MaintenanceMapper;
import com.autoinspection.polaris.model.mapper.UserMapper;
import com.autoinspection.polaris.vo.Inspection.AddMaintenanceRequest;
import com.autoinspection.polaris.vo.Inspection.MaintenanceDetailVo;
import com.autoinspection.polaris.vo.Inspection.MaintenanceVo;
import com.autoinspection.polaris.vo.Inspection.UpdateMaintenanceRequest;

@Service
public class MaintenanceServiceImpl implements MaintenanceService {
	
	@Autowired
	private MaintenanceMapper maintenanceMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private MaintenanceDetailMapper maintenanceDetailMapper;

	@Override
	public MaintenanceVo getMaintenance(long inspectionId) {
		MaintenanceVo vo = new MaintenanceVo();
		MaintenanceEntity entity = maintenanceMapper.getByInspectionId(inspectionId);
		if (entity != null) {
			vo.setId(entity.getId());
			vo.setInspectionId(entity.getInspectionId());
			vo.setPlate(entity.getPlate());
			vo.setDriverPhone(entity.getDriverPhone());
			vo.setOperatorName(entity.getOperatorName());
			
			List<MaintenanceDetailEntity> details = maintenanceDetailMapper.listDetails(entity.getId());
			List<MaintenanceDetailVo> ds = new ArrayList<MaintenanceDetailVo>();
			for (MaintenanceDetailEntity d : details) {
				MaintenanceDetailVo dvo = new MaintenanceDetailVo();
				dvo.setId(d.getId());
				dvo.setMaintenanceId(d.getMaintenanceId());
				dvo.setTirePosition(d.getTireposition());
				dvo.setServiceId(d.getServicePriceId());
				dvo.setServiceName(d.getServicePriceName());
				dvo.setServiceDesc(d.getServicePriceDesc());
				dvo.setNum(d.getNum());
				dvo.setStartTime(d.getStartTime());
				dvo.setEndTime(d.getEndTime());
				ds.add(dvo);
			}
			vo.setDetails(ds);
		}
		
		return vo;
	}

	@Override
	@Transactional( rollbackFor=Exception.class )
	public long insertMaintenance(AddMaintenanceRequest request, int uid) {
		MaintenanceEntity entity = new MaintenanceEntity();
		
		UserEntity userEntity = userMapper.getById(uid);
		entity.setOperatorName(userEntity.getName());
		entity.setInspectionId(request.getInspectionId());
		entity.setPlate(request.getPlate());
		entity.setDriverPhone(request.getDriverPhone());
		
		maintenanceMapper.insertMaintenance(entity, uid);
		
		for (MaintenanceDetailVo vo : request.getDetails()) {
			MaintenanceDetailEntity en = new MaintenanceDetailEntity();
			en.setMaintenanceId(entity.getId());
			en.setTireposition(vo.getTirePosition());
			en.setServicePriceDesc(vo.getServiceDesc());
			en.setServicePriceId(vo.getServiceId());
			en.setServicePriceName(vo.getServiceName());
			en.setNum(vo.getNum());
			en.setStartTime(vo.getStartTime());
			en.setEndTime(vo.getEndTime());
			maintenanceDetailMapper.insertMaintenanceDetail(en, uid);
		}
		
		return entity.getId();
	}

	@Override
	@Transactional( rollbackFor=Exception.class )
	public int updateMaintenance(UpdateMaintenanceRequest request, int uid) {
		MaintenanceEntity entity = new MaintenanceEntity();
		entity.setId(request.getId());
		entity.setInspectionId(request.getInspectionId());
		entity.setPlate(request.getPlate());
		entity.setDriverPhone(request.getDriverPhone());
		UserEntity userEntity = userMapper.getById(uid);
		entity.setOperatorName(userEntity.getName());
		
		int rows = maintenanceMapper.updateMaintenance(entity, uid);
		
		for (MaintenanceDetailVo vo : request.getDetails()) {
			MaintenanceDetailEntity en = new MaintenanceDetailEntity();
			en.setMaintenanceId(entity.getId());
			en.setTireposition(vo.getTirePosition());
			en.setServicePriceDesc(vo.getServiceDesc());
			en.setServicePriceId(vo.getServiceId());
			en.setServicePriceName(vo.getServiceName());
			en.setNum(vo.getNum());
			en.setStartTime(vo.getStartTime());
			en.setEndTime(vo.getEndTime());

			if (vo.getId() != 0) {
				en.setId(vo.getId());
				maintenanceDetailMapper.updateMaintenanceDetail(en, uid);
			} else {
				maintenanceDetailMapper.insertMaintenanceDetail(en, uid);
			}
		}
		
		return rows;
	}

}
