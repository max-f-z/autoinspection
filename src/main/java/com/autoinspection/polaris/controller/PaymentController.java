package com.autoinspection.polaris.controller;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.autoinspection.polaris.interceptor.Permission;
import com.autoinspection.polaris.interceptor.PermissionEnum;
import com.autoinspection.polaris.resolver.CurrentUser;
import com.autoinspection.polaris.service.PaymentService;
import com.autoinspection.polaris.utils.BizException;
import com.autoinspection.polaris.vo.Result;
import com.autoinspection.polaris.vo.UserVo;
import com.autoinspection.polaris.vo.payment.PaymentSearchRequest;
import com.autoinspection.polaris.vo.payment.PaymentSearchResp;
import com.autoinspection.polaris.vo.payment.PaymentUpdateReq;

@RestController
@RequestMapping(path = "${api.path}/payment")
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	@RequestMapping(path = "/search", method = RequestMethod.POST)
	@Permission( permissionTypes = { PermissionEnum.ADMIN })
	public PaymentSearchResp search(@RequestBody PaymentSearchRequest request) throws BizException {
		return paymentService.searchOrder(request);
	}
	
	@RequestMapping(path = "/update", method = RequestMethod.POST)
	@Permission( permissionTypes = { PermissionEnum.ADMIN })
	public Result<String> updateStatus(@RequestBody PaymentUpdateReq request, @CurrentUser UserVo user) throws BizException {
		paymentService.updateStatus(request, user.getUid());
		return new Result<>("");
	}
	
	@RequestMapping(path = "/export", method = RequestMethod.POST)
	@Permission( permissionTypes = { PermissionEnum.ADMIN })
	public Result<String> export(@RequestBody PaymentSearchRequest request) throws BizException {
		PaymentSearchResp resp = paymentService.searchOrder(request);
		
		HSSFWorkbook wb = new HSSFWorkbook();//建立新HSSFWorkbook对象
		HSSFSheet sheet = wb.createSheet("sheet1");//建立新的sheet对象
		HSSFRow row = sheet.createRow((short)0);//建立新行

		
		return new Result<>("");
	}
}
