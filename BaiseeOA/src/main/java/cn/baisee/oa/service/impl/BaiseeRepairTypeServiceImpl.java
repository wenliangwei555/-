package cn.baisee.oa.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.baisee.oa.dao.repairReceive.BaiseeRepairTypeMapper;
import cn.baisee.oa.model.repairReceive.BaiseeRepairType;
import cn.baisee.oa.page.pagehelper.PageHelper;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.BaiseeRepairTypeService;
import cn.baisee.oa.utils.StringUtil;
/**
 * 报修字典-故障地点 业务逻辑层实现
 * @author liangfeng
 */
@Service
public class BaiseeRepairTypeServiceImpl implements BaiseeRepairTypeService {

	@Autowired
	private BaiseeRepairTypeMapper repairTypeMapper;
	
	
	@Override
	public PageInfo<BaiseeRepairType> getRepairType(int pageNum, int pageSize, Map<String, String> map) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, pageSize);
		List<BaiseeRepairType> list=repairTypeMapper.getRepairType(map);
		PageInfo<BaiseeRepairType> page=new PageInfo<BaiseeRepairType>(list);
		return page;
	}

	@Override
	public BaiseeRepairType getById(String id) {
		// TODO Auto-generated method stub
		return repairTypeMapper.getById(id);
	}

	@Override
	public Integer saveOrUpdateAssigmentManage(BaiseeRepairType repairType) {
		// TODO Auto-generated method stub
		//判断如果id为空就是新增操作，反之则是更新操作
		if (StringUtil.isNotEmpty(repairType.getId())) {
			return repairTypeMapper.updateBaiseeRepairType(repairType);
		}
		return repairTypeMapper.saveBaiseeRepairType(repairType);
	}

	@Override
	public Integer delRepairType(String[] ids) {
		// TODO Auto-generated method stub
		return repairTypeMapper.delRepairType(ids);
	}

	@Override
	public List<BaiseeRepairType> checkTname(String tName, String pId) {
		// TODO Auto-generated method stub
		//拼装查询条件
		Map<String, String> map = new HashMap<String, String>();
		map.put("tName", tName);
		map.put("pId", pId);
		return repairTypeMapper.checkTname(map);
	}

	@Override
	public List<BaiseeRepairType> getRepairTypeAll() {
		// TODO Auto-generated method stub
		return repairTypeMapper.getRepairTypeAll();
	}

	@Override
	public List<BaiseeRepairType> getRepairTypeByPid(String pId) {
		// TODO Auto-generated method stub
		return repairTypeMapper.getRepairTypeByPid(pId);
	}

	@Override
	public List<BaiseeRepairType> getRepairTypeByPids(String[] ids) {
		// TODO Auto-generated method stub
		return repairTypeMapper.getRepairTypeByPids(ids);
	}
	

}
