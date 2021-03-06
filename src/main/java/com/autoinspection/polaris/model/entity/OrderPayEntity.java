package com.autoinspection.polaris.model.entity;

import java.util.List;

import com.autoinspection.polaris.vo.payment.PaymentDetail;

public class OrderPayEntity {
	private long id;
	private String plate;
	private String createTime;
	private String customerName;
	private int payStatus;
	private float total;
	private boolean isRetail;
	private List<PaymentDetail> detail;
	
	public boolean isRetail() {
		return isRetail;
	}
	public void setRetail(boolean isRetail) {
		this.isRetail = isRetail;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public int getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(int payStatus) {
		this.payStatus = payStatus;
	}
	
	public List<PaymentDetail> getDetail() {
		return detail;
	}
	public void setDetail(List<PaymentDetail> detail) {
		this.detail = detail;
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
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
}
