package com.autoinspection.polaris.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.autoinspection.polaris.model.entity.ServiceEntity;

@Mapper
public interface ServiceMapper {
	ServiceEntity getById(@Param("id") Integer id);
	List<ServiceEntity> listAllServices();
	int insertService(ServiceEntity entity);
	int updateService(ServiceEntity entity);
	int deleteService(@Param("id") Integer id, @Param("operatorId") Integer operatorId);
	List<ServiceEntity> search(@Param("level1") String level1,@Param("search") String search);
}
