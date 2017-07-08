package com.autoinspection.polaris.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autoinspection.polaris.model.entity.ServiceEntity;
import com.autoinspection.polaris.model.mapper.ServiceMapper;
import com.autoinspection.polaris.vo.service.AddServiceRequest;
import com.autoinspection.polaris.vo.service.UpdateServiceRequest;

@Service
public class ServiceServiceImpl implements ServiceService {

	@Autowired
	private ServiceMapper serviceMapper;
	
	@Override
	public ServiceEntity getStationById(int id) {
		return serviceMapper.getById(id);
	}

	@Override
	public List<ServiceEntity> listAllServices() {
		return serviceMapper.listAllServices();
	}

	@Override
	public int insertService(AddServiceRequest request, int uid) {
		ServiceEntity entity = new ServiceEntity();
		entity.setName(request.getName());
		entity.setDescription(request.getDescription());
		entity.setPrice(request.getPrice());
		entity.setStatus(request.getStatus());
		entity.setEnable(true);
		entity.setOperatorId(uid);
		serviceMapper.insertService(entity);
		
		return entity.getId();
	}

	@Override
	public int updateService(UpdateServiceRequest request, int uid) {
		ServiceEntity entity = new ServiceEntity();
		entity.setId(request.getId());
		entity.setName(request.getName());
		entity.setDescription(request.getDescription());
		entity.setPrice(request.getPrice());
		entity.setStatus(request.getStatus());
		entity.setOperatorId(uid);
		
		return serviceMapper.updateService(entity);
	}

	@Override
	public int deleteService(int id, int uid) {
		return serviceMapper.deleteService(id, uid);
	}
}
