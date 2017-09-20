package com.autoinspection.polaris.service;

import java.util.List;

import com.autoinspection.polaris.model.entity.VehicleTypeEntity;

public interface VehicleTypeService {
	VehicleTypeEntity getById(int id);
	List<VehicleTypeEntity> listVehicleTypes();
	VehicleTypeEntity getByCode(String code, String type);
}
