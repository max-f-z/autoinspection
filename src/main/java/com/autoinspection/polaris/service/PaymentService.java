package com.autoinspection.polaris.service;

import java.util.List;

import com.autoinspection.polaris.model.entity.OrderPayEntity;
import com.autoinspection.polaris.model.entity.PaymentEntity;
import com.autoinspection.polaris.utils.BizException;
import com.autoinspection.polaris.vo.payment.PaymentSearchRequest;
import com.autoinspection.polaris.vo.payment.PaymentSearchResp;
import com.autoinspection.polaris.vo.payment.PaymentUpdateReq;

public interface PaymentService {
	public PaymentSearchResp searchOrder(PaymentSearchRequest request) throws BizException;
	public void updateStatus(PaymentUpdateReq req, int operatorId) throws BizException;
	public OrderPayEntity getOrder(Long id) throws BizException;
	public List<PaymentEntity> getPaymentsByOrderId(Long id);
	public void updatePaymentStatus(Long id, int status);
	public void insertPayment(PaymentEntity en);
}
