package com.autoinspection.polaris.vo.wx;

public class RegisterRequest {
	private int wxid;
	private String appointmentDate;
	private String appointmentSlot;
	private String plate;
	public int getWxid() {
		return wxid;
	}
	public void setWxid(int wxid) {
		this.wxid = wxid;
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
	public String getPlate() {
		return plate;
	}
	public void setPlate(String plate) {
		this.plate = plate;
	}
}
