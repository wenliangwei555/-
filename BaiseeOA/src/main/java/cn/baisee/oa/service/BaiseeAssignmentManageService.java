package cn.baisee.oa.service;

import java.util.List;
import java.util.Map;


import cn.baisee.oa.model.repairReceive.BaiseeAssignmentManage;
import cn.baisee.oa.page.pagehelper.PageInfo;
/**
 * 分配报修任务人员 业务逻辑层
 * @author liangfeng
 */
public interface BaiseeAssignmentManageService {

	/**
	 * 查询报修任务分配人员
	 * @param pageNum	每页显示多少条
	 * @param pageSize	显示第几页
	 * @param map		查询条件
	 * @return
	 */
	public PageInfo<BaiseeAssignmentManage> getAssignmentManage(int pageNum,int pageSize, Map<String, String> map);
	
	/**
	 * 查询报修任务分配人员
	 * @return 分配表中在baisee_user表中对应的登录名
	 */
	public List<String> getAssignmentManageAll();
	
	/**
	 * 根据报id查询分配报修任务人员详情
	 * @param id	分配报修任务人员id
	 * @return
	 */
	public BaiseeAssignmentManage getById(String id);
	
	/**
	 * 新增或修改分配报修任务
	 * @param repair	分配报修任务对象
	 * @return
	 */
	public Integer saveOrUpdateAssigmentManage(BaiseeAssignmentManage assignmentManage);
	
	
	/**
	 * 删除报修管理任务
	 * @param ids	数组中装的是报修任务id
	 * @return
	 */
	public Integer delAssignmentManage(String [] ids);
	
	/**
	 * 根据分配人员id，查看分配表中是否有此分配人
	 * @param assignmentId	可分配任务的员工id
	 * @return 员工集合
	 */
	public List<BaiseeAssignmentManage> checkAssignmentPerson(String assignmentId);
	
}
