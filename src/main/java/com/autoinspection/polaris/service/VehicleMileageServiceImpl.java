package com.autoinspection.polaris.service;

import com.autoinspection.polaris.model.entity.VehicleMileageEntity;
import com.autoinspection.polaris.model.mapper.VehicleMileageMapper;
import com.autoinspection.polaris.vo.vehicle.VehicleMileageVo;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleMileageServiceImpl implements VehicleMileageService{

  @Autowired
  private VehicleMileageMapper mapper;

  @Override
  public int addVehicleMileage(VehicleMileageVo vo) {
    VehicleMileageEntity entity = new VehicleMileageEntity();
    BeanUtils.copyProperties(vo, entity);
    return mapper.insertVehicleMileage(entity);
  }

  @Override
  public List<VehicleMileageVo> getVehicleMileage(Map<String, Object> parameters) {
    List<VehicleMileageEntity> entityList = mapper.getVehicleMileage(parameters);
    List<VehicleMileageVo> resultList = new ArrayList<>();
    for(VehicleMileageEntity e : entityList) {
      VehicleMileageVo voi = new VehicleMileageVo();
      BeanUtils.copyProperties(e, voi);
      resultList.add(voi);
    }
    return resultList;
  }

  @Override
  public Long countVehicleMileage(Map<String, Object> parameters) {
    return mapper.countVehicleMileage(parameters);
  }
}
