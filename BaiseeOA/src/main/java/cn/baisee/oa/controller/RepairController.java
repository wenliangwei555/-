package cn.baisee.oa.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import cn.baisee.oa.annotation.Role;
import cn.baisee.oa.model.SessionUserInfo;
import cn.baisee.oa.model.empinfo.Employee;
import cn.baisee.oa.model.repairReceive.BaiseeAssignmentManage;
import cn.baisee.oa.model.repairReceive.BaiseeRepair;
import cn.baisee.oa.model.repairReceive.BaiseeRepairItem;
import cn.baisee.oa.model.repairReceive.BaiseeRepairPlace;
import cn.baisee.oa.model.repairReceive.BaiseeRepairType;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.BaiseeAssignmentManageService;
import cn.baisee.oa.service.BaiseeRepairItemService;
import cn.baisee.oa.service.BaiseeRepairPlaceService;
import cn.baisee.oa.service.BaiseeRepairService;
import cn.baisee.oa.service.BaiseeRepairTypeService;
import cn.baisee.oa.service.EmployeeService;
import cn.baisee.oa.utils.ParamUtils;
import cn.baisee.oa.utils.PropertiesUtil;
import cn.baisee.oa.utils.SessionUtil;
/**
 * 物品报修controller
 * @author liangfeng
 *
 */
