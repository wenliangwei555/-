package cn.baisee.oa.service;

import java.util.List;
import java.util.Map;

import cn.baisee.oa.model.BaiseeCardBill;
import cn.baisee.oa.page.pagehelper.PageInfo;

public interface BaiseeCardBillService {

	public PageInfo<BaiseeCardBill> getMoney(Map<String, String> map,int pageNum,int pageSize);
	
}
