package com.autoinspection.polaris.utils;

public class ErrorCode {
	public static final ErrorCode TOKEN_INVALID = new ErrorCode("1001", "token失效");
	public static final ErrorCode USER_NOTFOUND = new ErrorCode("1002", "找不到用户");
	public static final ErrorCode INVALID_USR_OR_PWD = new ErrorCode("1003", "错误的用户名或密码");
	public static final ErrorCode NOT_AUTHORIZED = new ErrorCode("1004", "权限不足");
	public static final ErrorCode INVALID_PARAM = new ErrorCode("1005", "非法参数");
	public static final ErrorCode NOT_FOUND = new ErrorCode("1006", "未找到资源");
	public static final ErrorCode TOO_MANY_AUTH_CODE = new ErrorCode("1007", "发送短信太多，请稍后再试");
	public static final ErrorCode ALREADY_SIGNED_UP = new ErrorCode("1008", "已经注册，请尝试登陆");
	public static final ErrorCode REGISTRATION_FULL = new ErrorCode("1009", "时间段达到预约上线");
	public static final ErrorCode EMPTY_AUTH_CODE = new ErrorCode("1010", "验证码为空");
	public static final ErrorCode INVALID_AUTH_CODE = new ErrorCode("1011", "验证码错误");
	public static final ErrorCode ONCE_PER_DAY = new ErrorCode("1012", "ͬ同一车牌号每天只能预约一次");
	public static final ErrorCode EXISTING_PLATE = new ErrorCode("1013", "ͬ已经存在该车牌号");
	public static final ErrorCode VEHICLETYPE_TIRES_DO_NOT_MATCH = new ErrorCode("1014", "ͬ车辆类型与轮胎数不匹配");
	public static final ErrorCode INSPECTIONID_EXISTS = new ErrorCode("1015", "已添加过维修单，请更新维修单");
	public static final ErrorCode NO_SERVICE_PRICE = new ErrorCode("1016", "存在没有设置的用户-服务价格关系");
	public static final ErrorCode CANNOT_FIND_ORDER = new ErrorCode("1017", "找不到订单");
	public static final ErrorCode INVALID_PAYSTATUS = new ErrorCode("1018", "订单状态不正确");
	
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
