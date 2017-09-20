package com.autoinspection.polaris.service;

import java.util.List;

import com.autoinspection.polaris.model.entity.VehicleTypeEntity;
import com.autoinspection.polaris.utils.BizException;
import com.autoinspection.polaris.vo.vehicle.VehicleVo;

public interface VehicleService {
	public List<VehicleVo> listVehicles(int skip, int pageSize);
	public List<VehicleTypeEntity> getTypes();
	public VehicleVo getDetail(int vid);

	Long countVehicles(String str);

	public VehicleVo getDetailByPlate(String plate);
	public int insertVehicle(VehicleVo vo, int uid) throws BizException;
	public int updateVehicle(VehicleVo vo, int uid);
	public int deleteVehicle(int vid);
	public boolean checkTireExists(String tId);
	public List<VehicleVo> search(int skip, int pageSize, String str);
	public List<VehicleVo> listVehiclesExcludes(List<String> excludes, int skip, int pageSize);
	public List<VehicleVo> searchExcludes(int skip, int pageSize, String str, List<String> excludes);
	public int getgetCount();
	public VehicleVo getVehicleInfoDetailByPlate(String plate);
}
