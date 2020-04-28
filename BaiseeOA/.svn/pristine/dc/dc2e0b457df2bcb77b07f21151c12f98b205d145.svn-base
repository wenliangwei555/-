package cn.baisee.oa.controller;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.history.HistoricActivityInstance;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.baisee.oa.annotation.Role;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.utils.ActivityUtils;
import cn.baisee.oa.utils.SessionUtil;
@Controller
@RequestMapping("/historyDone")
public class HaveDoneController {
	@RequestMapping("toHaveDoneList")
	@Role(value="WDYB")
	/**
	 * 我的已办分页显示
	 * @param request
	 * @param response
	 * @param pageInfo
	 * @return
	 */
	public ModelAndView toHaveDoneList(HttpServletRequest request,PageInfo<HistoricActivityInstance> pageInfo) throws Exception {
		ModelAndView view  = new ModelAndView("hd/hd_list");
		PageInfo<Map<String, String>> info = ActivityUtils.selectHisTaskByUserIdByPage(SessionUtil.getUserInfo(request).getUserId(), request);
		view.addObject("pageResult", info);
		return view;
	}
}
