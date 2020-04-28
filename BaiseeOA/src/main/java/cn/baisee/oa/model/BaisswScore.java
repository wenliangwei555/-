package cn.baisee.oa.model;

import java.io.Serializable;

public class BaisswScore implements Serializable{
	private String sname;  // 学生姓名
	private String subject;	// 科目
	private double scoer;	  //成绩
	private Integer id;
	private String cname;  // 班级名称
	private String sid;  //学生学号
	private String classid; // 班级Id
	private String stype;   // 状态   0试听   1正式
	private String term;	// 考试期数
	private Integer ispublish; // 待定
	private String updateTime; // 更新时间
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7833889182569305211L;

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public double getScoer() {
		return scoer;
	}

	public void setScoer(double scoer) {
		this.scoer = scoer;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getClassid() {
		return classid;
	}

	public void setClassid(String classid) {
		this.classid = classid;
	}

	public String getStype() {
		return stype;
	}

	public void setStype(String stype) {
		this.stype = stype;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public Integer getIspublish() {
		return ispublish;
	}

	public void setIspublish(Integer ispublish) {
		this.ispublish = ispublish;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "BaisswScore [sname=" + sname + ", subject=" + subject + ", scoer=" + scoer + ", id=" + id + ", cname="
				+ cname + ", sid=" + sid + ", classid=" + classid + ", stype=" + stype + ", term=" + term
				+ ", ispublish=" + ispublish + ", updateTime=" + updateTime + "]";
	}
	
}
