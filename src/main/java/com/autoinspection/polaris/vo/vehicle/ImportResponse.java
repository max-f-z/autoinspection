package com.autoinspection.polaris.vo.vehicle;

import java.util.List;

public class ImportResponse {
	private List<Integer> failedRows;

	public List<Integer> getFailedRows() {
		return failedRows;
	}

	public void setFailedRows(List<Integer> failedRows) {
		this.failedRows = failedRows;
	}
}
