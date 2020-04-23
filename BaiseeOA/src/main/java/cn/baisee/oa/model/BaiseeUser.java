package cn.baisee.oa.model;

import java.io.Serializable;

/**
 *
 */
public class BaiseeUser  implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2384213125499670297L;
	/**
	 * 用户ID
	 */
	private String userId;
	/**
	 * 用户姓名
	 */
	private String userName;
	/**
	 * 登陆名称
	 */
	private String loginName;
	/**
	 * 登陆密码
	 */
	private String loginPwd;
	/**
	 * 最后更新时间
	 */
	private String lastUpdateTime;
	/**
	 * 用户状态
	 */
	private String status;
	/**
	 * 用户类型
	 */
	private String userType;
	/**
	 * 是否可删除
	 */
	private String canDelete;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName == null ? null : loginName.trim();
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd == null ? null : loginPwd.trim();
	}

	public String getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getCanDelete() {
		return canDelete;
	}

	public void setCanDelete(String canDelete) {
		this.canDelete = canDelete;
	}
	
	
}
