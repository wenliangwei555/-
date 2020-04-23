package cn.baisee.oa.model;

import java.util.Date;

/**
 * 学生请假表
 * @author liangfeng
 *
 */
public class BaiseeAToken {
	
	/**
	 * 主键
	 */
	private Long id;
	/**
	 * token
	 */
	private String aToken;
	/**
	 * ticket
	 */
	private String ticket;

	/**
	 * 创建时间
	 */
	private Date createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getaToken() {
		return aToken;
	}

	public void setaToken(String aToken) {
		this.aToken = aToken;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "BaiseeAToken{" +
				"id=" + id +
				", aToken='" + aToken + '\'' +
				", ticket='" + ticket + '\'' +
				", createTime=" + createTime +
				'}';
	}
}
