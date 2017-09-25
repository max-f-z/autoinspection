/**
 * 
 */
package com.autoinspection.polaris.utils.wxpay.models;

/**
 * @author ThinkPad
 *
 */
public class WXUnifiedOrderRequest extends WXRequest{
	
	//mandatory fields
	
	//标价币种，符合ISO 4217标准的三位字母代码，默认人民币
	private String fee_type;
	//商品描述。
	private String body;
	//商户订单号
	private String out_trade_no;
	//标价金额 , 订单总金额，单位为分
	private String total_fee;
	//终端IP
	private String spbill_create_ip;
	
	//通知地址 异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url
	private String notify_url;
	//交易类型 NATIVE for 扫码支付
	private String trade_type;
	
	
	//other fields
	//可以为终端设备号(门店号或收银设备ID)，PC网页或公众号内支付可以传"WEB"
	private String device_info;  
	//商品详情
	private String detail;  
	//附加数据
	private String attach;  
	
	//交易起始时间	and 交易结束时间  格式为yyyyMMddHHmmss 20091225091010
	private String time_start;  
	private String time_expire; 
	//订单优惠标记 	订单优惠标记，使用代金券或立减优惠功能时需要的参数
	private String goods_tag; 
	//商品ID
	private String product_id; 
	//指定支付方式
	private String limit_pay; 
	//用户标识
	private String openid;
	//场景信息
	private String scene_info;
	
	public String getFee_type() {
		return fee_type;
	}
	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
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
	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}
	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
	}
	public String getNotify_url() {
		return notify_url;
	}
	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}
	public String getTrade_type() {
		return trade_type;
	}
	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}
	public String getDevice_info() {
		return device_info;
	}
	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	public String getTime_start() {
		return time_start;
	}
	public void setTime_start(String time_start) {
		this.time_start = time_start;
	}
	public String getTime_expire() {
		return time_expire;
	}
	public void setTime_expire(String time_expire) {
		this.time_expire = time_expire;
	}
	public String getGoods_tag() {
		return goods_tag;
	}
	public void setGoods_tag(String goods_tag) {
		this.goods_tag = goods_tag;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public String getLimit_pay() {
		return limit_pay;
	}
	public void setLimit_pay(String limit_pay) {
		this.limit_pay = limit_pay;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getScene_info() {
		return scene_info;
	}
	public void setScene_info(String scene_info) {
		this.scene_info = scene_info;
	}
	
	
	

}
