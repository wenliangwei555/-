package cn.baisee.oa.model;

import java.util.Date;
/**
 *	 学生绑定饭卡的实体类
 *
 */
public class BaiseeCardBinding {
	
	private String cardId;
	private String sid;
	private String sname;
	private String cardNum;
	private String cardNumId;
	private String cardState;
	private String type;
	private Date bindingTime;
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCardState() {
		return cardState;
	}
	public void setCardState(String cardState) {
		this.cardState = cardState;
	}
	public String getCardNumId() {
		return cardNumId;
	}
	public void setCardNumId(String cardNumId) {
		this.cardNumId = cardNumId;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getCardNum() {
		return cardNum;
	}
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	public Date getBindingTime() {
		return bindingTime;
	}
	public void setBindingTime(Date bindingTime) {
		this.bindingTime = bindingTime;
	}
	@Override
	public String toString() {
		return "BaiseeCardBinding [cardId=" + cardId + ", sid=" + sid + ", sname=" + sname + ", cardNum=" + cardNum
				+ ", cardNumId=" + cardNumId + ", cardState=" + cardState + ", type=" + type + ", bindingTime="
				+ bindingTime + "]";
	}

	
	
	
}
