package cn.baisee.oa.model.course;
/**
 * 课程教师表
 * @author Administrator
 *
 */
public class BaiseeCourseTeacher {

	private String cid;//课程老师主键
	private String ciId;//课程主键
	private String empId;//教师主键
	private String updateTs;//修改时间
	private String createTs;//新增时间
	private String sort;//排序
	
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getCiId() {
		return ciId;
	}
	public void setCiId(String ciId) {
		this.ciId = ciId;
	}
	
	
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
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
