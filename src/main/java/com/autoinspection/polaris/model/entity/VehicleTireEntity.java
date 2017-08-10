package com.autoinspection.polaris.model.entity;

import java.io.Serializable;

import com.autoinspection.polaris.model.BaseEntity;

public class VehicleTireEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = -8524171822189862127L;

	private long id;
	private int vehicleId;
	private String tireId;
	private String tireBrand;
	private int tirePosition;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
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
}
