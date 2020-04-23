package cn.baisee.oa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.baisee.oa.annotation.Role;
import cn.baisee.oa.model.ResponseResult;
import cn.baisee.oa.model.student.BaiseeStudentRefund;
import cn.baisee.oa.model.tuition.TuitionDiscount;
import cn.baisee.oa.model.tuition.TuitionRule;
import cn.baisee.oa.model.tuition.TuitionStages;
import cn.baisee.oa.model.tuition.TuitionStagesRule;
import cn.baisee.oa.service.BaiseeTuitionService;
import cn.baisee.oa.service.TuitionRuleService;
import cn.baisee.oa.utils.StringUtil;
/**
 * 学费规则管理Controller
 * @author WANGBAOXIANG
 *
 */
@Controller
@RequestMapping(value="tuition")
public class TuitionRuleController {
	@Autowired
	private TuitionRuleService tuitionService;
	@Autowired
	private BaiseeTuitionService baiseeTuitionService;
	/**
	 * 查询所有学费规则
	 * @param request
	 * @param tuition
	 * @return
	 */
	@RequestMapping(value="toTuitionList")
	@Role(value="XFGL_XFCK")
	public ModelAndView toTuitionList(HttpServletRequest request,TuitionRule tuition) {
		return tuitionService.getTuitionList(request, tuition);
	}
	/**
	 * 删除学费规则
	 * @param request
	 * @return
	 */
	@RequestMapping(value="delTuition")
	@ResponseBody
	@Role(value="XFGL_XFSC")
	public Object delTuition(HttpServletRequest request) {
		return tuitionService.delAllRilesByTid(request);
	}
	/**
	 * 跳转到编辑页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="toTuitionInfo")
	@Role(ignore=true)
	public ModelAndView toTuitionInfo(HttpServletRequest request) {
		return tuitionService.toTuitionInfo(request);
	}
	/**
	 * 添加或者修改
	 * @param tuition
	 * @return
	 */
	@RequestMapping(value="saveOrUpdate")
	@Role(value="XFGL_XFXG")
	public ModelAndView saveOrUpdate(TuitionRule tuition,TuitionDiscount discount,BaiseeStudentRefund refund,TuitionStages stages,HttpServletRequest request) {
		return tuitionService.addOrUpdateTuition(tuition, discount, refund, stages, request);
	}
	/**
	 * 通过优惠ID查询优惠详情
	 * @param request
	 * @return
	 */
	@RequestMapping(value="getDiscountById")
	@ResponseBody
	@Role(ignore=true)
	public Object selectDiscountById(HttpServletRequest request) {
		return tuitionService.getDiscountById(request);
	}
	/**
	 *查询分期规则
	 * @param request
	 * @return
	 */
	@RequestMapping(value="getStages")
	@ResponseBody
	@Role(ignore=true)
	public Map<Object, Object> getTuStCycle(HttpServletRequest request){
		return tuitionService.getTuStCycle(request);
	}
	/**
	 * 根据分期ID查询分期详情
	 * @param request
	 * @return
	 */
	@RequestMapping(value="getStagesRule")
	@ResponseBody
	@Role(ignore=true)
	public Map<Object, Object> getStagesRule(HttpServletRequest request) {
		return tuitionService.getStagesRule(request);
	}
	/**
	 * 查询分期规则
	 * @param request
	 * @return
	 */
	@RequestMapping(value="getStagesRuleByType")
	@ResponseBody
	@Role(ignore=true)
	public Object getStagesRuleByType(HttpServletRequest request) {
		return tuitionService.getTuStCycle(request);
	}
	/**
	 * 查看学费对应的优惠和新增优惠
	 * @param tuId
	 * @param tuDiId
	 * @return
	 */
     @RequestMapping("setDiscounts")
	@Role(value="XFGL_XFSC")
	public ModelAndView setDiscounts(String tuId,String tuDiId){
    	 if(StringUtil.isNotEmpty(tuId)&&StringUtil.isNotEmpty(tuDiId)){
    		 tuitionService.settingDiscounts(tuId, tuDiId);
    	 }
    	 ModelAndView mv=new ModelAndView("tuition/setDiscounts");
    	 List<TuitionDiscount> discountList = tuitionService.showDiscount();
    	 Map<String, String> map = new HashMap<String, String>();
    	 map.put("tuId", tuId);
	       List<TuitionDiscount> list = baiseeTuitionService.selectTuitionDiscountById(map);
		 mv.addObject("list", list);
    	 mv.addObject("discountList", discountList);
    	 mv.addObject("tuId", tuId);
		return mv;
	}
     /**
      * 根据学费删除对应的优惠
      * @param tuId
      * @param tuDiId
      * @return
      */
     @RequestMapping("cancelDiscounts")
 	@Role(value="XFGL_XFSC")
     @ResponseBody
     public ResponseResult<Integer>  cancelDiscounts(String tuId,String tuDiId){
    	 ResponseResult<Integer> rr=new ResponseResult<Integer>(1,"成功");
    	 int discounts = baiseeTuitionService.removeDiscounts(tuId, tuDiId);
    	 rr.setData(discounts);
		return rr;
    	 
     }
}