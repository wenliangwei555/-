package cn.baisee.oa.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.baisee.oa.annotation.Role;
import cn.baisee.oa.model.student.BaiseeStudentRefund;
import cn.baisee.oa.service.RefundService;
/**
 * 退费规则管理的Controller
 * @author WANGBAOXIANG
 *
 */
@Controller
@RequestMapping(value="refund")
public class RefundController {
	@Autowired
	private RefundService refService;
	/**
	 * 分页查询所有的退费规则
	 * @param request
	 * @return
	 */
	@RequestMapping(value="toRefundList")
	@Role(value="TFGL",writeLog=true)
	public ModelAndView toRefundList(HttpServletRequest request) {
		return refService.getRefundInfo(request);
	}




	/**
	 * 删除退费规则
	 * @param request
	 * @return
	 */
	@RequestMapping(value="delRefund")
	@ResponseBody
	@Role(value="TFGL_TFSC")
	public Object delRefund(HttpServletRequest request) {
		return refService.delRefund(request);
	}
	/**
	 * 跳转到编辑页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="toRefundInfo")
	@Role(value="TFGL")
	public ModelAndView toRefundInfo(HttpServletRequest request) {
		return refService.toRefundInfo(request);
	}

	/**
	 * 添加或者修改退费规则
	 * @param refund
	 * @return
	 */
	@RequestMapping(value="saveOrUpdateRefund")
	@Role(value="TFGL")
	public ModelAndView saveOrUpdateRefund(HttpServletRequest request,BaiseeStudentRefund refund) {
		return refService.saveOrUpdate(request,refund);
	}
	
}
