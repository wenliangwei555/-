package cn.baisee.oa.controller;

import cn.baisee.oa.annotation.Role;
import cn.baisee.oa.model.BaiseeClassMerger;
import cn.baisee.oa.model.BaiseeClazz;
import cn.baisee.oa.model.SessionUserInfo;
import cn.baisee.oa.model.student.BaiseeStudent;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.StudentService;
import cn.baisee.oa.utils.ParamUtils;
import cn.baisee.oa.utils.PropertiesUtil;
import cn.baisee.oa.utils.SessionUtil;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping(value = "/stuevaluate")
@Controller
public class BaiseeClsassMergerController {
    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/merger")
    @Role("BJHB")
    public ModelAndView stuList(HttpServletRequest request) throws Exception {
        Integer pageNum = ParamUtils.getPageNumParameters(request);
        Integer pageSize = ParamUtils.getPageSizeParameters(request);
        String itemlableSearch = "";
        String status = "";
        String startTime = "";
        String endTime = "";
        String returnFeeStatus = "";
        String str = ParamUtils.getParameter(request, "itemlableSearch");
        String sta = ParamUtils.getParameter(request, "status");
        String stuStartTime = ParamUtils.getParameter(request, "stuStartTime");
        String stuEndTime = ParamUtils.getParameter(request, "stuEndTime");
        String claStatus = ParamUtils.getParameter(request, "claStatus");
        String areas = ParamUtils.getParameter(request, "areas");
        String choiceStuState = ParamUtils.getParameter(request, "choiceStuState"); /* 学生状态 */
        if (str != null) {
            itemlableSearch = str;
        }
        if (sta != null) {
            status = sta;
        }
        if (stuStartTime != null) {
            startTime = stuStartTime.replace("-", "");
        }
        if (stuEndTime != null) {
            endTime = stuEndTime.replace("-", "");
        }
        PageInfo<BaiseeStudent> pageInfo = studentService.getForList(pageNum, pageSize, itemlableSearch, status,
                startTime, endTime, returnFeeStatus, request, claStatus, areas, choiceStuState);

        ModelAndView mv = new ModelAndView("stuevaluate/merger_list");
        if (pageInfo.getTotal() == 0) {
            mv.addObject("operationSuccess", "暂无数据");
        }
        mv.addObject("areas", areas);
        mv.addObject("pageResult", pageInfo);
        mv.addObject("itemlableSearch", itemlableSearch);
        mv.addObject("status", status);
        mv.addObject("stuStartTime", stuStartTime);
        mv.addObject("stuEndTime", stuEndTime);
        mv.addObject("claStatus", claStatus);
        List<BaiseeClazz> list = studentService.selectClass("1");
        mv.addObject("list", list);
        mv.addObject("userType", SessionUtil.getUserInfo(request).getUserType());
        return mv;
    }

    @RequestMapping(value = "classMerger")
    @Role("BJHB")
    @ResponseBody
    public ModelAndView stuMerger(HttpServletRequest request, String ids) throws Exception {
        ModelAndView model = new ModelAndView("stuevaluate/merger_updat");
        List<BaiseeClazz> list = studentService.selectClass("1");
        model.addObject("list", list);
        model.addObject("ids", ids);
        return model;
    }

    @RequestMapping(value = "updatMerger")
    @Role("BJHB")
    @ResponseBody
    public Map<String, String> updatMerger(HttpServletRequest request, String id, String cid) {
        SessionUserInfo user = SessionUtil.getUserInfo(request);
        String U_NAME = user.getUserName();
        String[] strs = id.split(",");
            Map<String, String> map = new HashMap<>();
        int flag = studentService.updatMerger(strs, cid, U_NAME);
        if (flag>0){
            map.put("code", "000");
        }else {
            map.put("code", "111");
        }
        return map;
    }
    @RequestMapping(value = "InfoList")
    @Role("BJHB_HBJL")
    @ResponseBody
    public ModelAndView mergerList(HttpServletRequest request){
        ModelAndView mv = new ModelAndView("stuevaluate/merger_InfoList");
        String STU_NAME = request.getParameter("STU_NAME");
        String OLD_CNAME = request.getParameter("OLD_CNAME");
        String NEW_CNAME = request.getParameter("NEW_CNAME");
        String U_NAME = request.getParameter("U_NAME");
        String stuStartTime = request.getParameter("stuStartTime");
        String stuEndTime = request.getParameter("stuEndTime");
        int pageNum = ParamUtils.getPageNumParameters(request);
        int pageSize = ParamUtils.getPageSizeParameters(request);
        PageInfo<BaiseeClassMerger>pageInfo=studentService.selectMerger(STU_NAME,OLD_CNAME,NEW_CNAME,stuStartTime,stuEndTime,U_NAME,pageNum,pageSize);
        mv.addObject("STU_NAME", STU_NAME);
        mv.addObject("OLD_CNAME", OLD_CNAME);
        mv.addObject("NEW_CNAME", NEW_CNAME);
        mv.addObject("U_NAME", U_NAME);
        mv.addObject("stuStartTime", stuStartTime);
        mv.addObject("stuEndTime", stuEndTime);
        mv.addObject("pageResult", pageInfo);
        return mv;
    }
}
