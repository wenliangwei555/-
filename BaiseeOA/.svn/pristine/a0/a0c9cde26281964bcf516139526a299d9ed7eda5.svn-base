package cn.baisee.oa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.baisee.oa.annotation.Role;
import cn.baisee.oa.model.BaiseeClazz;
import cn.baisee.oa.model.BaiseeUser;
import cn.baisee.oa.model.IcipBusDict;
import cn.baisee.oa.model.course.BaiseeClassCourse;
import cn.baisee.oa.model.course.BaiseeClassCourseId;
import cn.baisee.oa.model.course.BaiseeClassSystem;
import cn.baisee.oa.model.course.BaiseeClassroom;
import cn.baisee.oa.model.course.BaiseeCourse;
import cn.baisee.oa.model.course.BaiseeCourseSystem;
import cn.baisee.oa.model.course.BaiseeCourseTeacher;
import cn.baisee.oa.model.course.BaiseeTimeStage;
import cn.baisee.oa.model.empinfo.Employee;
import cn.baisee.oa.model.student.BaiseeStudentLeave;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.BaiseeClassCourseService;
import cn.baisee.oa.service.BaiseeClassSystemService;
import cn.baisee.oa.service.BaiseeClassroomService;
import cn.baisee.oa.service.BaiseeCoursePlanService;
import cn.baisee.oa.service.BaiseeCourseService;
import cn.baisee.oa.service.BaiseeCourseSystemService;
import cn.baisee.oa.service.BaiseeTimeStageService;
import cn.baisee.oa.service.ClazzService;
import cn.baisee.oa.service.DictionariesService;
import cn.baisee.oa.service.EmployeeService;
import cn.baisee.oa.service.UserService;
import cn.baisee.oa.utils.ParamUtils;
import cn.baisee.oa.utils.SessionUtil;
import cn.baisee.oa.utils.StringUtil;
/**
 * 课程计划
 * @author Administrator
 *
 */
@RequestMapping("/couPlan")
@Controller
public class BaiseeCoursePlanController {

