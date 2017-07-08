package com.autoinspection.polaris.service;

import java.util.List;

import com.autoinspection.polaris.model.entity.ServiceEntity;
import com.autoinspection.polaris.vo.service.AddServiceRequest;
import com.autoinspection.polaris.vo.service.UpdateServiceRequest;

public interface ServiceService {
	public ServiceEntity getStationById(int id);
	public List<ServiceEntity> listAllServices();
	public int insertService(AddServiceRequest request, int uid);
	public int updateService(UpdateServiceRequest request, int uid);
	public int deleteService(int id, int uid);
}
