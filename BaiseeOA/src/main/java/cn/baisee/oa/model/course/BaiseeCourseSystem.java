package cn.baisee.oa.model.course;
/**
 * 课制表
 * @author Administrator
 *
 */
public class BaiseeCourseSystem {

	
	private String  csId;//课制注解
	private String  lessonDescription;//课制描述（一周6节 2周11节）
	private String  weekCount;//星期总数量
	private String  createTs;//创建时间
	private String  updateTs;//更新时间
	private String  sort;//排序
	public String getCsId() {
		return csId;
	}
	public void setCsId(String csId) {
		this.csId = csId;
	}
	public String getLessonDescription() {
		return lessonDescription;
	}
	public void setLessonDescription(String lessonDescription) {
		this.lessonDescription = lessonDescription;
	}
	public String getWeekCount() {
		return weekCount;
	}
	public void setWeekCount(String weekCount) {
		this.weekCount = weekCount;
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
