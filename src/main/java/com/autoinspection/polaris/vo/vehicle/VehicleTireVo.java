package com.autoinspection.polaris.vo.vehicle;

public class VehicleTireVo {
	private int vehicleId;
	private String tireId;
	private String tireType;
	private String tireBrand;
	private int tirePosition;
	private long id;
	public int getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getTireId() {
		return tireId;
	}
	public void setTireId(String tireId) {
		this.tireId = tireId;
	}
	public String getTireBrand() {
		return tireBrand;
	}
	public void setTireBrand(String tireBrand) {
		this.tireBrand = tireBrand;
	}
	public int getTirePosition() {
		return tirePosition;
	}
	public void setTirePosition(int tirePosition) {
		this.tirePosition = tirePosition;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getTireType() {
		return tireType;
	}

	public void setTireType(String tireType) {
		this.tireType = tireType;
	}
}
