package cn.baisee.oa.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.baisee.oa.annotation.Role;
import cn.baisee.oa.model.order.BaiseewOrder;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.BaiseewOrderService;
import cn.baisee.oa.utils.ParamUtils;

@Controller
@RequestMapping("order")
public class BaiseewOrderController {
	@Autowired
	private BaiseewOrderService orderService;
	
	@RequestMapping("/getOrder") 
	@Role(value = "YYST") 
	public ModelAndView getOrder(HttpServletRequest request){
		int pageNum=ParamUtils.getPageNumParameters(request);
		int pageSize= ParamUtils.getPageSizeParameters(request);
		ModelAndView mv=new ModelAndView("order/order_list");
		String itemlableSearch = ParamUtils.getParameter(request, "itemlableSearch");
		String orderOld = ParamUtils.getParameter(request, "orderOld");
		mv.addObject("itemlableSearch",itemlableSearch);
		mv.addObject("contact",orderOld);
		PageInfo<BaiseewOrder> pageInfo=orderService.getOrder(pageNum,pageSize,itemlableSearch,orderOld);
		mv.addObject("pageResult", pageInfo);
		return mv;
	}
	/**
	 * 已通知
	 * @param id
	 * @return
	 */
	@RequestMapping("/orderOldInformed")
	@Role(value = "YYST")
	public String orderOldInformed(String id){
		int oderId=Integer.parseInt(id);
		orderService.orderOldInformed(oderId);
		return "redirect:order/getOrder.ht";
	}
	
	
	

}
