package cn.baisee.oa.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import cn.baisee.oa.exception.OAServiceException;
import cn.baisee.oa.model.student.BaiseeStudentReturn;
import cn.baisee.oa.model.student.BaiseeStudentReturnRule;

public interface ReturnService {
	
	/**
	 * 分页查询所有的返费规则
	 * @param request
	 * @return
	 * @throws OAServiceException
	 */
	public ModelAndView getReturnList(HttpServletRequest request) throws OAServiceException ;
	/**
	 * 删除返费规则
	 * @param request
	 * @return
	 * @throws OAServiceException
	 */
	public Map<String, Object> delReturn(HttpServletRequest request) throws OAServiceException;
	/**
	 * 跳转到编辑页面
	 * @return
	 */
	public ModelAndView toReturnInfo(HttpServletRequest request) throws OAServiceException;
	/**
	 * 添加或者修改返费规则
	 * @return
	 */
	public ModelAndView saveOrUpdate(HttpServletRequest request,BaiseeStudentReturn retu,BaiseeStudentReturnRule retuRule);
	/**
	 * 添加或修改时查询是否已有该规则类型
	 * @param request
	 * @return
	 */
	public boolean getRetuByRtId(HttpServletRequest request);
	/**
	 * 根据返费ID查询详细规则
	 * @param request
	 * @return
	 */
	public Map<String, Object> getRetuRule(HttpServletRequest request);
}
