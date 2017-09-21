package com.autoinspection.polaris.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.autoinspection.polaris.interceptor.Permission;
import com.autoinspection.polaris.interceptor.PermissionEnum;
import com.autoinspection.polaris.model.entity.OrderEntity;
import com.autoinspection.polaris.service.OrderService;
import com.autoinspection.polaris.vo.order.GetOrderRequest;

@RestController
@RequestMapping(value="${api.path}/order")
public class OrderController {
	
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value="/orders", method = RequestMethod.GET)
	@Permission( permissionTypes = { PermissionEnum.ADMIN })
	public List<OrderEntity> getOrdersAll() {
		return orderService.listOrdersAll();
	}
	
	@RequestMapping(value="/search", method = RequestMethod.POST)
	@Permission( permissionTypes = { PermissionEnum.ADMIN })
	public List<OrderEntity> getOrdersBySearchKey(@RequestBody GetOrderRequest request) {
		return orderService.listOrdersByKey(request.getStatus(),request.getSearch());
	}

}
