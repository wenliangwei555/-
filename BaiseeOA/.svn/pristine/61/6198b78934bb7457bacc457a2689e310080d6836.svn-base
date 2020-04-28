package cn.baisee.oa.dao.baisee;

import java.util.List;
import java.util.Map;

import cn.baisee.oa.core.datasource.Datasource;
import cn.baisee.oa.core.datasource.DatasourceTypes;
import cn.baisee.oa.model.course.BaiseeClassCourse;
import cn.baisee.oa.model.course.BaiseeClassCourseId;
import cn.baisee.oa.model.course.BaiseeCourseTeacher;

@Datasource(DatasourceTypes.OA)
public interface BaiseeClassCourseMapper {

	/**
	 * 根据班级id查询所有的课程
	 * @param cid
	 * @return
	 */
	public List<BaiseeClassCourse> selectClassCourseByCid(String cid);
	/**
	 * 新增班级课程
	 * @param classCourseId
	 * @return
	 */
	public int insertClassCourseId(BaiseeClassCourseId classCourse);
	/**
	 * 根据一些信息查出一条课程记录
	 * @param map
	 * @return
	 */
	public BaiseeClassCourseId selectClassCourseIdByInfo(Map<String, String> map);
	/**
	 * 修改班级课程
	 * @param claCour
	 * @return
	 */
	public int updateClassCourse(BaiseeClassCourseId claCour);
	/**
	 * 根据主键删除班级课程
	 * @param ccId
	 * @return
	 */
	public int delClassCourseByCcId(String ccId);
	/**
	 * 校验是否在同一星期，同一时间，同一班级重复添加数据
	 * @param map
	 * @return
	 */
	public String getCourseIdByInfo(Map<String, String> map);
	/**
	 * 根据课程老师表得到的主键去班级课程表中查主键
	 * @param list
	 * @return
	 */
	public List<String> getListCtid(String list);
}
