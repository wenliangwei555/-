package cn.baisee.oa.controller;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.HistoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.baisee.oa.annotation.Role;
import cn.baisee.oa.model.BaiseeUser;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.UserService;
import cn.baisee.oa.utils.ActivityUtils;
import cn.baisee.oa.utils.SessionUtil;

/**
 * 个人任务待办信息controller层
 */
@Controller
@RequestMapping("/task")
public class TaskController {
	
	@Autowired
	private UserService userService;
	
	@Resource(name = "taskService")
	private TaskService taskService;// 任务服务
	
	@Resource(name = "historyService")
	private HistoryService historyService;// 历史数据服务
	
	/**
	 * 查询自己的待办任务分页显示
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/toBacklogList")
	@Role("WDDB")
	public ModelAndView toBacklogList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ModelAndView view = new ModelAndView("task/task_backlog_list");
		PageInfo<Task> pageInfo = ActivityUtils.selectTaskByUserIdByPage(SessionUtil.getUserInfo(request).getUserId(), request);
		for (Task task : pageInfo.getList()) {
			String userId = taskService.getVariable(task.getId(), "userId").toString();
			BaiseeUser user = userService.getUserById(userId);
			task.setCategory(user.getUserName());
		}
		
		view.addObject("task", pageInfo);
		return view;
	}
	
	/**
	 * 根据任务ID办理任务
	 * @param request
	 * @param response
	 * @param taskId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/transactTask")
	@Role("WDDB_DBCL")
	public ModelAndView transactTask(HttpServletRequest request, HttpServletResponse response,String taskId) throws Exception{
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("approUserId", SessionUtil.getUserInfo(request).getUserId());// 办理谁的任务就将谁的ID放入 variables{"approUserId"}
		ActivityUtils.doTaskByUserId(taskId, variables);
		
		return toBacklogList(request, response);// 办理成功跳转列表界面
	}
	
	
	/**
	 * 查询任务的详细信息
	 * @param request
	 * @param response
	 * @param taskId 任务ID
	 * @return
	 */
	@RequestMapping("/showMyTaskInfo")
	@Role("WDDB_DBXQ")
	public ModelAndView showMyTaskInfo(HttpServletRequest request, HttpServletResponse response,String taskId) throws Exception{
		ModelAndView view = new ModelAndView("task/task_showInfo");
		Task task = ActivityUtils.selectTaskByTaskId(taskId);
		
		// 获取发起人信息
		String userId = taskService.getVariable(task.getId(), "userId").toString();
		BaiseeUser user = userService.getUserById(userId);
		view.addObject("transactUser", user);
		// 请假事由
		String leaveCause = taskService.getVariable(task.getId(), "leaveCause").toString();
		// 请假天数
		String leaveDays = taskService.getVariable(task.getId(), "leaveDays").toString();
		
		
		view.addObject("taskInfo", task);
		view.addObject("leaveCause", leaveCause);
		view.addObject("leaveDays", leaveDays);
		return view;
	}
	
}
