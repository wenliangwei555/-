package cn.baisee.oa.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.baisee.oa.annotation.Role;
import cn.baisee.oa.model.BaiseeClazz;
import cn.baisee.oa.model.empinfo.Employee;
import cn.baisee.oa.service.ClazzService;
import cn.baisee.oa.utils.ParamUtils;
/**
 * 班级的Controller
 * @author wangbaoxiang
 *
 */
@Controller
@RequestMapping("/cla")
public class ClazzController {
	@Autowired
	private ClazzService clazzService;
	/**
	 * 
	 */
	private static final Log logger = LogFactory
			.getLog(ClazzController.class);
	/**
	 * 班级列表
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/toClaList")
	@Role(value="BJGL_BJCK",writeLog=true )
	public ModelAndView toClaList(HttpServletRequest request,HttpServletResponse response,BaiseeClazz cla) throws Exception{

		return clazzService.getCla(cla,request);
	}
	/**
	 * 删除班级
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="delCla")
	@ResponseBody
	@Role(value="BJGL_BJSC")
	public Object delCla(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String cId=ParamUtils.getParameter(request, "cId");
		System.out.println(cId);
		Map<String, Object> map=clazzService.getStudentByCID(cId);
		return map;
	}
	
	/**
	 * 添加和修改时查询是否已有该班级
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/checkUpdCla", method = RequestMethod.POST)
	@Role(ignore=true)
	public boolean checkUpdCla(HttpServletRequest request) throws Exception{
		boolean result=clazzService.getClaNameList(request);
		return result;
	}
	/**
	 * 跳转到班级添加或修改页面
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value="/toClaInfo")
	@Role(ignore=true)
	public ModelAndView toClaInfo(HttpServletRequest request) throws Exception{
		System.out.println("进入班级");
		String cId=ParamUtils.getParameter(request, "cId");
		ModelAndView model=new ModelAndView("clazz/cla_info");
		List<Employee> empList=clazzService.getEmployeeName();
		model.addObject("list", empList);
		if (cId!=null&&!cId.equals("")) {
			BaiseeClazz cla=clazzService.getClaById(cId);
			model.addObject("cla", cla);
		}else{
			model.addObject("cla", new BaiseeClazz());
		}
		return model;
	}
	/**
	 * 班级添加或修改
	 * @param request
	 * @param response
	 * @param cla
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/saveOrUpdateCla")
	@Role(value="BJGL_BJXZ")
	public ModelAndView saveOrUpdateCla(HttpServletRequest request,HttpServletResponse response,BaiseeClazz cla) throws Exception{
		ModelAndView model=clazzService.saveOrUpdate(cla);
		return model;
	}
	
	/**
	 * 班级毕业
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/graCla")
	@ResponseBody
	@Role(value="BJGL_BJBY")
	public Object graCla(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String cId=ParamUtils.getParameter(request, "cId");
		Map<String, Object> map=clazzService.updClaIsGra(cId);
		return map;
	}
}
