package cn.baisee.oa.service;

import java.util.List;

import cn.baisee.oa.model.course.BaiseeCourse;
import cn.baisee.oa.page.pagehelper.PageInfo;

public interface BaiseeCourseService {

	public PageInfo<BaiseeCourse> selectCourseListByInfo(int pageNum,int pageSize,BaiseeCourse course);

	/**
	 * 新增和修改课程信息
	 * @param course
	 * @return
	 */
	public String addOrUpdateCourse(BaiseeCourse course);

	/**
	 * 根据id查询课程
	 * @param ciId
	 * @return
	 */
	public BaiseeCourse selectCourseById(String ciId);

	/**
	 * 删除课程
	 * @param ids
	 * @return
	 */
	public String deleteCourseByIds(String[] ids);

	/**
	 * 根据课程类型 查询所有课程
	 * @return
	 */
	public List<BaiseeCourse> selectCourseListByType(String courseType);

	/**
	 * 查询是否在同一课程下有同一课程内容
	 * @param courseType
	 * @param courseTtitle
	 * @return
	 */
	public List<BaiseeCourse> validateCourse(String courseType, String courseTtitle,String ciId);

}
