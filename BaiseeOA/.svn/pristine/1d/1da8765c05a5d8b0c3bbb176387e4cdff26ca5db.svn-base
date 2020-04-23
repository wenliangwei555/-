package cn.baisee.oa.service;

import cn.baisee.oa.model.BaiseeCalendar;
import cn.baisee.oa.page.pagehelper.PageInfo;
/**
 * 日程表 业务逻辑层
 */
public interface BaiseeCalendarService {

	/**
	 * 分页查询
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageInfo<BaiseeCalendar> getCalendarList(int pageNum,int pageSize);
	
	/**
	 * 根据Id查询
	 * @param cid
	 * @return
	 */
	public BaiseeCalendar getById(String cid);
	
	/**
	 * 根据日期查询
	 * @param date
	 * @return
	 */
	public BaiseeCalendar getByDate(String date);
	
	/**
	 * 新增或修改
	 * @param calendar
	 * @return
	 */
	public Integer saveOrUpdateCalendar(BaiseeCalendar calendar);
	
	
	/**
	 * 删除日程
	 * @param ids
	 * @return
	 */
	public Integer delCalendar(String [] ids);
	
}
