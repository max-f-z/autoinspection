package com.autoinspection.polaris.vo.vehicle;

import java.util.List;

public class VehicleVo {
	private int id;
	private String plate;
	private String customerName;
	private String vehicleType;
	private List<VehicleTireVo> tires;
	//车辆型号
	private String vehicleModel;
	//初始里程
	private String initialDistance;
	//行驶线路
	private String line;
	
	private String regTime;
	private String regDate;
	private boolean regStatus;
	private int stationId;
	private String bizType;
	private int isRetail; 
	
	public int getIsRetail() {
		return isRetail;
	}
	public void setIsRetail(int isRetail) {
		this.isRetail = isRetail;
	}
	public String getBizType() {
		return bizType;
	}
	public void setBizType(String bizType) {
		this.bizType = bizType;
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
	public boolean isRegStatus() {
		return regStatus;
	}
	public void setRegStatus(boolean regStatus) {
		this.regStatus = regStatus;
	}
	public int getStationId() {
		return stationId;
	}
	public void setStationId(int stationId) {
		this.stationId = stationId;
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
	public List<VehicleTireVo> getTires() {
		return tires;
	}
	public void setTires(List<VehicleTireVo> tires) {
		this.tires = tires;
	}
}
