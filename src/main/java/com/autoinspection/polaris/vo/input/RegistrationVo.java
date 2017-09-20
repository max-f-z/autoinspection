package com.autoinspection.polaris.vo.input;

public class RegistrationVo {
	private int regId;
	private String appointmentDate;
	private int appointmentSlot;
	private String slotName;
	private int stationId;
	private String stationName;
	private String createTime;
	private int serviceId;
	private String serviceName;
	private String plate;
	private String lastInspector;
	private String lastInspectTime;
	private String customerName;
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getPlate() {
		return plate;
	}
	public void setPlate(String plate) {
		this.plate = plate;
	}
	public String getLastInspector() {
		return lastInspector;
	}
	public void setLastInspector(String lastInspector) {
		this.lastInspector = lastInspector;
	}
	public String getLastInspectTime() {
		return lastInspectTime;
	}
	public void setLastInspectTime(String lastInspectTime) {
		this.lastInspectTime = lastInspectTime;
	}
	public int getRegId() {
		return regId;
	}
	public void setRegId(int regId) {
		this.regId = regId;
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
	public String getSlotName() {
		return slotName;
	}
	public void setSlotName(String slotName) {
		this.slotName = slotName;
	}
	public int getStationId() {
		return stationId;
	}
	public void setStationId(int stationId) {
		this.stationId = stationId;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public int getServiceId() {
		return serviceId;
	}
	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
}
