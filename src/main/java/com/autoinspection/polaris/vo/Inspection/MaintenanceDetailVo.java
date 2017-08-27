package com.autoinspection.polaris.vo.Inspection;

public class MaintenanceDetailVo {
	private long id;
	private long maintenanceId;
	private int tirePosition;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	private int serviceId;
	private String serviceName;
	private String serviceDesc;
	private int num;
	private String startTime;
	private String endTime;
	public long getMaintenanceId() {
		return maintenanceId;
	}
	public void setMaintenanceId(long maintenanceId) {
		this.maintenanceId = maintenanceId;
	}
	public int getTirePosition() {
		return tirePosition;
	}
	public void setTirePosition(int tirePosition) {
		this.tirePosition = tirePosition;
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
	public String getServiceDesc() {
		return serviceDesc;
	}
	public void setServiceDesc(String serviceDesc) {
		this.serviceDesc = serviceDesc;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
}