	@Autowired
	private ClazzService clazzService;//班级service
	@Autowired
	private BaiseeClassSystemService classSystemService;//班级课制的service
	@Autowired
	private BaiseeClassCourseService claCouserService;//班级课程的service
	@Autowired
	private BaiseeCourseSystemService courseSystemService;//课制的service
	@Autowired
	private BaiseeTimeStageService timeStageService;//时间段的service
	@Autowired
	private BaiseeClassroomService classroomService;//教室的service
	//字典service
	@Autowired
	private DictionariesService dictionariesService;
	@Autowired
	private BaiseeCoursePlanService coursePlanService;//课程计划的service
	@Autowired
	private EmployeeService empService;
	/**
	 * 班级列表
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/toClaList")
	@Role(value="KCGL_CKBJ")
	public ModelAndView toClaList(HttpServletRequest request,HttpServletResponse response,BaiseeClazz cla) throws Exception{
		ModelAndView model = clazzService.getCla(cla,request);
		model.setViewName("coursePlan/cla_list");
		return model;
	}
	
	/**
	 * 一个班级所对应的课程列表
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/toCourseList")
	@Role(value="KCGL_BJKCCK")
	public String toCourseList(Model model,String cid,HttpServletRequest request){
		BaiseeUser user = (BaiseeUser) SessionUtil.getUserInfo(request); // 获取当前登录的用户
		//根据当前登录用户类型查询考试可以用的场地
		List<BaiseeClassroom> classrooms = classroomService.selectClassroomList( user.getUserType());//考试场地
		model.addAttribute("classrooms",classrooms);
		//查询上课的时间段 高中4/初中9  为了制作课程表Y轴
		List<BaiseeTimeStage> timeStageList = timeStageService.selectTimeStages();
		model.addAttribute("timeStageList",timeStageList);//时间段
		//查询上课的天数  高中6/初中11  为了制作课程表X轴
		List<BaiseeCourseSystem> courseSystemList = courseSystemService.selectAllCourseSystem();
		model.addAttribute("courseSystemList", courseSystemList);//所有的课制
		List<BaiseeClassSystem> list = classSystemService.selectClassSystemsByCid(cid);
		model.addAttribute("list", list);//班级的课制
		List<BaiseeClassCourse> classList = claCouserService.selectClassCourseByCid(cid);
		model.addAttribute("classList", classList);//班级课程
		//字典中的所学类别要查出来
		List<IcipBusDict> dicts = dictionariesService.selectIcipBusDictByDictName("courseType");
		//根据用户类型查询对应的员工列表 
		List<Employee> users = empService.selectEmpByType(user.getUserType());
		model.addAttribute("users", users);//员工信息
		model.addAttribute("dicts", dicts);//课程类型
		model.addAttribute("classId", cid);//班级id
		return "coursePlan/course_list";
	}
	/**
	 *  新增班级课程或者修改班级课程
	 * @param model
	 * @param clsSys
	 * @param claCour
	 * @param couTea
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/editClassCourse")
	@Role(value="KCGL_BJKCXZ")
	public String editClassCourse(Model model,BaiseeClassSystem clsSys,BaiseeClassCourseId claCour,BaiseeCourseTeacher couTea, HttpServletRequest request){
		String operationSuccess = "无班级信息，请重新选择";
		String classId = ParamUtils.getParameter(request, "classId");//班级id
		String courseID = ParamUtils.getParameter(request, "courseID");//原课程id
		if(StringUtil.isEmpty(classId)){
			model.addAttribute("operationSuccess", operationSuccess);
			return "forward:toCourseList.ht";
		}
		//根据班级课程id的主键来判断是新增还是修改
		if(StringUtil.isNotEmpty(claCour.getCcId())){
			operationSuccess = coursePlanService.updateCourseAll(claCour, couTea,classId,courseID);
		}else{
			Map<String, String> map = new HashMap<String, String>();
			map.put("classId", classId);//班级id
			map.put("week", claCour.getWeek());//星期几
			map.put("tsId", claCour.getTsId());//时间排序的地址
			String index = coursePlanService.getCourseIdByInfo(map);//校验是否重复添加
			if(StringUtil.isNotEmpty(index)){
				operationSuccess = "已经在该时间有课程不能多次新增";
			}else{
				operationSuccess = coursePlanService.insertCourseAllInfo(clsSys, claCour, couTea,classId);
			}
		}
		model.addAttribute("operationSuccess", operationSuccess);
		return "forward:toCourseList.ht?cid="+classId;
	}
	
	/**
	   *   新增班级课程或者修改班级课程
	 * @param model
	 * @param clsSys
	 * @param claCour
	 * @param couTea
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/editClassCourseA")
	@ResponseBody
	@Role(value="KCGL_BJKCXZ")
	public Map<String,String> editClassCourseA(Model model,BaiseeClassSystem clsSys,BaiseeClassCourseId claCour,BaiseeCourseTeacher couTea, HttpServletRequest request){
		Map<String,String> result = new HashMap<String, String>();
		String operationSuccess = "无班级信息，请重新选择";
		String classId = ParamUtils.getParameter(request, "classId");//班级id
		String courseID = ParamUtils.getParameter(request, "courseID");//原课程id
		if(StringUtil.isEmpty(classId)){
			model.addAttribute("operationSuccess", operationSuccess);
			result.put("msg", operationSuccess);
			return result;
		}
		//根据班级课程id的主键来判断是新增还是修改
		if(StringUtil.isNotEmpty(claCour.getCcId())){
			operationSuccess = coursePlanService.updateCourseAll(claCour, couTea,classId,courseID);
		}else{
			Map<String, String> map = new HashMap<String, String>();
			map.put("classId", classId);//班级id
			map.put("week", claCour.getWeek());//星期几
			map.put("tsId", claCour.getTsId());//时间排序的地址
			String index = coursePlanService.getCourseIdByInfo(map);//校验是否重复添加
			if(StringUtil.isNotEmpty(index)){
				operationSuccess = "已经在该时间有课程不能多次新增";
			}else{
				operationSuccess = coursePlanService.insertCourseAllInfo(clsSys, claCour, couTea,classId);
			}
		}
		model.addAttribute("operationSuccess", operationSuccess);
		result.put("msg", operationSuccess);
		return result;
	}
	
	
	/**
	 * 根据信息查询一条班级课程
	 * @param request
	 * @return
	 */
	@RequestMapping("/selectClassCourseIdByInfo")
	@Role(ignore=true)
	@ResponseBody
	public BaiseeClassCourseId selectClassCourseIdByInfo(HttpServletRequest request){
		String courseOrder = ParamUtils.getParameter(request, "courseOrder");
		String classId = ParamUtils.getParameter(request, "classId");
		String week = ParamUtils.getParameter(request, "week");
		Map<String, String> map = new HashMap<String, String>();
		map.put("courseOrder", courseOrder);//时间排序的地址
		map.put("classId", classId);//班级id
		map.put("week", week);//星期几
		
		return coursePlanService.getClassCourseIdByInfo(map);
	}
	
	
	
	
	/**
	 * 去新增/编辑页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/toEidt")
	@Role(ignore=true)
	public String getClassCourseIdByInfo(Model model,String cid,HttpServletRequest request){
		String classId = ParamUtils.getParameter(request, "classId");
		String courseOrder = ParamUtils.getParameter(request, "courseOrder");
		//String ccid = ParamUtils.getParameter(request, "ccid");
		String week = ParamUtils.getParameter(request, "week");
		if (StringUtil.isNotEmpty(courseOrder)) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("courseOrder", courseOrder);//时间排序的地址
			map.put("classId", classId);//班级id
			map.put("week", week);//星期几
			BaiseeClassCourseId courseId = coursePlanService.getClassCourseIdByInfo(map);
			model.addAttribute("course", courseId);
		}
		BaiseeUser user = (BaiseeUser) SessionUtil.getUserInfo(request); // 获取当前登录的用户
		//根据当前登录用户类型查询考试可以用的场地
		List<BaiseeClassroom> classrooms = classroomService.selectClassroomList( user.getUserType());//考试场地
		model.addAttribute("classrooms",classrooms);
		//查询上课的时间段 高中4/初中9  为了制作课程表Y轴
		List<BaiseeTimeStage> timeStageList = timeStageService.selectTimeStages();
		model.addAttribute("timeStageList",timeStageList);//时间段
		//查询上课的天数  高中6/初中11  为了制作课程表X轴
		List<BaiseeCourseSystem> courseSystemList = courseSystemService.selectAllCourseSystem();
		model.addAttribute("courseSystemList", courseSystemList);//所有的课制
		List<BaiseeClassSystem> list = classSystemService.selectClassSystemsByCid(classId);
		model.addAttribute("list", list);//班级的课制
		List<BaiseeClassCourse> classList = claCouserService.selectClassCourseByCid(classId);
		model.addAttribute("classList", classList);//班级课程
		//字典中的所学类别要查出来
		List<IcipBusDict> dicts = dictionariesService.selectIcipBusDictByDictName("courseType");
		//根据用户类型查询对应的员工列表 
		List<Employee> users = empService.selectEmpByType(user.getUserType());
		model.addAttribute("users", users);//员工信息
		model.addAttribute("dicts", dicts);//课程类型
		model.addAttribute("classId", classId);//班级id
		return "coursePlan/classcourse_edit";
	}
	
	/**
	 * 校验是否有重复的数据
	 * @param request
	 * @return
	 */
	@RequestMapping("/validateClassCourse")
	@Role(ignore=true)
	@ResponseBody
	public Boolean validateCourse(HttpServletRequest request){
		String tsId = ParamUtils.getParameter(request, "tsId");
		String classId = ParamUtils.getParameter(request, "classId");
		String week = ParamUtils.getParameter(request, "week");
		String empId = ParamUtils.getParameter(request, "empId");
		Map<String, String> map = new HashMap<String, String>();
		map.put("empId", empId);//教师
		map.put("classId", classId);//班级id
		map.put("week", week);//星期几
		map.put("tsId", tsId);//时间排序的地址
		BaiseeClassCourseId index = coursePlanService.getClassCourseIdByInfo(map);
		System.out.println(index+"123");
		if(index == null){
			return true;
		}
		    return false;
	}
	
	/**
	 * 删除班级课程
	 * @param model
	 * @param couTea
	 * @param request
	 * @return
	 */
	@RequestMapping("/delClassCourse")
	@Role(value="KCGL_BJKCSC")
	public String delClassCourse(Model model,BaiseeCourseTeacher couTea,HttpServletRequest request){
		String courseTeacherId = ParamUtils.getParameter(request, "courseTeacherId");//课程主键
		String ccId = ParamUtils.getParameter(request, "ccId");//课程班级主键
		String classId = ParamUtils.getParameter(request, "classId");//班级id
		String operationSuccess = coursePlanService.delClassCourse(ccId,courseTeacherId,couTea);
		model.addAttribute("operationSuccess", operationSuccess);
		return "forward:toCourseList.ht?cid="+classId;
	}
}
