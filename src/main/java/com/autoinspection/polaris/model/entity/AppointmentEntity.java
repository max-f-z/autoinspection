package com.autoinspection.polaris.model.entity;

import com.autoinspection.polaris.model.BaseEntity;

public class AppointmentEntity extends BaseEntity{
	private int id;
	private String appointmentDate;
	private int appointmentSlot;
	private int wxUserId;
	private int serviceId;
	private int stationId;
	private String plate;
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
	public String getPlate() {
		return plate;
	}
	public void setPlate(String plate) {
		this.plate = plate;
	}
}
