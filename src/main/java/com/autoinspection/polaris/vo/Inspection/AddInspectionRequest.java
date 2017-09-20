package com.autoinspection.polaris.vo.Inspection;

import java.util.List;

public class AddInspectionRequest {
	private String plate;
	private float milometer;
	private float serviceMile;
	private int vehicleTypeId;
	private String customerName;
	private List<InspectionDetailVo> details;
	public List<InspectionDetailVo> getDetails() {
		return details;
	}
	public void setDetails(List<InspectionDetailVo> details) {
		this.details = details;
	}
	public String getPlate() {
		return plate;
	}
	public void setPlate(String plate) {
		this.plate = plate;
	}
	public float getMilometer() {
		return milometer;
	}
	public void setMilometer(float milometer) {
		this.milometer = milometer;
	}
	public float getServiceMile() {
		return serviceMile;
	}
	public void setServiceMile(float serviceMile) {
		this.serviceMile = serviceMile;
	}
	public int getVehicleTypeId() {
		return vehicleTypeId;
	}
	public void setVehicleTypeId(int vehicleTypeId) {
		this.vehicleTypeId = vehicleTypeId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
}
