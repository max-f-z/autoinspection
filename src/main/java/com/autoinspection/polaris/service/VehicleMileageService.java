package com.autoinspection.polaris.service;

import com.autoinspection.polaris.vo.vehicle.VehicleMileageVo;
import java.util.List;
import java.util.Map;

public interface VehicleMileageService {
  public int addVehicleMileage(VehicleMileageVo entity);

  public List<VehicleMileageVo> getVehicleMileage(Map<String, Object> vo);

  Long countVehicleMileage(Map<String, Object> parameters);
}
