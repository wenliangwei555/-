package cn.baisee.oa.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.baisee.oa.dao.card.BaiseeCardBillMapper;
import cn.baisee.oa.model.BaiseeCardBill;
import cn.baisee.oa.page.pagehelper.PageHelper;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.BaiseeCardBillService;
@Service
public class BaiseeCardBillServiceImpl implements BaiseeCardBillService {
	@Autowired
	private BaiseeCardBillMapper cardBillMapper;
	@Override
	public PageInfo<BaiseeCardBill> getMoney(Map<String, String> map,int pageNum,int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<BaiseeCardBill> list = cardBillMapper.getMoney(map);
		PageInfo<BaiseeCardBill> page = new PageInfo<>(list);
		return page;
	}

}
