package cn.baisee.oa.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.baisee.oa.annotation.Role;
import cn.baisee.oa.model.BaiseeAttence;
import cn.baisee.oa.model.empinfo.Employee;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.BaiseeAttenceService;
import cn.baisee.oa.service.EmployeeService;
import cn.baisee.oa.utils.ParamUtils;

@Controller
@RequestMapping("attence")
public class BaiseeAttenceController {

	@Autowired
	private BaiseeAttenceService attenceService;
	
	@Autowired
	private EmployeeService employeeService;
	
	/**
	 * 分页
	 * @param request
	 * @return
	 */
	@RequestMapping("toAttenceList")
	@Role(value="KQJL_JLCK")
	public ModelAndView toList(HttpServletRequest request,String empId,String eName) {
		ModelAndView mv = new ModelAndView("attence/attence_list");
		int pageNum = ParamUtils.getPageNumParameters(request);
		int pageSize = ParamUtils.getPageSizeParameters(request);
		String startTime = ParamUtils.getParameter(request, "startTime");
		String endTime = ParamUtils.getParameter(request, "endTime");
		PageInfo<BaiseeAttence> page = attenceService.toList(pageNum, pageSize,empId,startTime,endTime);
		mv.addObject("pageResult", page);
		mv.addObject("empId", empId);
		mv.addObject("eName", eName);
		mv.addObject("startTime", startTime);
		mv.addObject("endTime", endTime);
		return mv;
	}
	
	/**
	 * 员工查询
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/toEmpList")
	@Role(value="KQJL_JLCK")
	public ModelAndView toEmpList(HttpServletRequest request, HttpServletResponse response ){
		if(request.getSession().getAttribute("empId") != null){
			request.getSession().removeAttribute("empId");
		}
		String itemlableSearch = "";
		String str = ParamUtils.getParameter(request, "itemlableSearch");//前台传过来的查询值
		if(str!=null){
			itemlableSearch = str;
		}
		Integer pageNum = ParamUtils.getPageNumParameters(request);
		Integer pageSize = ParamUtils.getPageSizeParameters(request);
		PageInfo<Employee> pageInfo = employeeService.selectEmployeeList(pageNum, pageSize, str, null, null,null,null);
		ModelAndView model = new ModelAndView();
		model.addObject("itemlableSearch", itemlableSearch);
		model.addObject("pageResult", pageInfo);//设置参数pageInfo页面显示数据
		model.setViewName("attence/emp_list");//设置跳转页面
		return model;
	}
}
