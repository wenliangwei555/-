package cn.baisee.oa.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.baisee.oa.dao.pas.PasHisTaskMapper;
import cn.baisee.oa.page.pagehelper.PageHelper;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.HisTaskService;
@Service
public class HisTaskServiceImpl implements HisTaskService {

	@Autowired
	private PasHisTaskMapper baiseeHisTaskMapper;
	@Override
	public PageInfo<Map<String, String>> selectInsIdByUserId(int pageNum, int pageSize, String userId) {
		PageHelper.startPage(pageNum, pageSize);// 开始分页
		Map<String, Object> parmaMap = new HashMap<String, Object>();
		parmaMap.put("userId", userId);
		List<Map<String, String>> paramList = baiseeHisTaskMapper.selectHisPageByMap(parmaMap);
		PageInfo<Map<String, String>> pageInfo = new PageInfo<>(paramList);
		return pageInfo;
	}

}
