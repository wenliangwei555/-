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

import cn.baisee.oa.dao.baisee.BaiseeStagesMapper;
import cn.baisee.oa.model.tuition.TuitionStages;
import cn.baisee.oa.model.tuition.TuitionStagesRule;
import cn.baisee.oa.service.BaiseeStagesService;
import cn.baisee.oa.utils.ParamUtils;

@Service
public class BaiseeStagesServiceImpl implements BaiseeStagesService {

	@Autowired
	private BaiseeStagesMapper stagesMapper;

	@Override
	public List<TuitionStages> getTuStCycleList() {
		return stagesMapper.getTuStCycleList();
	}

	@Override
	public List<TuitionStagesRule> getTuitionStagesRuleList(String tuStId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tuStId", tuStId);
		return stagesMapper.getStagesRuleById(map);
	}
	@Override
	public ModelAndView toStagesInfo(HttpServletRequest request) {
		String tuStId=ParamUtils.getParameter(request, "tuStId");
		ModelAndView model=new ModelAndView("byStages/byStagesInfo");
		Map<String, Object> paramMap=new HashMap<String,Object>();
		paramMap.put("tuStId", tuStId);
		List<TuitionStagesRule> stagesRule=new ArrayList<TuitionStagesRule>();
		if (!"".equals(tuStId) && tuStId != null) {
			TuitionStages stages=stagesMapper.getStagesById(paramMap);
			stagesRule=stagesMapper.getStagesRuleById(paramMap);
			model.addObject("stages", stages);
			model.addObject("stagesList", stagesRule);
		}else {
			model.addObject("stages", new TuitionStages());
			model.addObject("stagesList", stagesRule);
		}
		return model;
	}
	@Override
	public ModelAndView saveOrUpdate(TuitionStages stages,HttpServletRequest request) {
		/*获取参数 */
		String tuStName=ParamUtils.getParameter(request, "tuStName");
		String tuStRuleMinimumTuition=ParamUtils.getParameter(request, "tuStRuleMinimumTuition");
		String tuStRuleHighestTuition=ParamUtils.getParameter(request, "tuStRuleHighestTuition");
		String tuStRuleTimeLimit=ParamUtils.getParameter(request, "tuStRuleTimeLimit");
		String tuStRuleId=ParamUtils.getParameter(request, "tuStRuleId");
		Map<String, Object> paramMap=new HashMap<String,Object>();
		paramMap.put("tuStId", stages.getTuStId());
		paramMap.put("tuStRuleId", tuStRuleId);
		paramMap.put("tuStName", tuStName);
		paramMap.put("tuStRuleMinimumTuition", tuStRuleMinimumTuition);
		paramMap.put("tuStRuleHighestTuition", tuStRuleHighestTuition);
		paramMap.put("tuStRuleTimeLimit", tuStRuleTimeLimit);
		ModelAndView model=new ModelAndView("byStages/byStagesInfo");
		if (tuStRuleId != null && !"".equals(tuStRuleId)) {//修改操作
			int r=stagesMapper.updateStages(stages);
			if (r>0) {
				stagesMapper.updateStagesRule(paramMap);
				model.addObject("prompt", "操作成功！！！");
			}
		}else {//添加操作
			if (StringUtils.isNotEmpty(stages.getTuStId())) {
				stagesMapper.updateStages(stages);
			}else {
				stagesMapper.insertStages(stages);
			}
			paramMap.put("tuStId", stages.getTuStId());
			stagesMapper.insertStagesRule(paramMap);
			model.addObject("prompt", "操作成功！！！");
		}
		List<TuitionStagesRule> stagesRule=stagesMapper.getStagesRuleById(paramMap);
		model.addObject("stages", stages);
		model.addObject("stagesList", stagesRule);
		return model;
	}

	@Override
	public Object delStages(HttpServletRequest request) {//删除分期规则方法
		String[] tuStId=ParamUtils.getParameters(request, "ids");
		Map<String, Object> map=new HashMap<String,Object>();
		Map<String, Object> paramMap=new HashMap<String,Object>();
		int r=0;
		for (int i = 0; i < tuStId.length; i++) {
			paramMap.put("tuStId", tuStId[i]);
			String[] tuStIds=stagesMapper.getTuStIdByTuId(paramMap);//查询要删除的分期规则是否和学费关联
			if (tuStIds.length <= 0) {//查询结果小于等于零的情况下进行删除操作
				r=stagesMapper.delStages(paramMap);
				stagesMapper.delStagesByStId(paramMap);
				stagesMapper.delStagesRule(paramMap);
			}
		}
		map.put("flag", "success");
		map.put("successCount", r);
		return map;
	}
	
	

}
