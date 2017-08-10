package com.autoinspection.polaris.service;

import java.util.List;

import com.autoinspection.polaris.utils.BizException;
import com.autoinspection.polaris.vo.vehicle.VehicleVo;

public interface VehicleService {
	public List<VehicleVo> listVehicles(int skip, int pageSize);
	public VehicleVo getDetail(int vid);
	public int insertVehicle(VehicleVo vo, int uid) throws BizException;
	public int updateVehicle(VehicleVo vo, int uid);
	public int deleteVehicle(int vid);
	public List<VehicleVo> search(int skip, int pageSize, String str);
}
