package com.autoinspection.polaris.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.autoinspection.polaris.interceptor.Permission;
import com.autoinspection.polaris.interceptor.PermissionEnum;
import com.autoinspection.polaris.model.entity.ServicePriceDisplayEntity;
import com.autoinspection.polaris.resolver.CurrentUser;
import com.autoinspection.polaris.service.ServicePriceService;
import com.autoinspection.polaris.utils.BizException;
import com.autoinspection.polaris.utils.ErrorCode;
import com.autoinspection.polaris.vo.UserVo;
import com.autoinspection.polaris.vo.serviceprice.AddServicePriceRequest;
import com.autoinspection.polaris.vo.serviceprice.AddServicePriceResponse;
import com.autoinspection.polaris.vo.serviceprice.DeleteServicePriceRequest;
import com.autoinspection.polaris.vo.serviceprice.DeleteServicePriceResponse;
import com.autoinspection.polaris.vo.serviceprice.SearchRequest;
import com.autoinspection.polaris.vo.serviceprice.UpdateServicePriceRequest;
import com.autoinspection.polaris.vo.serviceprice.UpdateServicePriceResponse;

@RestController
@RequestMapping(value="${api.path}/serviceprice")
public class ServicePriceController {
	@Autowired
	private ServicePriceService spService;
	
	@RequestMapping(value="/prices", method = RequestMethod.GET)
	@Permission( permissionTypes = { PermissionEnum.ADMIN })
	public List<ServicePriceDisplayEntity> getPrices() {
		return spService.listServicePrices();
	}
	
	@RequestMapping(value="/prices/search", method = RequestMethod.POST)
	@Permission( permissionTypes = { PermissionEnum.ADMIN })
	public List<ServicePriceDisplayEntity> search(@RequestBody SearchRequest request) {
		return spService.search(request.getSearch());
	}
	
	@RequestMapping(value="/prices/{id}", method = RequestMethod.GET)
	@Permission( permissionTypes = { PermissionEnum.ADMIN })
	public ServicePriceDisplayEntity getPrice(@PathVariable Integer id) throws BizException {
		ServicePriceDisplayEntity price = spService.getServicePriceById(id);
		if (price == null) {
			throw new BizException(ErrorCode.NOT_FOUND);
		}
		return price;
	}
	
	@RequestMapping(value="/prices/add", method = RequestMethod.POST) 
	@Permission( permissionTypes = { PermissionEnum.ADMIN })
	public AddServicePriceResponse insertPrice(@RequestBody AddServicePriceRequest request, @CurrentUser UserVo user) throws BizException {
		if (request == null) {
			throw new BizException(ErrorCode.INVALID_PARAM);
		}
		
		int id = spService.addServicePrice(request, user.getUid());
		AddServicePriceResponse resp = new AddServicePriceResponse();
		if (id != 0) {
			resp.setId(id);
		}
		return resp;
	}
	
	@RequestMapping(value="/prices/update", method = RequestMethod.POST)
	@Permission( permissionTypes = { PermissionEnum.ADMIN })
	public UpdateServicePriceResponse updatePrice(@RequestBody UpdateServicePriceRequest request, @CurrentUser UserVo user) throws BizException {
		if (request == null) {
			throw new BizException(ErrorCode.INVALID_PARAM);
		}
		int rows = spService.updateServicePrice(request, user.getUid());
		UpdateServicePriceResponse resp = new UpdateServicePriceResponse();
		resp.setAffectedRows(rows);
		return resp;
	}
	
	@RequestMapping(value="/prices/delete", method = RequestMethod.DELETE)
	@Permission( permissionTypes = { PermissionEnum.ADMIN })
	public DeleteServicePriceResponse deleteProduct(@RequestBody DeleteServicePriceRequest request, @CurrentUser UserVo user) throws BizException {
		if (request == null) {
			throw new BizException(ErrorCode.INVALID_PARAM);
		}
		int rows = spService.deleteServicePrice(request, user.getUid());
		DeleteServicePriceResponse resp = new DeleteServicePriceResponse();
		resp.setAffectedRows(rows);
		return resp;
	}
}
