package com.autoinspection.polaris.model.entity;

import java.util.Date;
import java.util.List;

public class OrderEntity {
	private long id;

	private String orderNo;

	private String totalAmount;

	private int status;

	private String isValid;

	private String leftAmount;

	private String payAmount;

	private String operatorName;

	private Date createDate;

	private String createBy;

	private Date updateDate;

	private String updateBy;

	private String plate;

	private String customerName;

	private String customerType;

	private String customerTypeName;
	
	private String customerPhone;
	
	private int maintenanceId;
	
	private List<MaintenanceDetailEntity> maintenanceList;

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	
	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
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
	

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public int getMaintenanceId() {
		return maintenanceId;
	}

	public void setMaintenanceId(int maintenanceId) {
		this.maintenanceId = maintenanceId;
	}
	
	public List<MaintenanceDetailEntity> getMaintenanceList() {
		return maintenanceList;
	}

	public void setMaintenanceList(List<MaintenanceDetailEntity> maintenanceList) {
		this.maintenanceList = maintenanceList;
	}

	@Override
	public String toString() {
		return "OrderEntity [id=" + id + ", orderNo=" + orderNo + ", totalAmount=" + totalAmount + ", status=" + status
				+ ", isValid=" + isValid + ", leftAmount=" + leftAmount + ", payAmount=" + payAmount + ", operatorName="
				+ operatorName + ", createDate=" + createDate + ", createBy=" + createBy + ", updateDate=" + updateDate
				+ ", updateBy=" + updateBy + ", plate=" + plate + ", customerName=" + customerName + ", customerType="
				+ customerType + ", customerTypeName=" + customerTypeName + ", customerPhone=" + customerPhone
				+ ", maintenanceId=" + maintenanceId + ", maintenanceList=" + maintenanceList + "]";
	}

	
}
