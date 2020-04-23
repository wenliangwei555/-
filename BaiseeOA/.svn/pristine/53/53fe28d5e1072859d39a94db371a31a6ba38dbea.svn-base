package cn.baisee.oa.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.baisee.oa.annotation.Role;
import cn.baisee.oa.model.BaiseeCalendar;
import cn.baisee.oa.model.IcipBusDict;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.BaiseeCalendarService;
import cn.baisee.oa.service.DictionariesService;
import cn.baisee.oa.utils.ParamUtils;
import cn.baisee.oa.utils.StringUtil;

@RequestMapping("/calendar")
@Controller
public class BaiseeCalendarController {

	@Autowired
	private BaiseeCalendarService calendarService;
	@Autowired
	private DictionariesService dictionariesService ;
	
	/**
	 * 跳转到日程查询页面
	 * @return
	 */
	@RequestMapping("/toCalendarList.ht")
	@Role(value="RCGL_RCCK")
	public ModelAndView toCalendarList(HttpServletRequest request){
		ModelAndView model=new ModelAndView("calendar/calendar_list");
		int pageNum=ParamUtils.getPageNumParameters(request);
		int pageSize=ParamUtils.getPageSizeParameters(request);
		PageInfo<BaiseeCalendar> info=calendarService.getCalendarList(pageNum, pageSize);
		model.addObject("pageResult", info);
		return model;
	}
	
	/**
	 * 去新增页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/goSaveCalendar.ht")
	@Role(value="RCGL_RCXZ")
	public ModelAndView goSaveCalendar(HttpServletRequest request,BaiseeCalendar calendar){
		ModelAndView model=new ModelAndView("calendar/calendar_add");
		if (StringUtil.isNotEmpty(calendar.getcId())) {
			calendar = calendarService.getById(calendar.getcId());
		}
		List<IcipBusDict> dic = dictionariesService.selectIcipBusDictByDictName("clockType");
		model.addObject("calendar", calendar);
		model.addObject("dic", dic);
		return model;
	}
	
	
	
	/**
	 * 新增日程
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveCalendar.ht")
	@Role(value="RCGL_RCXZ")
	public String saveCalendar(HttpServletRequest request,BaiseeCalendar calendar){
		calendarService.saveOrUpdateCalendar(calendar);
		return "forward:toCalendarList.ht";
	}
	
	/**
	 * 校验日期是否已存在
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/checkDate", method = RequestMethod.POST)  
	@Role(ignore=true)
	public Object checkDate(HttpServletRequest request)throws Exception{
		String date=request.getParameter("date");
		BaiseeCalendar calendar = calendarService.getByDate(date);
		String cId=request.getParameter("cId");
		if (calendar==null) {
			return true;
		}else{
			if (calendar.getcId().equals(cId)) {
				return true;
			}else{
				return false;
			}
		}
	}
	
	
	
	/**
	 * 删除日程
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteCalendar.ht")
	@Role(value="RCGL_RCSC")
	public String deleteCalendar(HttpServletRequest request){
		String[] ids=ParamUtils.getParameters(request, "ids");
		calendarService.delCalendar(ids);
		return "forward:toCalendarList.ht";
	}
	
	
	
}
