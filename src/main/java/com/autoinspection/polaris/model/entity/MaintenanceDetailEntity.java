package com.autoinspection.polaris.model.entity;

public class MaintenanceDetailEntity {
	private long id;
	private long maintenanceId;
	private int tireposition;
	private int servicePriceId;
	private String servicePriceDesc;
	private String servicePriceName;
	private int num;
	private String startTime;
	private String endTime;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getMaintenanceId() {
		return maintenanceId;
	}
	public void setMaintenanceId(long maintenanceId) {
		this.maintenanceId = maintenanceId;
	}
	public int getTireposition() {
		return tireposition;
	}
	public void setTireposition(int tireposition) {
		this.tireposition = tireposition;
	}
	public int getServicePriceId() {
		return servicePriceId;
	}
	public void setServicePriceId(int servicePriceId) {
		this.servicePriceId = servicePriceId;
	}
	public String getServicePriceDesc() {
		return servicePriceDesc;
	}
	public void setServicePriceDesc(String servicePriceDesc) {
		this.servicePriceDesc = servicePriceDesc;
	}
	public String getServicePriceName() {
		return servicePriceName;
	}
	public void setServicePriceName(String servicePriceName) {
		this.servicePriceName = servicePriceName;
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
