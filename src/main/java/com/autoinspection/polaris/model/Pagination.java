package com.autoinspection.polaris.model;

import java.io.Serializable;

public class Pagination implements Serializable {
	private static final long serialVersionUID = 1L;

	private int page = 1;
	
	private int limit = 10;
	
	private int totalCount = 0;
	
	private int totalPage = 0;
	
	public Pagination(){
	}
	
	public Pagination(int page, int limit, int totalCount){
		this.setPage(page);
		this.limit = limit;
		this.setTotalCount(totalCount);
		this.totalPage = totalCount%limit==0 ? totalCount/limit : totalCount/limit + 1;
	}
	
	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
}