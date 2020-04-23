package cn.baisee.oa.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.baisee.oa.annotation.Role;
import cn.baisee.oa.exception.OAServiceException;
import cn.baisee.oa.model.tuition.TuitionDiscount;
import cn.baisee.oa.service.DiscountService;
/**
 * 优惠信息controller
 * @author WANGBAOXIANG
 *
 */
@Controller
@RequestMapping(value="disc")
public class DiscountController {
	@Autowired
	private DiscountService discService;
	
	/**
	 * 分页查询优惠规则
	 * @return
	 * @throws OAServiceException 
	 */
	@RequestMapping(value="/toDisList")
	@Role(value="YHGL_YHCK",writeLog=true)
	public ModelAndView showDiscInfo(HttpServletRequest request,TuitionDiscount discount) throws OAServiceException {
		return discService.getDiscInfo(request,discount);
	}
	/**
	 *跳转到优惠添加或修改页面
	 * @return
	 */
	@RequestMapping(value="/toDiscInfo")
	@Role(value="YHGL_YHXZ")
	public ModelAndView toDiscInfo(HttpServletRequest request) {
		return discService.toDiscInfo(request);
	}
	/**
	 * 添加或修改优惠规则
	 * @param discounts
	 * @return
	 * @throws OAServiceException
	 */
	@RequestMapping(value="/saveOrUpdateDisc")
	@Role(value="YHGL_YHXZ")
	public ModelAndView saveOrUpdateDisc(TuitionDiscount discount) throws OAServiceException {
		return discService.saveOrUpdateDisc(discount);
	}
	/**
	 * 删除优惠信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value="delDisc")
	@ResponseBody
	@Role(value="YHGL_YHSC")
	public Object delDisc(HttpServletRequest request) {
		return discService.delDisc(request);
	}
}
