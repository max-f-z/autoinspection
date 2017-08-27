package com.autoinspection.polaris.utils;

public class ErrorCode {
	public static final ErrorCode TOKEN_INVALID = new ErrorCode("1001", "tokenʧЧ");
	public static final ErrorCode USER_NOTFOUND = new ErrorCode("1002", "�Ҳ����û�");
	public static final ErrorCode INVALID_USR_OR_PWD = new ErrorCode("1003", "������û���������");
	public static final ErrorCode NOT_AUTHORIZED = new ErrorCode("1004", "Ȩ�޲���");
	public static final ErrorCode INVALID_PARAM = new ErrorCode("1005", "�Ƿ�����");
	public static final ErrorCode NOT_FOUND = new ErrorCode("1006", "δ�ҵ���Դ");
	public static final ErrorCode TOO_MANY_AUTH_CODE = new ErrorCode("1007", "���Ͷ���̫�࣬���Ժ�����");
	public static final ErrorCode ALREADY_SIGNED_UP = new ErrorCode("1008", "�Ѿ�ע�ᣬ�볢�Ե�½");
	public static final ErrorCode REGISTRATION_FULL = new ErrorCode("1008", "ʱ��δﵽԤԼ����");
	public static final ErrorCode EMPTY_AUTH_CODE = new ErrorCode("1009", "��֤��Ϊ��");
	public static final ErrorCode INVALID_AUTH_CODE = new ErrorCode("1010", "��֤�����");
	public static final ErrorCode ONCE_PER_DAY = new ErrorCode("1011", "ͬһ���ƺ�ÿ��ֻ��ԤԼһ��");
	
	private String code;
	private String msg;
	
	public ErrorCode(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public String getMsg() {
		return msg;
	}
	
	public String getCode() {
		return code;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
