package com.autoinspection.polaris.vo.vehicle;

public class VehicleInfoVo {
	private String customerName;
	private String plate;
	private String typeName;
	private String typeCode;
	private float tireNum;
	private float backUp;
	public String getCustomerName() {
		return customerName;
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
