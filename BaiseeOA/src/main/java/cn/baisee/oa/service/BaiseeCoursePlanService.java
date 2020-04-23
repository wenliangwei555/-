package cn.baisee.oa.service;

import java.util.List;
import java.util.Map;

import cn.baisee.oa.model.course.BaiseeClassCourseId;
import cn.baisee.oa.model.course.BaiseeClassSystem;
import cn.baisee.oa.model.course.BaiseeCourseTeacher;

public interface BaiseeCoursePlanService {
	/**
	 * 新增课程时要更新的三个表
	 * @param clsSys 班级课制
	 * @param claCour 班级课程
	 * @param couTea 课程老师
	 * @param classId 班级id
	 * @return
	 */
	public String insertCourseAllInfo(BaiseeClassSystem clsSys,BaiseeClassCourseId claCour,BaiseeCourseTeacher couTea,String classId);
	
	/**
	 *  根据一些信息查出一条课程记录
	 * @param map
	 * @return
	 */
	public BaiseeClassCourseId getClassCourseIdByInfo(Map<String, String> map);

	/**
	 * 修改班级的某个课程
	 * @param claCour 班级课程的关联id
	 * @param couTea 课程老师表
	 * @param classId 班级id
	 * @param courseId 课程老师主键id
	 * @return
	 */
	public String updateCourseAll(BaiseeClassCourseId claCour, BaiseeCourseTeacher couTea, String classId,String courseId);

	/**
	 * 删除课程 （删除班级课程关联表，课程老师表）
	 * @param ccId 班级课程主键
	 * @param courseId 课程老师表的主键
	 * @return
	 */
	public String delClassCourse(String ccId,String courseTeacherId,BaiseeCourseTeacher couTea);

	/**
	 * 校验是否在同一星期，同一时间，同一班级重复添加数据
	 * @param map
	 * @return
	 */
	public String getCourseIdByInfo(Map<String, String> map);
}
