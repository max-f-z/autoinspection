/**
 * 
 */
package com.autoinspection.polaris.utils.wxpay.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author ThinkPad
 *
 */
public class WXRefundOrderQueryResponse extends WXResponse {
	
	private String transaction_id;
	private String out_trade_no;
	private String total_fee;
	private String settlement_total_fee;
	private String fee_type;
	private String cash_fee;
	private String refund_count;
	
	private List<Map<String,String>> detailList = new ArrayList<Map<String,String>>();
	
	
	
	public List<Map<String, String>> getDetailList() {
		return detailList;
	}
	public void setDetailList(List<Map<String, String>> detailList) {
		this.detailList = detailList;
	}
	public String getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}
	public String getSettlement_total_fee() {
		return settlement_total_fee;
	}
	public void setSettlement_total_fee(String settlement_total_fee) {
		this.settlement_total_fee = settlement_total_fee;
	}
	public String getFee_type() {
		return fee_type;
	}
	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}
	public String getCash_fee() {
		return cash_fee;
	}
	public void setCash_fee(String cash_fee) {
		this.cash_fee = cash_fee;
	}
	public String getRefund_count() {
		return refund_count;
	}
	public void setRefund_count(String refund_count) {
		this.refund_count = refund_count;
	}
	
}
