package cn.baisee.oa.dao.repairReceive;

import java.util.List;
import java.util.Map;

import cn.baisee.oa.core.datasource.Datasource;
import cn.baisee.oa.core.datasource.DatasourceTypes;
import cn.baisee.oa.model.repairReceive.BaiseeRepairPlace;
/**
 * 报修字典-所属校区表mapper接口
 */
@Datasource(DatasourceTypes.OA)
public interface BaiseeRepairPlaceMapper {
	
	/**
	 * 查询报修字典-所属校区表
	 * @param map		查询条件
	 * @return
	 */
	public List<BaiseeRepairPlace> getRepairPlace(Map<String, String> map);
	
	/**
	 * 查询报修字典-所属校区表
	 * @return
	 */
	public List<BaiseeRepairPlace> getRepairPlaceAll();
	
	/**
	 * 根据报id查询报修字典-所属校区表详情
	 * @param id	任务id
	 * @return
	 */
	public BaiseeRepairPlace getById(String id);
	
	/**
	 * 新增任务
	 * @param repair	任务对象
	 * @return
	 */
	public Integer saveBaiseeRepairPlace(BaiseeRepairPlace repairPlace);
	
	/**
	 * 修改任务
	 * @param repair	任务对象
	 * @return
	 */
	public Integer updateBaiseeRepairPlace(BaiseeRepairPlace repairPlace);
	
	
	/**
	 * 批量删除任务
	 * @param ids	数组中装的是任务id
	 * @return
	 */
	public Integer delRepairPlace(String [] ids);
	
	/**
	 * 查询所属校区表中是否已有此校区名称
	 * @param pName	校区名称
	 * @return 
	 */
	public List<BaiseeRepairPlace> checkPname(String pName);
	
}
