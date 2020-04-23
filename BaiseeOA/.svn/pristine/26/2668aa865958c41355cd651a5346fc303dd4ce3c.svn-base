package cn.baisee.oa.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.baisee.oa.dao.repairReceive.BaiseeAssigmentManageMapper;
import cn.baisee.oa.model.repairReceive.BaiseeAssignmentManage;
import cn.baisee.oa.page.pagehelper.PageHelper;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.BaiseeAssignmentManageService;
import cn.baisee.oa.utils.StringUtil;
/**
 * 分配报修任务人员 业务逻辑层实现
 * @author liangfeng
 */
@Service
public class BaiseeAssigmentManageServiceImpl implements BaiseeAssignmentManageService {

	@Autowired
	private BaiseeAssigmentManageMapper assigmentManageMapper ;
	
	@Override
	public PageInfo<BaiseeAssignmentManage> getAssignmentManage(int pageNum, int pageSize, Map<String, String> map) {
		PageHelper.startPage(pageNum, pageSize);
		List<BaiseeAssignmentManage> list=assigmentManageMapper.getAssignmentManage(map);
		PageInfo<BaiseeAssignmentManage> page=new PageInfo<BaiseeAssignmentManage>(list);
		return page;
	}
	
	@Override
	public List<String> getAssignmentManageAll() {
		return assigmentManageMapper.getAssignmentManageAll();
		
	}
	
	@Override
	public BaiseeAssignmentManage getById(String id) {
		return assigmentManageMapper.getById(id);
	}
	
	@Override
	public Integer saveOrUpdateAssigmentManage(BaiseeAssignmentManage assignmentManage) {
		//判断ID是否为空，如果为空，表示是更新任务，否则则是添加任务。
		if (StringUtil.isNotEmpty(assignmentManage.getId())) {
			return assigmentManageMapper.updateAssigmentManage(assignmentManage);
		}
		return assigmentManageMapper.saveAssigmentManage(assignmentManage);
	}
	
	@Override
	public Integer delAssignmentManage(String[] ids) {
		return assigmentManageMapper.delAssignmentManage(ids);
	}
	

	@Override
	public List<BaiseeAssignmentManage> checkAssignmentPerson(String assignmentId) {
		List<BaiseeAssignmentManage> list=assigmentManageMapper.checkAssignmentPerson(assignmentId);
		return list;
	}

}
