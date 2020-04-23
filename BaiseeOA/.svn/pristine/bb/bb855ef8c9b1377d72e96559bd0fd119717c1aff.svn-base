package cn.baisee.oa.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.baisee.oa.dao.repairReceive.BaiseeRepairItemMapper;
import cn.baisee.oa.model.repairReceive.BaiseeRepairItem;
import cn.baisee.oa.page.pagehelper.PageHelper;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.BaiseeRepairItemService;
import cn.baisee.oa.utils.StringUtil;
/**
 * 报修字典-故障类型 业务逻辑层实现
 * @author liangfeng
 */
@Service
public class BaiseeRepairItemServiceImpl implements BaiseeRepairItemService {

	@Autowired
	private BaiseeRepairItemMapper repairItemMapper;
	
	
	@Override
	public PageInfo<BaiseeRepairItem> getRepairItem(int pageNum, int pageSize, Map<String, String> map) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, pageSize);
		List<BaiseeRepairItem> list=repairItemMapper.getRepairItem(map);
		PageInfo<BaiseeRepairItem> page=new PageInfo<BaiseeRepairItem>(list);
		return page;
	}

	@Override
	public List<BaiseeRepairItem> getRepairItemAll() {
		// TODO Auto-generated method stub
		return repairItemMapper.getRepairItemAll();
	}

	@Override
	public List<BaiseeRepairItem> getRepairItemByTid(String tId) {
		// TODO Auto-generated method stub
		return repairItemMapper.getRepairItemByTid(tId);
	}

	@Override
	public BaiseeRepairItem getById(String id) {
		// TODO Auto-generated method stub
		return repairItemMapper.getById(id);
	}

	@Override
	public Integer saveOrUpdateAssigmentManage(BaiseeRepairItem repairItem) {
		// TODO Auto-generated method stub
		//判断如果id为空就是新增操作，反之则是更新操作
		if (StringUtil.isNotEmpty(repairItem.getId())) {
			return repairItemMapper.updateBaiseeRepairItem(repairItem);
		}
		return repairItemMapper.saveBaiseeRepairItem(repairItem);
	}

	@Override
	public Integer delRepairItem(String[] ids) {
		// TODO Auto-generated method stub
		return repairItemMapper.delRepairItem(ids);
	}

	@Override
	public List<BaiseeRepairItem> checkIname(String iName, String tId) {
		// TODO Auto-generated method stub
		//拼装查询条件
		Map<String, String> map = new HashMap<String, String>();
		map.put("iName", iName);
		map.put("tId", tId);
		return repairItemMapper.checkIname(map);
	}

	@Override
	public List<BaiseeRepairItem> getRepairItemByTids(String[] ids) {
		// TODO Auto-generated method stub
		return repairItemMapper.getRepairItemByTids(ids);
	}

}
