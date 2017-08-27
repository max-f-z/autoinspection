package com.autoinspection.polaris.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.autoinspection.polaris.model.entity.VehicleInfoEntity;
import com.autoinspection.polaris.model.entity.VehicleTireEntity;
import com.autoinspection.polaris.model.mapper.VehicleInfoMapper;
import com.autoinspection.polaris.model.mapper.VehicleTireMapper;
import com.autoinspection.polaris.utils.BizException;
import com.autoinspection.polaris.vo.vehicle.VehicleTireVo;
import com.autoinspection.polaris.vo.vehicle.VehicleVo;

@Service
public class VehicleServiceImpl implements VehicleService {
	
	@Autowired
	private VehicleInfoMapper vehicleMapper;
	
	@Autowired
	private VehicleTireMapper vehicleTireMapper;

	@Override
	public List<VehicleVo> listVehicles(int skip, int pageSize) {
		List<VehicleVo> list = new ArrayList<VehicleVo>();
		
		List<VehicleInfoEntity> infos = vehicleMapper.listVehicles(skip, pageSize);
		for (VehicleInfoEntity entity : infos) {
			VehicleVo vo = new VehicleVo();
			vo.setId(entity.getId());
			vo.setCustomerName(entity.getCustomerName());
			vo.setPlate(entity.getPlate());
			vo.setVehicleType(entity.getVehicleType());
			
			List<VehicleTireVo> tireVos = new ArrayList<VehicleTireVo>();
			List<VehicleTireEntity> tires = vehicleTireMapper.listTires(entity.getId());
			for (VehicleTireEntity tire : tires) {
				VehicleTireVo tireVo = new VehicleTireVo();
				tireVo.setId(tire.getId());
				tireVo.setTireBrand(tire.getTireBrand());
				tireVo.setTireId(tire.getTireId());
				tireVo.setTirePosition(tire.getTirePosition());
				tireVo.setVehicleId(tire.getVehicleId());
				tireVos.add(tireVo);
			}
			vo.setTires(tireVos);
			list.add(vo);
		}
		return list;
	}

	@Override
	@Transactional( rollbackFor=Exception.class )
	public int insertVehicle(VehicleVo vo, int uid) throws BizException {
		VehicleInfoEntity entity = new VehicleInfoEntity();
		entity.setPlate(vo.getPlate());
		entity.setCustomerName(vo.getCustomerName());
		entity.setVehicleType(vo.getVehicleType());
		
		vehicleMapper.insertVehicle(entity, uid);
		
		for (VehicleTireVo tireVo : vo.getTires()) {
			VehicleTireEntity tireEntity = new VehicleTireEntity();
			tireEntity.setVehicleId(entity.getId());
			tireEntity.setTireBrand(tireVo.getTireBrand());
			tireEntity.setTireId(tireVo.getTireId());
			tireEntity.setTirePosition(tireVo.getTirePosition());
			vehicleTireMapper.insertVehicleTire(tireEntity, uid);
		}
		return entity.getId();
	}

	@Override
	@Transactional( rollbackFor=Exception.class)
	public int updateVehicle(VehicleVo vo, int uid) {
		VehicleInfoEntity entity = new VehicleInfoEntity();
		entity.setId(vo.getId());
		entity.setPlate(vo.getPlate());
		entity.setCustomerName(vo.getCustomerName());
		entity.setVehicleType(vo.getVehicleType());
		
		int rows = vehicleMapper.updateVehicle(entity, uid);
		
		for (VehicleTireVo tireVo : vo.getTires()) {
			VehicleTireEntity tireEntity = new VehicleTireEntity();
			tireEntity.setVehicleId(entity.getId());
			tireEntity.setTireBrand(tireVo.getTireBrand());
			tireEntity.setTireId(tireVo.getTireId());
			tireEntity.setTirePosition(tireVo.getTirePosition());
			
			if (tireVo.getId() != 0) {
				tireEntity.setId(vo.getId());
				vehicleTireMapper.updateVehicleTire(tireEntity, uid);
			} else {
				vehicleTireMapper.insertVehicleTire(tireEntity, uid);
			}
		}
		return rows;
	}

	@Override
	@Transactional( rollbackFor=Exception.class)
	public int deleteVehicle(int vid) {
		List<VehicleTireEntity> tires = vehicleTireMapper.listTires(vid);
		for (VehicleTireEntity en : tires) {
			vehicleTireMapper.deleteVehicleTire(en.getId());
		}
		
		return vehicleMapper.deleteVehicle(vid);
	}

	@Override
	public VehicleVo getDetail(int vid) {
		VehicleInfoEntity en = vehicleMapper.getById(vid);
		VehicleVo vo = new VehicleVo();
		vo.setCustomerName(en.getCustomerName());
		vo.setId(en.getId());
		vo.setPlate(en.getPlate());
		vo.setVehicleType(en.getVehicleType());
		
		List<VehicleTireEntity> tires = vehicleTireMapper.listTires(vid);
		List<VehicleTireVo> tiresVo = new ArrayList<VehicleTireVo>();
		for (VehicleTireEntity tire : tires) {
			VehicleTireVo tireVo = new VehicleTireVo();
			tireVo.setId(tire.getId());
			tireVo.setTireBrand(tire.getTireBrand());
			tireVo.setTireId(tire.getTireId());
			tireVo.setTirePosition(tire.getTirePosition());
			tireVo.setVehicleId(tire.getVehicleId());
			tiresVo.add(tireVo);
		}
		vo.setTires(tiresVo);
		return vo;
	}

	@Override
	public List<VehicleVo> search(int skip, int pageSize, String str) {
		List<VehicleVo> list = new ArrayList<VehicleVo>();
		
		List<VehicleInfoEntity> infos = vehicleMapper.search(skip, pageSize, str);
		for (VehicleInfoEntity entity : infos) {
			VehicleVo vo = new VehicleVo();
			vo.setId(entity.getId());
			vo.setCustomerName(entity.getCustomerName());
			vo.setPlate(entity.getPlate());
			vo.setVehicleType(entity.getVehicleType());
			
			List<VehicleTireVo> tireVos = new ArrayList<VehicleTireVo>();
			List<VehicleTireEntity> tires = vehicleTireMapper.listTires(entity.getId());
			for (VehicleTireEntity tire : tires) {
				VehicleTireVo tireVo = new VehicleTireVo();
				tireVo.setId(tire.getId());
				tireVo.setTireBrand(tire.getTireBrand());
				tireVo.setTireId(tire.getTireId());
				tireVo.setTirePosition(tire.getTirePosition());
				tireVo.setVehicleId(tire.getVehicleId());
				tireVos.add(tireVo);
			}
			vo.setTires(tireVos);
			list.add(vo);
		}
		return list;
	}

	@Override
	public VehicleVo getDetailByPlate(String plate) {
		VehicleVo vo = new VehicleVo();
		VehicleInfoEntity en = vehicleMapper.getByPlate(plate);
		if (en != null) {
			vo.setId(en.getId());
			vo.setCustomerName(en.getCustomerName());
			vo.setPlate(en.getPlate());
			vo.setVehicleType(en.getVehicleType());
		}
		return vo;
	}
}
