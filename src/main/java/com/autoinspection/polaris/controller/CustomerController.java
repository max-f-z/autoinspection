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
import com.autoinspection.polaris.model.entity.CustomerEntity;
import com.autoinspection.polaris.resolver.CurrentUser;
import com.autoinspection.polaris.service.CustomerService;
import com.autoinspection.polaris.utils.BizException;
import com.autoinspection.polaris.utils.ErrorCode;
import com.autoinspection.polaris.vo.UserVo;
import com.autoinspection.polaris.vo.customer.AddCustomerRequest;
import com.autoinspection.polaris.vo.customer.AddCustomerResponse;
import com.autoinspection.polaris.vo.customer.DeleteCustomerRequest;
import com.autoinspection.polaris.vo.customer.DeleteCustomerResponse;
import com.autoinspection.polaris.vo.customer.GetCustomerRequest;
import com.autoinspection.polaris.vo.customer.UpdateCustomerRequest;
import com.autoinspection.polaris.vo.customer.UpdateCustomerResponse;

@RestController
@RequestMapping(value="${api.path}/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(path = "/customers", method = RequestMethod.POST)
	@Permission( permissionTypes = { PermissionEnum.ADMIN })
	public List<CustomerEntity> getCustomers(@RequestBody GetCustomerRequest request) {
		return customerService.listCustomers(request);
	}
	
	@RequestMapping(path = "/customers/{id}", method = RequestMethod.POST)
	@Permission( permissionTypes = { PermissionEnum.ADMIN })
	public CustomerEntity getCustomer(@PathVariable Integer id) throws BizException {
		CustomerEntity customer = customerService.getCustomerById(id);
		if (customer == null) {
			throw new BizException(ErrorCode.NOT_FOUND);
		}
		return customer;
	}
	
	@RequestMapping(path = "/customers/add", method = RequestMethod.POST)
	@Permission( permissionTypes = { PermissionEnum.ADMIN })
	public AddCustomerResponse addCustomer(@RequestBody AddCustomerRequest request, @CurrentUser UserVo user) throws BizException {
		if (request == null) {
			throw new BizException(ErrorCode.INVALID_PARAM);
		}
		
		int id = customerService.insertCustomer(request, user.getUid());
		AddCustomerResponse resp = new AddCustomerResponse();
		if (id != 0) {
			resp.setId(id);
		}
		return resp;
	}
	
	@RequestMapping(path = "/customers/update", method = RequestMethod.POST)
	@Permission( permissionTypes = { PermissionEnum.ADMIN })
	public UpdateCustomerResponse updateCustomer(@RequestBody UpdateCustomerRequest request, @CurrentUser UserVo user) throws BizException {
		if (request == null) {
			throw new BizException(ErrorCode.INVALID_PARAM);
		}
		int rows = customerService.updateCustomer(request, user.getUid());
		UpdateCustomerResponse resp = new UpdateCustomerResponse();
		resp.setAffectedRows(rows);
		return resp;
	}
	
	@RequestMapping(path = "/customers/delete", method = RequestMethod.DELETE)
	@Permission( permissionTypes = { PermissionEnum.ADMIN })
	public DeleteCustomerResponse deleteCustomer(@RequestBody DeleteCustomerRequest request, @CurrentUser UserVo user) throws BizException {
		if (request == null) {
			throw new BizException(ErrorCode.INVALID_PARAM);
		}
		int rows = customerService.deleteCustomer(request.getId(), user.getUid());
		DeleteCustomerResponse resp = new DeleteCustomerResponse();
		resp.setAffectedRows(rows);
		return resp;
	}
}
