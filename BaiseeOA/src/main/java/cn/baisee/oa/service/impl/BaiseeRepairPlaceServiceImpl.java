package cn.baisee.oa.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.baisee.oa.dao.repairReceive.BaiseeRepairPlaceMapper;
import cn.baisee.oa.model.repairReceive.BaiseeRepairPlace;
import cn.baisee.oa.page.pagehelper.PageHelper;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.BaiseeRepairPlaceService;
import cn.baisee.oa.utils.StringUtil;
/**
 * 报修字典-地理位置 业务逻辑层实现
 * @author liangfeng
 */
@Service
public class BaiseeRepairPlaceServiceImpl implements BaiseeRepairPlaceService {

	@Autowired
	private BaiseeRepairPlaceMapper repairplaceMapper;
	
	
	
	@Override
	public PageInfo<BaiseeRepairPlace> getRepairPlace(int pageNum, int pageSize, Map<String, String> map) {
		PageHelper.startPage(pageNum, pageSize);
		List<BaiseeRepairPlace> list=repairplaceMapper.getRepairPlace(map);
		PageInfo<BaiseeRepairPlace> page=new PageInfo<BaiseeRepairPlace>(list);
		return page;
	}

	@Override
	public BaiseeRepairPlace getById(String id) {
		// TODO Auto-generated method stub
		return repairplaceMapper.getById(id);
	}

	@Override
	public Integer saveOrUpdateAssigmentManage(BaiseeRepairPlace repairPlace) {
		// TODO Auto-generated method stub
		//判断如果id为空就是新增操作，反之则是更新操作
		if (StringUtil.isNotEmpty(repairPlace.getId())) {
			return repairplaceMapper.updateBaiseeRepairPlace(repairPlace);
		}
		return repairplaceMapper.saveBaiseeRepairPlace(repairPlace);
	}

	@Override
	public Integer delRepairPlace(String[] ids) {
		// TODO Auto-generated method stub
		return repairplaceMapper.delRepairPlace(ids);
	}

	@Override
	public List<BaiseeRepairPlace> checkAssignmentPerson(String pName) {
		// TODO Auto-generated method stub
		return repairplaceMapper.checkPname(pName);
	}

	@Override
	public List<BaiseeRepairPlace> getRepairPlaceAll() {
		// TODO Auto-generated method stub
		return repairplaceMapper.getRepairPlaceAll();
	}

	

}
