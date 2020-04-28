package cn.baisee.oa.model;

import java.util.List;

public class baiseeTerm {
	private Integer id;  //主键
	private Integer term; // 第几期考试 期数
	private String cid;   //班级Id
	private String startdate;
	private String updatedate; // 更新时间
	private String des;  //描述
	
	private List<BaisswScore> scoreList; // 学生成绩表
	
	public List<BaisswScore> getScoreList() {
		return scoreList;
	}
	public void setScoreList(List<BaisswScore> scoreList) {
		this.scoreList = scoreList;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getTerm() {
		return term;
	}
	public void setTerm(Integer term) {
		this.term = term;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	
}
