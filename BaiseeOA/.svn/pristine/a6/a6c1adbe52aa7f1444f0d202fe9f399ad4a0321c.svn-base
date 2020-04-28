package cn.baisee.oa.dao.repairReceive;

import java.util.List;
import java.util.Map;

import cn.baisee.oa.core.datasource.Datasource;
import cn.baisee.oa.core.datasource.DatasourceTypes;
import cn.baisee.oa.model.repairReceive.BaiseeReceive;
/**
 * 日程表mapper接口
 */
@Datasource(DatasourceTypes.OA)
public interface BaiseeReceiveMapper {
	
	/**
	 *  查询所有报修信息
	 * @return
	 */
	List<BaiseeReceive> selectReceiveList(Map<String, String> map);
	
	/**
	 * 根据id查询
	 * @param Id
	 * @return
	 */
	BaiseeReceive getReceiveById(String Id);
	
	/**
	   *  新增
	 * @param calendar
	 * @return
	 */
	Integer addReceive(BaiseeReceive receive);
	
	/**
	 * 修改
	 * @param calendar
	 * @return
	 */
	Integer updateReceive(BaiseeReceive receive);
	
	/**
	 * 完成任务
	 * @param calendar
	 * @return
	 */
	Integer completeReceive(BaiseeReceive receive);
	
	/**
	 * 取消任务
	 * @param calendar
	 * @return
	 */
	Integer cancelReceive(BaiseeReceive receive);
	
	/**
	 * 删除日程
	 * @param ids
	 * @return
	 */
	Integer delReceive(String[] ids);
	
	/**
	 * 查询申领开始时间到结束时间这个时间段的任务
	 * @param startTime	开始时间
	 * @param endTime	结束时间
	 * @return
	 */
	public List<BaiseeReceive> getStartTimeAndEndTime(Map<String, Object> map);
	
}
