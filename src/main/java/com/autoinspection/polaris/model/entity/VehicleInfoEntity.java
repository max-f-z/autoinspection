package com.autoinspection.polaris.model.entity;

import java.io.Serializable;

import com.autoinspection.polaris.model.BaseEntity;

public class VehicleInfoEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = -2412775827686228654L;
	
	private int id;
	private String plate;
	private String customerName;
	private int vehicleType;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPlate() {
		return plate;
	}
	public void setPlate(String plate) {
		this.plate = plate;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public int getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(int vehicleType) {
		this.vehicleType = vehicleType;
	}
}
