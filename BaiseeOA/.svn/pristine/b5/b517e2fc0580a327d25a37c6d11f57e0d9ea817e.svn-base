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
import cn.baisee.oa.constants.CacheKeyEnum;
import cn.baisee.oa.model.BaiseeUser;
import cn.baisee.oa.model.ReturnInfo;
import cn.baisee.oa.model.course.BaiseeCourse;
import cn.baisee.oa.model.empinfo.Employee;
import cn.baisee.oa.model.examination.BaiseeItemBank;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.BaiseeCourseService;
import cn.baisee.oa.service.BaiseeItemBankService;
import cn.baisee.oa.service.EmployeeService;
import cn.baisee.oa.service.UserService;
import cn.baisee.oa.utils.ParamUtils;
import cn.baisee.oa.utils.RedisUtils;
import cn.baisee.oa.utils.SessionUtil;
/**
 * 题库Controller
 * @author jxx
 *
 */
@Controller
@RequestMapping("/itemBank")
public class BaiseeItemBankController {
	
	@Autowired
	private BaiseeItemBankService itemBankServiceImpl; //考试表Service
	@Autowired
	private UserService userService;		//用户表Service
	@Autowired 
	private BaiseeCourseService courseService;
	@Autowired
	private EmployeeService service;
	
	// 查询试题列表
	@RequestMapping("/itemBankList")
	@Role("KSGL_STGL")
	public ModelAndView itemBankList(HttpServletRequest request, ReturnInfo info) {
		Integer pageNum = ParamUtils.getPageNumParameters(request);
		Integer pageSize = ParamUtils.getPageSizeParameters(request);
		String itemlableSearch = ParamUtils.getParameter(request, "itemlableSearch");
		String dictId = ParamUtils.getParameter(request, "dictId");
		String empId = ParamUtils.getParameter(request, "empId");
		ModelAndView mv = new ModelAndView();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNum", pageNum);
		map.put("pageSize", pageSize);
		map.put("itemlableSearch", itemlableSearch);
		map.put("dictId", dictId);//类型
		map.put("empId", empId);//创建人
		PageInfo<BaiseeItemBank> pageInfo = itemBankServiceImpl.selectitemBankList(map);
		// 查询试题分类数据（作为查询条件出现）
		List<BaiseeCourse> course = courseService.selectCourseListByType("");// 查询试题分类数据
		// 查询所有教师（作为查询条件出现）
		List<Employee> empNames = service.selectEmpByType(null);
		if( !"".equals(info.getMessage())) {
			mv.addObject("returnInfo", info);
		}
		mv.addObject("pageResult", pageInfo);
		mv.addObject("course", course);
		mv.addObject("empNames", empNames);
		mv.addObject("itemlableSearch", itemlableSearch);
		mv.addObject("dictId", dictId);
		mv.addObject("empId", empId);
		mv.setViewName("examination/ItemBank");
		return mv;
	}
	
		// 查询单个试题
		@RequestMapping("/itemBankById")
		@Role("KSGL_STYL")
		public ModelAndView itemBankById(HttpServletRequest request) {
			ModelAndView mv = new ModelAndView();
			String iId = ParamUtils.getParameter(request, "iId");
			BaiseeItemBank itemBank = itemBankServiceImpl.getitemBankById(iId);
			mv.addObject("itemBank", itemBank);
			mv.setViewName("examination/questionPreview");
			return mv;
		}
		// 跳转（新增/修改）试题页面
		@RequestMapping("/itemBankjudge")
		@Role("KSGL_XZST")
		public ModelAndView itemBankjudge(HttpServletRequest request) {
			ModelAndView mv = new ModelAndView();
			String iId = ParamUtils.getParameter(request, "iId");
			BaiseeItemBank itemBank = new BaiseeItemBank();
			// 查询试题分类数据（作为下拉选择）
			List<BaiseeCourse> course = courseService.selectCourseListByType("");// 查询试题分类数据
			if(StringUtils.isNotEmpty(iId)) {//做修改     根据Id查询信息 赋值给 itemBank
				itemBank.setiId(iId);
				itemBank = itemBankServiceImpl.getitemBankById(itemBank.getiId());
			} else {
				// 做新增
			}
			mv.addObject("course", course);
			mv.addObject("itemBank", itemBank);
			mv.setViewName("examination/ItemBank_info");
			return mv;
		}
		// 修改时候判断题目是否有重复
		@RequestMapping("/judgeItemBank2")
		@Role("KSGL_XZST")
		@ResponseBody
		public Boolean judgeItemBank2(HttpServletRequest request) {
			String subject = ParamUtils.getParameter(request, "subject");
			String iId = ParamUtils.getParameter(request, "iId");
			boolean success = itemBankServiceImpl.getitemBankBySubject1( subject, iId);
			return success;
		}
		
