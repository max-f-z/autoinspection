package com.autoinspection.polaris.model.mapper;

import com.autoinspection.polaris.model.entity.VehicleMileageEntity;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface VehicleMileageMapper {

  public int insertVehicleMileage(VehicleMileageEntity entity);

  public List<VehicleMileageEntity> getVehicleMileage(Map<String, Object> parameters);

  public Long countVehicleMileage(Map<String, Object> parameters);


}
