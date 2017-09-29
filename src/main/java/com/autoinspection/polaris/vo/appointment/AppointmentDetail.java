package com.autoinspection.polaris.vo.appointment;

import java.util.List;

import com.autoinspection.polaris.model.entity.RemainEntity;

public class AppointmentDetail {
	private String date;
	private List<RemainEntity> slots;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public List<RemainEntity> getSlots() {
		return slots;
	}
	public void setSlots(List<RemainEntity> slots) {
		this.slots = slots;
	}
}
