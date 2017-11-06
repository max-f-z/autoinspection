package com.autoinspection.polaris.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.autoinspection.polaris.model.entity.CustomerEntity;
import com.autoinspection.polaris.model.entity.MaintenanceDetailEntity;
import com.autoinspection.polaris.model.entity.MaintenanceEntity;
import com.autoinspection.polaris.model.entity.OrderEntity;
import com.autoinspection.polaris.model.entity.ServicePriceDisplayEntity;
import com.autoinspection.polaris.model.entity.UserEntity;
import com.autoinspection.polaris.model.entity.VehicleInfoEntity;
import com.autoinspection.polaris.model.mapper.CustomerMapper;
import com.autoinspection.polaris.model.mapper.MaintenanceDetailMapper;
import com.autoinspection.polaris.model.mapper.MaintenanceMapper;
import com.autoinspection.polaris.model.mapper.OrderMapper;
import com.autoinspection.polaris.model.mapper.ServicePriceMapper;
import com.autoinspection.polaris.model.mapper.UserMapper;
import com.autoinspection.polaris.model.mapper.VehicleInfoMapper;
import com.autoinspection.polaris.utils.BizException;
import com.autoinspection.polaris.utils.ErrorCode;
import com.autoinspection.polaris.utils.RandomUtil;
import com.autoinspection.polaris.vo.Inspection.AddMaintenanceRequest;
import com.autoinspection.polaris.vo.Inspection.MaintenanceDetailVo;
import com.autoinspection.polaris.vo.Inspection.MaintenanceVo;
import com.autoinspection.polaris.vo.Inspection.UpdateMaintenanceRequest;
import com.mysql.jdbc.StringUtils;

@Service
public class MaintenanceServiceImpl implements MaintenanceService {
	
	@Autowired
	private MaintenanceMapper maintenanceMapper;
	
	@Autowired
	private ServicePriceMapper servicePriceMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private VehicleInfoMapper vehicleMapper;
	
	@Autowired
	private MaintenanceDetailMapper maintenanceDetailMapper;
	
	
	@Autowired
	private OrderMapper orderMapper;
	
	
	private static final int ORDER_START = 0;
	
	private static final String IS_VALID = "T";
	
	private static final String ZERO ="0.00";
	

	@Autowired
	private CustomerMapper cMapper;
	

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
			vo.setPayStatus(entity.getPayStatus());
			vo.setAmount(0);
			
			VehicleInfoEntity ven = vehicleMapper.getByPlate(entity.getPlate());
			if (ven == null || StringUtils.isNullOrEmpty(ven.getCustomerName())) {
				return vo;
			}
			CustomerEntity c = cMapper.getByCode(ven.getCustomerName());
			String customerCode = "";
			if (c != null && !"SH".equals(c.getCode())) {
				customerCode = ven.getCustomerName();
			} else {
				customerCode = "SH";
			}
			
			
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
				
				double price;
				ServicePriceDisplayEntity spde = servicePriceMapper.getByServiceIdAndCustomerCode(d.getServicePriceId(), customerCode);
				if (spde == null) {
//					throw new BizException(ErrorCode.NO_SERVICE_PRICE);
					price = 0;
				} else {
					price = spde.getPrice();
				}
				vo.setAmount(vo.getAmount() + d.getNum() * price);
			}
			vo.setAmount(Math.round(vo.getAmount() * 100.0) / 100.0);
			
			vo.setDetails(ds);
		}
		
		return vo;
	}

	@Override
	@Transactional( rollbackFor=Exception.class )
	public long insertMaintenance(AddMaintenanceRequest request, int uid) throws BizException {
		MaintenanceEntity entity = new MaintenanceEntity();
		
		MaintenanceEntity exists = maintenanceMapper.getByInspectionId(request.getInspectionId());
		if (exists != null && exists.getId() != 0) {
			throw new BizException(ErrorCode.INSPECTIONID_EXISTS);
		}
		UserEntity userEntity = userMapper.getById(uid);
		entity.setOperatorName(userEntity.getName());
		entity.setInspectionId(request.getInspectionId());
		entity.setPlate(request.getPlate());
		entity.setDriverPhone(request.getDriverPhone());
		
		maintenanceMapper.insertMaintenance(entity, uid);
		BigDecimal orderTotalAmount = new BigDecimal(ZERO);
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
		
		VehicleInfoEntity en = vehicleMapper.getByPlate(request.getPlate());
		en.setRegStatus(false);
		en.setStationId(null);
		en.setRegDate(null);
		en.setRegTime(null);
		vehicleMapper.updateRegStatusById(en, uid);
		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setCreateDate(new Date());
		orderEntity.setOrderNo(RandomUtil.getOrderNo());
		orderEntity.setTotalAmount(orderTotalAmount.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		orderEntity.setStatus(ORDER_START);
		orderEntity.setIsValid(IS_VALID);
		orderEntity.setCustomerName(request.getPlate());
		orderEntity.setPlate(request.getPlate());
		orderEntity.setLeftAmount(ZERO);
		orderEntity.setOperatorName(userEntity.getName());
		orderEntity.setPayAmount(ZERO);
		orderEntity.setMaintenanceId(en.getId());
		orderMapper.insertOrder(orderEntity);
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
