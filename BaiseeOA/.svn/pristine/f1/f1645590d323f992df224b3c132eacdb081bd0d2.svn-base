package cn.baisee.oa.service;

import java.util.List;
import java.util.Map;

import cn.baisee.oa.model.BaiseeVideoSubject;
import cn.baisee.oa.page.pagehelper.PageInfo;
/**
 * 视频科目 业务逻辑层
 * @author liangfeng
 */
public interface BaiseeVideoSubjectService {

	/**
	 * 查询视频科目列表
	 * @param pageNum	每页显示多少条
	 * @param pageSize	显示第几页
	 * @param map		查询条件
	 * @return
	 */
	public PageInfo<BaiseeVideoSubject> getVideoSubjects(int pageNum,int pageSize, Map<String, String> map);
	
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
	 * 新增或修改任务
	 * @param videoSubject	任务对象
	 * @param deptType		部门类型，高中开发，高中测试.....
	 * @return
	 */
	public Integer saveOrUpdateVideoSubject(BaiseeVideoSubject videoSubject);
	
	
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
