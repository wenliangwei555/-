package cn.baisee.oa.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.baisee.oa.annotation.Role;
import cn.baisee.oa.model.BaiseeClazz;
import cn.baisee.oa.model.BaiseeUser;
import cn.baisee.oa.model.course.BaiseeClassroom;
import cn.baisee.oa.model.empinfo.Employee;
import cn.baisee.oa.model.examination.BaiseeExamination;
import cn.baisee.oa.model.examination.BaiseeTestpaper;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.BaiseeClassroomService;
import cn.baisee.oa.service.BaiseeExaminationService;
import cn.baisee.oa.service.BaiseeTestpaperService;
import cn.baisee.oa.service.ClazzService;
import cn.baisee.oa.service.EmployeeService;
import cn.baisee.oa.service.UserService;
import cn.baisee.oa.utils.ParamUtils;
import cn.baisee.oa.utils.SessionUtil;

/**
 * 考试Controller
 * @author jxx
 *
 */
@Controller
@RequestMapping("/examination")
public class BaiseeExaminationController {
	
	@Autowired
	private BaiseeExaminationService examinationService;
	@Autowired
	private BaiseeTestpaperService testPaperService;
	@Autowired
	private ClazzService classService;
	@Autowired
	private BaiseeClassroomService classroomService;
	@Autowired
	private UserService userService;
	@Autowired
	private EmployeeService empoyeeService;
	
	
	// 列表信息
	@RequestMapping("/eInformationList")
	@Role("KSGL_CKKS")
	public ModelAndView eInformationList(HttpServletRequest request, String promptInformation) {
		Integer pageNum = ParamUtils.getPageNumParameters(request);
		Integer pageSize = ParamUtils.getPageSizeParameters(request);
		ModelAndView mv = new ModelAndView();
		BaiseeUser user = (BaiseeUser) SessionUtil.getUserInfo(request); // 获取当前登录的用户
		PageInfo<BaiseeExamination> pageInfo = examinationService.eInformationList(pageNum, pageSize, user.getUserType());
		mv.addObject("pageResult", pageInfo);
		mv.addObject("promptInformation", promptInformation);
		mv.setViewName("examination/examinationList");
		return mv;
	}
	
	@RequestMapping("/examinationJump")
	@Role("KSGL_XZKS")
	public ModelAndView examinationJump(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		String eid = ParamUtils.getParameter(request, "eid");
		BaiseeExamination examination = new BaiseeExamination();
		BaiseeUser user = (BaiseeUser) SessionUtil.getUserInfo(request); // 获取当前登录的用户
		List<BaiseeTestpaper> testpapers = testPaperService.selectTestPaperList(user.getUserType());//查询所有试卷
		List<Employee> employee = empoyeeService.selectEmpByType(user.getUserType());// 查询监考老师
		List<BaiseeClassroom> classroom = classroomService.selectClassroomList( user.getUserType());//考试场地
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userType", user.getUserType());
		String[] bsClass = null;
		try {
			bsClass = (String[]) request.getSession().getAttribute("bsClass");// 根据当前登录人查询所带的班级
		} catch (Exception e) {
			e.printStackTrace();
			bsClass = new String [0];
		}
		List<BaiseeClazz> clazz = classService.selectClazzByClaId( bsClass, user.getUserType()); //选择班级
		if(StringUtils.isNotEmpty(eid)) {
			// 查询信息展示到页面上
			examination = examinationService.getExaminatioByEid(eid);
		}
		mv.addObject("testpapers", testpapers);// 查询所有的试卷
		mv.addObject("employee", employee);    	   // 查询所有的监考
		mv.addObject("classrooms", classroom);  // 查询考试场地
		mv.addObject("clazzs", clazz);		   // 班级信息
		mv.addObject("examination", examination); //考试信息
		mv.setViewName("examination/examinationInfo");
		return mv;
	}
	
	@RequestMapping("/addExamination")
	@Role("KSGL_XZKS")
	public ModelAndView addExamination(HttpServletRequest request, BaiseeExamination examination) {
		BaiseeUser user = (BaiseeUser) SessionUtil.getUserInfo(request);
		examination.setStateOfOwnership(user.getUserType());
		ModelAndView mv = new ModelAndView();
		String createId = "";
		int success = 0;
		if(StringUtils.isNotEmpty(examination.getEid())) {// 修改
			success = examinationService.updateExaminationById( examination);
			mv.setViewName("forward:/examination/eInformationList.ht");
		}else { // 新增
			examination.setEid(createId);
			success = examinationService.addExamination( examination);
			if (success > 0) {
				mv.setViewName("forward:/examination/eInformationList.ht");
			}
			else {
				mv.setViewName("forward:/examination/examinationJump.ht");
			}
		}
		return mv;
	}
	@RequestMapping("/delExamination")
	@Role("KSGL_SCKS")
	public ModelAndView addExamination(HttpServletRequest request) {
		String[] parameterValues = request.getParameterValues("ids");
		int success = examinationService.delExaminationById( parameterValues);
		return eInformationList(request, success>0?"操作成功":"该考试已经在使用当中不能删除!");
	}

	@RequestMapping("/whetherToOccupyOrNot")
	@Role("KSGL_SCKS") // 校验考试考试是否在使用当中
	@ResponseBody
	public boolean whetherToOccupyOrNot(HttpServletRequest request) {
		String eid = ParamUtils.getParameter(request, "eid");
		if("".equals(eid)) {// 证明是新增
			return true;
		}else {
			return examinationService.whetherToOccupyOrNot( eid);
		}
	}
	
	
}
