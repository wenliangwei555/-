package cn.baisee.oa.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cn.baisee.oa.core.file.FileDir;
import cn.baisee.oa.core.file.FileUploadUtil;
import cn.baisee.oa.dao.repairReceive.BaiseeRepairMapper;
import cn.baisee.oa.model.repairReceive.BaiseeRepair;
import cn.baisee.oa.page.pagehelper.PageHelper;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.BaiseeRepairService;
import cn.baisee.oa.utils.PropertiesUtil;
import cn.baisee.oa.utils.StringUtil;
/**
 * 物品报修 业务逻辑层实现
 * @author liangfeng
 */
@Service
public class BaiseeRepairServiceImpl implements BaiseeRepairService {

	@Autowired
	private BaiseeRepairMapper repairMapper ;
	
	@Override
	public PageInfo<BaiseeRepair> getRepairList(int pageNum, int pageSize, Map<String, String> map) {
		PageHelper.startPage(pageNum, pageSize);
		List<BaiseeRepair> list=repairMapper.selectRepairList(map);
		PageInfo<BaiseeRepair> page=new PageInfo<BaiseeRepair>(list);
		return page;
	}
	
	@Override
	public PageInfo<BaiseeRepair> getRepairHandleList(int pageNum, int pageSize, Map<String, String> map) {
		PageHelper.startPage(pageNum, pageSize);
		List<BaiseeRepair> list=repairMapper.selectHandleRepairList(map);
		PageInfo<BaiseeRepair> page=new PageInfo<BaiseeRepair>(list);
		return page;
	}
	
	@Override
	public BaiseeRepair getById(String id) {
		return repairMapper.getRepairById(id);
	}
	
	@Override
	public Integer saveOrUpdateRepair(BaiseeRepair repair) throws Exception {
		//判断报修任务的ID是否为空，如果为空，表示是更新任务，否则则是添加报修任务。
		if (StringUtil.isNotEmpty(repair.getId())) {
			return repairMapper.updateRepair(repair);
		}
		return repairMapper.addRepair(repair);
	}
	
	@Override
	public Integer distributeTask(BaiseeRepair repair) {
		return repairMapper.distributeTask(repair);
	}
	
	@Override
	public Integer repairComplete(BaiseeRepair repair) {
		return repairMapper.repairComplete(repair);
	}

	@Override
	public Integer delRepair(String[] ids) {
		return repairMapper.delRepair(ids);
	}

}
