package com.autoinspection.polaris.vo.vehicle;

import java.util.List;

public class VehicleVo {
	private int id;
	private String plate;
	private String customerName;
	private int vehicleType;
	private List<VehicleTireVo> tires;
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
	public List<VehicleTireVo> getTires() {
		return tires;
	}
	public void setTires(List<VehicleTireVo> tires) {
		this.tires = tires;
	}
}
