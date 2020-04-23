package cn.baisee.oa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.baisee.oa.dao.baisee.BaiseeSysParamMapper;
import cn.baisee.oa.model.BaiseeSysParam;
import cn.baisee.oa.service.SysParamService;
@Service
public class SysParamServiceImpl implements SysParamService {

	@Autowired
	private BaiseeSysParamMapper sysParamDao;
	
	@Override
	public List<BaiseeSysParam> queryAll() {
		List<BaiseeSysParam> sysparams = sysParamDao.queryAll();
		return sysparams;
	}

	@Override
	public BaiseeSysParam getInitParameter(Integer id) {
		return sysParamDao.readTheParameter(id);
	}

}
