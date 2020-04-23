package cn.baisee.oa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.baisee.oa.dao.baisee.BaiseeClassSystemMapper;
import cn.baisee.oa.model.course.BaiseeClassSystem;
import cn.baisee.oa.service.BaiseeClassSystemService;
import cn.baisee.oa.utils.StringUtil;

@Service
public class BaiseeClassSystemServiceImpl implements BaiseeClassSystemService {

	@Autowired
	private BaiseeClassSystemMapper claSysMapper;
	@Override
	public List<BaiseeClassSystem> selectClassSystemsByCid(String cid) {
		if(StringUtil.isNotEmpty(cid)){
			return claSysMapper.selectClassSystemsByCid(cid);
		}	
			return null;
	}

}
