package com.autoinspection.polaris.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.autoinspection.polaris.model.entity.ServicePriceDisplayEntity;
import com.autoinspection.polaris.model.entity.ServicePriceEntity;

@Mapper
public interface ServicePriceMapper {
	ServicePriceDisplayEntity getById(@Param("id") Integer id);
	List<ServicePriceDisplayEntity> listAllServicePrices();
	int insertServicePrice(ServicePriceEntity entity);
	int updateServicePrice(ServicePriceEntity entity);
	int deleteServicePrice(@Param("id") Integer id, @Param("operatorId") Integer operatorId);
	List<ServicePriceDisplayEntity> search(@Param("search") String search);
}
