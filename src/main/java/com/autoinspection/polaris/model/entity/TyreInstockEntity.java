package com.autoinspection.polaris.model.entity;

import java.math.BigInteger;

import com.autoinspection.polaris.model.BaseEntity;

public class TyreInstockEntity extends BaseEntity{
	
	private BigInteger id;
	//轮胎品牌
	private String tyreBrand;
	//轮胎型号
	private String tyreType;
	//DOT
	private String barCode;
	//是否已经使用
	private String used;
	//是否是超级单胎
	private String supersingle;
	public String getSupersingle() {
		return supersingle;
	}

	public void setSupersingle(String supersingle) {
		this.supersingle = supersingle;
	}

	public String getFigure() {
		return figure;
	}

	public void setFigure(String figure) {
		this.figure = figure;
	}

	//轮胎花纹
	private String figure;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getTyreBrand() {
		return tyreBrand;
	}

	public void setTyreBrand(String tyreBrand) {
		this.tyreBrand = tyreBrand;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getUsed() {
		return used;
	}

	public void setUsed(String used) {
		this.used = used;
	}

	public String getTyreType() {
		return tyreType;
	}

	public void setTyreType(String tyreType) {
		this.tyreType = tyreType;
	}
}
