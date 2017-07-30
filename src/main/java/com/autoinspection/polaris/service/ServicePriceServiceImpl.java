package com.autoinspection.polaris.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.autoinspection.polaris.model.entity.ServicePriceDisplayEntity;
import com.autoinspection.polaris.model.entity.ServicePriceEntity;
import com.autoinspection.polaris.model.mapper.ServicePriceMapper;

public class ServicePriceServiceImpl implements ServicePriceService {

	@Autowired
	private ServicePriceMapper servicePriceMapper;
	
	@Override
	public List<ServicePriceDisplayEntity> listServicePrices(int cid) {
		return servicePriceMapper.listServicePrices(cid);
	}

	@Override
	public ServicePriceEntity getServicePriceById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
