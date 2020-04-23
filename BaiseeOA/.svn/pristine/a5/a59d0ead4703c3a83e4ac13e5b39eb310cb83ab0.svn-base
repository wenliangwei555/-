package cn.baisee.oa.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import cn.baisee.oa.exception.OAServiceException;
import cn.baisee.oa.model.tuition.TuitionDiscount;
/**
 * 优惠信息Service接口
 * @author WANGBAOXIANG
 *
 */
public interface DiscountService {
	/**
	 * 分页查询优惠信息列表
	 * @return
	 */
	public ModelAndView getDiscInfo(HttpServletRequest request,TuitionDiscount discount) throws OAServiceException ;
	
	/**
	 * 跳转到编辑页面并根据ID查询要修改的信息
	 * @param request
	 * @return
	 */
	public ModelAndView toDiscInfo(HttpServletRequest request);
	/**
	 * 添加优惠信息
	 * @param discount
	 * @return
	 */
	public int adddiscount(TuitionDiscount discount) throws OAServiceException ;
	/**
	 * 修改优惠信息
	 * @param discount
	 * @return
	 */
	public int updatediscount(TuitionDiscount discount) throws OAServiceException ;
	
	/**
	 * 添加和修改优惠信息
	 * @param discount
	 * @return
	 */
	public ModelAndView saveOrUpdateDisc(TuitionDiscount discount) throws OAServiceException ;
	/**
	 * 删除优惠信息
	 * @param request
	 * @return
	 */
	public Map<String, Object> delDisc(HttpServletRequest request);
}