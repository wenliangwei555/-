package cn.baisee.oa.dao.baisee;

import java.util.List;

import cn.baisee.oa.model.course.BaiseeCourseSystem;

/**
 * 课制的数据访问层
 * @author Administrator
 *
 */	
public interface BaiseeCourseSystemMapper {

	/**
	 * 查询所有课制
	 * @return
	 */
	public List<BaiseeCourseSystem> selectAllCourseSystem();
}
