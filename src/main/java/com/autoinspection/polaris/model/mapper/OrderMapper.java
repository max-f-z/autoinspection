package com.autoinspection.polaris.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.autoinspection.polaris.model.entity.OrderEntity;


@Mapper
public interface OrderMapper {
	
	 void insertOrder(@Param("order")OrderEntity orderEntity);
	
	 List<OrderEntity> listAllOrders();
	 
	 List<OrderEntity> listOrdersByKey(@Param("status")int status,@Param("search")String search);
	
}

