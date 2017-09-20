package com.autoinspection.polaris.service;

import java.util.List;


import com.autoinspection.polaris.model.entity.ParametersEntity;

public interface ParametersService {
	
	List<ParametersEntity> getParametersByType( String paraType);

  String decode(String id, String type);
  String encode(String name, String type);
}
