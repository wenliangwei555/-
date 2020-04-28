package cn.baisee.oa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.baisee.oa.dao.baisee.BaiseeCardBindingMapper;
import cn.baisee.oa.model.BaiseeCardBinding;
import cn.baisee.oa.service.BaiseeCardBindingService;

@Service
public class BaiseeCardBindingServiceImpl implements BaiseeCardBindingService{

	@Autowired 
	private BaiseeCardBindingMapper cardBindingMapper;

	@Override
	public BaiseeCardBinding getCardBinding(String cardNum) {
		return cardBindingMapper.getCardBinding(cardNum);
	}
	
	
	
	
}