		// 新增试题
		@RequestMapping("/saveitemBank")
		@Role("KSGL_XZST")
		public ModelAndView saveitemBank(HttpServletRequest request, BaiseeItemBank itemBank) {
			ModelAndView mv = new ModelAndView();
			String iId = itemBank.getiId();
			List<BaiseeCourse> course = courseService.selectCourseListByType("");// 查询试题分类数据
			if(StringUtils.isEmpty(itemBank.getAnswer())|| StringUtils.isEmpty(itemBank.getSelectOne())|| 
					StringUtils.isEmpty(itemBank.getSelectTwo())) {
				mv.addObject("itemlableSearch", "不能输入空值!");
				mv.addObject("itemBank", itemBank);
				mv.addObject("course", course);
				mv.setViewName("examination/ItemBank_info");
				return mv;
			}
			int success = 0;
			if(StringUtils.isNotEmpty(iId)) {// 修改
				success = itemBankServiceImpl.updateItemBank( itemBank);// 根据Id修改试题
				mv.addObject("promptInformation",success > 0 ? "操作成功" : "操作 失败");
			}else {// 新增
				BaiseeUser user = (BaiseeUser) SessionUtil.getUserInfo(request); // 获取当前登录的用户
				itemBank.setCreateName(user.getUserId());
				success = itemBankServiceImpl.saveitemBank( itemBank);
				mv.addObject("promptInformation",success > 0 ? "操作成功" : "操作 失败");
			}
			mv.addObject("itemBank", itemBank);
			mv.addObject("course", course);
			mv.setViewName("forward:/itemBank/itemBankList.ht");
			return mv;
		}
		// 删除试题
		@RequestMapping("/delitemBank")
		@Role("KSGL_SCST")
		public ModelAndView delitemBank(HttpServletRequest request) {
			String[] ids = request.getParameterValues("ids");
			ReturnInfo returnInfo = itemBankServiceImpl.delitemBankById( ids);
			return itemBankList( request, returnInfo);
		}
		
		//根据试题类型进行查找
		@ResponseBody
		@Role("KSGL_STGL")
		@RequestMapping("/typeQuery")
		public List<BaiseeItemBank> typeQuery(HttpServletRequest request){
			String did = ParamUtils.getParameter(request, "did");
			List<BaiseeItemBank> itemBanks = (List<BaiseeItemBank>) RedisUtils.utils.get(CacheKeyEnum.CURRICULUM.getCacheKey()+did);
			if(itemBanks != null) {
				return itemBanks;
			}else {
				List<BaiseeItemBank> typeQuery = itemBankServiceImpl.selectTypeQuery(did);
				RedisUtils.utils.set(CacheKeyEnum.CURRICULUM.getCacheKey()+did, typeQuery, null);
				return typeQuery;
			}
		}
		
		
		@ResponseBody
		@Role("KSGL_STGL")
		@RequestMapping("/whetherToOccupyOrNot")
		public boolean whetherToOccupyOrNot(HttpServletRequest request){	//判断试题是否在被试卷使用中
			String iId = ParamUtils.getParameter(request, "iId");
			return itemBankServiceImpl.whetherToOccupyOrNot(iId);
		}
}
