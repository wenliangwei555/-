package cn.baisee.oa.dao.baisee;

import java.util.List;
import java.util.Map;

import cn.baisee.oa.core.datasource.Datasource;
import cn.baisee.oa.core.datasource.DatasourceTypes;
import cn.baisee.oa.model.BaiseeVideoSubject;
/**
 * 视频科目表mapper接口
 */
@Datasource(DatasourceTypes.OA)
public interface BaiseeVideoSubjectMapper {
	
	/**
	 * 查询视频科目列表
	 * @param pageNum	每页显示多少条
	 * @param pageSize	显示第几页
	 * @param map		查询条件
	 * @return
	 */
	public List<BaiseeVideoSubject> getVideoSubjects(Map<String, String> map);
	
	/**
	 * 查询视频科目列表
	 * @return
	 */
	public List<BaiseeVideoSubject> getVideoSubjectAll();
	
	/**
	 * 根据id，查询详情
	 * @param id	视频科目id
	 * @return
	 */
	public List<BaiseeVideoSubject> getVideoSubjectById(String id);
	
	
	/**
	 * 新增任务
	 * @param videoSubject	任务对象
	 * @return
	 */
	public Integer saveVideoSubject(BaiseeVideoSubject videoSubject);
	
	
	/**
	 * 修改任务
	 * @param videoSubject	任务对象
	 * @return
	 */
	public Integer updateVideoSubject(BaiseeVideoSubject videoSubject);
	
	/**
	 * 批量删除任务
	 * @param ids	数组中装的是任务id
	 * @return
	 */
	public Integer delVideoSubject(String [] ids);
	
	/**
	 * 校验该老师下是否有该科目
	 * @param subjectName	科目名称	
	 * @param teacherName	老师名称	
	 * @return 
	 */
	public List<BaiseeVideoSubject> checkTname(String subjectName, String teacherName);
	
}
