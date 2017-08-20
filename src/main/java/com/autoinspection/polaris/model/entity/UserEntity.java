package com.autoinspection.polaris.model.entity;

import java.io.Serializable;
import com.autoinspection.polaris.model.BaseEntity;

public class UserEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = -8813563970424171978L;
	private String station;
	private int stationId;
	public String getStation() {
		return station;
	}
	public void setStation(String station) {
		this.station = station;
	}
	public int getStationId() {
		return stationId;
	}
	public void setStationId(int stationId) {
		this.stationId = stationId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private String name;
	private String password;
	private int role;

	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
}
