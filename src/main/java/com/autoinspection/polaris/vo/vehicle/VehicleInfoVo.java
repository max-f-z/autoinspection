package com.autoinspection.polaris.vo.vehicle;

public class VehicleInfoVo {
	private String customerName;
	private String plate;
	private String typeName;
	private String typeCode;
	private float tireNum;
	private float backUp;
	//车辆型号
	private String vehicleModel;
	//初始里程
	private String initialDistance;
	//行驶线路
	private String line;
	private String bizType;
	public String getBizType() {
		return bizType;
	}
	public void setBizType(String bizType) {
		this.bizType = bizType;
	}
	public String getCustomerName() {
		return customerName;
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
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getPlate() {
		return plate;
	}
	public void setPlate(String plate) {
		this.plate = plate;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public float getTireNum() {
		return tireNum;
	}
	public void setTireNum(float tireNum) {
		this.tireNum = tireNum;
	}
	public float getBackUp() {
		return backUp;
	}
	public void setBackUp(float backUp) {
		this.backUp = backUp;
	}
}
