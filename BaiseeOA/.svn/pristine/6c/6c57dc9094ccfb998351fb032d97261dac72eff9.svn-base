package cn.baisee.oa.dao.repairReceive;

import java.util.List;
import java.util.Map;

import cn.baisee.oa.core.datasource.Datasource;
import cn.baisee.oa.core.datasource.DatasourceTypes;
import cn.baisee.oa.model.repairReceive.BaiseeRepair;
/**
 * 日程表mapper接口
 */
@Datasource(DatasourceTypes.OA)
public interface BaiseeRepairMapper {
	
	/**
	 *  查询所有报修信息
	 * @return
	 */
	List<BaiseeRepair> selectRepairList(Map<String, String> map);
	
	/**
	 *  查询所有报修信息
	 * @return
	 */
	List<BaiseeRepair> selectHandleRepairList(Map<String, String> map);
	
	/**
	 * 根据id查询
	 * @param Id
	 * @return
	 */
	BaiseeRepair getRepairById(String Id);
	
	/**
	   *  新增
	 * @param calendar
	 * @return
	 */
	Integer addRepair(BaiseeRepair repair);
	
	/**
	 * 修改
	 * @param calendar
	 * @return
	 */
	Integer updateRepair(BaiseeRepair repair);
	
	/**
	 * 完成任务
	 * @param calendar
	 * @return
	 */
	Integer repairComplete(BaiseeRepair repair);
	
	/**
	 * 修改
	 * @param calendar
	 * @return
	 */
	Integer distributeTask(BaiseeRepair repair);
	
	/**
	 * 删除日程
	 * @param ids
	 * @return
	 */
	Integer delRepair(String[] ids);
	
	
	
}
