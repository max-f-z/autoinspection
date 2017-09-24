package com.autoinspection.polaris.model.entity;

import com.autoinspection.polaris.model.BaseEntity;

public class MaintenanceEntity extends BaseEntity{
	private long id;
	private long inspectionId;
	private Integer payStatus;
	private String plate;
	private String driverPhone;
	private String operatorName;
	
	public Integer getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getInspectionId() {
		return inspectionId;
	}
	public void setInspectionId(long inspectionId) {
		this.inspectionId = inspectionId;
	}
	public String getPlate() {
		return plate;
	}
	public void setPlate(String plate) {
		this.plate = plate;
	}
	public String getDriverPhone() {
		return driverPhone;
	}
	public void setDriverPhone(String driverPhone) {
		this.driverPhone = driverPhone;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
}
