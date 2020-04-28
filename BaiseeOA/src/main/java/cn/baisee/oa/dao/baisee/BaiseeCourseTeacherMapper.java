package cn.baisee.oa.dao.baisee;

import java.util.List;

import cn.baisee.oa.model.course.BaiseeCourseTeacher;

public interface BaiseeCourseTeacherMapper {

	/**
	 * 新增课程老师
	 * @param couTea
	 * @return
	 */
	public int insertCourseTeacher(BaiseeCourseTeacher couTea);
	/**
	 * 根据课程和老师查询课程老师表的id
	 * @param couTea
	 * @return
	 */
	public List<BaiseeCourseTeacher> getCourseTeacherByIds(BaiseeCourseTeacher couTea);
	/**
	 * 根据主键id删除课程老师表
	 * @param courseTeacherId
	 * @return
	 */
	public int delCourseTeacher(String courseTeacherId);
}
