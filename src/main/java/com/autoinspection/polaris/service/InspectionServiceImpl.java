package com.autoinspection.polaris.service;

import com.autoinspection.polaris.utils.Const;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.autoinspection.polaris.model.entity.InspectionDetailEntity;
import com.autoinspection.polaris.model.entity.InspectionEntity;
import com.autoinspection.polaris.model.entity.TyreInstockEntity;
import com.autoinspection.polaris.model.entity.UserEntity;
import com.autoinspection.polaris.model.entity.VehicleInfoEntity;
import com.autoinspection.polaris.model.mapper.InspectionDetailMapper;
import com.autoinspection.polaris.model.mapper.InspectionMapper;
import com.autoinspection.polaris.model.mapper.TyreInStockMapper;
import com.autoinspection.polaris.model.mapper.UserMapper;
import com.autoinspection.polaris.model.mapper.VehicleInfoMapper;
import com.autoinspection.polaris.utils.BizException;
import com.autoinspection.polaris.utils.ErrorCode;
import com.autoinspection.polaris.vo.Inspection.AddInspectionRequest;
import com.autoinspection.polaris.vo.Inspection.DeleteInspectionRequest;
import com.autoinspection.polaris.vo.Inspection.InspectionDetailVo;
import com.autoinspection.polaris.vo.Inspection.InspectionVo;
import com.autoinspection.polaris.vo.Inspection.UpdateInspectionRequest;

@Service
public class InspectionServiceImpl implements InspectionService {
	
	@Autowired
	private InspectionMapper inspectionMapper;
	
	@Autowired
	private InspectionDetailMapper inspectionDetailMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private VehicleInfoMapper vehicleMapper;

	@Autowired
	private ParametersService parametersService;
	
	@Autowired
	private TyreInStockMapper  tyreInStockMapper;

	@Override
	public List<InspectionVo> listInspections(int skip, int pageSize, String plate) {
		List<InspectionVo> vos = new ArrayList<InspectionVo>();
		
		List<InspectionEntity> entities= inspectionMapper.listInspections(skip, pageSize, plate);
		for (InspectionEntity entity : entities) {
			InspectionVo vo = new InspectionVo();
			vo.setId(entity.getId());
			vo.setMilometer(entity.getMilometer());
			vo.setOperatorName(entity.getOperatorName());
			vo.setServiceMile(entity.getServiceMile());
			vo.setCreateTime(entity.getCreateTime());
			vo.setPlate(entity.getPlate());
			vo.setMaintained(entity.getMaintained());
			VehicleInfoEntity ven = vehicleMapper.getByPlate(entity.getPlate());
			if (ven != null) {
				vo.setBizType(ven.getBizType());
			}
			
			List<InspectionDetailEntity> list = inspectionDetailMapper.listDetails(entity.getId());
			List<InspectionDetailVo> ds = new ArrayList<InspectionDetailVo>();
			for (InspectionDetailEntity en : list) {
				InspectionDetailVo dvo = new InspectionDetailVo();
				dvo.setId(en.getId());
				dvo.setInspectionId(en.getInspectionId());
				dvo.setTireBrand(parametersService.decode(en.getTireBrand(), Const.TYRE_BRAND_TYPE));
				dvo.setTirePosition(en.getTirePosition());
				dvo.setTireId(en.getTireId());
				dvo.setStripe(en.getStripe());
				dvo.setPressure(en.getPressure());
				dvo.setDepth(en.getDepth());
				dvo.setBrake(en.getBrake());
				ds.add(dvo);
			}
			vo.setDetails(ds);
			vos.add(vo);
		}
		
		return vos;
	}

