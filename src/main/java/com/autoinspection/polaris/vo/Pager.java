package com.autoinspection.polaris.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Pager<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<T> data;
	
	private Pagination pagination;
	
	private Map<String, Object> variables;

    public Pager(List<T> dataList, Pagination pagination){
		this.data = dataList;
		this.pagination = pagination;
	}

    public Pager(List<T> dataList, Pagination pagination, Map<String, Object> variables){
        this.data = dataList;
        this.pagination = pagination;
        this.variables = variables;
    }
    
	public Map<String, Object> getVariables() {
		return variables;
	}

	public void setVariables(Map<String, Object> variables) {
		this.variables = variables;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

}