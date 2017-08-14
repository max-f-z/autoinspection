package com.autoinspection.polaris.model.entity;

import com.autoinspection.polaris.model.BaseEntity;

public class DistrictEntity extends BaseEntity{
	private int id;
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
