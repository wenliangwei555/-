package cn.baisee.oa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.baisee.oa.dao.baisee.BaiseeClockTimeMapper;
import cn.baisee.oa.model.BaiseeClockTime;
import cn.baisee.oa.page.pagehelper.PageHelper;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.BaiseeClockTimeService;

@Service
public class BaiseeClockTimeServiceImpl implements  BaiseeClockTimeService {
	
	@Autowired
	private BaiseeClockTimeMapper clockTimeMapper;

	@Override
	public PageInfo<BaiseeClockTime> getList(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<BaiseeClockTime> list = clockTimeMapper.getClockTime(null);
		PageInfo<BaiseeClockTime> info = new PageInfo<BaiseeClockTime>(list);
		return info;
	}
	
	@Override
	public BaiseeClockTime getTimeByID(String ctId) {
		return clockTimeMapper.getTimeByID(ctId);
	}
	@Override
	public Integer addOrupdateTime(BaiseeClockTime clockTime) {
		if (clockTime!=null && clockTime.getCtId()!=null && clockTime.getCtId().length()>0) {
			return clockTimeMapper.updateTime(clockTime);
		}else {
			return clockTimeMapper.addTime(clockTime);
		}
	}

	@Override
	public Integer deleTime(String[] ids) {
		return clockTimeMapper.deleTime(ids);
	}

}
