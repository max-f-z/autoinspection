package com.autoinspection.polaris.service;

import java.util.List;

import com.autoinspection.polaris.model.entity.ServicePriceDisplayEntity;
import com.autoinspection.polaris.vo.serviceprice.AddServicePriceRequest;
import com.autoinspection.polaris.vo.serviceprice.DeleteServicePriceRequest;
import com.autoinspection.polaris.vo.serviceprice.UpdateServicePriceRequest;

public interface ServicePriceService {
	public List<ServicePriceDisplayEntity> listServicePrices();
	public ServicePriceDisplayEntity getServicePriceById(int id);
	public int addServicePrice(AddServicePriceRequest request, int uid);
	public int updateServicePrice(UpdateServicePriceRequest request, int uid);
	public int deleteServicePrice(DeleteServicePriceRequest request, int uid);
	public List<ServicePriceDisplayEntity> search(String search);
}
