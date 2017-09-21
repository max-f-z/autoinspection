package com.autoinspection.polaris.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.autoinspection.polaris.interceptor.Permission;
import com.autoinspection.polaris.interceptor.PermissionEnum;
import com.autoinspection.polaris.model.entity.OrderPayEntity;
import com.autoinspection.polaris.service.PaymentService;
import com.autoinspection.polaris.vo.payment.PaymentSearchRequest;

@RestController
@RequestMapping(path = "${api.path}/payment")
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	@RequestMapping(path = "/search", method = RequestMethod.POST)
	@Permission( permissionTypes = { PermissionEnum.ADMIN })
	public List<OrderPayEntity> search(@RequestBody PaymentSearchRequest request) {
		return paymentService.searchOrder(request);
	}
}
