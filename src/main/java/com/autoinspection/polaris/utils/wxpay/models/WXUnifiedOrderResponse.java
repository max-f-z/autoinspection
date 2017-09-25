/**
 * 
 */
package com.autoinspection.polaris.utils.wxpay.models;

/**
 * @author 王忠杰
 *
 */
public class WXUnifiedOrderResponse extends WXResponse {
	
	
	//预支付交易会话标识 
	private String prepay_id;
	//交易类型 调用接口提交的交易类型，取值如下：JSAPI，NATIVE，APP，,H5支付固定传MWEB
	private String trade_type;
	//二维码链接
	private String code_url;
	//重定向URL
	private String mweb_url;
	
	public String getPrepay_id() {
		return prepay_id;
	}
	public void setPrepay_id(String prepay_id) {
		this.prepay_id = prepay_id;
	}
	public String getTrade_type() {
		return trade_type;
	}
	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}
	public String getCode_url() {
		return code_url;
	}
	public void setCode_url(String code_url) {
		this.code_url = code_url;
	}
	public String getMweb_url() {
		return mweb_url;
	}
	public void setMweb_url(String mweb_url) {
		this.mweb_url = mweb_url;
	}
	
}
