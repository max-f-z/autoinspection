package com.autoinspection.polaris.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autoinspection.polaris.model.entity.OrderPayEntity;
import com.autoinspection.polaris.model.mapper.MaintenanceMapper;
import com.autoinspection.polaris.vo.payment.PaymentSearchRequest;

@Service
public class PaymentServiceImpl implements PaymentService {
	
	@Autowired
	private MaintenanceMapper maintenanceMapper;

	@Override
	public List<OrderPayEntity> searchOrder(PaymentSearchRequest request) {
		return maintenanceMapper.search(request);
	}

}
