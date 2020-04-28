package cn.baisee.oa.controller;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
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
import cn.baisee.oa.dao.stu.BaiseeItemBankMapper;
import cn.baisee.oa.model.BaiseeUser;
import cn.baisee.oa.model.ReturnInfo;
import cn.baisee.oa.model.course.BaiseeCourse;
import cn.baisee.oa.model.empinfo.Employee;
import cn.baisee.oa.model.examination.BaiseeItemBank;
import cn.baisee.oa.model.examination.BaiseeTestpaper;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.BaiseeCourseService;
import cn.baisee.oa.service.BaiseeItemBankService;
import cn.baisee.oa.service.BaiseeTestpaperService;
import cn.baisee.oa.service.EmployeeService;
import cn.baisee.oa.utils.ParamUtils;
import cn.baisee.oa.utils.SessionUtil;

import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * 试卷controller
 * @author admin
 *
 */
@RequestMapping("/testPaper")
@Controller
public class BaiseeTestPaperController {
	
	@Autowired
	private BaiseeTestpaperService testpaperService; // 试卷Service
	@Autowired
	private BaiseeItemBankService itemBankService;	// 试题Service
	@Autowired 
	private BaiseeCourseService courseService;
	@Autowired
	private EmployeeService service;
	@Autowired
	private BaiseeItemBankMapper itemBankMapper;
	/*查询试卷列表*/
	@RequestMapping("/testPaperList")
	@Role("KSGL_SGGL")
	public ModelAndView testPaperList(HttpServletRequest request, ReturnInfo info) {
		ModelAndView mv = new ModelAndView();
		String itemlableSearch = ParamUtils.getParameter(request, "itemlableSearch");
		String empId = ParamUtils.getParameter(request, "empId");
		Integer pageNum = ParamUtils.getPageNumParameters(request);
		Integer pageSize = ParamUtils.getPageSizeParameters(request);
		BaiseeUser user = (BaiseeUser) SessionUtil.getUserInfo(request); // 获取当前登录的用户
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("pageNum", pageNum);
		maps.put("pageSize", pageSize);
		maps.put("itemlableSearch", itemlableSearch);
		maps.put("userType", user.getUserType());
		maps.put("empId", empId);
		PageInfo<BaiseeTestpaper> pageResult = testpaperService.testPaperList(maps);
		// 查询所有教师（作为查询条件出现）
		List<Employee> empNames = service.selectEmpByType(null);
		if( !"".equals(info.getMessage())) {
			mv.addObject("returnInfo", info);
		}
		for (int i = 0; i < lists.size(); i++) {
			lists.remove(i);
			lists.clear();
		}
		mv.addObject("pageResult", pageResult);
		mv.addObject("empNames", empNames);
		mv.addObject("itemlableSearch", itemlableSearch);
		mv.addObject("empId", empId);
		mv.setViewName("examination/testPaperList");
		return mv;
	}
	
	List<String> lists = new ArrayList<String>();
	@RequestMapping("/savePaper")
	@Role("KSGL_CXSJ")
	public ModelAndView savePaper(HttpServletRequest request) {
		String[] ids = request.getParameterValues("iIds");
//		String tid = ParamUtils.getParameter(request, "tid");
		String paperName = ParamUtils.getParameter(request, "paperName");
		String examinationType = ParamUtils.getParameter(request, "examinationType");
//		BaiseeTestpaper testpapers = testpaperService.getTestpaperById( tid);// 根据Id查询试卷信息
		System.err.println("进来了");
		String[] values = null;
		ModelAndView mv = new ModelAndView();
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < ids.length; i++) {
			list.add(ids[i]);
		}
		values = list.get(0).split(",");
		for (int i = 0; i < values.length; i++) {
			list.add(values[i]);
		}
		for (int i = 0; i < values.length; i++) {
			lists.add(values[i]);
		}
		int itemBankCount = itemBankService.getCheckCountItemBankById(lists);
		List<BaiseeItemBank> itemBanks = itemBankService.getCheckitemBankById(lists);
		List<BaiseeCourse> course = courseService.selectCourseListByType("");
		if (course != null) {
			for (BaiseeCourse baiseeCourse : course) {
				mv.addObject("ciId", baiseeCourse.getCiId());
			}
		}
		BaiseeTestpaper testPaper = new BaiseeTestpaper();
		testPaper.setPaperName(paperName);
		testPaper.setExaminationType(examinationType);
		
		
		
