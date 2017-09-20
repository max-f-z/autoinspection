package com.autoinspection.polaris.model.entity;

public class VehicleTypeEntity {
	private int id;
	private String type;
	private String code;
	private String driveType;
	private int axle;
	private int tireType;
	private int tireNum;
	private int backup;
	public int getBackup() {
		return backup;
	}
	public void setBackup(int backup) {
		this.backup = backup;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDriveType() {
		return driveType;
	}
	public void setDriveType(String driveType) {
		this.driveType = driveType;
	}
	public int getAxle() {
		return axle;
	}
	public void setAxle(int axle) {
		this.axle = axle;
	}
	public int getTireType() {
		return tireType;
	}
	public void setTireType(int tireType) {
		this.tireType = tireType;
	}
	public int getTireNum() {
		return tireNum;
	}
	public void setTireNum(int tireNum) {
		this.tireNum = tireNum;
	}
}
