package cn.baisee.oa.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.baisee.oa.annotation.Role;
import cn.baisee.oa.model.IcipBusDict;
import cn.baisee.oa.model.course.BaiseeCourse;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.BaiseeCourseService;
import cn.baisee.oa.service.DictionariesService;
import cn.baisee.oa.utils.ParamUtils;
import cn.baisee.oa.utils.StringUtil;

/**
 * 课程
 * @author Administrator
 *
 */
@Controller
@RequestMapping("course")
public class BaiseeCourseController {
	//课程service
	@Autowired
	private BaiseeCourseService courseService;
	
	//字典service
	@Autowired
	private DictionariesService dictionariesService;
	/**
	 * 分页查询所有课程信息
	 * @param request
	 * @param retuAO
	 * @return
	 */
	@RequestMapping(value="toCourseList")
	@Role(value="KCGL_KCCK")
	public ModelAndView toCourseList(HttpServletRequest request,BaiseeCourse course,ModelAndView model) {
		Integer pageNum = ParamUtils.getPageNumParameters(request);								/*当前页*/
		Integer pageSize = ParamUtils.getPageSizeParameters(request);
		PageInfo<BaiseeCourse> pageInfo = courseService.selectCourseListByInfo(pageNum, pageSize, course);
		model.addObject("pageResult", pageInfo);
		model.addObject("course", course);
		model.setViewName("course/course_list");
		//字典中的所学类别要查出来
		List<IcipBusDict> dicts = dictionariesService.selectIcipBusDictByDictName("courseType");
		model.addObject("dicts", dicts);
		return model;	
	}
	/**
	 * 跳转到编辑课程页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="toAddOrUpdateCourse")
	@Role(value="KCGL_KCXZ")
	public String toAddOrUpdateCourse(HttpServletRequest request,Model model){
		String ciId = ParamUtils.getParameter(request, "ciId");//修改页面传过来的参数
		if(StringUtil.isNotEmpty(ciId)){//修改
			//从数据库查询要修改的课程
			BaiseeCourse course = courseService.selectCourseById(ciId);
			if(course != null){
				model.addAttribute("course",course);
			}else{
				model.addAttribute("operationSuccess", "无对应的数据");
			}			
		}else{
			model.addAttribute("course", new BaiseeCourse());//要新增一个课程信息
		}
		//字典中的所学类别要查出来
		List<IcipBusDict> dicts = dictionariesService.selectIcipBusDictByDictName("courseType");
		model.addAttribute("dicts", dicts);
		return "course/courseEdit";
	}
	
	/**
	 * 新增或者修改课程
	 * @param request
	 * @param model
	 * @param course
	 * @return
	 */
	@RequestMapping(value="addOrUpdateCourse")
	@Role(value="KCGL_KCXZ")
	public String addOrUpdateCourse(HttpServletRequest request,Model model,BaiseeCourse course){
		 String operationSuccess = courseService.addOrUpdateCourse(course);
		//字典中的所学类别要查出来
		List<IcipBusDict> dicts = dictionariesService.selectIcipBusDictByDictName("courseType");
		model.addAttribute("dicts", dicts);
		model.addAttribute("course", course);
		model.addAttribute("operationSuccess", operationSuccess);
		return "course/courseEdit";
	}
	/**
	 * 删除课程
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="deleteCourse")
	@Role(value="KCGL_KCSC")
	public String deleteCourse(HttpServletRequest request,Model model){
		String [] ids = ParamUtils.getParameters(request, "ids");//得到页面穿过来的 eid的数组
		String operationSuccess = courseService.deleteCourseByIds(ids);
		model.addAttribute("operationSuccess", operationSuccess);
		return "forward: toCourseList.ht";
	}
	@Role(ignore=true)
	@RequestMapping("/selectCourseListByType")
	@ResponseBody
	public List<BaiseeCourse> selectCourseListByType(HttpServletRequest request){
		String courseType = ParamUtils.getParameter(request, "courseType");
		return courseService.selectCourseListByType(courseType);
	}
	
	@Role(ignore=true)
	@RequestMapping("/validateCourse")
	@ResponseBody
	public boolean validateCourse(HttpServletRequest request){
		String courseType = ParamUtils.getParameter(request, "courseType");
		String courseTtitle = ParamUtils.getParameter(request, "courseTtitle");
		String ciId = ParamUtils.getParameter(request, "ciId");
		List<BaiseeCourse> list = courseService.validateCourse(courseType,courseTtitle,ciId);
		if(list !=null && list.size()>0){
			return false;
		}
		return true;
	}
}
