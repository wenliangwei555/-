package cn.baisee.oa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.baisee.oa.dao.baisee.BaiseeCalendarMapper;
import cn.baisee.oa.model.BaiseeCalendar;
import cn.baisee.oa.page.pagehelper.PageHelper;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.BaiseeCalendarService;
import cn.baisee.oa.utils.StringUtil;
/**
 * 日程表 业务逻辑层实现
 */
@Service
public class BaiseeCalendarServiceImpl implements BaiseeCalendarService {

	@Autowired
	private BaiseeCalendarMapper calendarMapper ;
	
	@Override
	public PageInfo<BaiseeCalendar> getCalendarList(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<BaiseeCalendar> list=calendarMapper.selectCalendarList(null);
		PageInfo<BaiseeCalendar> page=new PageInfo<BaiseeCalendar>(list);
		return page;
	}
	
	@Override
	public BaiseeCalendar getById(String cid) {
		return calendarMapper.getCalendarById(cid);
	}
	
	
	@Override
	public BaiseeCalendar getByDate(String date) {
		List<BaiseeCalendar> list = calendarMapper.getCalendarByDate(date);
		if (list!=null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public Integer saveOrUpdateCalendar(BaiseeCalendar calendar) {
		if (StringUtil.isNotEmpty(calendar.getcId())) {
			return calendarMapper.updateCalendar(calendar);
		}else {
			return calendarMapper.addCalendar(calendar);
		}
	}

	@Override
	public Integer delCalendar(String[] ids) {
		return calendarMapper.delCalendar(ids);
	}

}
