package cn.baisee.oa.model;

/**
 * 校验用户返回的状态
 * 
 */
public enum UserCheckResult {
	VALID("正常登录"), 
	INCORRECT("用户名或密码错误"), 
	NOMENU("用户无权限设置，请联系管理员"), 
	ABNORMAL("用户状态异常，禁止登陆，请联系管理员"), 
	LEAVE("您已经离职,没有权限登录");

	private String msg;

	private UserCheckResult(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return this.msg;
	}
}
