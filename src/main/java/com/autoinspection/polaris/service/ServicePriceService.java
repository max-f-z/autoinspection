package com.autoinspection.polaris.service;

import java.util.List;

import com.autoinspection.polaris.model.entity.ServicePriceEntity;

public interface ServicePriceService {
	public List<ServicePriceEntity> listAllServicePrices();
	public ServicePriceEntity getServicePriceById(int id);
}
