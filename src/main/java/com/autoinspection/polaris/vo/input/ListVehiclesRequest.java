package com.autoinspection.polaris.vo.input;

public class ListVehiclesRequest {
	private int page;
	private int pageSize;
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