@Controller
@RequestMapping("/repair")
public class RepairController {
	@Autowired
	private BaiseeRepairService repairService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private BaiseeAssignmentManageService assignmentManageService;
	@Autowired
	private BaiseeRepairPlaceService repairPlaceService;
	@Autowired
	private BaiseeRepairTypeService repairTypeService;
	@Autowired
	private BaiseeRepairItemService repairItemService;
	
	
	//获取配置文件中要查询的角色名称
	PropertiesUtil restUtil = new PropertiesUtil("parameter.properties");

	
	/**
	 * 
	 * 根据登录者ID，查询当前登录者创建的报修管理任务，然后跳到报修管理列表页面页面。
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/repairList")
	@Role(value="BXGL")
	public ModelAndView toRepairList(HttpServletRequest request, String itemlableSearch) throws Exception{
		//获取当前用户名称
		SessionUserInfo user = SessionUtil.getUserInfo(request);
		Integer pageNum=ParamUtils.getPageNumParameters(request);
		Integer pageSize=ParamUtils.getPageSizeParameters(request);
		//添加查询条件
		Map<String, String> map = new HashMap<>();
		//添加搜索查询条件
		map.put("itemlableSearch",itemlableSearch == null?"":itemlableSearch);
		//获取当前登录账户
		String loginName = user.getLoginName();
		//如果是true前端页面显示操作列，否则不显示
		boolean flag = false;
		//查询可以分配报修任务的员工
		List<String> loginNames = assignmentManageService.getAssignmentManageAll();
		//遍历可以分配报修任务的员工，是否匹配当前用户
		for (String name : loginNames) {
			flag = loginName.equals(name);	
			//如果flag为true，说明当前登录人可以有操作列，停止循环
			if (flag) break;
		}
		//如果flag为true说明要查询全部因为是可以分配任务的员工，否则只查询自己的报修任务
		map.put("userId", flag?null:user.getUserId());
		//执行查询
		PageInfo<BaiseeRepair> pageInfo=repairService.getRepairList(pageNum, pageSize, map);
		ModelAndView mv=new ModelAndView("repairReceive/repair_list");
		mv.addObject("pageResult", pageInfo);
		mv.addObject("flag", flag);
		mv.addObject("userId", user.getUserId());
		mv.addObject("itemlableSearch", itemlableSearch);
		return mv;
	}
	
	/**
	 * 去报修管理任务的添加页面
	 * @return
	 */
	@RequestMapping("/toAddRepair")
	@Role(value="BXGL_XZBX")
	public ModelAndView toAddRepair(HttpServletRequest request, HttpSession session){
		//获取当前用户名称
		SessionUserInfo user = SessionUtil.getUserInfo(request);
		//查询校区
		List<BaiseeRepairPlace> repairPlaces = repairPlaceService.getRepairPlaceAll();
		ModelAndView mav=new ModelAndView("repairReceive/repair_add");
		//将物品类型集合和物品库存集合返回到页面
		mav.addObject("repairPlaces", repairPlaces);
		mav.addObject("deptId", user.getEmpDept());
		return mav;
	}
	
	
	/**
	 * 根据所属校区id，查询该校区下所有故障点，和故障点下所有的故障类型
	 * @param pId	校区id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findGoodsTypeById")
	@Role(value="BXGL_XZBX")
	public Object findGoodsTypeById(String pId){
		//根据校区id，查询校区下的所有故障地点
		List<BaiseeRepairType> repairTypes = repairTypeService.getRepairTypeByPid(pId);
		//将查询到的物品库存集合返回给页面
		Map<String, Object> map = new HashMap<>();
		map.put("repairTypes", repairTypes);
		return map;		
	}
	
	/**
	 * 根据tid查询该故障地点下的所有故障类型
	 * @param tId	故障地点id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getRepairItemByTid")
	@Role(value="BXGL_XZBX")
	public Object getRepairItemByTid(String tId){
		//查看所属校区下的故障点
		List<BaiseeRepairItem> repairItems = repairItemService.getRepairItemByTid(tId);
		Map<String, Object> map = new HashMap<>();
		map.put("repairItems", repairItems);
		return map;		
	}
	
	/**
	 * 执行添加报修管理任务，然后跳转到报修管理任务列表页面
	 * @param request
	 * @param repair	要添加的报修管理任务
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addRepair")
	@Role(value="BXGL_XZBX")
	public ModelAndView addRepair(HttpServletRequest request, BaiseeRepair repair) throws Exception {
		try {
			//获取当前用户名称
			SessionUserInfo user = SessionUtil.getUserInfo(request);
			//添加创建任务的报修人id和名称
			repair.setcId(user.getUserId());//报修人id
			repair.setcName(user.getUserName());//报修人名称
			repair.setGoodsTime(new Date());
			//状态0：未维修的报修任务
			repair.setState("0");
			repairService.saveOrUpdateRepair(repair);
			return toRepairList(request, null);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return toRepairList(request, null);
	}
	
	/**
	 * 点击编辑的时候，根据报修管理任务的id，查询报修管理任务，然后跳转到报修管理任务的编辑页面
	 * @param id	报修任务id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/selectRepairId")
	@Role(value="BXGL_CKBX")
	public ModelAndView selectByRepairId(HttpServletRequest request, String id) throws Exception{
		//根据报修任务ID，查询报修任务详情，并返回到页面回填显示
		BaiseeRepair repair=repairService.getById(id);
		//获取当前用户名称
		SessionUserInfo user = SessionUtil.getUserInfo(request);
		//根据故障类型id，查询故障类型详情
		BaiseeRepairItem repairItem = repairItemService.getById(repair.getGoodsId());
		List<BaiseeRepairPlace> repairPlaces = repairPlaceService.getRepairPlaceAll();//查询校区
		List<BaiseeRepairType> repairTypes = null;
		List<BaiseeRepairItem> repairItems = null;
		if (repairItem != null) {
			//查询上次选中校区的故障点
			repairTypes = repairTypeService.getRepairTypeByPid(repairItem.getpId());	
			//查询上次选中故障点下所有故障类型
			repairItems = repairItemService.getRepairItemByTid(repairItem.gettId());
		}
		
		
		ModelAndView mav=new ModelAndView("repairReceive/repair_update");
		mav.addObject("repairItem", repairItem);
		mav.addObject("repairPlaces", repairPlaces);
		mav.addObject("repair", repair);
		mav.addObject("repairTypes", repairTypes);
		mav.addObject("repairItems", repairItems);
		mav.addObject("deptId", user.getEmpDept());
		return mav;
		
	}
	
	/**
	 * 报修管理任务编辑页面点击保存时，根据报修管理任务的id，去更新该条报修管理任务。然后跳转到报修管理页面
	 * @param request
	 * @param repair 要更新的报修管理任务
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateRepair")
	@Role(value="BXGL_XGBX")
	public ModelAndView updateDic(MultipartFile input_file, HttpServletRequest request, BaiseeRepair repair) throws Exception{
		//获取当前用户名称
		SessionUserInfo user = SessionUtil.getUserInfo(request);
		repair.setLastPersonId(user.getUserId());
		repair.setLastPerson(user.getUserName());
		//更新创建时间
		repair.setGoodsTime(new Date());
		repairService.saveOrUpdateRepair(repair);
		return toRepairList(request,null);
	}
	
	
	/**
	 * 根据前端页面传来的报修管理任务ID，删除报修管理任务。然后跳转到报修管理任务列表页面
	 * @param request
	 * @param ids	数组中装的是列表页面被选中的报修管理任务的ID
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteRepair")
	@Role(value="BXGL_SCBX")
	public ModelAndView delRepair(HttpServletRequest request) throws Exception{
		String [] ids = ParamUtils.getParameters(request, "ids");
		//ids数组中装的是列表页面被选中的报修管理任务的ID
		repairService.delRepair(ids);
		return toRepairList(request, null);
	}
	
	
	/**
	 * 点击分配按钮，根据页面传来的报修管理任务id，查询报修管理任务详情，并且查询员工，员工作为分配报修管理任务的人员。然后跳转到报修管理任务分配页面
	 * @param request
	 * @param id	报修管理任务的ID
	 * @return
	 */
	@RequestMapping("/findRepairAndDistributeById")
	@Role(value="BXGL_CKBX")
	public ModelAndView findRepairAndDistributeById(String id){
		//获取配置文件中要查询的角色名称
		String roleName = restUtil.get("role.name");
		BaiseeRepair repair=repairService.getById(id);
		BaiseeRepairItem repairItem = null;
		if (repair != null) {
			//根据故障类型id查询故障详情
			repairItem = repairItemService.getById(repair.getGoodsId());
		}
		//查询该部门下所有后勤角色下的员工
		List<Employee> employees = employeeService.findDeptAndRoleEmp(roleName);
		//跳转到分配任务页面
		ModelAndView mav=new ModelAndView("repairReceive/distribute_task");
		Map<String, Object> modelMap = new HashMap<>();
		modelMap.put("repair", repair);
		modelMap.put("employees", employees);
		modelMap.put("repairItem", repairItem);
		mav.addAllObjects(modelMap);
		return mav;
	}
	
