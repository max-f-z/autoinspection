package com.autoinspection.polaris.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autoinspection.polaris.model.entity.MaintenanceDetailEntity;
import com.autoinspection.polaris.model.entity.OrderEntity;
import com.autoinspection.polaris.model.mapper.MaintenanceDetailMapper;
import com.autoinspection.polaris.model.mapper.OrderMapper;
import com.autoinspection.polaris.vo.Inspection.MaintenanceVo;


@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private MaintenanceDetailMapper maintenanceDetailMapper;

	@Override
	public int insertOorder(MaintenanceVo mantenanceVo) {
		return 0;
	}
	
	public List<OrderEntity> listOrdersAll(){
		 List<OrderEntity> list = orderMapper.listAllOrders();
		 for(OrderEntity oe : list){
			 List<MaintenanceDetailEntity> mlist = maintenanceDetailMapper.listDetails(Long.valueOf(oe.getMaintenanceId()));
			 oe.setMaintenanceList(mlist);
		 }
		return list;
	}
	
	
	public List<OrderEntity> listOrdersByKey(int status,String search){
		 List<OrderEntity> list = orderMapper.listOrdersByKey(status,search);
		 for(OrderEntity oe : list){
			 List<MaintenanceDetailEntity> mlist = maintenanceDetailMapper.listDetails(Long.valueOf(oe.getMaintenanceId()));
			 oe.setMaintenanceList(mlist);
		 }
		return list;
	}

}