		mv.addObject("itemBankCount", itemBankCount);
		mv.addObject("testpaper", testPaper);
		mv.addObject("course", course);
		mv.addObject("itemBanks", itemBanks);
		mv.setViewName("examination/newTestPaper");
		return mv;
	}
	
	/*查询试卷详情*/
	@RequestMapping("/queryPapers")
	@Role("KSGL_CXSJ")
	public ModelAndView queryPapers(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		String tid = ParamUtils.getParameter(request, "tid");
		String createTs = ParamUtils.getParameter(request, "createTs");
		String createUser = ParamUtils.getParameter(request, "createUser");
		Integer pageNum = ParamUtils.getPageNumParameters(request);
		Integer pageSize = ParamUtils.getPageSizeParameters(request);
		// 根据试卷Id查询关联试题
		PageInfo<BaiseeItemBank> pageResult = itemBankService.selectItemBankListById( tid, pageNum, pageSize);
		mv.addObject("pageResult", pageResult);
		mv.addObject("createTs", createTs);
		mv.addObject("createUser", createUser);
		mv.setViewName("examination/queryPapers");
		return mv;
	}
	
	@RequestMapping("/delItemBankById")
	@Role("KSGL_XZSJ")
	public ModelAndView delItemBankById(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		String ids = ParamUtils.getParameter(request, "iIds");
//		String tid = ParamUtils.getParameter(request, "tid");
//		String paperName = ParamUtils.getParameter(request, "paperName");
//		String examinationType = ParamUtils.getParameter(request, "examinationType");
		System.err.println(ids);
		for (int i = 0; i < lists.size(); i++) {
			if (ids.equals(lists.get(i))) {
				lists.remove(lists.get(i));
			}
		}
		mv.setViewName("forward:/testPaper/savePaper.ht?iIds="+lists);
		return mv;
	}
	
	@RequestMapping("/testPaperInfo")
	@Role("KSGL_XZSJ")
	public ModelAndView testPaperInfo(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		BaiseeTestpaper testpaper = new BaiseeTestpaper();
		String did = ParamUtils.getParameter(request, "did");
		String paperName = ParamUtils.getParameter(request, "paperName");
		String examinationType = ParamUtils.getParameter(request, "examinationType");
		List<BaiseeItemBank> itemBanks = itemBankService.selectTypeQuery(did);
		for (int i = 0; i < lists.size(); i++) {
			for (int j = 0; j < itemBanks.size(); j++) {
				if (itemBanks.get(j).getiId()==lists.get(i)) {
					itemBanks.remove(j);
				}
			}
		}
		if (lists.size() == 0) {
			mv.addObject("itemBanks", itemBanks);
		}
		if (lists.size() > 0) {
			mv.addObject("itemBanks", itemBanks);
		}
		testpaper.setPaperName(paperName);
		testpaper.setExaminationType(examinationType);
		mv.addObject("testpaper", testpaper);
		mv.setViewName("examination/testPaperInfo");
		return mv;
	}
	
	/*新增或者修改试卷跳转方法*/
	@RequestMapping("/testPaperjudge")
	@Role("KSGL_XZSJ")
	public ModelAndView testPaperjudge(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		String tid = ParamUtils.getParameter(request, "tid");// tid 试卷ID
		List<BaiseeItemBank> list = new ArrayList<BaiseeItemBank>();
		// 查询试题分类数据（作为查询条件出现）
		List<BaiseeCourse> course = courseService.selectCourseListByType("");// 查询试题分类数据
		if (course != null) {
			for (BaiseeCourse baiseeCourse : course) {
				mv.addObject("ciId", baiseeCourse.getCiId());
			}
		}
		BaiseeTestpaper testpaper = new BaiseeTestpaper();
		if(StringUtils.isNotEmpty(tid)) {// 修改
			list= testpaperService.selectTestPaperListById(tid);//  查出试卷地下所有试题
			testpaper = testpaperService.getTestpaperById( tid);// 根据Id查询试卷信息
		}
		for (BaiseeItemBank baiseeItemBank : list) {
			lists.add(baiseeItemBank.getiId());
		}
		
		int itemBankCount = itemBankMapper.getItemBankCount(tid);
		mv.addObject("itemBankCount", itemBankCount);
		mv.addObject("itemBanks", list); // 查询所有的信息
		mv.addObject("course", course);
		mv.addObject("testpaper", testpaper);
		mv.setViewName("examination/newTestPaper");
		return mv;
	}
	
	/*删除试卷*/
	@RequestMapping("/delTestPaper")
	@Role("KSGL_SCSJ")
	public ModelAndView delTestPaper(HttpServletRequest request) {
		String[] ids = request.getParameterValues("ids");
		ReturnInfo info = testpaperService.delTestPaper( ids);
		return testPaperList(request, info);
	}
	
	
	/*用于更新题库js*/
	@ResponseBody()
	@Role("KSGL_SXTK")
	@RequestMapping("/tes")
	public void tes(HttpServletRequest request) {
		List<BaiseeItemBank> back = itemBankService.selectItem();
		OutputStream out = null;
		String genpath = request.getSession().getServletContext().getRealPath("/"); 
		try {
			String path = genpath + "\\js\\testQuestions.js"; // 模板位置
			out = new FileOutputStream(path);
			ObjectMapper mapper = new ObjectMapper();  
			String writeValueAsString = mapper.writeValueAsString(back); 
			out.write(writeValueAsString.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	// 新增或者修改页面
	@RequestMapping("/addedOrModified")
	@Role("KSGL_XZSJ")
	public ModelAndView addedOrModified(HttpServletRequest request) {
		String tid = ParamUtils.getParameter(request, "tid"); // 试题Id
//		String iIds = ParamUtils.getParameter(request, "iIds"); // 试题列表的所有ID
		String paperName = ParamUtils.getParameter(request, "paperName"); 
		String examinationType = ParamUtils.getParameter(request, "examinationType"); //考试类型
		BaiseeUser user = (BaiseeUser) SessionUtil.getUserInfo(request); 
		ReturnInfo info = new ReturnInfo();
		List<String> list = new ArrayList<String>();
		String values = null;
		String val = null;
		int success = 0;
		if (lists.size() > 0) {
			for (int i = 0; i < lists.size(); i++) {
				list.add(lists.get(i));
			}
		}
		values = list.toString().trim();
		val = values.substring(1, values.length()-1).trim();
		if(StringUtils.isNotEmpty(tid)) {
			// 修改
			success = testpaperService.updateTestPaer(val, paperName,user.getUserId(), examinationType, tid);
			info.setMessage(success > 0 ? "操作成功!" : "操作失败!");
		}else {
			// 新增
			success = testpaperService.addTestPaer(val, paperName,user.getUserId(), examinationType, user.getUserType());
			info.setMessage(success > 0 ? "操作成功!" : "操作失败!");
		}
		return testPaperList(request, info);
	}
	
	// 校验 试卷名称是否被重复
	@RequestMapping("/checkName")
	@Role("KSGL_XZSJ")
	@ResponseBody
	public boolean checkName(HttpServletRequest request) {
		String paperName = ParamUtils.getParameter(request, "paperName"); // 试卷名称
		String tid = ParamUtils.getParameter(request, "tid"); // 试卷ID
		boolean success = testpaperService.checkName( paperName, tid);
		return success;
	}
	// 校验 试卷是否在被班级使用着  如果使用不能修改 如果重复数据不能新增
	@RequestMapping("/checkId")
	@Role("KSGL_XZSJ")
	@ResponseBody
	public ReturnInfo checkId(HttpServletRequest request) {
		String paperName = ParamUtils.getParameter(request, "paperName"); // 试卷名称
		String tid = ParamUtils.getParameter(request, "tid"); // 试卷ID
		String id = ParamUtils.getParameter(request, "id"); // 选中的试题ID
		ReturnInfo info = testpaperService.checkId( paperName, tid, id);
		return info;
	}
	
	
}
