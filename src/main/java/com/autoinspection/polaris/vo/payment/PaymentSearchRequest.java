package com.autoinspection.polaris.vo.payment;

public class PaymentSearchRequest {
	private Integer payStatus;
	private String startDate;
	private String endDate;
	private Integer isRetail;
	private String customerName;
	private String plate;
	
	public Integer getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Integer getIsRetail() {
		return isRetail;
	}
	public void setIsRetail(Integer isRetail) {
		this.isRetail = isRetail;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getPlate() {
		return plate;
	}
	public void setPlate(String plate) {
		this.plate = plate;
	}
}
