package cn.baisee.oa.service;

import java.util.List;
import java.util.Map;

import cn.baisee.oa.model.repairReceive.BaiseeRepairPlace;
import cn.baisee.oa.page.pagehelper.PageInfo;
/**
 * 报修字典-校区 业务逻辑层
 * @author liangfeng
 */
public interface BaiseeRepairPlaceService {

	/**
	 * 查询报修字典-校区表
	 * @param pageNum	每页显示多少条
	 * @param pageSize	显示第几页
	 * @param map		查询条件
	 * @return
	 */
	public PageInfo<BaiseeRepairPlace> getRepairPlace(int pageNum,int pageSize, Map<String, String> map);
	
	/**
	 * 查询报修字典-校区表
	 * @return
	 */
	public List<BaiseeRepairPlace> getRepairPlaceAll();
	
	/**
	 * 根据报id查询报修字典-校区表详情
	 * @param id	任务id
	 * @return
	 */
	public BaiseeRepairPlace getById(String id);
	
	/**
	 * 新增或修改任务
	 * @param repairPlace	分配报修任务对象
	 * @return
	 */
	public Integer saveOrUpdateAssigmentManage(BaiseeRepairPlace repairPlace);
	
	
	/**
	 * 批量删除任务
	 * @param ids	数组中装的是任务id
	 * @return
	 */
	public Integer delRepairPlace(String [] ids);
	
	/**
	 * 查询校区表中是否已有此校区名称
	 * @param pName	校区名称
	 * @return 员工集合
	 */
	public List<BaiseeRepairPlace> checkAssignmentPerson(String pName);
	
}
