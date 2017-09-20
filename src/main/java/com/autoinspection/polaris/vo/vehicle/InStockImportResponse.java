package com.autoinspection.polaris.vo.vehicle;

import java.util.List;

public class InStockImportResponse extends ImportResponse {
	
	
	private List<String> duplicatedRows;
	
	private List<String> notBrandcode;

	public List<String> getDuplicatedRows() {
		return duplicatedRows;
	}

	public void setDuplicatedRows(List<String> duplicatedRows) {
		this.duplicatedRows = duplicatedRows;
	}

	public List<String> getNotBrandcode() {
		return notBrandcode;
	}

	public void setNotBrandcode(List<String> notBrandcode) {
		this.notBrandcode = notBrandcode;
	}

}
