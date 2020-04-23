package cn.baisee.oa.model;

import java.util.Date;

/**
 * 家长绑定微信
 * @author ASUS
 *
 */
public class BaiseeParent {
	
	private String fid,
	f_parent,  //学生家长
	f_phone, //手机 号
	f_identity, //身份证
	sid, //学生的id
	f_openid; //关注时候的openid
	private Date f_bindingDate;
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	public String getF_parent() {
		return f_parent;
	}
	public void setF_parent(String f_parent) {
		this.f_parent = f_parent;
	}
	public String getF_phone() {
		return f_phone;
	}
	public void setF_phone(String f_phone) {
		this.f_phone = f_phone;
	}
	public String getF_identity() {
		return f_identity;
	}
	public void setF_identity(String f_identity) {
		this.f_identity = f_identity;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getF_openid() {
		return f_openid;
	}
	public void setF_openid(String f_openid) {
		this.f_openid = f_openid;
	}
	public Date getF_bindingDate() {
		return f_bindingDate;
	}
	public void setF_bindingDate(Date f_bindingDate) {
		this.f_bindingDate = f_bindingDate;
	}
	@Override
	public String toString() {
		return "BaiseeParent [fid=" + fid + ", f_parent=" + f_parent + ", f_phone=" + f_phone + ", f_identity="
				+ f_identity + ", sid=" + sid + ", f_openid=" + f_openid + ", f_bindingDate=" + f_bindingDate + "]";
	}
	
	

	
	
	

}
