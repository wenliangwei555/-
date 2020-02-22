package com.msghub.msghub.utils;/*package cn.baisee.oa.utils;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.junit.Test;

import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.HisTaskService;

*//**
 * 工作流操作工作类
 * @author Administrator
 *
 *//*
public class ActivityUtils {
	
	*//**
	 * 查找所有的部署的流程
	 * @return
	 *//*
	public static List<ProcessDefinition> selectAllProcess() {
		RepositoryService repositoryService = (RepositoryService) ApplicationContextUtil.getBean(RepositoryService.class);
		List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery() 
		.orderByProcessDefinitionVersion().asc() 
		.list();
		return list;
	}
	
	*//**
	 *      带分页的获取当前流程
	 * @param pageInfo
	 * @return
	 *//*
	public static PageInfo<ProcessDefinition> selectAllProcessByPage(HttpServletRequest request) {
		RepositoryService repositoryService = (RepositoryService) ApplicationContextUtil.getBean(RepositoryService.class);
		Integer pageNum = ParamUtils.getPageNumParameters(request);
		Integer pageSize = ParamUtils.getPageSizeParameters(request);
		PageInfo<ProcessDefinition> pageInfo = new PageInfo<ProcessDefinition>();
		pageInfo.setPageNum(pageNum);
		pageInfo.setPageSize(pageSize);
		List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery() 
				.orderByProcessDefinitionVersion().asc() 
				.listPage((pageInfo.getPageNum()-1)*pageInfo.getPageSize(), pageInfo.getPageSize());
				pageInfo.setList(list);
		return pageInfo;
	}
	
	*//**
	 * 部署流程
	 * @param deployName
	 * @param deployKey
	 * @param bpmnFile
	 * @param pngFile
	 *//*
	public static void deployProceess(String deployName, String deployKey, String bpmnFile, String pngFile) {
		RepositoryService repositoryService = (RepositoryService) ApplicationContextUtil.getBean(RepositoryService.class);
		repositoryService.createDeployment()//创建一个部署对象
		.name(deployName)//添加部署的名称
		.key(deployKey)
		.addClasspathResource(bpmnFile)
		.addClasspathResource(pngFile)
		.deploy();
	}
	
	*//**
	 * 	启动流程
	 * @param processId
	 * @param variables
	 *//*
	public static void startProcess(String processId, Map<String, Object> variables, String userId) {
		RuntimeService runtimeService = (RuntimeService) ApplicationContextUtil.getBean(RuntimeService.class);
		TaskService taskService = (TaskService) ApplicationContextUtil.getBean(TaskService.class);
		runtimeService.startProcessInstanceById(processId, variables);
		RepositoryService repositoryService = (RepositoryService) ApplicationContextUtil.getBean(RepositoryService.class);
		String deployId = repositoryService.getProcessDefinition(processId).getDeploymentId();
		Task task = taskService.createTaskQuery().deploymentId(deployId).taskAssignee(userId).list().get(0);
		doTaskByUserId(task.getId(), variables);
	}
	
	*//**
	 * 
	 * @param processId
	 *//*
	public static void startProcess(String processId, String userId) {
		RuntimeService runtimeService = (RuntimeService) ApplicationContextUtil.getBean(RuntimeService.class);
		TaskService taskService = (TaskService) ApplicationContextUtil.getBean(TaskService.class);
		runtimeService.startProcessInstanceById(processId);
		RepositoryService repositoryService = (RepositoryService) ApplicationContextUtil.getBean(RepositoryService.class);
		String deployId = repositoryService.getProcessDefinition(processId).getDeploymentId();
		Task task = taskService.createTaskQuery().deploymentId(deployId).taskAssignee(userId).list().get(0);
		doTaskByUserId(task.getId());
	}
	*//**
	 * 查找自己的代办任务
	 * @param userId
	 * @return
	 *//*
	public static List<Task> selectTaskByUserId(String userId){
		TaskService taskService = (TaskService) ApplicationContextUtil.getBean(TaskService.class);
		List<Task> list = taskService 
						.createTaskQuery() 
						.taskAssignee(userId) 
						.list();
		 return list;
	}
	
	*//**
	 * 查找代办任务根据任务ID
	 * @param taskId
	 * @return
	 *//*
	public static Task selectTaskByTaskId(String taskId) {
		TaskService taskService = (TaskService) ApplicationContextUtil.getBean(TaskService.class);
		Task task = taskService.createTaskQuery() .taskId(taskId).singleResult();
		return task;
	}
	
	*//**
	 *   查找自己的代办任务分页
	 * @param userId
	 * @return
	 *//*
	@Test
	public static PageInfo<Task> selectTaskByUserIdByPage(String userId, HttpServletRequest request){
		Integer pageNum = ParamUtils.getPageNumParameters(request);
		Integer pageSize = ParamUtils.getPageSizeParameters(request);
		TaskService taskService = (TaskService) ApplicationContextUtil.getBean(TaskService.class);
		PageInfo<Task> pageInfo = new PageInfo<Task>();
		pageInfo.setPageNum(pageNum);
		pageInfo.setPageSize(pageSize);
		List<Task> list = taskService 
						.createTaskQuery() 
						.taskAssignee(userId) 
						.listPage((pageNum-1)*pageSize, pageSize);
		pageInfo.setList(list);
		return pageInfo;
	}
	
	*//**
	 *     已办理
	 * @param userId
	 * @param pageInfo
	 * @return
	 *//*
	
	public static List<HistoricActivityInstance> selectHisTaskByUserId(String userId){
		HistoryService historyService = (HistoryService) ApplicationContextUtil.getBean(HistoryService.class);
		List<HistoricActivityInstance> list = historyService 
						.createHistoricActivityInstanceQuery().taskAssignee(userId)
						.list();
		return list;
	}
	
	
	*//**
	 * 	已办理分页
	 * @param userId
	 * @param pageInfo
	 * @return
	 *//*
	
	public static PageInfo<Map<String, String>> selectHisTaskByUserIdByPage(String userId, HttpServletRequest request){
		Integer pageNum = ParamUtils.getPageNumParameters(request);
		Integer pageSize = ParamUtils.getPageSizeParameters(request);
		HisTaskService historyService = (HisTaskService) ApplicationContextUtil.getBean(HisTaskService.class);
		PageInfo<Map<String, String>> pageInfo = historyService.selectInsIdByUserId(pageNum, pageSize, userId);
		return pageInfo;
	}
	
	
	
	
	*//**
	   *     完成代办
	 * @param taskId
	 * @param variables
	 *//*
	public static void doTaskByUserId(String taskId, Map<String, Object> variables) {
		TaskService taskService = (TaskService) ApplicationContextUtil.getBean(TaskService.class);
		taskService.complete(taskId, variables);
	}
	

	*//**
	   *     完成代办
	 * @param taskId
	 *//*
	public static void doTaskByUserId(String taskId) {
		TaskService taskService = (TaskService) ApplicationContextUtil.getBean(TaskService.class);
		taskService.complete(taskId);
	}
	
}
*/