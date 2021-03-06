package cn.baisee.oa.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import cn.baisee.oa.model.BaiseeRetuAO;

public interface BaiseeStudentReturnService {

	/**
	 *  查询返费金额是否返全
	 * @param studentID
	 * @param type
	 */
	 void selectReturnfee(String studentID, String type);
	
	/**
	 *  查询返费规则
	 * @param stuId 学生ID
	 * @param trId  学费ID
	 * @param formalAdmissionTime 正式入学时间
	 * @return
	 */
	 Map<String, Object> selectRule(String stuId, String tuId, String formalAdmissionTime);
	/**
	 *分页查询学生返费信息
	 * @return
	 */
	public ModelAndView getRetuStatusList(HttpServletRequest request,BaiseeRetuAO retuAO);
	
	/**
	 * 根据条件查询所有返费信息
	 * @return
	 */
	public List<BaiseeRetuAO> getAllRetu(String sName,String enterStartTime,String enterEndTime,String audStartTime,String audEndTime,String area);
	/**
	 * 查询学生所属的地区
	 * @param area
	 * @return
	 */
	public String selectByAreaCode(String area);
}
