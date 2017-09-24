package com.autoinspection.polaris.vo.payment;

import java.util.List;

import com.autoinspection.polaris.model.entity.OrderPayEntity;

public class PaymentSearchResp {
	private List<OrderPayEntity> items;
	private int count;
	
	public List<OrderPayEntity> getItems() {
		return items;
	}
	public void setItems(List<OrderPayEntity> items) {
		this.items = items;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
