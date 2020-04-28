package cn.baisee.oa.model.course;
/**
 * 上课时间段(一共几节课)
 * @author Administrator
 *
 */

import java.util.List;

public class BaiseeTimeStage {

	
	private String ctId;//时间段的id
	private String timeType;//时间的类型(0初中 1高中)
	private String timeStage;//时间段数
	private String createTs;//新增时间
	private String updateTs;//修改时间
	private String sort;//排序
	private List<BaiseeLessonTime> lessonTimes;//每节课的时间
	public List<BaiseeLessonTime> getLessonTimes() {
		return lessonTimes;
	}
	public void setLessonTimes(List<BaiseeLessonTime> lessonTimes) {
		this.lessonTimes = lessonTimes;
	}
	public String getTimeStage() {
		return timeStage;
	}
	public void setTimeStage(String timeStage) {
		this.timeStage = timeStage;
	}

	public String getCtId() {
		return ctId;
	}
	public void setCtId(String ctId) {
		this.ctId = ctId;
	}
	public String getTimeType() {
		return timeType;
	}
	public void setTimeType(String timeType) {
		this.timeType = timeType;
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
