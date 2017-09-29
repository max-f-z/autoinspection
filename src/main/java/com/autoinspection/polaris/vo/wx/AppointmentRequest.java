package com.autoinspection.polaris.vo.wx;

public class AppointmentRequest {
	public String date;
	public Integer stationId;

	public Integer getStationId() {
		return stationId;
	}

	public void setStationId(Integer stationId) {
		this.stationId = stationId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
