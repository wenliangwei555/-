package cn.baisee.oa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.baisee.oa.dao.baisee.CommMapper;
import cn.baisee.oa.service.CommSysSequenceService;
@Service
public class CommSysSequenceServiceImpl implements CommSysSequenceService {
	@Autowired
	private CommMapper commMapper;
	
	@Override
	public String nextId(String prefix) {
		String key = commMapper.getSeq(prefix);
		return key;
	}

}
