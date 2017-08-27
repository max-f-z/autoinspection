package com.autoinspection.polaris.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autoinspection.polaris.model.entity.VehicleTypeEntity;
import com.autoinspection.polaris.model.mapper.VehicleTypeMapper;

@Service
public class VehicleTypeServiceImpl implements VehicleTypeService {

	@Autowired
	private VehicleTypeMapper vehicleTypeMapper;
	
	@Override
	public VehicleTypeEntity getById(int id) {
		return vehicleTypeMapper.getById(id);
	}

}
