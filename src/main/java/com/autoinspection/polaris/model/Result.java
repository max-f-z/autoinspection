package com.autoinspection.polaris.model;

import java.io.Serializable;

import com.autoinspection.polaris.utils.ErrorCode;

public class Result<T> implements Serializable {
	private static final long serialVersionUID = 1739491769416144669L;

	private Integer result = 1;
    
	private String code = "";
	private String msg = "";
	
	private T data ;
	
	public Result(T data){
		this.result = 1;
		this.data = data;
	}
	
	public Result(T data, String code, String msg){
		this.data =data;
		this.code = code;
		this.msg = msg;
		this.result = -1;
	}
	
	public Result(T data, String code){
		this.data =data;
		this.code = code;
		this.result = -1;
	}

	public Result(ErrorCode error){
		this.code = error.getCode();
		this.msg = error.getMsg();
		this.result = -1;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}
}
