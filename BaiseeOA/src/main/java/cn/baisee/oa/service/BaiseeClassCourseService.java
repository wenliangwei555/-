package cn.baisee.oa.service;

import java.util.List;

import cn.baisee.oa.model.course.BaiseeClassCourse;

public interface BaiseeClassCourseService {

	/**
	 * 根据班级id查询所有的课程
	 * @param cid
	 * @return
	 */
	public List<BaiseeClassCourse> selectClassCourseByCid(String cid);
}
