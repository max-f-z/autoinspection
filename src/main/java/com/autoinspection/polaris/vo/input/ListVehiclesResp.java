package com.autoinspection.polaris.vo.input;

import java.util.List;

import com.autoinspection.polaris.vo.vehicle.VehicleVo;

public class ListVehiclesResp {
	private List<VehicleVo> items;
	private int count;
	public List<VehicleVo> getItems() {
		return items;
	}
	public void setItems(List<VehicleVo> items) {
		this.items = items;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
