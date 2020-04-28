package cn.baisee.oa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.baisee.oa.annotation.Login;
import cn.baisee.oa.annotation.Role;
import cn.baisee.oa.utils.ActivityUtils;
import cn.baisee.oa.utils.ParamUtils;
import cn.baisee.oa.utils.SessionUtil;
import cn.baisee.oa.utils.StringUtil;

@Controller
@RequestMapping(value="engine")
public class EngineController {

	@Resource(name = "repositoryService")
	private RepositoryService rService;
	
	@RequestMapping("/toEngineList")
	@Role("CZLC")
	@Login(ignore=true)
	public ModelAndView toEmpList(HttpServletRequest request, HttpServletResponse response ){
		// 获取所有的部署流程
		List<Deployment> deployments = rService.createDeploymentQuery().list();
		ModelAndView view = new ModelAndView();
		view.setViewName("engine/engine_list");
		view.addObject("pageResult", deployments);
		return view;
	}
	
	
	
	@RequestMapping("/toEngineInfo")
	@Role(ignore=true)
	@Login(ignore=true)
	public ModelAndView toEngineInfo(HttpServletRequest request, HttpServletResponse response){
		ModelAndView view = new ModelAndView();
		view.setViewName("engine/engine_edit");
		return view;
	}
	
	
	
	@RequestMapping("/engineInfoStart")
	@Role("CZLC_QDLC")
	@Login(ignore=true)
	public ModelAndView engineInfoStart(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> variables = new HashMap<String, Object>();
		String deploymentId = ParamUtils.getParameter(request, "deploymentId");// 流程ID
		String leaveDays = ParamUtils.getParameter(request, "leaveDays");// 请假天数
		String leaveCause = ParamUtils.getParameter(request, "leaveCause");// 请假原因
		ProcessDefinition definition = null;
		if (StringUtil.isNotEmpty(deploymentId)) {
			definition = rService.createProcessDefinitionQuery().deploymentId(deploymentId).singleResult();
		}
		variables.put("userId",  SessionUtil.getUserInfo(request).getUserId());
		variables.put("approUserId", "EMID20181212135221421");
		variables.put("leaveDays", leaveDays);
		variables.put("leaveCause", leaveCause);
		ActivityUtils.startProcess(definition.getId(), variables, SessionUtil.getUserInfo(request).getUserId());
		return toEmpList(request, response);
	}
	
	/**
	 * 去往变量界面中转站
	 * @param request
	 * @param response
	 * @param deploymentId
	 * @return
	 */
	@RequestMapping("/toEngineVariable")
	@Role(ignore=true)
	@Login(ignore=true)
	public ModelAndView toEngineVariable(HttpServletRequest request, HttpServletResponse response, String deploymentId) {
		ModelAndView view = new ModelAndView("engine/engine_var");
		if (StringUtil.isNotEmpty(deploymentId)) {
			view.addObject("deploymentId", deploymentId);
		}
		
		return view;
	}
}
