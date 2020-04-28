package cn.baisee.oa.model;

import java.io.Serializable;

import cn.baisee.oa.utils.StringUtil;

/**
 * 用户通知信息表
 * @author dongchao
 */
public class BaiseeNotice implements Serializable{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8008066163613456623L;
	
	/**
	 * 通知状态  0未读
	 */
	public final static String NOTICE_STATUS_0 = "0";
	/**
	 * 通知状态  1已读
	 */
	public final static String NOTICE_STATUS_1 = "1";
	/**
	 * 通知状态  2已处理
	 */
	public final static String NOTICE_STATUS_2 = "2";
	/**
	 * 通知状态  3已关闭
	 */
	public final static String NOTICE_STATUS_3 = "3";
	
	/**
	 * id
	 * 
	 */
	private Integer id;
	/**
	 * 标题
	 * 
	 */
	private String title;
	/**
	 * 内容
	 * 
	 */
	private String content;
	/**
	 * 接收人id，为空则为所有用户
	 * 
	 */
	private String toUserId;
	/**
	 * 接收人id，为空则为所有用户
	 * 
	 */
	private String toUserName;
	/**
	 * 发送人id
	 * 
	 */
	private String fromUserId;
	/**
	 * 发送人名称
	 * 
	 */
	private String fromUserName;
	
	/**
	 * 状态
	 * 0 未处理
	 * 1 已处理
	 */
	private String status;
	 /**
	  * 更新时间
	  */
	private String updateTime;
	
	/**
	 * 创建时间
	 */
	private String createTime;
	
	/**
	 * 辅助查询
	 */
	private String updateStartTime;
	private String updateEndTime;
	/**
	 * 辅助查询
	 */
	private String createStartTime;
	private String createEndTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getToUserId() {
		return toUserId;
	}

	public void setToUserId(String toUserId) {
		this.toUserId = toUserId;
	}

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(String fromUserId) {
		this.fromUserId = fromUserId;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateStartTime() {
		return updateStartTime;
	}

	public void setUpdateStartTime(String updateStartTime) {
		this.updateStartTime = updateStartTime;
	}

	public String getUpdateEndTime() {
		return updateEndTime;
	}

	public void setUpdateEndTime(String updateEndTime) {
		this.updateEndTime = updateEndTime;
	}

	public String getCreateStartTime() {
		return createStartTime;
	}

	public void setCreateStartTime(String createStartTime) {
		this.createStartTime = createStartTime;
	}

	public String getCreateEndTime() {
		return createEndTime;
	}

	public void setCreateEndTime(String createEndTime) {
		this.createEndTime = createEndTime;
	}

	@Override
	public String toString() {
		return "BaiseeNotice{" +
				"id=" + id +
				", title='" + title + '\'' +
				", content='" + content + '\'' +
				", toUserId='" + toUserId + '\'' +
				", toUserName='" + toUserName + '\'' +
				", fromUserId='" + fromUserId + '\'' +
				", fromUserName='" + fromUserName + '\'' +
				", status='" + status + '\'' +
				", updateTime='" + updateTime + '\'' +
				", createTime='" + createTime + '\'' +
				", updateStartTime='" + updateStartTime + '\'' +
				", updateEndTime='" + updateEndTime + '\'' +
				", createStartTime='" + createStartTime + '\'' +
				", createEndTime='" + createEndTime + '\'' +
				'}';
	}
}
