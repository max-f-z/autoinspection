package com.autoinspection.polaris.vo.vehicle;

import java.util.List;

public class ImportResponse {
	private List<Integer> failedRows;

	private List<Integer> invalidRows;

	public List<Integer> getInvalidRows() {
		return invalidRows;
	}

	public void setInvalidRows(List<Integer> invalidRows) {
		this.invalidRows = invalidRows;
	}

	public List<Integer> getFailedRows() {
		return failedRows;
	}

	public void setFailedRows(List<Integer> failedRows) {
		this.failedRows = failedRows;
	}
}
