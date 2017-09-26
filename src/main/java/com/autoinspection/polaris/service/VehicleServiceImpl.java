package com.autoinspection.polaris.service;

import com.autoinspection.polaris.model.entity.InspectionDetailEntity;
import com.autoinspection.polaris.model.entity.InspectionEntity;
import com.autoinspection.polaris.model.entity.MaintenanceDetailEntity;
import com.autoinspection.polaris.model.entity.MaintenanceEntity;
import com.autoinspection.polaris.model.entity.ParametersEntity;
import com.autoinspection.polaris.model.entity.TyreInstockEntity;
import com.autoinspection.polaris.model.mapper.InspectionDetailMapper;
import com.autoinspection.polaris.model.mapper.InspectionMapper;
import com.autoinspection.polaris.model.mapper.MaintenanceDetailMapper;
import com.autoinspection.polaris.model.mapper.MaintenanceMapper;
import com.autoinspection.polaris.model.mapper.ParametersMapper;
import com.autoinspection.polaris.model.mapper.TyreInStockMapper;
import com.autoinspection.polaris.utils.Const;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.autoinspection.polaris.model.entity.VehicleInfoEntity;
import com.autoinspection.polaris.model.entity.VehicleTireEntity;
import com.autoinspection.polaris.model.entity.VehicleTypeEntity;
import com.autoinspection.polaris.model.mapper.VehicleInfoMapper;
import com.autoinspection.polaris.model.mapper.VehicleTireMapper;
import com.autoinspection.polaris.model.mapper.VehicleTypeMapper;
import com.autoinspection.polaris.utils.BizException;
import com.autoinspection.polaris.utils.ErrorCode;
import com.autoinspection.polaris.vo.vehicle.VehicleTireVo;
import com.autoinspection.polaris.vo.vehicle.VehicleVo;

@Service
public class VehicleServiceImpl implements VehicleService {
	
	@Autowired
	private VehicleInfoMapper vehicleMapper;
	
	@Autowired
	private VehicleTireMapper vehicleTireMapper;
	
	@Autowired
	private VehicleTypeMapper vehicleTypeMapper;

	@Autowired
	private InspectionMapper inspectionMapper;

	@Autowired
	private InspectionDetailMapper inspectionDetailMapper;

	@Autowired
	private MaintenanceMapper maintenanceMapper;

	@Autowired
	private MaintenanceDetailMapper maintenanceDetailMapper;
	
	@Autowired
	private ParametersMapper parametersMapper;

	@Autowired
	private ParametersService parametersService;
	
