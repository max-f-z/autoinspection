/**
 * 
 */
package com.autoinspection.polaris.utils.wxpay.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author ThinkPad
 *
 */
public class WXResponse {
	@JsonIgnore
	private String xmlString;
	
	//返回状态码 SUCCESS / FAIL
	private String return_code;
	//返回信息 如果非空表示fail
	private String return_msg;
	//公众账号ID
	@JsonIgnore
	private String appid;
	//商户号
	@JsonIgnore
	private String mch_id;
	//随机字符串
	@JsonIgnore
	private String nonce_str;
	//sign
	@JsonIgnore
	private String sign;
	//业务结果 SUCCESS/FAIL
	private String result_code;
	//错误代码
	private String err_code;
	//错误代码描述
	private String err_code_des;
	
	public String getReturn_code() {
		return return_code;
	}
	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}
	public String getReturn_msg() {
		return return_msg;
	}
	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getMch_id() {
		return mch_id;
	}
	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}
	public String getNonce_str() {
		return nonce_str;
	}
	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getResult_code() {
		return result_code;
	}
	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}
	
	public String getErr_code() {
		return err_code;
	}
	public void setErr_code(String err_code) {
		this.err_code = err_code;
	}
	public String getErr_code_des() {
		return err_code_des;
	}
	public void setErr_code_des(String err_code_des) {
		this.err_code_des = err_code_des;
	}
	/**
	 * @return the xmlString
	 */
	public String getXmlString() {
		return xmlString;
	}
	/**
	 * @param xmlString the xmlString to set
	 */
	public void setXmlString(String xmlString) {
		this.xmlString = xmlString;
	}	
	
}
