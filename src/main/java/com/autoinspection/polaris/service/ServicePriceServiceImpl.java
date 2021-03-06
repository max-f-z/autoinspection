package com.autoinspection.polaris.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autoinspection.polaris.model.entity.ServicePriceDisplayEntity;
import com.autoinspection.polaris.model.entity.ServicePriceEntity;
import com.autoinspection.polaris.model.mapper.ServicePriceMapper;
import com.autoinspection.polaris.utils.BizException;
import com.autoinspection.polaris.utils.ErrorCode;
import com.autoinspection.polaris.vo.serviceprice.AddServicePriceRequest;
import com.autoinspection.polaris.vo.serviceprice.DeleteServicePriceRequest;
import com.autoinspection.polaris.vo.serviceprice.UpdateServicePriceRequest;
import com.mysql.jdbc.StringUtils;

@Service
public class ServicePriceServiceImpl implements ServicePriceService {

	@Autowired
	private ServicePriceMapper servicePriceMapper;
	
	@Override
	public List<ServicePriceDisplayEntity> listServicePrices() {
		return servicePriceMapper.listAllServicePrices();
	}

	@Override
	public ServicePriceDisplayEntity getServicePriceById(int id) {
		return servicePriceMapper.getById(id);
	}

	@Override
	public int addServicePrice(AddServicePriceRequest request, int uid) throws BizException {
		
		ServicePriceDisplayEntity en = servicePriceMapper.getByServiceIdAndcustomerId(request.getServiceId(), request.getCustomerId());
		if (en != null) {
			throw new BizException(ErrorCode.ALREADY_EXISTS_PLEASE_UPDATE);
		}
		
		ServicePriceEntity entity = new ServicePriceEntity();
		entity.setCustomerId(request.getCustomerId());
		entity.setServiceId(request.getServiceId());
		entity.setPrice(request.getPrice());
		entity.setPriceDesc(request.getPriceDesc());
		entity.setOperatorId(uid);
		entity.setEnable(true);
		
		servicePriceMapper.insertServicePrice(entity);
		
		return entity.getId();
	}

	@Override
	public int updateServicePrice(UpdateServicePriceRequest request, int uid) {
		ServicePriceEntity entity = new ServicePriceEntity();
		entity.setCustomerId(request.getCustomerId());
		entity.setServiceId(request.getServiceId());
		entity.setPrice(request.getPrice());
		entity.setPriceDesc(request.getPriceDesc());
		entity.setOperatorId(uid);
		entity.setId(request.getId());
		int rows = servicePriceMapper.updateServicePrice(entity);
		
		return rows;
	}

	@Override
	public int deleteServicePrice(DeleteServicePriceRequest request, int uid) {
		int rows = servicePriceMapper.deleteServicePrice(request.getId(), uid);
		return rows;
	}

	@Override
	public List<ServicePriceDisplayEntity> search(String level1,String search) {
		List<ServicePriceDisplayEntity> list = servicePriceMapper.search(level1,search);
		return list;
	}
	
}
