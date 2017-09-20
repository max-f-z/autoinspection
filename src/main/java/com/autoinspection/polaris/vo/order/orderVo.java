package com.autoinspection.polaris.vo.order;

public class orderVo {
	
	private long id;
	
	private String orderNo;
	
	private String totalAmount;
	
	private String status;
	
	private String isValid;
	
	private String leftAmount;
	
	private String payAmount;
	
	private String operatorName;
	
	private String createDate;
	
	private String creteBy;
	
	private String updateDate;
	
	private String updateBy;
	
	private String plate;
	
	private String customerName;
	
	private String customerType;
	
	private String customerTypeName;

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public String getLeftAmount() {
		return leftAmount;
	}

	public void setLeftAmount(String leftAmount) {
		this.leftAmount = leftAmount;
	}

	public String getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCreteBy() {
		return creteBy;
	}

	public void setCreteBy(String creteBy) {
		this.creteBy = creteBy;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getCustomerTypeName() {
		return customerTypeName;
	}

	public void setCustomerTypeName(String customerTypeName) {
		this.customerTypeName = customerTypeName;
	}

	@Override
	public String toString() {
		return "orderVo [id=" + id + ", orderNo=" + orderNo + ", totalAmount=" + totalAmount + ", status=" + status
				+ ", isValid=" + isValid + ", leftAmount=" + leftAmount + ", payAmount=" + payAmount + ", operatorName="
				+ operatorName + ", createDate=" + createDate + ", creteBy=" + creteBy + ", updateDate=" + updateDate
				+ ", updateBy=" + updateBy + ", plate=" + plate + ", customerName=" + customerName + ", customerType="
				+ customerType + ", customerTypeName=" + customerTypeName + "]";
	}
}
