package cn.baisee.oa.service;

import java.util.List;
import java.util.Map;

import cn.baisee.oa.model.repairReceive.BaiseeRepairItem;
import cn.baisee.oa.page.pagehelper.PageInfo;
/**
 * 报修字典-故障类型 业务逻辑层
 * @author liangfeng
 */
public interface BaiseeRepairItemService {

	/**
	 * 查询报修字典-故障类型表
	 * @param pageNum	每页显示多少条
	 * @param pageSize	显示第几页
	 * @param map		查询条件
	 * @return
	 */
	public PageInfo<BaiseeRepairItem> getRepairItem(int pageNum,int pageSize, Map<String, String> map);
	
	/**
	 * 查询报修字典-故障类型表
	 * @return
	 */
	public List<BaiseeRepairItem> getRepairItemAll();
	
	/**
	 * 根据所属校区的id，查询该校区下的故障类型
	 * @param tId	故障点id
	 * @return
	 */
	public List<BaiseeRepairItem> getRepairItemByTid(String tId);
	
	/**
	 * 根据故障地点id集合，查询故障地点下是否有故障类型
	 * @param ids	故障地点id集合
	 * @return
	 */
	public List<BaiseeRepairItem> getRepairItemByTids(String[] ids);
	
	/**
	 * 根据报id查询报修字典-故障类型表详情
	 * @param id	任务id
	 * @return
	 */
	public BaiseeRepairItem getById(String id);
	
	/**
	 * 新增或修改任务
	 * @param repairItem	任务对象
	 * @return
	 */
	public Integer saveOrUpdateAssigmentManage(BaiseeRepairItem repairItem);
	
	
	/**
	 * 批量删除任务
	 * @param ids	数组中装的是任务id
	 * @return
	 */
	public Integer delRepairItem(String [] ids);
	
	/**
	 * 查询故障类型表中是否已有此故障点名称并且故障点名称是所属同一校区的
	 * @param iName	故障类型名称	
	 * @param tId	故障点id名称	
	 * @return 
	 */
	public List<BaiseeRepairItem> checkIname(String iName, String tId);
	
}
