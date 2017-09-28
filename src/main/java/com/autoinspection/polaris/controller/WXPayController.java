package com.autoinspection.polaris.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.autoinspection.polaris.model.entity.OrderPayEntity;
import com.autoinspection.polaris.model.entity.PaymentEntity;
import com.autoinspection.polaris.service.PaymentService;
import com.autoinspection.polaris.utils.BizException;
import com.autoinspection.polaris.utils.ErrorCode;
import com.autoinspection.polaris.utils.wxpay.models.WXUnifiedOrderQueryRequest;
import com.autoinspection.polaris.utils.wxpay.models.WXUnifiedOrderQueryResponse;
import com.autoinspection.polaris.utils.wxpay.models.WXUnifiedOrderRequest;
import com.autoinspection.polaris.utils.wxpay.models.WXUnifiedOrderResponse;
import com.autoinspection.polaris.utils.wxpay.utils.WXPayUtil;
import com.autoinspection.polaris.utils.wxpay.WXPayConstants;
import com.autoinspection.polaris.vo.Result;
import com.autoinspection.polaris.vo.payment.PaymentUpdateReq;
import com.autoinspection.polaris.vo.payment.UnifiedOrderReq;
import com.autoinspection.polaris.vo.payment.UnifiedOrderResp;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.mysql.jdbc.StringUtils;

@RestController
@RequestMapping(path = "/v1/pay")
@Transactional
public class WXPayController {
	
	@Autowired
	private PaymentService paymentService;
	
	@Value("${wx.appid}")
	private String appId;
	
	@Value("${wx.secret}")
	private String secret;
	
	@Value("${wx.mchid}")
	private String mchId;
	
	@Value("${wx.pay_sign_key}")
	private String key;
	
	private static String clientIP = "123.185.17.154";
	private static String tradeType = "NATIVE";
	
	@RequestMapping(path = "/unifiedOrder", method = RequestMethod.POST)
	public UnifiedOrderResp unifiedOrder(@RequestBody UnifiedOrderReq req) throws Exception {
		
		OrderPayEntity en = paymentService.getOrder(req.getOrderId());
		if (en == null) {
			throw new BizException(ErrorCode.CANNOT_FIND_ORDER);
		}
		if (en.getPayStatus() != 0) {
			throw new BizException(ErrorCode.INVALID_PAYSTATUS);
		}
		
		List<PaymentEntity> payments = paymentService.getPaymentsByOrderId(req.getOrderId());
		
		if (payments != null) {
			for (PaymentEntity p : payments) {
				if (p.getPayStatus() == 1) {
					throw new BizException(ErrorCode.ALREADY_PAID);
				}
				
				// 状态置为取消 2
				paymentService.updatePaymentStatus(p.getId(), 2);
			}
		}
		
		PaymentEntity payment = new PaymentEntity();
		payment.setFlowNo(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32));
		payment.setOrderId(req.getOrderId());
		payment.setPayStatus(0);
		payment.setAmount(en.getTotal());
		
		WXUnifiedOrderRequest request = new WXUnifiedOrderRequest();
		request.setAppid(appId);
		request.setMch_id(mchId);
		request.setNotify_url("http://9tts.cn/v1/pay");
		request.setNonce_str(WXPayUtil.generateNonceStr());
		request.setOut_trade_no(payment.getFlowNo());
		request.setBody("玖通轮胎订单");
		DecimalFormat decimalFormat=new DecimalFormat("#");
		request.setTotal_fee(decimalFormat.format(payment.getAmount()*100));
		request.setTrade_type(tradeType);
		request.setAttach("玖通轮胎订单");
		request.setSpbill_create_ip(clientIP);
		
		WXUnifiedOrderResponse response = doUnifiedOrder(request);
		if( !"SUCCESS".equals(response.getReturn_code())) {
			throw new BizException(ErrorCode.UNIFIEDORDER_FAILED);
		}
		paymentService.insertPayment(payment);
		
