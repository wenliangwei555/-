package cn.baisee.oa.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.baisee.oa.dao.repairReceive.BaiseeReceiveMapper;
import cn.baisee.oa.model.repairReceive.BaiseeReceive;
import cn.baisee.oa.page.pagehelper.PageHelper;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.BaiseeReceiveService;
import cn.baisee.oa.utils.DateUtil;
import cn.baisee.oa.utils.StringUtil;
/**
 * 物品申领业务逻辑实现层
 * @author liangfeng
 */
@Service
public class BaiseeReceiveServiceImpl implements BaiseeReceiveService {

	@Autowired
	private BaiseeReceiveMapper receiveMapper ;
	
	@Override
	public PageInfo<BaiseeReceive> getReceiveList(int pageNum, int pageSize, Map<String, String> map) {
		PageHelper.startPage(pageNum, pageSize);
		List<BaiseeReceive> list=receiveMapper.selectReceiveList(map);
		PageInfo<BaiseeReceive> page=new PageInfo<BaiseeReceive>(list);
		return page;
	}
	
	@Override
	public BaiseeReceive getById(String id) {
		return receiveMapper.getReceiveById(id);
	}
	
	@Override
	public Integer saveOrUpdateReceive(BaiseeReceive receive) {
		//判断申领管理任务的id是否为空，如果为空表示是更新申领管理任务，否则是添加申领管理任务
		if (StringUtil.isNotEmpty(receive.getId())) {
			return receiveMapper.updateReceive(receive);
		}else {
			return receiveMapper.addReceive(receive);
		}
	}
	
	@Override
	public Integer completeReceive(BaiseeReceive receive) {
		return receiveMapper.completeReceive(receive);
	}
	
	@Override
	public Integer cancelReceive(BaiseeReceive receive) {
		return receiveMapper.cancelReceive(receive);
	}
	
	@Override
	public Integer delRepair(String[] ids) {
		return receiveMapper.delReceive(ids);
	}

	@Override
	public List<BaiseeReceive> getStartTimeAndEndTime(String startTime, String endTime) {
		Date startDate = DateUtil.parseDate(startTime);
		Date endDate = DateUtil.parseDate(endTime);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startTime", startDate);
		map.put("endTime", endDate);
		return receiveMapper.getStartTimeAndEndTime(map);
	}

}
