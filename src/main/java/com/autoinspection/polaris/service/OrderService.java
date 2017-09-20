package com.autoinspection.polaris.service;

import java.util.List;

import com.autoinspection.polaris.model.entity.OrderEntity;
import com.autoinspection.polaris.vo.Inspection.MaintenanceVo;

public interface OrderService {
	
	public int insertOorder(MaintenanceVo mantenanceVo);
	
	public List<OrderEntity> listOrdersAll();
	
	public List<OrderEntity> listOrdersByKey(int status,String search);
	

}
