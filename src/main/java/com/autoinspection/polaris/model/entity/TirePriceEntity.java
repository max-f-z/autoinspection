package com.autoinspection.polaris.model.entity;

import java.io.Serializable;

import com.autoinspection.polaris.model.BaseEntity;

public class TirePriceEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = -3198291394514018651L;
	private int id;
	private String brand;
	private String description;
	private String stripe;
	private float price;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStripe() {
		return stripe;
	}
	public void setStripe(String stripe) {
		this.stripe = stripe;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
}	
