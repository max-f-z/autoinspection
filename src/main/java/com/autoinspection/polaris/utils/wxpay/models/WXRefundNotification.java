/**
 * 
 */
package com.autoinspection.polaris.utils.wxpay.models;

/**
 * @author 王忠杰
 *
 */
public class WXRefundNotification {
	
	private String returnCode;
	
	//微信分配的公众账号ID
	private String appid;	
	//微信支付分配的商户号
	private String mchid;
	//随机字符串
	private String nonceStr;
	//商户订单号
	private String reqInfo;
	
	//微信订单号
	private String transactionId;
	//商户订单号
	private String outTradeNo;
	//微信退款单号
	private String refundId;	
	//商户退款单号
	private String outRefundNo;	
	//订单金额
	private String totalFee;
	//申请退款金额
	private String refundFee;
	//退款金额
	private String settlementRefundFee;
	//应结订单金额
	private String settlementTotalFee;	
	//退款状态 SUCCESS-退款成功 	CHANGE-退款异常  REFUNDCLOSE—退款关闭
	private String refundStatus;
	//退款成功时间
	private String successTime;	
	//退款入账账户
	private String refundRecvAccout;
	//退款资金来源
	private String refundAccount;
	//退款发起来源
	private String refundRequestSource;
	
	
	/**
	 * @return the returnCode
	 */
	public String getReturnCode() {
		return returnCode;
	}
	/**
	 * @param returnCode the returnCode to set
	 */
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	/**
	 * @return the settlementRefundFee
	 */
	public String getSettlementRefundFee() {
		return settlementRefundFee;
	}
	/**
	 * @param settlementRefundFee the settlementRefundFee to set
	 */
	public void setSettlementRefundFee(String settlementRefundFee) {
		this.settlementRefundFee = settlementRefundFee;
	}
	/**
	 * @return the appid
	 */
	public String getAppid() {
		return appid;
	}
	/**
	 * @param appid the appid to set
	 */
	public void setAppid(String appid) {
		this.appid = appid;
	}
	/**
	 * @return the mchid
	 */
	public String getMchid() {
		return mchid;
	}
	/**
	 * @param mchid the mchid to set
	 */
	public void setMchid(String mchid) {
		this.mchid = mchid;
	}
	/**
	 * @return the nonceStr
	 */
	public String getNonceStr() {
		return nonceStr;
	}
	/**
	 * @param nonceStr the nonceStr to set
	 */
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}
	/**
	 * @return the reqInfo
	 */
	public String getReqInfo() {
		return reqInfo;
	}
	/**
	 * @param reqInfo the reqInfo to set
	 */
	public void setReqInfo(String reqInfo) {
		this.reqInfo = reqInfo;
	}
	/**
	 * @return the transactionId
	 */
	public String getTransactionId() {
		return transactionId;
	}
	/**
	 * @param transactionId the transactionId to set
	 */
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	/**
	 * @return the outTradeNo
	 */
	public String getOutTradeNo() {
		return outTradeNo;
	}
	/**
	 * @param outTradeNo the outTradeNo to set
	 */
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
	/**
	 * @return the refundId
	 */
	public String getRefundId() {
		return refundId;
	}
	/**
	 * @param refundId the refundId to set
	 */
	public void setRefundId(String refundId) {
		this.refundId = refundId;
	}
	/**
	 * @return the outRefundNo
	 */
	public String getOutRefundNo() {
		return outRefundNo;
	}
	/**
	 * @param outRefundNo the outRefundNo to set
	 */
	public void setOutRefundNo(String outRefundNo) {
		this.outRefundNo = outRefundNo;
	}
	/**
	 * @return the totalFee
	 */
	public String getTotalFee() {
		return totalFee;
	}
	/**
	 * @param totalFee the totalFee to set
	 */
	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}
	/**
	 * @return the refundFee
	 */
	public String getRefundFee() {
		return refundFee;
	}
	/**
	 * @param refundFee the refundFee to set
	 */
	public void setRefundFee(String refundFee) {
		this.refundFee = refundFee;
	}
	/**
	 * @return the settlementTotalFee
	 */
	public String getSettlementTotalFee() {
		return settlementTotalFee;
	}
	/**
	 * @param settlementTotalFee the settlementTotalFee to set
	 */
	public void setSettlementTotalFee(String settlementTotalFee) {
		this.settlementTotalFee = settlementTotalFee;
	}
	/**
	 * @return the refundStatus
	 */
	public String getRefundStatus() {
		return refundStatus;
	}
	/**
	 * @param refundStatus the refundStatus to set
	 */
	public void setRefundStatus(String refundStatus) {
		this.refundStatus = refundStatus;
	}
	/**
	 * @return the successTime
	 */
	public String getSuccessTime() {
		return successTime;
	}
	/**
	 * @param successTime the successTime to set
	 */
	public void setSuccessTime(String successTime) {
		this.successTime = successTime;
	}
	/**
	 * @return the refundRecvAccout
	 */
	public String getRefundRecvAccout() {
		return refundRecvAccout;
	}
	/**
	 * @param refundRecvAccout the refundRecvAccout to set
	 */
	public void setRefundRecvAccout(String refundRecvAccout) {
		this.refundRecvAccout = refundRecvAccout;
	}
	/**
	 * @return the refundAccount
	 */
	public String getRefundAccount() {
		return refundAccount;
	}
	/**
	 * @param refundAccount the refundAccount to set
	 */
	public void setRefundAccount(String refundAccount) {
		this.refundAccount = refundAccount;
	}
	/**
	 * @return the refundRequestSource
	 */
	public String getRefundRequestSource() {
		return refundRequestSource;
	}
	/**
	 * @param refundRequestSource the refundRequestSource to set
	 */
	public void setRefundRequestSource(String refundRequestSource) {
		this.refundRequestSource = refundRequestSource;
	}
	
	
}
