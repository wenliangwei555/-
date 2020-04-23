package cn.baisee.oa.dao.repairReceive;

import java.util.List;
import java.util.Map;

import cn.baisee.oa.core.datasource.Datasource;
import cn.baisee.oa.core.datasource.DatasourceTypes;
import cn.baisee.oa.model.repairReceive.BaiseeAssignmentManage;
/**
 * 分配报修任务人员表mapper接口
 */
@Datasource(DatasourceTypes.OA)
public interface BaiseeAssigmentManageMapper {
	
	/**
	 * 查询报修任务分配人员
	 * @param map		查询条件
	 * @return
	 */
	public List<BaiseeAssignmentManage> getAssignmentManage(Map<String, String> map);
	
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
	 * 新增分配报修任务
	 * @param repair	分配报修任务对象
	 * @return
	 */
	public Integer saveAssigmentManage(BaiseeAssignmentManage assignmentManage);
	
	/**
	 * 修改分配报修任务
	 * @param repair	分配报修任务对象
	 * @return
	 */
	public Integer updateAssigmentManage(BaiseeAssignmentManage assignmentManage);
	
	
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
	List<BaiseeAssignmentManage> checkAssignmentPerson(String assignmentId);
	
	
}
