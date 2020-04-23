package cn.baisee.oa.dao.baisee;

import java.util.List;
import java.util.Map;

import cn.baisee.oa.core.datasource.Datasource;
import cn.baisee.oa.core.datasource.DatasourceTypes;
import cn.baisee.oa.model.course.BaiseeCourse;

@Datasource(DatasourceTypes.OA)
public interface BaiseeCourseMapper {

	public List<BaiseeCourse> selectPageCourse(BaiseeCourse course);

	/**
	 * 新增课程
	 * @param course
	 * @return
	 */
	public int addCourse(BaiseeCourse course);

	/**
	 * 修改课程
	 * @param course
	 * @return
	 */
	public int updateCourse(BaiseeCourse course);

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
	public int deleteCourseByIds(String[] ids);
	
	/**
	 * 修改课程状态
	 * @param map
	 */
	public void updCourseStatus(Map<String, String> map);

	/**
	 * 查询是否在同一课程下有同一课程内容
	 * @param course
	 * @return
	 */
	public List<BaiseeCourse> selectCoursesByInfo(BaiseeCourse course);
	
}
