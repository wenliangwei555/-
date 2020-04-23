package cn.baisee.oa.service;

import cn.baisee.oa.model.BaiseeClockTime;
import cn.baisee.oa.page.pagehelper.PageInfo;

public interface BaiseeClockTimeService {
	/**
	 * 分页
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageInfo<BaiseeClockTime> getList(int pageNum,int pageSize);
	
	/**
	 * 根据Id查询
	 * @param ctId
	 * @return
	 */
	public BaiseeClockTime getTimeByID(String ctId);
	
	/**
	 * 修改/新增时间段
	 * @return
	 */
	public Integer addOrupdateTime(BaiseeClockTime clockTime);
	
	/**
	 * 删除
	 * @param ids
	 * @return
	 */
	public Integer deleTime(String [] ids);
}
