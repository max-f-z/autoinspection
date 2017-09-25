package com.autoinspection.polaris.utils.wxpay;

/**
 * 常量
 */
public class WXPayConstants {

    public enum SignType {
        MD5, HMACSHA256
    }


    public static final String FAIL     = "FAIL";
    public static final String SUCCESS  = "SUCCESS";
    public static final String HMACSHA256 = "HMAC-SHA256";
    public static final String MD5 = "MD5";

    public static final String FIELD_SIGN = "sign";
    public static final String FIELD_SIGN_TYPE = "sign_type";
    
    
   
    
    public static final String WX_COMMON_APPID = "wx.common.appid";
    public static final String WX_COMMON_APPSECRET = "wx.common.appsecret";    
    public static final String WX_COMMON_DOMAIN = "wx.common.domain";
    public static final String WX_PAY_KEY = "wx.pay.key";
    public static final String WX_PAY_MCHID = "wx.pay.mchid";
    public static final String WX_PAY_NOTIFY_URL = "wx.pay.notify.url";
    public static final String WX_OAUTH2_REDIRECT_URL = "wx.oauth2.redirect.url";
    public static final String WX_OAUTH2_AUTHORISE_URL = "wx.oauth2.authorize.url";
    public static final String WX_OAUTH2_ACCESSTOKEN_URL = "wx.oauth2.accesstoken.url";
    public static final String WX_COMMON_READTIMEOUT = "wx.common.readtimeout";
    public static final String WX_COMMON_CONNECTTIMEOUT = "wx.common.connecttimeout";
    public static final String WX_REFUND_CERT_NAME = "wx.refund.cert.name";
    
    
    
}

