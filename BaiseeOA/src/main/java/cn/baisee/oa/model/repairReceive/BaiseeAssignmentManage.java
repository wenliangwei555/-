package cn.baisee.oa.model.repairReceive;

import java.util.Date;

/**
 * 分配报修任务人员表  baisee_assignment_manage
 * @author liangfeng
 *
 */
public class BaiseeAssignmentManage {
	
	/**
	 * 主键
	 */
	private String id;
	
	/**
	 * 分配人id
	 */
	private String assignmentPersonId;
	/**
	 * 分配人名称
	 */
	private String assignmentPersonName;
	/**
	 * 创建人id
	 */
	private String creaatePersonId;
	/**
	 * 创建人名称
	 */
	private String creaatePersonName;
	/**
	 * 更新人人id
	 */
	private String updatePersonId;
	/**
	 * 更新人名称
	 */
	private String updatePersonName;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAssignmentPersonId() {
		return assignmentPersonId;
	}
	public void setAssignmentPersonId(String assignmentPersonId) {
		this.assignmentPersonId = assignmentPersonId;
	}
	public String getAssignmentPersonName() {
		return assignmentPersonName;
	}
	public void setAssignmentPersonName(String assignmentPersonName) {
		this.assignmentPersonName = assignmentPersonName;
	}
	public String getCreaatePersonId() {
		return creaatePersonId;
	}
	public void setCreaatePersonId(String creaatePersonId) {
		this.creaatePersonId = creaatePersonId;
	}
	public String getCreaatePersonName() {
		return creaatePersonName;
	}
	public void setCreaatePersonName(String creaatePersonName) {
		this.creaatePersonName = creaatePersonName;
	}
	public String getUpdatePersonId() {
		return updatePersonId;
	}
	public void setUpdatePersonId(String updatePersonId) {
		this.updatePersonId = updatePersonId;
	}
	public String getUpdatePersonName() {
		return updatePersonName;
	}
	public void setUpdatePersonName(String updatePersonName) {
		this.updatePersonName = updatePersonName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