		UnifiedOrderResp resp = new UnifiedOrderResp();
		resp.setPayFlowNo(payment.getFlowNo());
		resp.setQrCodeKey(response.getCode_url());
		return resp;
	}
	
	public WXUnifiedOrderResponse doUnifiedOrder(WXUnifiedOrderRequest request) throws Exception {
		Map<String, String> data = new HashMap<String, String>();    	
    	data.put("appid",request.getAppid());
    	data.put("mch_id",request.getMch_id());
    	data.put("notify_url", request.getNotify_url());
    	data.put("nonce_str", request.getNonce_str());
    	data.put("attach",request.getAttach());
    	data.put("body", request.getBody());
    	data.put("spbill_create_ip", request.getSpbill_create_ip());
    	if(!StringUtils.isNullOrEmpty( request.getOpenid() ))
    		data.put("openid", request.getOpenid());
    	data.put("out_trade_no", request.getOut_trade_no());
    	data.put("total_fee", request.getTotal_fee());
    	data.put("trade_type",request.getTrade_type());   	
    	
    	String sign = null;    	
    	try {
	    	sign = WXPayUtil.generateSignedXml(data, key ,WXPayConstants.SignType.MD5);
	    	request.setSign(sign);
    	}catch(Exception ex) {
    		throw ex;
    	}
    	
    	HttpClient httpClient = getHttpClient();

        String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";        
        
        HttpPost httpPost = new HttpPost(url);        

        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(60000).setConnectTimeout(60000).build();
        httpPost.setConfig(requestConfig);

        StringEntity postEntity = new StringEntity(sign, "UTF-8");
        httpPost.addHeader("Content-Type", "text/xml");
        httpPost.addHeader("User-Agent", "wxpay sdk java v1.0 " + request.getMch_id());  // TODO: 很重要，用来检测 sdk 的使用情况，要不要加上商户信息？
        httpPost.setEntity(postEntity);

        HttpResponse httpResponse = httpClient.execute(httpPost);
        HttpEntity httpEntity = httpResponse.getEntity();
        String responseString = EntityUtils.toString(httpEntity, "UTF-8");
        
        Map<String, String> responseMap = WXPayUtil.xmlToMap(responseString);
        WXUnifiedOrderResponse response = new WXUnifiedOrderResponse();
        response.setXmlString(responseString);
        response.setAppid(responseMap.get("appid"));
		response.setMch_id(responseMap.get("mch_id"));
		response.setReturn_code(responseMap.get("return_code"));
		response.setReturn_msg(responseMap.get("return_msg"));
		response.setNonce_str(responseMap.get("nonce_str"));	
		response.setSign(responseMap.get("sign"));
		response.setResult_code(responseMap.get("result_code"));
		response.setErr_code(responseMap.get("err_code"));
		response.setErr_code_des(responseMap.get("err_code_des"));
		response.setPrepay_id(responseMap.get("prepay_id"));
		response.setCode_url(responseMap.get("code_url"));
		response.setTrade_type(responseMap.get("trade_type"));
		response.setMweb_url(responseMap.get("mweb_url"));
        
		return response;
	}
	
	private HttpClient getHttpClient() {
		BasicHttpClientConnectionManager connManager = new BasicHttpClientConnectionManager(
                RegistryBuilder.<ConnectionSocketFactory>create()
                        .register("http", PlainConnectionSocketFactory.getSocketFactory())
                        .register("https", SSLConnectionSocketFactory.getSocketFactory())
                        .build(),
                null,
                null,
                null
        );
    	
    	HttpClient httpClient = HttpClientBuilder.create().setConnectionManager(connManager).build();
		return httpClient;
	}
	
	@RequestMapping(value = "/unifiedorder/image")
	public void showIICodeImage(@RequestParam("qrcodekey")String qrcodekey , HttpServletResponse httpresponse) throws Exception {
		int width = 300; 
        int height = 300;		
		Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();        
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); 
        try {
        	httpresponse.setContentType("image/jpeg"); 
        	httpresponse.setDateHeader("expries", -1);  
        	httpresponse.setHeader("Cache-Control", "no-cache");  
        	httpresponse.setHeader("Pragma", "no-cache"); 
        	
            BitMatrix bitMatrix = new MultiFormatWriter().encode(qrcodekey,BarcodeFormat.QR_CODE, width, height, hints); 
            MatrixToImageWriter.writeToStream(bitMatrix, "gif", httpresponse.getOutputStream()); 
            
        }catch (Exception e) { 
        	httpresponse.setStatus(400);
        } 
	}

	@RequestMapping(path = "/unifiedorder/queryByPayFlowNo")
	public WXUnifiedOrderQueryResponse queryUnifiedOrderDetail(@RequestParam("payFlowNo") String payFlowNo) throws Exception {
		PaymentEntity en = paymentService.getPaymentByFlowNo(payFlowNo);
		if (en == null) {
			throw new BizException(ErrorCode.INVALID_FLOWNO);
		}
		
		WXUnifiedOrderQueryRequest request = new WXUnifiedOrderQueryRequest();
		request.setAppid(appId);
		request.setMch_id(mchId);
		request.setNonce_str(WXPayUtil.generateNonceStr());
		request.setOut_trade_no(payFlowNo);
		
		WXUnifiedOrderQueryResponse response= unifiedOrderQuery(request);
		if( !"SUCCESS".equals(response.getReturn_code())) {
			throw new BizException(ErrorCode.QUERYDETAIL_FAILED);
		}
		
		if( !"SUCCESS".equals(response.getResult_code())) {
			throw new BizException(ErrorCode.QUERYDETAIL_FAILED);
		}
		
		if ("SUCCESS".equals(response.getTrade_state())) {
			PaymentUpdateReq req = new PaymentUpdateReq();
			List<Long> orderIds = new ArrayList<Long>();
			orderIds.add(en.getOrderId());
			req.setItems(orderIds);
			req.setStatus(1);
			paymentService.updateStatus(req, 1);
			paymentService.updatePaymentStatus(en.getId(), 1);
		}
		
		return response;
	}
	
	public WXUnifiedOrderQueryResponse unifiedOrderQuery(WXUnifiedOrderQueryRequest request) throws Exception {
		Map<String, String> data = new HashMap<String, String>();    	
    	data.put("appid",request.getAppid());
    	data.put("mch_id",request.getMch_id());
    	data.put("nonce_str", request.getNonce_str());
    	data.put("out_trade_no", request.getOut_trade_no());
    	
    	String sign = null;    	
    	try {
	    	sign = WXPayUtil.generateSignedXml(data, key,WXPayConstants.SignType.MD5);
    	}catch(Exception ex) {
    		throw ex;
    	}
    	
    	//build http client
    	HttpClient httpClient = getHttpClient();

        String url = "https://api.mch.weixin.qq.com/pay/orderquery";        
        
        HttpPost httpPost = new HttpPost(url);        

        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(60000).setConnectTimeout(60000).build();
        httpPost.setConfig(requestConfig);

        StringEntity postEntity = new StringEntity(sign, "UTF-8");
        httpPost.addHeader("Content-Type", "text/xml");
        httpPost.addHeader("User-Agent", "wxpay sdk java v1.0 " + request.getMch_id());
        httpPost.setEntity(postEntity);

        HttpResponse httpResponse = httpClient.execute(httpPost);
        HttpEntity httpEntity = httpResponse.getEntity();
        String responseString = EntityUtils.toString(httpEntity, "UTF-8");
        
        WXUnifiedOrderQueryResponse response = new WXUnifiedOrderQueryResponse();		
		Map<String, String> responseMap = WXPayUtil.xmlToMap(responseString);	
		response.setXmlString(responseString);
		response.setAppid(responseMap.get("appid"));
		response.setMch_id(responseMap.get("mch_id"));
		response.setReturn_code(responseMap.get("return_code"));
		response.setReturn_msg(responseMap.get("return_msg"));
		response.setNonce_str(responseMap.get("nonce_str"));	
		response.setSign(responseMap.get("sign"));
		response.setResult_code(responseMap.get("result_code"));
		response.setErr_code(responseMap.get("err_code"));
		response.setErr_code_des(responseMap.get("err_code_des"));	
		response.setTrade_state(responseMap.get("trade_state"));
		response.setTrade_state_desc(responseMap.get("trade_state_desc"));		
		response.setOpenid(responseMap.get("openid"));
		response.setIs_subscribe(responseMap.get("is_subscribe"));
		response.setBank_type(responseMap.get("bank_type"));
		response.setTotal_fee(responseMap.get("total_fee"));
		response.setFee_type(responseMap.get("fee_type"));
		response.setTransaction_id(responseMap.get("transaction_id"));
		response.setOut_trade_no(responseMap.get("out_trade_no"));
		response.setAttach(responseMap.get("attach"));
		response.setTime_end(responseMap.get("time_end"));
		response.setCash_fee(responseMap.get("cash_fee"));
        
		return response;
	}
}
