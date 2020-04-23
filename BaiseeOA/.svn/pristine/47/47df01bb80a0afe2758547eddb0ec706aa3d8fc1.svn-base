package cn.baisee.oa.controller;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;























import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.baisee.oa.annotation.Role;
import cn.baisee.oa.model.BaiseeEvaluateInfo;
import cn.baisee.oa.model.BaiseeUser;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.BaiseeEvaluateInfoService;
import cn.baisee.oa.service.BaiseeLeaveService;
import cn.baisee.oa.utils.ParamUtils;
import cn.baisee.oa.utils.SessionUtil;


@Controller
@RequestMapping("/evaluate")
public class BaiseeEvaluateInfoController {
	
	@Autowired
	private BaiseeEvaluateInfoService service;
	
	@Autowired
	private BaiseeLeaveService baiseeLeaveService;
	
	
	/**
	 * 分页列表
	 * @param model
	 * @return
	 */
	@RequestMapping("/toList")
	@Role("PJGL_ZS")
	public ModelAndView toList(ModelAndView model,HttpServletRequest request,HttpServletResponse response){
		//获取页面的数据
		 response.setContentType("text/html;charset=utf-8");
		int pageNum=ParamUtils.getPageNumParameters(request);
		int pageSize=ParamUtils.getPageSizeParameters(request);
		String itemlableSearch=ParamUtils.getParameter(request, "itemlableSearch");
		String clazz=ParamUtils.getParameter(request, "clazz");
		/*Cookie cookie = new Cookie("clazz", clazz);
		cookie.setMaxAge(24 * 60 * 60);
		response.addCookie(cookie);*/
		//查询列表分页
		PageInfo<BaiseeEvaluateInfo> list = service.selectAll(pageNum,pageSize,itemlableSearch,clazz);
		//获取所有的班级
		BaiseeEvaluateInfo info = service.Cname();
		//经过条件的数据
		model.addObject("itemlableSearch",itemlableSearch);
		model.addObject("clazz",clazz);
		model.addObject("info",info);
		if(list!=null){
			model.addObject("pageResult", list);// 将页面数据放进去	
		}
		model.setViewName("stuevaluate/eva_list");
		return model;
	}
	  
	
	/**
	 * 添加数据
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addORupdate") 
	@Role(value="PJGL_ZS")
	public ModelAndView addEval(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("stuevaluate/eva_Info");
		BaiseeEvaluateInfo levInfo = new BaiseeEvaluateInfo();
		mv.addObject("info", levInfo);
		return mv;
	}
	/**
	 * 修改数据
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/upDate")
	@Role("KSGL_CKKS")
	public ModelAndView update(ModelAndView model,int id,HttpServletRequest request){
		BaiseeEvaluateInfo info = service.selectById(id);
		model.addObject("info",info);
		System.out.println(info.getName());
		model.setViewName("stuevaluate/eva_Info");
		return model;
	}
	
	
	/**
	 * 修改/添加执行方法
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/add")
	@Role("KSGL_CKKS")
	public ModelAndView add(HttpServletRequest request,HttpServletResponse response){
		ModelAndView model = new ModelAndView();
		//获取页面的数据
		String eid = request.getParameter("evid");
		String sid = request.getParameter("sid");
		String reason = request.getParameter("reason");
		//--------------
		
		//获取当前登录人的id
		BaiseeUser currUser = (BaiseeUser)SessionUtil.getUserInfo(request);
		String uid =currUser.getUserId();
		//--------------
		String cname = service.getCname(sid);
		if(eid == null || "".equals(eid)){
			BaiseeEvaluateInfo insert = new BaiseeEvaluateInfo();
			//添加数据
			insert.setcName(cname);
			insert.setSid(sid);
			insert.setsId(sid);
			insert.setEmp_id(uid);
			insert.setEvInfo(reason);
			insert.setEvDate(getNowTime());
			//添加数据
			service.insertDate(insert);
		}else{
			BaiseeEvaluateInfo info = new BaiseeEvaluateInfo();
			Integer evid = Integer.valueOf(eid);
			info.setEvInfo(reason);
			info.setEvid(evid);
			info.setsId(sid);
			info.setcName(cname);
			service.updateEVinfo(info);
		}
		String itemlableSearch=ParamUtils.getParameter(request, "itemlableSearch");
		String clazz=ParamUtils.getParameter(request, "clazz");
		model.addObject("itemlableSearch",itemlableSearch);
		model.addObject("clazz",clazz);
		return toList(model,request,response);
	}
	
	/**
	 * 获得当前时间并转换为String类型
	 * @return
	 */
	public String getNowTime(){
		Date time = new Date();
		SimpleDateFormat timeo = new SimpleDateFormat("yyyy-MM-dd");
		String NowTime = timeo.format(time);
		return NowTime;
	}
	
	
	/**
	 * 批量删除评价信息
	 * @param model
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/del")
	@Role(value = "PJXX_SCXSPJXX")
	public ModelAndView del(String itemlableSearch,String clazz,ModelAndView model,String id,HttpServletRequest request,HttpServletResponse response){
		String[] s = id.split(",");	//分割字符串并放入数组
		int[] ids = StringToInt(s);
		service.deleteEva(ids);
		return toList(model,request,response);
	}
	/**
	 * 将String类型数组转换为Int类型
	 * @param arrs
	 * @return
	 */
	public int[] StringToInt(String[] arrs){
		int[] ints = new int[arrs.length];
		for(int i=0;i<arrs.length;i++){
			ints[i] = Integer.parseInt(arrs[i]);
		}
		return ints;
	}
	
	

		
}
