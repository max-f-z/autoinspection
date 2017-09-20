package com.autoinspection.polaris.model.entity;

import com.autoinspection.polaris.model.BaseEntity;

public class InspectionDetailEntity extends BaseEntity {
	private long id;
	private long inspectionId;
	private int tirePosition;
	private String tireId;
	private String tireBrand;
	private String tireType;
	private String stripe;
	private float pressure;
	private String depth;
	private String brake;
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
	public int getTirePosition() {
		return tirePosition;
	}
	public void setTirePosition(int tirePosition) {
		this.tirePosition = tirePosition;
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
	public String getStripe() {
		return stripe;
	}
	public void setStripe(String stripe) {
		this.stripe = stripe;
	}
	public float getPressure() {
		return pressure;
	}
	public void setPressure(float pressure) {
		this.pressure = pressure;
	}
	public String getDepth() {
		return depth;
	}
	public void setDepth(String depth) {
		this.depth = depth;
	}
	public String getBrake() {
		return brake;
	}
	public void setBrake(String brake) {
		this.brake = brake;
	}

	public String getTireType() {
		return tireType;
	}

	public void setTireType(String tireType) {
		this.tireType = tireType;
	}
}
