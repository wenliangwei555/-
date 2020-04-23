package cn.baisee.oa.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.baisee.oa.constants.AppConstants;
import cn.baisee.oa.dao.baisee.BaiseeDormMapper;
import cn.baisee.oa.exception.OAServiceException;
import cn.baisee.oa.model.SessionUserInfo;
import cn.baisee.oa.model.dorm.BaiseeDorm;
import cn.baisee.oa.model.dorm.BaiseeStudorm;
import cn.baisee.oa.model.student.BaiseeStudent;
import cn.baisee.oa.page.pagehelper.PageHelper;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.BaiseeDormService;
import cn.baisee.oa.utils.ParamUtils;

@Service
public class BaiseeDormServiceImpl implements BaiseeDormService {
	
	@Autowired
	private BaiseeDormMapper baiseeDormMapper;
	@Override
	public PageInfo<BaiseeDorm> queryDorm(HttpServletRequest request) throws OAServiceException {
		Map<String, Object> map = new HashMap<String, Object>();
		String itemlableSearch= request.getParameter("itemlableSearch");
		String claStatus= request.getParameter("claStatus");
		String floor= request.getParameter("floor");
		String dormStatus= request.getParameter("dormStatus");
		String choiceStuState= request.getParameter("choiceStuState");
		SessionUserInfo user = (SessionUserInfo)request.getSession().getAttribute(AppConstants.SESSION_USER_INFO);
		Integer pageNum = ParamUtils.getPageNumParameters(request);
		Integer pageSize = ParamUtils.getPageSizeParameters(request);
		PageHelper.startPage(pageNum, pageSize);
		map.put("itemlableSearch", itemlableSearch);
		map.put("claStatus", claStatus);
		map.put("floor", floor);
		map.put("dormStatus", dormStatus);
		map.put("choiceStuState", choiceStuState);
		map.put("stype", user.getUserType());
		List<BaiseeDorm> list =  baiseeDormMapper.selectDorm(map);
		PageInfo<BaiseeDorm> page = new PageInfo<BaiseeDorm>(list);
		return page;
	}
	@Override
	public List<BaiseeStudorm> queryCheckList(HttpServletRequest request) throws OAServiceException {
		Map<String, Object> map = new HashMap<String, Object>();
		String dormId= request.getParameter("dormId");
		map.put("dormid", dormId);
		List<BaiseeStudorm> list =  baiseeDormMapper.selectDormStu(map);
		return list;
	}
	@Override
	public PageInfo<BaiseeStudent> queryCheckStuList(HttpServletRequest request) throws OAServiceException {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer pageNum = ParamUtils.getPageNumParameters(request);
		Integer pageSize = 6;
		String dormId= request.getParameter("dormId");
		String itemlableSearch= request.getParameter("itemlableSearch");
		PageHelper.startPage(pageNum, pageSize);
		map.put("dormid", dormId);
		map.put("itemlableSearch", itemlableSearch);
		List<BaiseeStudent> list =  baiseeDormMapper.selectCheckStu(map);
		PageInfo<BaiseeStudent> page = new PageInfo<BaiseeStudent>(list);
		return page;
	}
	@Override
	public Integer insertStu(String stuId,String dormid) throws Exception {
		if("".equals(stuId)||"".equals(dormid)||stuId==null||dormid==null) {
			return 0;
		}
		return baiseeDormMapper.insertDormStu(stuId, dormid);
	}
	@Override
	public Integer deleteStu(String stuId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("stuId", stuId);
		return baiseeDormMapper.delDormStu(map);
	}
	@Override
	public BaiseeDorm selectDorm(HttpServletRequest request) throws OAServiceException {
		String dormId= request.getParameter("id");
		BaiseeDorm dorm =  baiseeDormMapper.queryDorm(dormId);
		return dorm;
	}
	@Override
	public Integer mergeDorm(BaiseeDorm dorm) throws Exception {
		Integer success=0;
		if("".equals(dorm.getId())||dorm.getId()==null) {
			BaiseeDorm dormtamp = baiseeDormMapper.queryDormNum(dorm.getDorm());
			if(dormtamp!=null) {
				return -2;
			}
			baiseeDormMapper.insertDorm(dorm);
			success = Integer.parseInt(dorm.getId());
		}else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("dormid", dorm.getId());
			List<BaiseeStudorm> list =  baiseeDormMapper.selectDormStu(map);
			if(list.size()==0) {
				BaiseeDorm dormtamp = baiseeDormMapper.queryDormNum(dorm.getDorm());
				if(dormtamp==null||dormtamp.getId().equals(dorm.getId())) {
					baiseeDormMapper.updateDorm(dorm);
				}else {
					return -2;
				}
				System.out.println(success);
			}else {
				success = -1;
			}
		}
		return success;
	}
	@Override
	public Integer deleteDorm(String dormId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dormid", dormId);
		return baiseeDormMapper.delDorm(map);
	}
}