	@Autowired
	private TyreInStockMapper  tyreInStockMapper;

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
			vo.setRegDate(entity.getRegDate());
			vo.setRegTime(entity.getRegTime());
			vo.setRegStatus(entity.isRegStatus());
			if (entity.getStationId() != null) {
				vo.setStationId(entity.getStationId());
			}			
			List<VehicleTireVo> tireVos = new ArrayList<VehicleTireVo>();
			List<VehicleTireEntity> tires = vehicleTireMapper.listTires(entity.getId());
			if (tires != null && tires.size() > 0) {
				for (VehicleTireEntity tire : tires) {
					VehicleTireVo tireVo = new VehicleTireVo();
					tireVo.setId(tire.getId());
					tireVo.setTireBrand(parametersService.decode(tire.getTireBrand(), Const.TYRE_BRAND_TYPE));
					tireVo.setTireId(tire.getTireId());
					tireVo.setTirePosition(tire.getTirePosition());
					tireVo.setVehicleId(tire.getVehicleId());
					tireVos.add(tireVo);
				}
			}
			vo.setTires(tireVos);
			list.add(vo);
		}
		return list;
	}



	@Override
	@Transactional( rollbackFor={Exception.class,RuntimeException.class} )
	public int insertVehicle(VehicleVo vo, int uid) throws BizException {
		VehicleInfoEntity en = vehicleMapper.getByPlate(vo.getPlate());
		if (en != null && en.getId() != 0) {
			throw new BizException(ErrorCode.EXISTING_PLATE);
		}
		
		VehicleTypeEntity type = vehicleTypeMapper.getByCode(vo.getVehicleModel(),String.valueOf(vo.getVehicleType()));
		
//		if (type.getTireNum() > vo.getTires().size()) {
//			throw new BizException(ErrorCode.VEHICLETYPE_TIRES_DO_NOT_MATCH);
//		}
		
		VehicleInfoEntity entity = new VehicleInfoEntity();
		entity.setPlate(vo.getPlate());
		entity.setCustomerName(vo.getCustomerName());
		entity.setVehicleType(vo.getVehicleType());
		entity.setVehicleModel(vo.getVehicleModel());
		entity.setBizType(vo.getBizType());
		entity.setInitialDistance(vo.getInitialDistance());
		entity.setLine(vo.getLine());
		
		vehicleMapper.insertVehicle(entity, uid);

		InspectionEntity inspectionEntity = new InspectionEntity();
		inspectionEntity.setPlate(vo.getPlate());
		inspectionEntity.setOperatorName("admin");
		inspectionMapper.insertInspection(inspectionEntity, uid);
//
//		MaintenanceEntity maintenanceEntity = new MaintenanceEntity();
//		maintenanceEntity.setInspectionId(inspectionEntity.getId());
//		maintenanceEntity.setPlate(vo.getPlate());
//		maintenanceEntity.setOperatorName("admin");
//		maintenanceMapper.insertMaintenance(maintenanceEntity, uid);

		for (VehicleTireVo tireVo : vo.getTires()) {
			VehicleTireEntity tireEntity = new VehicleTireEntity();
			tireEntity.setVehicleId(entity.getId());
			tireEntity.setTireBrand(tireVo.getTireBrand());
			tireEntity.setTireId(tireVo.getTireId());
			tireEntity.setTirePosition(tireVo.getTirePosition());
			tireEntity.setTireType(tireVo.getTireType());
			tireEntity.setFigure(tireVo.getFigure());
			vehicleTireMapper.insertVehicleTire(tireEntity, uid);
			Calendar now = Calendar.getInstance();
			now.setTime(new Date());
			List<ParametersEntity> pressures = parametersMapper.getParametersByTypeAndKey(tireVo.getTireBrand(), String.valueOf(now.get(Calendar.MONTH)+1));
			
			InspectionDetailEntity inspectionDetailEntity = new InspectionDetailEntity();
			inspectionDetailEntity.setInspectionId(inspectionEntity.getId());
			inspectionDetailEntity.setTireBrand(tireVo.getTireBrand());
			inspectionDetailEntity.setTireId(tireVo.getTireId());
			inspectionDetailEntity.setTirePosition(tireVo.getTirePosition());
			inspectionDetailEntity.setTireType(tireVo.getTireType());
			inspectionDetailEntity.setStripe(tireVo.getFigure());
			if (pressures != null && pressures.size() > 0) {
				float p = Float.parseFloat(pressures.get(0).getKeyValues());
				inspectionDetailEntity.setPressure(p);
			}
			inspectionDetailMapper.insertInspectionDetail(inspectionDetailEntity, uid);
//
//			MaintenanceDetailEntity maintenanceDetailEntity = new MaintenanceDetailEntity();
//			maintenanceDetailEntity.setMaintenanceId(maintenanceEntity.getId());
//			maintenanceDetailEntity.setTireposition(tireVo.getTirePosition());
//			maintenanceDetailEntity.setServicePriceId(8);
//			maintenanceDetailEntity.setServicePriceName("租赁服务");
//			maintenanceDetailEntity.setServicePriceDesc("租赁服务");
//			maintenanceDetailEntity.setNum(1);
//			maintenanceDetailMapper.insertMaintenanceDetail(maintenanceDetailEntity, uid);
			//更新库存状态
			TyreInstockEntity entityParam = new TyreInstockEntity();
			entityParam.setBarCode(tireVo.getTireId());
			entityParam.setTyreBrand(tireVo.getTireBrand());
			entityParam.setTyreType(tireVo.getTireType());
			
			int i = tyreInStockMapper.updateStockStatus(entityParam, uid);
			//更新库存状态

		}


		return entity.getId();
	}
	
	public static void main(String[] args) {
		Calendar now = Calendar.getInstance();
		now.setTime(new Date());
		System.out.println(now.get(Calendar.MONTH));
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
		vo.setVehicleModel(en.getVehicleModel());
		
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
	public Long countVehicles(String str) {
		return vehicleMapper.countVehicles(str);
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
			vo.setVehicleModel(en.getVehicleModel());
		}
		return vo;
	}

	@Override
	public boolean checkTireExists(String tId) {
		VehicleTireEntity en = vehicleTireMapper.getByTireId(tId);
		if (en != null && en.getId() != 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<VehicleTypeEntity> getTypes() {
		return vehicleTypeMapper.listVehicleTypes();
	}

	@Override
	public List<VehicleVo> listVehiclesExcludes(List<String> excludes, int skip, int pageSize) {
		List<VehicleVo> list = new ArrayList<VehicleVo>();
		
		List<VehicleInfoEntity> infos = vehicleMapper.listVehiclesExclude(excludes, skip, pageSize);
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
	public List<VehicleVo> searchExcludes(int skip, int pageSize, String str, List<String> excludes) {
		List<VehicleVo> list = new ArrayList<VehicleVo>();
		
		List<VehicleInfoEntity> infos = vehicleMapper.searchExcludes(skip, pageSize, str, excludes);
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
	public int getgetCount() {
		return this.vehicleMapper.getCount();
	}



	@Override
	public VehicleVo getVehicleInfoDetailByPlate(String plate) {
		
		VehicleInfoEntity entity = this.vehicleMapper.getByPlate(plate);
		VehicleVo vo = new VehicleVo();
		vo.setCustomerName(entity.getCustomerName());
		vo.setId(entity.getId());
		vo.setPlate(entity.getPlate());
		vo.setVehicleType(entity.getVehicleType());
		
		List<VehicleTireEntity> tires = vehicleTireMapper.listTires(entity.getId());
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
}
