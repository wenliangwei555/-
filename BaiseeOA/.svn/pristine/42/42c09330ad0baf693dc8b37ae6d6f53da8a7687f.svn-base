package cn.baisee.oa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import cn.baisee.oa.model.tuition.TuitionStages;
import cn.baisee.oa.model.tuition.TuitionStagesRule;

public interface BaiseeStagesService {
	// 根据分期分组
	List<TuitionStages> getTuStCycleList();
	
	// 根据分期总表查询详细表
	List<TuitionStagesRule> getTuitionStagesRuleList(String tuStId);
	/**
	 * 跳转到添加或修改页面
	 * @param request
	 * @return
	 */
	public ModelAndView toStagesInfo(HttpServletRequest request);
	/**
	 * 添加或者修改分期规则
	 * @param request
	 * @return
	 */
	ModelAndView saveOrUpdate(TuitionStages stages,HttpServletRequest request);
	
	Object delStages(HttpServletRequest request);
}
