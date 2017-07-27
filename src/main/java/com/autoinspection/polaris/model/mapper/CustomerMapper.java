package com.autoinspection.polaris.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.autoinspection.polaris.model.entity.CustomerEntity;

@Mapper
public interface CustomerMapper {
	CustomerEntity getById(@Param("id") Integer id);
	List<CustomerEntity> listAllCustomers();
	int insertCustomer(CustomerEntity entity);
	int updateCustomer(CustomerEntity entity);
	int deleteCustomer(@Param("id") Integer id, @Param("operatorId") Integer operatorId);
}
