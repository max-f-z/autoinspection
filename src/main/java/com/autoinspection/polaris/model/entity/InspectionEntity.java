package com.autoinspection.polaris.model.entity;

import com.autoinspection.polaris.model.BaseEntity;

public class InspectionEntity extends BaseEntity {
	private long id;
	private String plate;
	private float milometer;
	private float serviceMile;
	private String operatorName;
	private String maintained;
	private String appointmentDate;
	private int appointmentSlot;
	private int wxUserId;
	private int serviceId;
	private int stationId;
	
	
	
	public String getMaintained() {
		return maintained;
	}
	public void setMaintained(String maintained) {
		this.maintained = maintained;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
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
	public String getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	public int getAppointmentSlot() {
		return appointmentSlot;
	}
	public void setAppointmentSlot(int appointmentSlot) {
		this.appointmentSlot = appointmentSlot;
	}
	public int getWxUserId() {
		return wxUserId;
	}
	public void setWxUserId(int wxUserId) {
		this.wxUserId = wxUserId;
	}
	public int getServiceId() {
		return serviceId;
	}
	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}
	public int getStationId() {
		return stationId;
	}
	public void setStationId(int stationId) {
		this.stationId = stationId;
	}
	
}
