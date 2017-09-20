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
import com.autoinspection.polaris.model.entity.ServiceEntity;
import com.autoinspection.polaris.resolver.CurrentUser;
import com.autoinspection.polaris.service.ServiceService;
import com.autoinspection.polaris.utils.BizException;
import com.autoinspection.polaris.utils.ErrorCode;
import com.autoinspection.polaris.vo.UserVo;
import com.autoinspection.polaris.vo.service.AddServiceRequest;
import com.autoinspection.polaris.vo.service.AddServiceResponse;
import com.autoinspection.polaris.vo.service.DeleteServiceRequest;
import com.autoinspection.polaris.vo.service.DeleteServiceResponse;
import com.autoinspection.polaris.vo.service.SearchRequest;
import com.autoinspection.polaris.vo.service.UpdateServiceRequest;
import com.autoinspection.polaris.vo.service.UpdateServiceResponse;

@RestController
@RequestMapping(value="${api.path}/service")
public class ServicesController {
	
	@Autowired
	private ServiceService serviceService;
	
	@RequestMapping(value="/services", method = RequestMethod.GET)
	@Permission( permissionTypes = { PermissionEnum.ADMIN })
	public List<ServiceEntity> getStations() {
		return serviceService.listAllServices();
	}
	
	@RequestMapping(value="/services/{id}", method = RequestMethod.GET)
	@Permission( permissionTypes = { PermissionEnum.ADMIN })
	public ServiceEntity getService(@PathVariable Integer id) throws BizException {
		ServiceEntity service = serviceService.getStationById(id);
		if (service == null) {
			throw new BizException(ErrorCode.NOT_FOUND);
		}
		return service;
	}
	
	@RequestMapping(value="/services/add", method = RequestMethod.POST) 
	@Permission( permissionTypes = { PermissionEnum.ADMIN })
	public AddServiceResponse insertStation(@RequestBody AddServiceRequest request, @CurrentUser UserVo user) throws BizException {
		if (request == null) {
			throw new BizException(ErrorCode.INVALID_PARAM);
		}
		
		int id = serviceService.insertService(request, user.getUid());
		AddServiceResponse resp = new AddServiceResponse();
		if (id != 0) {
			resp.setId(id);
		}
		return resp;
	}
	
	@RequestMapping(value="/services/update", method = RequestMethod.POST)
	@Permission( permissionTypes = { PermissionEnum.ADMIN })
	public UpdateServiceResponse updateStation(@RequestBody UpdateServiceRequest request, @CurrentUser UserVo user) throws BizException {
		if (request == null) {
			throw new BizException(ErrorCode.INVALID_PARAM);
		}
		int rows = serviceService.updateService(request, user.getUid());
		UpdateServiceResponse resp = new UpdateServiceResponse();
		resp.setAffectedRows(rows);
		return resp;
	}
	
	@RequestMapping(value="/services/delete", method = RequestMethod.DELETE)
	@Permission( permissionTypes = { PermissionEnum.ADMIN })
	public DeleteServiceResponse deleteStation(@RequestBody DeleteServiceRequest request, @CurrentUser UserVo user) throws BizException {
		if (request == null) {
			throw new BizException(ErrorCode.INVALID_PARAM);
		}
		int rows = serviceService.deleteService(request.getId(), user.getUid());
		DeleteServiceResponse resp = new DeleteServiceResponse();
		resp.setAffectedRows(rows);
		return resp;
	}
	
	@RequestMapping(value="/services/search", method = RequestMethod.POST)
	@Permission( permissionTypes = { PermissionEnum.ADMIN })
	public List<ServiceEntity> search(@RequestBody SearchRequest request) {
		return serviceService.search(request.getLevel1(),request.getSearch());
	}
}
