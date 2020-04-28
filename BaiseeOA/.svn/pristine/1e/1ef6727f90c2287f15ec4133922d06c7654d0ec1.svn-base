package cn.baisee.oa.service;

import java.util.List;

import cn.baisee.oa.model.BaiseeUser;
import cn.baisee.oa.model.order.BaiseewOrder;
import cn.baisee.oa.page.pagehelper.PageInfo;

public interface BaiseewOrderService {
	
	 /**
	  * 查询列表 并根据姓名 或状态查询信息
	  * @param itemlableSearch
	  * @return
	  */
	public PageInfo<BaiseewOrder> getOrder(int pageNum, int pageSize ,String itemlableSearch,String contact);
	/**
	 * 更新联系状态为已联系
	 * @param id
	 * @return
	 */
	public int orderOldInformed(int id);
	

}
