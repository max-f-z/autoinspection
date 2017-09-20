package com.autoinspection.polaris.vo.order;

public class GetOrderRequest {
	
	private int status;
	
	private String search;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	@Override
	public String toString() {
		return "GetOrderRequest [status=" + status + ", search=" + search + "]";
	}

}
