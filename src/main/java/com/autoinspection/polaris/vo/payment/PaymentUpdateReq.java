package com.autoinspection.polaris.vo.payment;

import java.util.List;

public class PaymentUpdateReq {
	private List<Long> items;
	private int status;
	public List<Long> getItems() {
		return items;
	}
	public void setItems(List<Long> items) {
		this.items = items;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
