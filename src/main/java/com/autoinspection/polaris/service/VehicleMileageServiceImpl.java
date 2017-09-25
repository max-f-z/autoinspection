package com.autoinspection.polaris.service;

import com.autoinspection.polaris.model.entity.VehicleInfoEntity;
import com.autoinspection.polaris.model.entity.VehicleMileageEntity;
import com.autoinspection.polaris.model.mapper.VehicleInfoMapper;
import com.autoinspection.polaris.model.mapper.VehicleMileageMapper;
import com.autoinspection.polaris.vo.vehicle.VehicleMileageVo;

import java.util.Date;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleMileageServiceImpl implements VehicleMileageService{

  @Autowired
  private VehicleMileageMapper mapper;
  
  @Autowired
  private VehicleInfoMapper vMapper;

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
      Date now = new Date();
      
      Calendar calendar1= Calendar.getInstance();
      Calendar calendar2= Calendar.getInstance();
      calendar1.setTime(now);
      calendar2.setTime(voi.getMonth());
      if (calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR) && calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH)) {
    	  VehicleInfoEntity ven = vMapper.getByPlate(e.getPlate());
    	  double v1 = Double.parseDouble(e.getEndMile());
    	  double v2 = Double.parseDouble(ven.getInitialDistance());
    	  e.setMile(new BigDecimal(v1 - v2));
      }

      resultList.add(voi);
    }
    return resultList;
  }

  @Override
  public Long countVehicleMileage(Map<String, Object> parameters) {
    return mapper.countVehicleMileage(parameters);
  }
}
