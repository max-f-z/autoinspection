package com.autoinspection.polaris.vo.payment;

public class UnifiedOrderResp {
	private String payFlowNo;
	private String qrCodeKey;
	
	public String getPayFlowNo() {
		return payFlowNo;
	}
	public void setPayFlowNo(String payFlowNo) {
		this.payFlowNo = payFlowNo;
	}
	public String getQrCodeKey() {
		return qrCodeKey;
	}
	public void setQrCodeKey(String qrCodeKey) {
		this.qrCodeKey = qrCodeKey;
	}
}
