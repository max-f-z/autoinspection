package com.autoinspection.polaris.model.entity;

import java.io.Serializable;

import com.autoinspection.polaris.model.BaseEntity;

public class VehicleInfoEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = -2412775827686228654L;
	
	private int id;
	private String plate;
	private String customerName;
	private String vehicleType;
	//车辆型号
	private String vehicleModel;
	//初始里程
	private String initialDistance;
	//行驶线路
	private String line;
	
	private boolean regStatus;
	private String regTime;
	private String regDate;
	private Integer stationId;
	
	public Integer getStationId() {
		return stationId;
	}
	public void setStationId(Integer stationId) {
		this.stationId = stationId;
	}
	public boolean isRegStatus() {
		return regStatus;
	}
	public void setRegStatus(boolean regStatus) {
		this.regStatus = regStatus;
	}
	public String getRegTime() {
		return regTime;
	}
	public void setRegTime(String regTime) {
		this.regTime = regTime;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
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
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	public String getVehicleModel() {
		return vehicleModel;
	}
	public void setVehicleModel(String vehicleModel) {
		this.vehicleModel = vehicleModel;
	}
	public String getInitialDistance() {
		return initialDistance;
	}
	public void setInitialDistance(String initialDistance) {
		this.initialDistance = initialDistance;
	}
	public String getLine() {
		return line;
	}
	public void setLine(String line) {
		this.line = line;
	}
}
