package com.autoinspection.polaris.vo.Inspection;

import java.util.List;

public class InspectionVo {
	private long id;
	private String plate;
	private float milometer;
	private float serviceMile;
	private String operatorName;
	private String createTime;
	private String maintained;
	private String bizType;
	public String getBizType() {
		return bizType;
	}
	public void setBizType(String bizType) {
		this.bizType = bizType;
	}
	public String getMaintained() {
		return maintained;
	}
	public void setMaintained(String maintained) {
		this.maintained = maintained;
	}
	private List<InspectionDetailVo> details;
	public List<InspectionDetailVo> getDetails() {
		return details;
	}
	public void setDetails(List<InspectionDetailVo> details) {
		this.details = details;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
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
}
