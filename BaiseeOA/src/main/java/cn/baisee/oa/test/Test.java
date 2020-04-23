package cn.baisee.oa.test;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
public class Test {

	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

	/**部署流程定义*/
 	@org.junit.Test
	public void deploymentProcessDefinition(){
		Deployment deployment = processEngine.getRepositoryService()//与流程定义和部署对象相关的Service
						.createDeployment()//创建一个部署对象
						.name("请假条流程")//添加部署的名称
						.key("leaveKey")
						.addClasspathResource("diagrams/LeaveApplicationProcess.bpmn")//从classpath的资源中加载，一次只能加载一个文件
						.addClasspathResource("diagrams/LeaveApplicationProcess.png")//从classpath的资源中加载，一次只能加载一个文件
						.deploy();//完成部署
		System.out.println("部署ID："+deployment.getId());//1
		System.out.println("流程key："+deployment.getKey());
		System.out.println("部署名称："+deployment.getName());
	} 
 	
 	

    /**
     * 删除流程
     * @param deployId
     */
// 	 @org.junit.Test
//    public void delDeployment(){
//        try{
//        	processEngine.getRepositoryService().deleteDeployment("62501",true);
//        }catch (Exception e){
//            System.err.println(e.getMessage());
//        }
//    }  
	
//	/**启动流程实例*/
//	@org.junit.Test
//	public void startProcessInstance(){
//		//流程定义的key
//		String processId = "myProcess:1:62504";
//		Map<String, Object> variables = new HashMap<String, Object>();
//		variables.put("userId", "EMID20180921013409167");
//		variables.put("approInfo", "私事儿请假");
//		ProcessInstance pi = processEngine.getRuntimeService()//与正在执行的流程实例和执行对象相关的Service
//						.startProcessInstanceById(processId, variables);
//		System.out.println("activityID:"+pi.getActivityId());
//		System.out.println("startID:"+pi.getStartUserId());
//		System.out.println("流程实例ID:"+pi.getId());//流程实例ID    101
//		System.out.println("流程定义ID:"+pi.getProcessDefinitionId());//流程定义ID   helloworld:1:4
//	}
//	
//	
//	/**查询当前人的个人任务*/
/*	@org.junit.Test
	public void findMyPersonalTask(){
		String assignee = "EMID20180919114809194";
		String deployId = processEngine.getRepositoryService().getProcessDefinition("myProcess:1:62504").getDeploymentId();
		
		List<Task> list = processEngine.getTaskService().createTaskQuery().deploymentId(deployId).taskAssignee("EMID20180921013409167").list();
		if(list!=null && list.size()>0){
			for(Task task:list){
				System.out.println("任务ID:"+task.getId());
				System.out.println("任务名称:"+task.getName());
				System.out.println("任务的创建时间:"+task.getCreateTime());
				System.out.println("任务的办理人:"+task.getAssignee());
				System.out.println("流程实例ID："+task.getProcessInstanceId());
				System.out.println("执行对象ID:"+task.getExecutionId());
				System.out.println("流程定义ID:"+task.getProcessDefinitionId());
				System.out.println("########################################################");
			}
		}
	}*/
//	
//	/**完成我的任务*/
//	@org.junit.Test
//	public void completeMyPersonalTask(){
//		//任务ID
//		String processId = "myProcess:1:62504";
//		String userId = "EMID20180921013409167";
//		processEngine.getTaskService().createTaskQuery().deploymentId(processId);
//		processEngine.getTaskService()//与正在执行的任务管理相关的Service
//					.complete("57507");
//		System.out.println("完成任务：任务ID："+taskId);
//	}
	
	
//	@org.junit.Test
//	public void findMyHisProcess(){
//		String assignee = "EMID20180919114809194";
//		List<HistoricTaskInstance> list = processEngine.getHistoryService().createHistoricTaskInstanceQuery().taskAssignee(assignee).list();
//		if(list!=null && list.size()>0){
//			for(HistoricTaskInstance task:list){
//				System.out.println("任务ID:"+task.getId());
//				System.out.println("任务名称:"+task.getName());
//				System.out.println("任务的创建时间:"+task.getCreateTime());
//				System.out.println("任务的办理人:"+task.getAssignee());
//				System.out.println("流程实例ID："+task.getProcessInstanceId());
//				System.out.println("执行对象ID:"+task.getExecutionId());
//				System.out.println("流程定义ID:"+task.getProcessDefinitionId());
//				System.out.println("########################################################");
//			}
//		}
//	}
}
