package com.autoinspection.polaris.model.entity;

import com.autoinspection.polaris.model.BaseEntity;

public class AppointmentEntity extends BaseEntity{
	private int id;
	private String appointmentDate;
	private String appointmentSlot;
	private int appointmentLimit;
	private int stationId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	public String getAppointmentSlot() {
		return appointmentSlot;
	}
	public void setAppointmentSlot(String appointmentSlot) {
		this.appointmentSlot = appointmentSlot;
	}
	public int getAppointmentLimit() {
		return appointmentLimit;
	}
	public void setAppointmentLimit(int appointmentLimit) {
		this.appointmentLimit = appointmentLimit;
	}
	public int getStationId() {
		return stationId;
	}
	public void setStationId(int stationId) {
		this.stationId = stationId;
	}
}
