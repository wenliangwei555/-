package cn.baisee.oa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.baisee.oa.dao.baisee.BaiseeATokenMapper;
import cn.baisee.oa.model.BaiseeAToken;
import cn.baisee.oa.service.BaiseeATokenService;
@Service
public class BaiseeATokenServiceImpl implements BaiseeATokenService{
	@Autowired
	private BaiseeATokenMapper mapper;
	@Override
	public BaiseeAToken getToken() {
		return mapper.getToken();
	}

}
