package com.autoinspection.polaris.vo.Inspection;

import java.util.List;

public class AddInspectionRequest {
	private String plate;
	private float milometer;
	private float serviceMile;
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
}
