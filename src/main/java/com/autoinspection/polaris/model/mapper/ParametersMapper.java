package com.autoinspection.polaris.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.autoinspection.polaris.model.entity.ParametersEntity;



@Mapper
public interface ParametersMapper {
	
	
	List<ParametersEntity> getParametersByType(@Param("paraType") String paraType);

}
