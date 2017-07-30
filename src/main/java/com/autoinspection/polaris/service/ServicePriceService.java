package com.autoinspection.polaris.service;

import java.util.List;

import com.autoinspection.polaris.model.entity.ServicePriceDisplayEntity;
import com.autoinspection.polaris.model.entity.ServicePriceEntity;

public interface ServicePriceService {
	public List<ServicePriceDisplayEntity> listServicePrices(int cid);
	public ServicePriceEntity getServicePriceById(int id);
}
