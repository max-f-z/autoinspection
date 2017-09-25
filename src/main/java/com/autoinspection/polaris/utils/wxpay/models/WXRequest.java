/**
 * 
 */
package com.autoinspection.polaris.utils.wxpay.models;

/**
 * @author 王忠杰
 *
 */
public class WXRequest {
	
	//公众账号ID
	private String appid;
	//微信支付分配的商户号
	private String mch_id;
	//随机字符串，长度要求在32位以内
	private String nonce_str;
	//通过签名算法计算得出的签名值
	private String sign;
	//签名类型，默认为MD5，支持HMAC-SHA256和MD5。
	private String sign_type;
	
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
	public String getSign_type() {
		return sign_type;
	}
	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}

	
	
}
