package com.autoinspection.polaris.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

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
import com.autoinspection.polaris.model.entity.OrderPayEntity;
import com.autoinspection.polaris.resolver.CurrentUser;
import com.autoinspection.polaris.service.PaymentService;
import com.autoinspection.polaris.utils.BizException;
import com.autoinspection.polaris.vo.Result;
import com.autoinspection.polaris.vo.UserVo;
import com.autoinspection.polaris.vo.payment.PaymentDetail;
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
	public void export(@RequestBody PaymentSearchRequest request, HttpServletResponse response) throws BizException, FileNotFoundException {
		PaymentSearchResp resp = paymentService.searchOrder(request);
		
		@SuppressWarnings("resource")
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("sheet1");
		int rowNum = 0;
		
		for (OrderPayEntity en : resp.getItems()) {
			HSSFRow row = sheet.createRow((short)rowNum);
			row.createCell(0).setCellValue("订单编号");
			row.createCell(1).setCellValue(en.getId());
			row.createCell(2).setCellValue("日期");
			row.createCell(3).setCellValue(en.getCreateTime().substring(0, 10));
			row.createCell(4).setCellValue("车牌号");
			row.createCell(5).setCellValue(en.getPlate());
			row.createCell(6).setCellValue("客户类型");
			if (en.isRetail()) {
				row.createCell(7).setCellValue("散户");
			} else {
				row.createCell(7).setCellValue("非散户");
			}
			row.createCell(8).setCellValue("客户名称");
			row.createCell(9).setCellValue(en.getCustomerName());
			row.createCell(10).setCellValue("总金额");
			row.createCell(11).setCellValue(en.getTotal());
			if (en.getPayStatus() == 0) {
				row.createCell(12).setCellValue("未支付");
			} else if (en.getPayStatus() == 1) {
				row.createCell(13).setCellValue("已支付");
			}
			rowNum++;
			for (PaymentDetail d : en.getDetail()) {
				HSSFRow rowD = sheet.createRow((short)rowNum);
				rowD.createCell(0).setCellValue("服务类型");
				rowD.createCell(1).setCellValue(d.getServiceType());
				rowD.createCell(2).setCellValue("描述");
				rowD.createCell(3).setCellValue(d.getDescription());
				rowD.createCell(4).setCellValue("单价");
				rowD.createCell(5).setCellValue(d.getPrice());
				rowD.createCell(6).setCellValue("数量");
				rowD.createCell(7).setCellValue(d.getNum());
				rowD.createCell(8).setCellValue("金额");
				rowD.createCell(9).setCellValue(d.getTotal());
				rowD.createCell(10).setCellValue("单价");
				rowD.createCell(11).setCellValue(d.getPrice());
				rowNum++;
			}
		}
		FileOutputStream fileOut = new FileOutputStream("workbook.xls");
		try {
			wb.write(fileOut);
			fileOut.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.setContentType("Application/msexcel");
		response.setHeader("Content-disposition","attachment; filename=workbook.xls" );
		try {
			wb.write(response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