	/**
	 * 分配报修保存时。根据页面传来报修管理任务，更新报修管理任务的维修人。
	 * @param repair 报修任务
	 * @return
	 */
	@RequestMapping("/distributeTask")
	@Role(value="BXGL_CKBX")
	public ModelAndView distributeTask(HttpServletRequest request, BaiseeRepair repair) throws Exception{
		//更新报修任务的状态。1：表示正在处理
		repair.setState("1");
		repairService.distributeTask(repair);
		return toRepairList(request, null);
	}
	
	/**
	 * 点击报修任务菜单，根据当前后勤部登录者，查询该登录者下需要他修复的所有报修管理任务。然后跳转到报修任务列表页面
	 * @return
	 */
	@RequestMapping("/repairTask")
	@Role(value="BXRW")
	public ModelAndView repairTask(HttpServletRequest request, String itemlableSearch) throws Exception{
		//获取当前用户名称
		SessionUserInfo user = SessionUtil.getUserInfo(request);
		Integer pageNum=ParamUtils.getPageNumParameters(request);
		Integer pageSize=ParamUtils.getPageSizeParameters(request);
		//组装查询条件
		Map<String, String> map = new HashMap<>();
		//添加搜索查询条件
		map.put("itemlableSearch",itemlableSearch == null?"":itemlableSearch);
		//添加只查询条件，只查询已分配给该用户的报修任务
		map.put("userId", user.getUserId());
		PageInfo<BaiseeRepair> pageInfo=repairService.getRepairHandleList(pageNum, pageSize, map);
		ModelAndView mv=new ModelAndView("repairReceive/repair_task_list");
		mv.addObject("pageResult", pageInfo);
		mv.addObject("itemlableSearch", itemlableSearch);
		return mv;
	}
	
	/**
	 * 页面点击修复任务，根据当前任务的ID，查询该报修任务。然后跳转到任务修复页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/selectRepairTask")
	@Role(value="BXRW_CLBX")
	public ModelAndView selectRepairTask(String id) throws Exception{
		//获取配置文件中要查询的角色名称
		String roleName = restUtil.get("role.name");
		BaiseeRepair repair=repairService.getById(id);
		BaiseeRepairItem repairItem = null;
		if (repair != null) {
			//根据故障类型id查询故障详情
			repairItem = repairItemService.getById(repair.getGoodsId());
		}
		//查询该部门下所有后勤角色下的员工
		List<Employee> employees = employeeService.findDeptAndRoleEmp(roleName);
		//查询后勤角色下的所有用户，选择最终的处理人
		ModelAndView mav=new ModelAndView("repairReceive/handle_repair_task");
		mav.addObject("repair", repair);
		mav.addObject("employees", employees);
		mav.addObject("repairItem", repairItem);
		return mav;
		
	}
	
	/**
	 * 修复任务完成时。根据页面传来的报修任务的ID，更新报修任务的状态和最终处理人。然后跳转到报修任务列表页面
	 * @param request
	 * @param repair	报修任务
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/repairComplete")
	@Role(value="BXRW_CLBX")
	public ModelAndView repairComplete(HttpServletRequest request, BaiseeRepair repair) throws Exception{
		//更新报修任务的状态。2：已修复
		repair.setState("2");
		repair.setLastTime(new Date());
		repairService.repairComplete(repair);
		return repairTask(request, null);
		
	}
	
	
	/***================= 报修任务分配管理 ========================*/
	/**
	 * 查询可以分配任务的员工
	 * @param request
	 * @param itemlableSearch 查询条件 可以分配任务员工的姓名
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/assignment")
	@Role(value="FPGL")
	public ModelAndView assignmentManage(HttpServletRequest request, String itemlableSearch) {
		Integer pageNum=ParamUtils.getPageNumParameters(request);
		Integer pageSize=ParamUtils.getPageSizeParameters(request);
		//添加查询条件
		Map<String, String> map = new HashMap<>();
		//添加搜索查询条件
		map.put("itemlableSearch",itemlableSearch == null?"":itemlableSearch);
		//执行查询
		PageInfo<BaiseeAssignmentManage> pageInfo=assignmentManageService.getAssignmentManage(pageNum, pageSize, map);
		ModelAndView mv=new ModelAndView("repairReceive/assignment_manage");
		mv.addObject("pageResult", pageInfo);
		return mv;
	}
	
	/**
	 * 去分配任务的添加页面
	 * @return
	 */
	@RequestMapping("/toAddAssigmentManage")
	@Role(value="FPGL_XZFP")
	public ModelAndView toAddAssigmentManage(){
		//获取配置文件中要查询的角色名称
		String roleName = restUtil.get("role.name");
		//查询该部门下所有后勤角色下的员工
		List<Employee> employees = employeeService.findDeptAndRoleEmp(roleName);
		ModelAndView mav=new ModelAndView("repairReceive/assignment_add");
		//将物品类型集合和物品库存集合返回到页面
		mav.addObject("employees", employees);
		return mav;
	}
	