	@Override
	public InspectionVo getById(long id) throws BizException {
		InspectionEntity entity = inspectionMapper.getById(id);
		if (entity == null) {
			throw new BizException(ErrorCode.NOT_FOUND);
		}
		VehicleInfoEntity ven = vehicleMapper.getByPlate(entity.getPlate());
		if (ven == null) {
			throw new BizException(ErrorCode.NOT_FOUND);
		}
		InspectionVo vo = new InspectionVo();
		vo.setId(entity.getId());
		vo.setMilometer(entity.getMilometer());
		vo.setOperatorName(entity.getOperatorName());
		vo.setServiceMile(entity.getServiceMile());
		vo.setCreateTime(entity.getCreateTime());
		vo.setPlate(entity.getPlate());
		vo.setBizType(ven.getBizType());
		List<InspectionDetailEntity> list = inspectionDetailMapper.listDetails(entity.getId());
		List<InspectionDetailVo> ds = new ArrayList<InspectionDetailVo>();
		for (InspectionDetailEntity en : list) {
			InspectionDetailVo dvo = new InspectionDetailVo();
			dvo.setId(en.getId());
			dvo.setInspectionId(en.getInspectionId());
			dvo.setTireBrand(en.getTireBrand());
			dvo.setTirePosition(en.getTirePosition());
			dvo.setTireId(en.getTireId());
			dvo.setStripe(en.getStripe());
			dvo.setPressure(en.getPressure());
			dvo.setDepth(en.getDepth());
			dvo.setBrake(en.getBrake());
			ds.add(dvo);
		}
		vo.setDetails(ds);
		
		return vo;
	}

	@Transactional( rollbackFor=Exception.class)
	@Override
	public long insertInspection(AddInspectionRequest request, int uid) {
		VehicleInfoEntity ven = vehicleMapper.getByPlate(request.getPlate());
		if (ven == null || ven.getId() == 0) {
			
		}
		
		
		InspectionEntity entity = new InspectionEntity();
		entity.setPlate(request.getPlate());
		entity.setMilometer(request.getMilometer());
		entity.setServiceMile(request.getServiceMile());
		
		UserEntity userEntity = userMapper.getById(uid);
		entity.setOperatorName(userEntity.getName());
		
		inspectionMapper.insertInspection(entity, uid);
		
		for (InspectionDetailVo vo : request.getDetails()) {
			InspectionDetailEntity en = new InspectionDetailEntity();
			en.setInspectionId(entity.getId());
			en.setTirePosition(vo.getTirePosition());
			en.setTireId(vo.getTireId());
			en.setTireBrand(vo.getTireBrand());
			en.setStripe(vo.getStripe());
			en.setPressure(vo.getPressure());
			en.setDepth(vo.getDepth());
			en.setBrake(vo.getBrake());
			inspectionDetailMapper.insertInspectionDetail(en, uid);
			
			TyreInstockEntity entityParam = new TyreInstockEntity();
			entityParam.setBarCode(en.getTireId());
			entityParam.setTyreBrand(en.getTireBrand());
			tyreInStockMapper.updateStockStatus(entityParam, uid);
		}
		return entity.getId();
	}

	@Override
	public int deletInspection(DeleteInspectionRequest request) {
		return inspectionMapper.deleteInspection(request.getId());
	}

	@Override
	public List<InspectionEntity> search(int skip, int pageSize, String search) {
		return inspectionMapper.search(skip, pageSize, search);
	}

	@Override
	public int updateInspection(UpdateInspectionRequest request, int uid) {
		InspectionEntity entity = new InspectionEntity();
		entity.setId(request.getId());
		entity.setPlate(request.getPlate());
		entity.setMilometer(request.getMilometer());
		entity.setServiceMile(request.getServiceMile());
		
		UserEntity userEntity = userMapper.getById(uid);
		entity.setOperatorName(userEntity.getName());
		
		int rows = inspectionMapper.updateInspection(entity, uid);
		
		for (InspectionDetailVo vo : request.getDetails()) {
			InspectionDetailEntity en = new InspectionDetailEntity();
			en.setInspectionId(entity.getId());
			en.setTirePosition(vo.getTirePosition());
			en.setTireId(vo.getTireId());
			en.setTireBrand(vo.getTireBrand());
			en.setStripe(vo.getStripe());
			en.setPressure(vo.getPressure());
			en.setDepth(vo.getDepth());
			en.setBrake(vo.getBrake());
			
			if (vo.getId() != 0) {
				en.setId(vo.getId());
				inspectionDetailMapper.updateInspectionDetail(en, uid);
			} else {
				inspectionDetailMapper.insertInspectionDetail(en, uid);
			}
		}
		return rows;
	}

	@Override
	public List<InspectionDetailEntity> findTyre(InspectionDetailEntity enity) {
		
		return this.inspectionDetailMapper.findTyre(enity);
	}
}
