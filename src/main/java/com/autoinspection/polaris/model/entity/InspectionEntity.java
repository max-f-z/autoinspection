package com.autoinspection.polaris.model.entity;

import com.autoinspection.polaris.model.BaseEntity;

public class InspectionEntity extends BaseEntity {
	private long id;
	private String plate;
	private float milometer;
	private float serviceMile;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
