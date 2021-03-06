package com.autoinspection.polaris.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autoinspection.polaris.model.entity.CustomerEntity;
import com.autoinspection.polaris.model.entity.VehicleInfoEntity;
import com.autoinspection.polaris.model.mapper.CustomerMapper;
import com.autoinspection.polaris.model.mapper.VehicleInfoMapper;
import com.autoinspection.polaris.vo.customer.AddCustomerRequest;
import com.autoinspection.polaris.vo.customer.GetCustomerRequest;
import com.autoinspection.polaris.vo.customer.UpdateCustomerRequest;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerMapper customerMapper;
	
	@Autowired
	private VehicleInfoMapper vehicleMapper;

	@Override
	public List<CustomerEntity> listAllCustomers() {
		return customerMapper.listAllCustomers();
	}
	
	@Override
	public List<CustomerEntity> listCustomers(GetCustomerRequest request) {
		return customerMapper.listCustomers(request.getSearch());
	}

	@Override
	public CustomerEntity getCustomerById(int id) {
		return customerMapper.getById(id);
	}

	@Override
	public int insertCustomer(AddCustomerRequest request, int uid) {
		CustomerEntity entity = new CustomerEntity();
		entity.setName(request.getName());
		entity.setCode(request.getCode());
		entity.setContactName(request.getContactName());
		entity.setContactPhone(request.getContactPhone());
		entity.setAddress(request.getAddress());
		entity.setSalesman(request.getSalesman());
		entity.setOperatorId(uid);
		entity.setEnable(true);
		customerMapper.insertCustomer(entity);
		
		return entity.getId();
	}

	@Override
	public int updateCustomer(UpdateCustomerRequest request, int uid) {
		CustomerEntity entity = new CustomerEntity();
		entity.setId(request.getId());
		entity.setName(request.getName());
		entity.setContactName(request.getContactName());
		entity.setContactPhone(request.getContactPhone());
		entity.setAddress(request.getAddress());
		entity.setSalesman(request.getSalesman());
		entity.setCode(request.getCode());
		entity.setOperatorId(uid);
		entity.setEnable(true);
		customerMapper.updateCustomer(entity);
		
		return customerMapper.updateCustomer(entity);
	}

	@Override
	public int deleteCustomer(int id, int uid) {
		return customerMapper.deleteCustomer(id, uid);
	}
	
	@Override
	public CustomerEntity getCustomerByPlate(String plate) {
		VehicleInfoEntity entity = vehicleMapper.getByPlate(plate);
		CustomerEntity cen = customerMapper.getByCode(entity.getCustomerName());
		if (cen == null) {
			cen = customerMapper.getByCode("SH");
		}
		return cen;
	}
}
