package cn.baisee.oa.model.course;
/**
 * 一节课的
 * @author Administrator
 *
 */
public class BaiseeLessonTime {

	private String  tsId;//一节课的id
	private String courseOrder;//一节课的顺序编号
	private String beginTime;//开始时间
	private String endTime;//结束时间
	private String createTs;//新增时间
	private String updateTs;//修改时间
	private String sort;//排序
	public String getTsId() {
		return tsId;
	}
	public void setTsId(String tsId) {
		this.tsId = tsId;
	}
	public String getCourseOrder() {
		return courseOrder;
	}
	public void setCourseOrder(String courseOrder) {
		this.courseOrder = courseOrder;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getCreateTs() {
		return createTs;
	}
	public void setCreateTs(String createTs) {
		this.createTs = createTs;
	}
	public String getUpdateTs() {
		return updateTs;
	}
	public void setUpdateTs(String updateTs) {
		this.updateTs = updateTs;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	
	
	
}
