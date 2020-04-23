package cn.baisee.oa.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import cn.baisee.oa.dao.baisee.BaiseeReturnMapper;
import cn.baisee.oa.exception.OAServiceException;
import cn.baisee.oa.model.student.BaiseeStudentReturn;
import cn.baisee.oa.model.student.BaiseeStudentReturnRule;
import cn.baisee.oa.page.pagehelper.PageHelper;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.ReturnService;
import cn.baisee.oa.utils.ParamUtils;
import cn.baisee.oa.utils.SessionUtil;
@Service
public class ReturnServiceImpl implements ReturnService {
	@Autowired
	private BaiseeReturnMapper returnMapper;
	@Override
	public ModelAndView getReturnList(HttpServletRequest request) throws OAServiceException {
		try {
			ModelAndView model=new ModelAndView("return/ret_list");
			int pageNum=ParamUtils.getPageNumParameters(request);
			int pageSize=ParamUtils.getPageSizeParameters(request);
			PageHelper.startPage(pageNum, pageSize);
			List<BaiseeStudentReturn> list=returnMapper.getReturnList();
			PageInfo<BaiseeStudentReturn> pageInfo=new PageInfo<BaiseeStudentReturn>(list);
			model.addObject("pageResult", pageInfo);
			return model;
		} catch (Exception e) {
			throw new OAServiceException("查询返费规则失败", e);
		}
		
	}

	@Override
	public Map<String, Object> delReturn(HttpServletRequest request) throws OAServiceException {
		try {
			String rtId=ParamUtils.getParameter(request, "rtId");
			Map<String, Object> map=new HashMap<String,Object>();
			Map<String, Object> paramMap=new HashMap<String,Object>();
			paramMap.put("rtId", rtId);
			String rid=returnMapper.getRid(paramMap);//查看要删除的返费规则是否与学费关联
			if (StringUtils.isEmpty(rid)) {//没有关联进行删除操作
				int r=returnMapper.delReturn(paramMap);
				if (r>0) {
					int tr=returnMapper.delRetuRule(paramMap);
					if (tr>0) {
						map.put("flag", "success");
					}
					map.put("flag", "error");
				}else {
					map.put("flag", "error");
				}
			}else {//如果关联则不能进行删除
				map.put("flag", "undeletable");
			}
			return map;
		} catch (Exception e) {
			throw new OAServiceException("删除返费规则失败", e);
		}
	}

	@Override
	public ModelAndView toReturnInfo(HttpServletRequest request) throws OAServiceException {
		try {
			String rtId=ParamUtils.getParameter(request, "rtId");
			ModelAndView model=new ModelAndView("return/ret_info");
			List<BaiseeStudentReturnRule> retuList=new ArrayList<BaiseeStudentReturnRule>();
			BaiseeStudentReturn retu=new BaiseeStudentReturn();
			Map<String, Object> paramMap=new HashMap<String,Object>();
			paramMap.put("rtId", rtId);
			if (rtId!=null && !"".equals(rtId)) {
				retu=returnMapper.getReturnById(paramMap);
				retuList=returnMapper.getReturnRuleById(paramMap);
				model.addObject("retuList", retuList);
				model.addObject("retu", retu);
			}else {
				model.addObject("retu", retu);
				model.addObject("retuList",retuList);
			}
			return model;
		} catch (Exception e) {
			throw new OAServiceException("跳转到返编辑页面失败", e);
		}
	}

	@Override
	public ModelAndView saveOrUpdate(HttpServletRequest request,BaiseeStudentReturn retu,BaiseeStudentReturnRule retuRule) {
		ModelAndView model=new ModelAndView("return/ret_info");
		Map<String, Object> map=new HashMap<String,Object>();
		if (StringUtils.isNotEmpty(retuRule.getRrid())) {//返费附表ID不为null进行修改
			returnMapper.updateReturn(retu);
			returnMapper.updateReturnRule(retuRule);
		}else {//返费附表ID为null进行添加
			retu.setCreateUser(SessionUtil.getUserInfo(request).getUserId());
			if (StringUtils.isNotEmpty(retu.getRtId())) {//如果返费主表ID不为空的话就执行修改，为空则执行添加
				returnMapper.updateReturn(retu);
			}else {
				returnMapper.saveReturn(retu);
			}
			retuRule.setRtId(retu.getRtId());
			returnMapper.saveReturnRule(retuRule);//添加返费附表信息
		}
		map.put("rtId", retu.getRtId());
		List<BaiseeStudentReturnRule> retuList=returnMapper.getReturnRuleById(map);
		model.addObject("retuList", retuList);
		model.addObject("retu", retu);
		model.addObject("prompt", "保存成功！");
		return model;
	}

	@Override
	public boolean getRetuByRtId(HttpServletRequest request) {//查看返费规则是否已存在想同类型
		Map<String, Object> map=new HashMap<String,Object>();
		String returnType=ParamUtils.getParameter(request, "returnType");
		String rtId=ParamUtils.getParameter(request, "rtId");
		String rrid=ParamUtils.getParameter(request, "rrid");
		map.put("returnType", returnType);
		map.put("rtId", rtId);
		if (StringUtils.isEmpty(rtId)) {//主表ID为null直接可以操作
			return true;
		}else {
			BaiseeStudentReturnRule retuRule=returnMapper.getRetuRuleByRtId(map);//主表ID不为null进行查询
			if (retuRule != null) {//查询到的对象是否为空，为空可直接进行操作，不为空进行判断
				if (retuRule.getRrid().equals(rrid)) {//判断页面传递过来的返费附表信息是否与查询到的一致，一致可执行操作，不一致则不可以进行操作
					return true;
				}else {
					return false;
				}
			}else {
				return true;
			}
		}
	}
	
	@Override
	public Map<String, Object> getRetuRule(HttpServletRequest request) {//根据返费主表ID查询附表详细规则
		String rtId=ParamUtils.getParameter(request, "rtId");
		Map<String, Object> paramMap=new HashMap<String,Object>();
		paramMap.put("rtId", rtId);
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("retuRule", returnMapper.getRetuRule(paramMap));
		return map;
	}
	
	
	
	
	
}
