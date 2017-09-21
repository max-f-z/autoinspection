package com.autoinspection.polaris.service;

import java.util.List;

import com.autoinspection.polaris.model.entity.OrderPayEntity;
import com.autoinspection.polaris.vo.payment.PaymentSearchRequest;

public interface PaymentService {
	public List<OrderPayEntity> searchOrder(PaymentSearchRequest request);
}
