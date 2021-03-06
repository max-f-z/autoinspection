/**
 * 
 */
package com.autoinspection.polaris.utils.wxpay.models;

/**
 * @author ThinkPad
 *
 */
public class WXUnifiedOrderQueryResponse extends WXResponse {
	
	private String trade_state;
	private String trade_state_desc;
	private String openid;
	private String is_subscribe;
	private String bank_type;
	private String total_fee;
	private String fee_type;
	private String transaction_id;
	private String out_trade_no;
	private String attach;
	private String time_end;
	private String cash_fee;
	
	public String getTrade_state() {
		return trade_state;
	}
	
	public void setTrade_state(String trade_state) {
		this.trade_state = trade_state;
	}
	
	public String getTrade_state_desc() {
		return trade_state_desc;
	}
	
	public void setTrade_state_desc(String trade_state_desc) {
		this.trade_state_desc = trade_state_desc;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	
	public String getIs_subscribe() {
		return is_subscribe;
	}

	public void setIs_subscribe(String is_subscribe) {
		this.is_subscribe = is_subscribe;
	}

	public String getBank_type() {
		return bank_type;
	}

	public void setBank_type(String bank_type) {
		this.bank_type = bank_type;
	}

	public String getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}

	public String getFee_type() {
		return fee_type;
	}

	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
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

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getTime_end() {
		return time_end;
	}

	public void setTime_end(String time_end) {
		this.time_end = time_end;
	}

	public String getCash_fee() {
		return cash_fee;
	}

	public void setCash_fee(String cash_fee) {
		this.cash_fee = cash_fee;
	}
	
	
	
}
