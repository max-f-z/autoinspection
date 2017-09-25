/**
 * 
 */
package com.autoinspection.polaris.utils.wxpay.models;

/**
 * @author 王忠杰
 *
 */
public class WXPayNotification {
	//微信分配的公众账号ID
	private String appid;
	//银行类型，采用字符串类型的银行标识 SPDB_CREDIT
	private String bankType;
	//现金支付金额 订单现金支付金额
	private String cashFee;
	//现金支付货币类型 CNY
	private String feeType;
	//用户是否关注公众账号，Y-关注，N-未关注，仅在公众账号类型支付有效
	private String subscribe;
	//微信支付分配的商户号
	private String mchid;
	//随机字符串
	private String nonceStr;
	//商户订单号
	private String outTradeNo;
	//业务结果 SUCCESS/FAIL
	private String resultCode;
	//错误代码
	private String errCode;
	//错误描述
	private String errCodeDesc;
	
	//返回状态码 SUCCESS/FAIL
	private String returnCode;
//	返回信息，如非空，为错误原因
//	签名失败
//	参数格式校验错误
	private String returnMsg;
	//签名
	private String sign;
	//支付完成时间，格式为yyyyMMddHHmmss
	private String timeEnd;
	//订单总金额，单位为分
	private String totalFee;
	//交易类型 JSAPI、NATIVE、APP
	private String tradeType;
	//微信支付订单号
	private String transactionId;
	
	private String openid;
	
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getBankType() {
		return bankType;
	}
	public void setBankType(String bankType) {
		this.bankType = bankType;
	}
	public String getCashFee() {
		return cashFee;
	}
	public void setCashFee(String cashFee) {
		this.cashFee = cashFee;
	}
	public String getFeeType() {
		return feeType;
	}
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}
	public String getSubscribe() {
		return subscribe;
	}
	public void setSubscribe(String subscribe) {
		this.subscribe = subscribe;
	}
	public String getMchid() {
		return mchid;
	}
	public void setMchid(String mchid) {
		this.mchid = mchid;
	}
	public String getNonceStr() {
		return nonceStr;
	}
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}
	public String getOutTradeNo() {
		return outTradeNo;
	}
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	public String getReturnMsg() {
		return returnMsg;
	}
	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getTimeEnd() {
		return timeEnd;
	}
	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}
	public String getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}	
	
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	public String getErrCode() {
		return errCode;
	}
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	public String getErrCodeDesc() {
		return errCodeDesc;
	}
	public void setErrCodeDesc(String errCodeDesc) {
		this.errCodeDesc = errCodeDesc;
	}
	public boolean isPaySuccess() {
		return "SUCCESS".equals(this.resultCode);
	}
}
