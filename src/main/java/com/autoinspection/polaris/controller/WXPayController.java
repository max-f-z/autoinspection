package com.autoinspection.polaris.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.autoinspection.polaris.model.entity.OrderPayEntity;
import com.autoinspection.polaris.service.PaymentService;
import com.autoinspection.polaris.utils.BizException;
import com.autoinspection.polaris.utils.ErrorCode;
import com.autoinspection.polaris.vo.Result;
import com.autoinspection.polaris.vo.payment.UnifiedOrderReq;

@RestController
@RequestMapping(path = "/v1/pay")
@Transactional
public class WXPayController {
	
	@Autowired
	private PaymentService paymentService;
	
	@RequestMapping(path = "/unifiedOrder", method = RequestMethod.POST)
	public Result<String> unifiedOrder(UnifiedOrderReq req) throws BizException {
		
		OrderPayEntity en = paymentService.getOrder(req.getOrderId());
		if (en == null) {
			throw new BizException(ErrorCode.CANNOT_FIND_ORDER);
		}
		if (en.getPayStatus() != 0) {
			throw new BizException(ErrorCode.INVALID_PAYSTATUS);
		}
		
		
		
		return new Result<>("");
	}
}
