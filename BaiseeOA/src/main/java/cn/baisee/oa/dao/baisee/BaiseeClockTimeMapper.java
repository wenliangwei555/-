package cn.baisee.oa.dao.baisee;

import java.util.List;
import java.util.Map;

import cn.baisee.oa.model.BaiseeClockTime;
/**
 *打卡参数 Mapper接口
 */
public interface BaiseeClockTimeMapper {

	/**
	 * 查询工作时间
	 * @param map
	 * @return
	 */
	List<BaiseeClockTime> getClockTime(Map<String,Object> map);
	
	/**
	 * 根据Id查询
	 * @param ctId
	 * @return
	 */
	BaiseeClockTime getTimeByID(String ctId);
	
	/**
	 * 新增
	 * @param clockTime
	 * @return
	 */
	Integer addTime(BaiseeClockTime clockTime);
	
	/**
	 * 修改
	 * @param clockTime
	 * @return
	 */
	Integer updateTime(BaiseeClockTime clockTime);
	
	/**
	 * 删除时间段
	 * @param ids
	 * @return
	 */
	Integer deleTime(String [] ids);
}
