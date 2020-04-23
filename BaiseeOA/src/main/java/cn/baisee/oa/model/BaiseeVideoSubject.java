package cn.baisee.oa.model;

import java.util.Date;

/**
 * 视频科目管理表	baisee_video_subject
 * @author liangfeng
 *
 */
public class BaiseeVideoSubject {
	
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 老师名称
	 */
	private String teacherName;
	/**
	 * 科目名称
	 */
	private String subjectName;
	/**
	 * 科目路径
	 */
	private String subjectPath;
	/**
	 * 科目类别，例：高中开发、高中测试
	 */
	private String deptType;
	/**
	 * 创建人id
	 */
	private String cId;
	/**
	 * 创建人名称
	 */
	private String cName;
	/**
	 * 创建时间
	 */
	private Date cTime;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getcId() {
		return cId;
	}
	public void setcId(String cId) {
		this.cId = cId;
	}
	public String getcName() {
		return cName;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getSubjectPath() {
		return subjectPath;
	}
	public void setSubjectPath(String subjectPath) {
		this.subjectPath = subjectPath;
	}
	public String getDeptType() {
		return deptType;
	}
	public void setDeptType(String deptType) {
		this.deptType = deptType;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public Date getcTime() {
		return cTime;
	}
	public void setcTime(Date cTime) {
		this.cTime = cTime;
	}
}
