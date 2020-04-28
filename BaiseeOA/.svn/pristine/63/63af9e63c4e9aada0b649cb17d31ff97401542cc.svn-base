package cn.baisee.oa.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.baisee.oa.annotation.Role;
import cn.baisee.oa.model.BaiseeClockTime;
import cn.baisee.oa.model.IcipBusDict;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.BaiseeClockTimeService;
import cn.baisee.oa.service.DictionariesService;
import cn.baisee.oa.utils.ParamUtils;

@Controller
@RequestMapping("/clockTime")
public class BaiseeClockTimeController {
	
	@Autowired
	private BaiseeClockTimeService timeService;
	
	@Autowired
	private  DictionariesService  dictionariesService ;

	/**
	 * 分页
	 * @param request
	 * @return
	 */
	@RequestMapping("toList")
	@Role(value="CSGL_CSCX")
	public ModelAndView toList(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("clockTime/clockTime_list");
		Integer pageNum = ParamUtils.getPageNumParameters(request);
		Integer pageSize = ParamUtils.getPageSizeParameters(request);
		PageInfo<BaiseeClockTime> page = timeService.getList(pageNum, pageSize);
		mv.addObject("pageResult", page);
		return mv;
	}
	
	/**
	 * 去新增页面
	 * @param request
	 * @return
	 */
	@RequestMapping("goAddTime")
	@Role(value="CSGL_CSXZ")
	public ModelAndView goAddTime(HttpServletRequest request,BaiseeClockTime time) {
		ModelAndView mv = new ModelAndView("clockTime/clockTime_add");
		if(time!=null && time.getCtId()!=null) {
			time = timeService.getTimeByID(time.getCtId());
		}
		List<IcipBusDict> dic =dictionariesService.selectIcipBusDictByDictName("clockType");
		mv.addObject("info", time);
		mv.addObject("dic", dic);
		return mv;
	}
	
	/**
	 * 执行新增/修改
	 * @param request
	 * @param time
	 * @return
	 */
	@RequestMapping("toSaveTime")
	@Role(value="CSGL_CSXZ")
	public String toSaveTime(HttpServletRequest request,BaiseeClockTime time) {
		timeService.addOrupdateTime(time);
		return "forward:toList.ht";
	}
	
	
	/**
	 * 删除
	 * @param request
	 * @return
	 */
	@RequestMapping("deleteTime")
	@Role(value="CSGL_CSSC")
	public String deleteTime(HttpServletRequest request) {
		String[] ids=ParamUtils.getParameters(request, "ids");
		timeService.deleTime(ids);
		return "forward:toList.ht";
	}
	
}
