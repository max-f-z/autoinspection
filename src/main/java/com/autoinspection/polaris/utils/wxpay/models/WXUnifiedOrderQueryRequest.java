/**
 * 
 */
package com.autoinspection.polaris.utils.wxpay.models;

/**
 * @author 王忠杰
 *
 */
public class WXUnifiedOrderQueryRequest extends WXRequest{
	
	private String out_trade_no;

	/**
	 * @return the out_trade_no
	 */
	public String getOut_trade_no() {
		return out_trade_no;
	}

	/**
	 * @param out_trade_no the out_trade_no to set
	 */
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	
	

}
