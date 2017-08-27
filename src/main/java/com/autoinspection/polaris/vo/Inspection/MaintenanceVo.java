package com.autoinspection.polaris.vo.Inspection;

import java.util.List;

public class MaintenanceVo {
	private long id;
	private long inspectionId;
	private String plate;
	private String driverPhone;
	private String operatorName;
	private List<MaintenanceDetailVo> details;
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
	public List<MaintenanceDetailVo> getDetails() {
		return details;
	}
	public void setDetails(List<MaintenanceDetailVo> details) {
		this.details = details;
	}
}
