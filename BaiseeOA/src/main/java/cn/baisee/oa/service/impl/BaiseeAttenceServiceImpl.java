package cn.baisee.oa.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.baisee.oa.dao.baisee.BaiseeAttenceMapper;
import cn.baisee.oa.model.BaiseeAttence;
import cn.baisee.oa.page.pagehelper.PageHelper;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.BaiseeAttenceService;

@Service
public class BaiseeAttenceServiceImpl implements BaiseeAttenceService {
	
	@Autowired
	private BaiseeAttenceMapper attenceMapper;

	@Override
	public PageInfo<BaiseeAttence> toList(int pageNum, int pageSize,String eId,String startTime,String endTime) {
		PageHelper.startPage(pageNum, pageSize);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("eId", eId);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		List<BaiseeAttence> list = attenceMapper.selectAttenceList(map);
		PageInfo<BaiseeAttence> page = new PageInfo<BaiseeAttence>(list);
		return page;
	}

}
