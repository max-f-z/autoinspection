package com.autoinspection.polaris.service;

import java.util.List;

import com.autoinspection.polaris.model.entity.CustomerEntity;
import com.autoinspection.polaris.vo.customer.AddCustomerRequest;
import com.autoinspection.polaris.vo.customer.GetCustomerRequest;
import com.autoinspection.polaris.vo.customer.UpdateCustomerRequest;

public interface CustomerService {
	public List<CustomerEntity> listAllCustomers();
	public List<CustomerEntity> listCustomers(GetCustomerRequest request);
	public CustomerEntity getCustomerById(int id);
	public int insertCustomer(AddCustomerRequest request, int uid);
	public int updateCustomer(UpdateCustomerRequest request, int uid);
	public int deleteCustomer(int id, int uid);
}
