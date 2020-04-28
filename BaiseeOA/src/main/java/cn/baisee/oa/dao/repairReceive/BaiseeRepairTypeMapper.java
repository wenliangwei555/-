package cn.baisee.oa.dao.repairReceive;

import java.util.List;
import java.util.Map;

import cn.baisee.oa.core.datasource.Datasource;
import cn.baisee.oa.core.datasource.DatasourceTypes;
import cn.baisee.oa.model.repairReceive.BaiseeRepairType;
/**
 * 报修字典-故障地点表mapper接口
 */
@Datasource(DatasourceTypes.OA)
public interface BaiseeRepairTypeMapper {
	
	/**
	 * 查询报修字典-故障地点表
	 * @param map		查询条件
	 * @return
	 */
	public List<BaiseeRepairType> getRepairType(Map<String, String> map);
	
	/**
	 * 查询报修字典-故障地点表
	 * @return
	 */
	public List<BaiseeRepairType> getRepairTypeAll();
	
	/**
	 * 根据所属校区的id，查询该校区下的故障地点
	 * @param pId	所属校区id
	 * @return
	 */
	public List<BaiseeRepairType> getRepairTypeByPid(String pId);
	
	/**
	 * 根据校区的id集合，查询校区下是否有故障地点
	 * @param ids	校区id集合
	 * @return
	 */
	public List<BaiseeRepairType> getRepairTypeByPids(String[] ids);
	
	/**
	 * 根据报id查询报修字典-故障地点表详情
	 * @param id	任务id
	 * @return
	 */
	public BaiseeRepairType getById(String id);
	
	/**
	 * 新增任务
	 * @param repair	任务对象
	 * @return
	 */
	public Integer saveBaiseeRepairType(BaiseeRepairType repairType);
	
	/**
	 * 修改任务
	 * @param repair	任务对象
	 * @return
	 */
	public Integer updateBaiseeRepairType(BaiseeRepairType repairType);
	
	
	/**
	 * 批量删除任务
	 * @param ids	数组中装的是任务id
	 * @return
	 */
	public Integer delRepairType(String [] ids);
	
	/**
	 * 查询故障地点表中是否已有此故障点名称并且故障点名称是所属同一校区的
	 * @param tName	故障点名称	
	 * @param pId	所属校区id名称	
	 * @return 
	 */
	public List<BaiseeRepairType> checkTname(Map<String, String> map);
	
}
