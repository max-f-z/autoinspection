package com.autoinspection.polaris.vo.Inspection;

import java.util.List;

public class AddMaintenanceRequest {
	private long inspectionId;
	private String plate;
	private String driverPhone;
	private List<MaintenanceDetailVo> details;
	private int costomerId;
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
	public List<MaintenanceDetailVo> getDetails() {
		return details;
	}
	public void setDetails(List<MaintenanceDetailVo> details) {
		this.details = details;
	}
	public int getCostomerId() {
		return costomerId;
	}
	public void setCostomerId(int costomerId) {
		this.costomerId = costomerId;
	}
}
