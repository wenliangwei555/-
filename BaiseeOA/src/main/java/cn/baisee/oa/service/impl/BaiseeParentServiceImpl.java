package cn.baisee.oa.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.baisee.oa.dao.baisee.BaiseeParentMapper;
import cn.baisee.oa.model.BaiseeParent;
import cn.baisee.oa.service.BaiseeParentService;


@Service
public class BaiseeParentServiceImpl implements BaiseeParentService{
	
	@Autowired
	private BaiseeParentMapper baiseePaentMapper;
	
	public BaiseeParent getByPhone(String f_phone) {
		BaiseeParent baiseeParent = baiseePaentMapper.getByPhone(f_phone);
		return baiseeParent;
	}
	
	public int addParent(BaiseeParent baiseeParent) {
		baiseeParent.setFid(UUID.randomUUID().toString().replace("-", ""));
		return baiseePaentMapper.addParent(baiseeParent);
	}
	public BaiseeParent getByParent(String f_parent) {
		BaiseeParent baiseeParent = baiseePaentMapper.getByParent(f_parent);
		return baiseeParent;
	}
	
	public BaiseeParent getByOpenId(String f_openId) {
		BaiseeParent map = baiseePaentMapper.getByOpenId(f_openId);
		return map;
	}
	/**
	 * 根据sid查询出绑定过该学生的家长信息
	 * @param sid
	 * @return
	 */
	public List<BaiseeParent> getParentBySid(String sid){
		return baiseePaentMapper.getParent(sid);
	}
}
