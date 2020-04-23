package cn.baisee.oa.dao.baisee;

import java.util.List;
import java.util.Map;

import cn.baisee.oa.core.datasource.Datasource;
import cn.baisee.oa.core.datasource.DatasourceTypes;
import cn.baisee.oa.model.BaiseeCalendar;
/**
 * 日程表mapper接口
 */
@Datasource(DatasourceTypes.OA)
public interface BaiseeCalendarMapper {
	
	/**
	 *  查询所有日程信息
	 * @return
	 */
	List<BaiseeCalendar> selectCalendarList(Map<String, Object> map);
	
	/**
	 * 根据id查询
	 * @param Id
	 * @return
	 */
	BaiseeCalendar getCalendarById(String Id);
	
	/**
	 * 根据日期查询
	 * @param date
	 * @return
	 */
	List<BaiseeCalendar> getCalendarByDate(String date);
	
	
	/**
	   *  新增
	 * @param calendar
	 * @return
	 */
	Integer addCalendar(BaiseeCalendar calendar);
	
	/**
	 * 修改
	 * @param calendar
	 * @return
	 */
	Integer updateCalendar(BaiseeCalendar calendar);
	
	/**
	 * 删除日程
	 * @param ids
	 * @return
	 */
	Integer delCalendar(String[] ids);
	
	
	
}
