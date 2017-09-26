package com.autoinspection.polaris.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.autoinspection.polaris.model.entity.CustomerEntity;
import com.autoinspection.polaris.model.entity.MaintenanceDetailEntity;
import com.autoinspection.polaris.model.entity.OrderPayEntity;
import com.autoinspection.polaris.model.entity.ServicePriceDisplayEntity;
import com.autoinspection.polaris.model.entity.VehicleInfoEntity;
import com.autoinspection.polaris.model.mapper.CustomerMapper;
import com.autoinspection.polaris.model.mapper.MaintenanceDetailMapper;
import com.autoinspection.polaris.model.mapper.MaintenanceMapper;
import com.autoinspection.polaris.model.mapper.ServicePriceMapper;
import com.autoinspection.polaris.model.mapper.VehicleInfoMapper;
import com.autoinspection.polaris.utils.BizException;
import com.autoinspection.polaris.utils.ErrorCode;
import com.autoinspection.polaris.vo.payment.PaymentDetail;
import com.autoinspection.polaris.vo.payment.PaymentSearchRequest;
import com.autoinspection.polaris.vo.payment.PaymentSearchResp;
import com.autoinspection.polaris.vo.payment.PaymentUpdateReq;

@Service
public class PaymentServiceImpl implements PaymentService {
	
	@Autowired
	private MaintenanceMapper maintenanceMapper;
	
	@Autowired
	private MaintenanceDetailMapper maintenanceDetailMapper;
	
	@Autowired
	private VehicleInfoMapper vehicleMapper;
	
	@Autowired
	private ServicePriceMapper servicePriceMapper;
	
	@Autowired
	private CustomerMapper cMapper;

	@Override
	public PaymentSearchResp searchOrder(PaymentSearchRequest request) throws BizException {
		if (request.getPageNo() == null || request.getPageNo() == 0) {
			request.setPageNo(1);
		}
		if (request.getPageSize() == null || request.getPageSize() == 0) {
			request.setPageSize(10);
		}
		
		PaymentSearchResp resp = new PaymentSearchResp();
		
		List<OrderPayEntity> data = maintenanceMapper.search(request, (request.getPageNo()-1) * request.getPageSize(), request.getPageSize());
		for (OrderPayEntity item : data) {
			List<MaintenanceDetailEntity> lists = maintenanceDetailMapper.listDetails(item.getId());
			Map<String, PaymentDetail> map = new HashMap<String, PaymentDetail>();
			
			VehicleInfoEntity ven = vehicleMapper.getByPlate(item.getPlate());
			CustomerEntity c = cMapper.getByCode(ven.getCustomerName());
			String customerCode = "";
			if (c != null) {
				customerCode = ven.getCustomerName();
				item.setRetail(false);
			} else {
				customerCode = "SH";
				item.setRetail(true);
			}
			
			for (MaintenanceDetailEntity en : lists) {
				if (map.containsKey(en.getServicePrice())) {
					PaymentDetail tmp = map.get(en.getServicePriceName());
					tmp.setNum(tmp.getNum() + 1);
				} else {
					PaymentDetail tmp = new PaymentDetail();
					tmp.setDescription(en.getServicePriceDesc());
					tmp.setServiceType(en.getServicePriceName());
					tmp.setNum(1);
					
					ServicePriceDisplayEntity spde = servicePriceMapper.getByServiceIdAndCustomerCode(en.getServicePriceId(), customerCode);
					if (spde == null) {
						throw new BizException(ErrorCode.NO_SERVICE_PRICE);
					}
					tmp.setPrice(spde.getPrice());
					map.put(en.getServicePriceName(), tmp);
				}
			}
			float total = 0;
			List<PaymentDetail> pd = new ArrayList<PaymentDetail>();
			for (Map.Entry<String, PaymentDetail> entry : map.entrySet()) {
				entry.getValue().setTotal(entry.getValue().getPrice() * entry.getValue().getNum());
				pd.add(entry.getValue());
				total += entry.getValue().getTotal();
			}
			item.setDetail(pd);
			item.setTotal(total);
		}
		resp.setItems(data);
		int cnt = maintenanceMapper.count(request);
		resp.setCount(cnt);
		return resp;
	}

	@Override
	@Transactional( rollbackFor=Exception.class )
	public void updateStatus(PaymentUpdateReq req, int operatorId) throws BizException {
		for (Long id : req.getItems()) {
			maintenanceMapper.updatePayStatusById(req.getStatus(), operatorId, id);
		}
	}

	@Override
	public OrderPayEntity getOrder(Long id) throws BizException {
		OrderPayEntity item = maintenanceMapper.getOrderById(id);
		List<MaintenanceDetailEntity> lists = maintenanceDetailMapper.listDetails(item.getId());
		Map<String, PaymentDetail> map = new HashMap<String, PaymentDetail>();
		
		VehicleInfoEntity ven = vehicleMapper.getByPlate(item.getPlate());
		CustomerEntity c = cMapper.getByCode(ven.getCustomerName());
		String customerCode = "";
		if (c != null) {
			customerCode = ven.getCustomerName();
			item.setRetail(false);
		} else {
			customerCode = "SH";
			item.setRetail(true);
		}
		
		for (MaintenanceDetailEntity en : lists) {
			if (map.containsKey(en.getServicePrice())) {
				PaymentDetail tmp = map.get(en.getServicePriceName());
				tmp.setNum(tmp.getNum() + 1);
			} else {
				PaymentDetail tmp = new PaymentDetail();
				tmp.setDescription(en.getServicePriceDesc());
				tmp.setServiceType(en.getServicePriceName());
				tmp.setNum(1);
				
				ServicePriceDisplayEntity spde = servicePriceMapper.getByServiceIdAndCustomerCode(en.getServicePriceId(), customerCode);
				if (spde == null) {
					throw new BizException(ErrorCode.NO_SERVICE_PRICE);
				}
				tmp.setPrice(spde.getPrice());
				map.put(en.getServicePriceName(), tmp);
			}
		}
		float total = 0;
		List<PaymentDetail> pd = new ArrayList<PaymentDetail>();
		for (Map.Entry<String, PaymentDetail> entry : map.entrySet()) {
			entry.getValue().setTotal(entry.getValue().getPrice() * entry.getValue().getNum());
			pd.add(entry.getValue());
			total += entry.getValue().getTotal();
		}
		item.setDetail(pd);
		item.setTotal(total);
		
		return item;
	}
}
