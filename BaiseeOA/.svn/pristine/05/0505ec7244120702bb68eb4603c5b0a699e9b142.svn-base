package cn.baisee.oa.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 学生成绩表
 * @author Wang Chunhuan
 *
 */
public class StudentScore {
	
    private int id;//成绩ID
	private String sid;//学生学号SID
	private String sname;// 学生姓名
    private String classId;//班级IDCLASSID
    private String cname;  // 班级名称
	private String s_type;//学籍S_TYPE
	private String term;//学期TERM
	private String subject;//科目SUBJECT
	private double scoer;//成绩SOCER
	private String ispublish; // 是否推出状态
	private String updateTime; // 更新时间
	private String openId; // Op的id
	private String rank;// 排名
	private String tid; //学期ID
	private String des;
	private Date updateTime1;
	
	public Date getUpdateTime1() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM
        try {
			return simpleDateFormat.parse(updateTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
	}
	public void setUpdateTime1(Date updateTime1) {
		this.updateTime1 = updateTime1;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getIspublish() {
		return ispublish;
	}
	public void setIspublish(String ispublish) {
		this.ispublish = ispublish;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public String getS_type() {
		return s_type;
	}
	public void setS_type(String s_type) {
		this.s_type = s_type;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
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
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	@Override
	public String toString() {
		return "StudentScore [id=" + id + ", sid=" + sid + ", sname=" + sname + ", classId=" + classId + ", cname="
				+ cname + ", s_type=" + s_type + ", term=" + term + ", subject=" + subject + ", score=" + scoer
				+ ", ispublish=" + ispublish + ", updateTime=" + updateTime + ", openId=" + openId + ", rank=" + rank
				+ ", tid=" + tid + "]";
	}

	
}
