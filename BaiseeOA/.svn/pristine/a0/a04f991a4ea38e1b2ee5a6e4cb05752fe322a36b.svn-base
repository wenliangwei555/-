package cn.baisee.oa.dao.repairReceive;

import java.util.List;
import java.util.Map;

import cn.baisee.oa.core.datasource.Datasource;
import cn.baisee.oa.core.datasource.DatasourceTypes;
import cn.baisee.oa.model.repairReceive.BaiseeRepairItem;
/**
 * 报修字典-故障类型表mapper接口
 */
@Datasource(DatasourceTypes.OA)
public interface BaiseeRepairItemMapper {
	
	/**
	 * 查询报修字典-故障类型表
	 * @param map		查询条件
	 * @return
	 */
	public List<BaiseeRepairItem> getRepairItem(Map<String, String> map);
	
	/**
	 * 查询报修字典-故障类型表
	 * @return
	 */
	public List<BaiseeRepairItem> getRepairItemAll();
	
	/**
	 * 根据故障点的id，查询该故障地点的故障类型
	 * @param tId	所属校区id
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
	 * 新增任务
	 * @param repair	任务对象
	 * @return
	 */
	public Integer saveBaiseeRepairItem(BaiseeRepairItem repairItem);
	
	/**
	 * 修改任务
	 * @param repair	任务对象
	 * @return
	 */
	public Integer updateBaiseeRepairItem(BaiseeRepairItem repairItem);
	
	
	/**
	 * 批量删除任务
	 * @param ids	数组中装的是任务id
	 * @return
	 */
	public Integer delRepairItem(String [] ids);
	
	/**
	 * 查询故障类型表中是否已有此故障点名称并且故障点名称是所属同一校区的
	 * @param map	查询条件	
	 * @return 
	 */
	public List<BaiseeRepairItem> checkIname(Map<String, String> map);
	
}
