package com.autoinspection.polaris.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.autoinspection.polaris.model.entity.ServicePriceEntity;

@Mapper
public interface ServicePriceMapper {
	ServicePriceEntity getById(@Param("id") Integer id);
	List<ServicePriceEntity> listAllServicePrices();
	int insertServicePrice(ServicePriceEntity entity);
	int updateServicePrice(ServicePriceEntity entity);
	int deleteServicePrice(@Param("id") Integer id, @Param("operatorId") Integer operatorId);
}
