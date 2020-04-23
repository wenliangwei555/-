package cn.baisee.oa.model.course;

import cn.baisee.oa.model.empinfo.Employee;

/**
 * 班级课程关联表
 * @author Administrator
 *
 */
public class BaiseeClassCourseId {
	private String ccId;//主键
	private String cid;//班级id
	private String ctId;//课程老师id
	private String week;//星期几
	private String crId;//机房id
	private String tsId;//时间顺序id
	private String updateTs;//修改时间
	private String createTs;//新增时间
	private String sort;//排序
	
	private Employee employee;//员工
	private BaiseeCourse course;//课程
	
	
	
	
	public String getCcId() {
		return ccId;
	}
	public void setCcId(String ccId) {
		this.ccId = ccId;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public BaiseeCourse getCourse() {
		return course;
	}
	public void setCourse(BaiseeCourse course) {
		this.course = course;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getCtId() {
		return ctId;
	}
	public void setCtId(String ctId) {
		this.ctId = ctId;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public String getCrId() {
		return crId;
	}
	public void setCrId(String crId) {
		this.crId = crId;
	}
	public String getTsId() {
		return tsId;
	}
	public void setTsId(String tsId) {
		this.tsId = tsId;
	}
	public String getUpdateTs() {
		return updateTs;
	}
	public void setUpdateTs(String updateTs) {
		this.updateTs = updateTs;
	}
	public String getCreateTs() {
		return createTs;
	}
	public void setCreateTs(String createTs) {
		this.createTs = createTs;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	
	

}
