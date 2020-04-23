package cn.baisee.oa.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.baisee.oa.dao.baiseew.BaiseewOrderMapper;
import cn.baisee.oa.model.BaiseeUser;
import cn.baisee.oa.model.order.BaiseewOrder;
import cn.baisee.oa.page.pagehelper.PageHelper;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.BaiseewOrderService;

@Service
public class BaiseewOrderServiceImpl implements BaiseewOrderService {
	@Autowired
	private BaiseewOrderMapper orderMapper;

	public PageInfo<BaiseewOrder> getOrder(int pageNum, int pageSize ,String itemlableSearch,String contact) {
		PageHelper.startPage(pageNum, pageSize);// 开始分页
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("itemlableSearch", itemlableSearch);
		map.put("contact", contact);
		List<BaiseewOrder> list= orderMapper.getOrder(map);
		PageInfo<BaiseewOrder> pageInfo = new PageInfo<BaiseewOrder>(list);
		return pageInfo;
	}

	public int orderOldInformed(int id) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("id",id);
		return orderMapper.orderOldInformed(map);
	}

	

}
