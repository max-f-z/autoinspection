package com.autoinspection.polaris.model.entity;

import com.autoinspection.polaris.model.BaseEntity;

public class ParametersEntity extends BaseEntity {
	
	private int id;
	
	private String paraType;
	
	private String keyCode;
	
	private String keyValues;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getParaType() {
		return paraType;
	}

	public void setParaType(String paraType) {
		this.paraType = paraType;
	}

	public String getKeyCode() {
		return keyCode;
	}

	public void setKeyCode(String keyCode) {
		this.keyCode = keyCode;
	}

	public String getKeyValues() {
		return keyValues;
	}

	public void setKeyValues(String keyValues) {
		this.keyValues = keyValues;
	}

	
	
	

}
