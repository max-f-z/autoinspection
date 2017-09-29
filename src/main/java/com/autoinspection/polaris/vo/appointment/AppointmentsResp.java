package com.autoinspection.polaris.vo.appointment;

import java.util.List;

public class AppointmentsResp {
	List<AppointmentDetail> items;

	public List<AppointmentDetail> getItems() {
		return items;
	}

	public void setItems(List<AppointmentDetail> items) {
		this.items = items;
	}
}