	/**
	 * 执行添加分配任务员工
	 * @param request
	 * @param assignmentManage	要添加的任务
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addAssignmentManage")
	@Role(value="FPGL_XZFP")
	public ModelAndView addDic(HttpServletRequest request, BaiseeAssignmentManage assignmentManage) throws Exception{
		//获取当前用户名称
		SessionUserInfo user = SessionUtil.getUserInfo(request);
		//添加创建任务人的id和名称
		assignmentManage.setCreaatePersonId(user.getUserId());//创建人id
		assignmentManage.setCreaatePersonName(user.getUserName());//创建人名称
		assignmentManage.setCreateTime(new Date());
		assignmentManageService.saveOrUpdateAssigmentManage(assignmentManage);
		return assignmentManage(request, null);
	}
	
	/**
	 * 根据id查询可以分配任务员工信息
	 * @param id	任务id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/selectAssignmentId")
	@Role(value="FPGL_CXFP")
	public ModelAndView selectAssignmentId(String id) throws Exception{
		//获取配置文件中要查询的角色名称
		String roleName = restUtil.get("role.name");
		//查询该部门下所有后勤角色下的员工
		List<Employee> employees = employeeService.findDeptAndRoleEmp(roleName);
		//根据id查询分配任务
		BaiseeAssignmentManage assignment = assignmentManageService.getById(id);
		ModelAndView mav=new ModelAndView("repairReceive/assignment_update");
		mav.addObject("employees", employees);
		mav.addObject("assignment", assignment);
		return mav;
		
	}
	
	/**
	 * 修改可分配任务员工信息
	 * @param request
	 * @param assignmentManage 要更新的任务
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateAssignment")
	@Role(value="FPGL_XGFP")
	public ModelAndView updateAssignment(HttpServletRequest request, BaiseeAssignmentManage assignmentManage) throws Exception{
		//获取当前用户名称
		SessionUserInfo user = SessionUtil.getUserInfo(request);
		assignmentManage.setCreaatePersonId(user.getUserId());//更新人id
		assignmentManage.setCreaatePersonName(user.getUserName());//更新人名称
		assignmentManage.setUpdateTime(new Date());//更新时间
		assignmentManageService.saveOrUpdateAssigmentManage(assignmentManage);
		return assignmentManage(request, null);
	}
	
	
	/**
	 * 删除任务
	 * @param request
	 * @param ids	数组中装的是列表页面被选中的任务的ID
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteAssigmentManage")
	@Role(value="FPGL_SCFP")
	public ModelAndView deleteAssigmentManage(HttpServletRequest request, String[] ids) throws Exception{
		//ids数组中装的是列表页面被选中任务的ID
		assignmentManageService.delAssignmentManage(ids);
		return assignmentManage(request, null);
	}
	
	/**
	 * 根据员工id检查分配任务员工表中是否有此员工
	 * @param assignmentPersonId	员工id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/checkAssignmentId")
	@Role(value="FPGL_CXFP")
	public Object checkAssignmentId(String assignmentPersonId){
		//查看分配表中是否有此分配人
		List<BaiseeAssignmentManage> assignmentManages = assignmentManageService.checkAssignmentPerson(assignmentPersonId);
		Map<String, String> map = new HashMap<>();
		//判断assignmentManages中是否有数据,如果为000说明没有此分配任务的员工，反之111说明有此员工
		if (assignmentManages != null && assignmentManages.size() > 0) {
			map.put("codes", "111");
		}else {
			map.put("codes", "000");
		}
		return map;		
	}
	
}
