/**
 * 
 */
package com.autoinspection.polaris.utils.wxpay.models;

/**
 * @author 王忠杰
 *
 */
public class WXAccessTokenResponse{// extends WXResponse{
	
//	access_token	接口调用凭证
//	expires_in	access_token接口调用凭证超时时间，单位（秒）
//	refresh_token	用户刷新access_token
//	openid	授权用户唯一标识
//	scope	用户授权的作用域，使用逗号（,）分隔
//	unionid	 当且仅当该移动应用已获得该用户的userinfo授权时，才会出现该字段
	
	//{"access_token":"ACCESS_TOKEN", "expires_in":7200, "refresh_token":"REFRESH_TOKEN", "openid":"OPENID", "scope":"SCOPE" }
	private String access_token;
	private String expires_in;
	private String refersh_token;
	private String openid;
	private String scope;
	private String unionid;
	//{"errcode":40030,"errmsg":"invalid refresh_token"}
	private String errcode;
	private String errmsg;
	
	
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}
	public String getRefersh_token() {
		return refersh_token;
	}
	public void setRefersh_token(String refersh_token) {
		this.refersh_token = refersh_token;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	public String getErrcode() {
		return errcode;
	}
	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
}
