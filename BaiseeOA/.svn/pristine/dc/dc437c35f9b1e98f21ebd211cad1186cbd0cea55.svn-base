package cn.baisee.oa.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import cn.baisee.oa.model.student.BaiseeStudentRefund;
import cn.baisee.oa.model.tuition.TuitionDiscount;
import cn.baisee.oa.model.tuition.TuitionRule;
import cn.baisee.oa.model.tuition.TuitionStages;


public interface TuitionRuleService {
	/**
	 * 分页查询学费规则
	 * @param tuition
	 * @return
	 */
	public ModelAndView getTuitionList(HttpServletRequest request,TuitionRule tuition);
	/**
	 * 跳转到编辑页面
	 * @param request
	 * @return
	 */
	public ModelAndView toTuitionInfo(HttpServletRequest request);
	/**
	 * 根据ID查询所有的数据
	 * @param paramMap
	 * @return
	 */
	public ModelAndView getAllData(ModelAndView model,Map<String, Object> paramMap);
	
	/**
	 * 添加学费规则
	 * @param request
	 * @return
	 */
	public ModelAndView addOrUpdateTuition(TuitionRule tuition,TuitionDiscount discount,
			BaiseeStudentRefund refund,TuitionStages stages,HttpServletRequest request);
	/**
	 * 修改优惠规则
	 * @param tuition
	 * @param tuition2
	 * @param discount
	 * @param paramMap
	 * @param request
	 */
	public void updateDiscount(TuitionRule tuition,TuitionRule tuition2,TuitionDiscount discount,
			Map<String, Object> paramMap);
	/**
	 * 修改退费规则
	 * @param tuition
	 * @param tuition2
	 * @param refund
	 * @param paramMap
	 * @param request
	 */
	public void updateRefund(TuitionRule tuition,TuitionRule tuition2,BaiseeStudentRefund refund,
			Map<String, Object> paramMap);
	/**
	 * 修改分期和分期规则
	 * @param tuition
	 * @param tuition2
	 * @param stages
	 * @param paramMap
	 * @param request
	 */
	public void updateStagesAndRule(TuitionRule tuition,TuitionRule tuition2,TuitionStages stages,
			Map<String, Object> paramMap);
	/**
	 * 修改学费返费关联
	 * @param paramMap
	 * @param request
	 */
	public void updateTuitionReturn(Map<String, Object> paramMap,HttpServletRequest request);
	/**
	 * 新增学费
	 * @param tuition
	 * @param discount
	 * @param refund
	 * @param stages
	 * @return
	 */
	public String insertTuition(TuitionRule tuition,TuitionDiscount discount,BaiseeStudentRefund refund,TuitionStages stages,HttpServletRequest request);
	/**
	 * 添加退费规则及学费退费关联
	 * @param refund
	 * @param tuId
	 * @return
	 */
	public int insertRefund(BaiseeStudentRefund refund,Map<String, Object> map);
	/**
	 * 添加优惠规则及学费优惠关联
	 * @param discount
	 * @param tuId
	 * @return
	 */
	public int insertDiscount(TuitionDiscount discount,Map<String, Object> map);
	/**
	 * 添加学费分期关联
	 * @param stages
	 * @param map
	 * @return
	 */
	public int insertStages(TuitionStages stages,HttpServletRequest request,Map<String, Object> map);
	/**
	 * 添加学费返费关联
	 * @param request
	 * @return
	 */
	public int insertReturn(HttpServletRequest request, Map<String, Object> map);
	/**
	 * 根据tid删除所有有关的规则
	 * @param request
	 * @return
	 */
	public Object delAllRilesByTid(HttpServletRequest request);
	/**
	 * 
	 * @param paramMap
	 * @return
	 */
	public Object delTuition(Map<String, Object> paramMap,TuitionRule tuition);
	/**
	 * 删除退费规则及学费退费关联表
	 * @param paramMap
	 * @return
	 */
	public int delRefund(Map<String, Object> paramMap) ;
	/**
	 * 删除优惠规则及学费优惠关联表
	 * @param paramMap
	 * @return
	 */
	public int delDiscount(Map<String, Object> paramMap);
	/**
	 * 删除分期，学费分期关联
	 * @param paramMap
	 * @return
	 */
	public int delStages(Map<String, Object> paramMap);
	/**
	 * 删除学费返费关联
	 * @param paramMap
	 * @return
	 */
	public int delRturn(Map<String, Object> paramMap);
	/**
	 * 根据ID查询优惠
	 * @param request
	 * @return
	 */
	public Map<String, Object> getDiscountById(HttpServletRequest request);
	/**
	 * 根据分期id查询分期详情
	 * @param request
	 * @return
	 */
	public Map<Object, Object> getStagesRule(HttpServletRequest request);
	/**
	 * 查询所有的分期期数
	 * @return
	 */
	Map<Object, Object> getTuStCycle(HttpServletRequest request);
	
	   void settingDiscounts(String tuId,String tuDiId);
	   
	   List<TuitionDiscount> showDiscount();
}
