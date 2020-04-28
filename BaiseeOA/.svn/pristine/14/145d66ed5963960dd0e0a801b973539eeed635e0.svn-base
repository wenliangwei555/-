package cn.baisee.oa.controller;

import cn.baisee.oa.annotation.Role;
import cn.baisee.oa.model.BaiseeLeaveRecord;
import cn.baisee.oa.model.SessionUserInfo;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.BaiseeLeaveRecordService;
import cn.baisee.oa.utils.ParamUtils;
import cn.baisee.oa.utils.SessionUtil;
import cn.baisee.oa.utils.TemplateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 试听学生    学生催费信息
 */
@Controller
@RequestMapping("/leave")
public class BaiseeLeaveRecordController {
    @Autowired
    private BaiseeLeaveRecordService baiseeLeaveRecordService;

    /*
     * 展示页面
     * 学生请假审批
     * */
    @RequestMapping("/leaveList")
    @Role(value = "QJJL_XS")
    public ModelAndView getStuList(HttpServletRequest request) {
        SessionUserInfo user = SessionUtil.getUserInfo(request);
        String check_user = user.getUserId();
        String student_name = request.getParameter("student_name");//获取学生名称
        String c_name = request.getParameter("c_name");//获取班级名称
        String type = request.getParameter("type");//获取请假类型
        String status = request.getParameter("status");//获取状态
        String check_user_name = request.getParameter("check_user_name");//获取审核人名称
        String start_time = request.getParameter("start_time");//获取开始时间
        String end_time = request.getParameter("end_time");//获取结束时间
        ModelAndView mv = new ModelAndView("leave/leave_ListLeave");
        //分页查询
        int pageNum = ParamUtils.getPageNumParameters(request);
        int pageSize = ParamUtils.getPageSizeParameters(request);
        PageInfo<BaiseeLeaveRecord> claInfo = baiseeLeaveRecordService.getDateQueryTotalBill(pageNum, pageSize, check_user, student_name, c_name, type, check_user_name, start_time, end_time, status);
        mv.addObject("student_name", student_name);
        mv.addObject("c_name", c_name);
        mv.addObject("start_time", start_time);
        mv.addObject("check_user_name", check_user_name);
        mv.addObject("end_time", end_time);
        if (type == null || "".equals(type)) {
            type = "0";
        }
        if (status == null || "".equals(status)) {
            status = "0";
        }
        mv.addObject("status", status);
        mv.addObject("type", type);
        mv.addObject("pageResult", claInfo);
        return mv;
    }

    /*
     * 跳转审批页面
     * */
    @RequestMapping("/leave")
    @Role(value = "QJJL_SP")
    @ResponseBody
    public BaiseeLeaveRecord leaveList(Integer id) {
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");
        ModelAndView mav = new ModelAndView();
        mav.setViewName("leave/leave_ListInfo");
        BaiseeLeaveRecord baiseeLeaveRecord = baiseeLeaveRecordService.getId(id);
        mav.addObject("baiseeLeaveRecord", baiseeLeaveRecord);
        return baiseeLeaveRecord;
    }


    /*
     * 跳转审批页面
     * */
//    @ResponseBody
//    @RequestMapping("/findReceive")
//    @Role(value = "QJJL_SP")
//    public Object findReceive(HttpServletRequest request, Integer id) throws ParseException {
//
//        SessionUserInfo user = SessionUtil.getUserInfo(request);
//        String check_user = user.getUserId();
//        int a = baiseeLeaveRecordService.findReceive(id, check_user);
//        Map<String, String> map = new HashMap<>();
//        if (a > 0) {
//            map.put("code", "000");
//            return map;
//        } else {
//            map.put("code", "111");
//            return map;
//        }
//    }


    /*
     * 审批拒绝
     * */
    @ResponseBody
    @RequestMapping("/cancelReceive")
    @Role(value = "QJJL_SP")
    public Object toreceive(HttpServletRequest request, Integer id, String reject_reason) throws Exception {
        SessionUserInfo user = SessionUtil.getUserInfo(request);
        try {
            String status = "3";
            String check_user = user.getUserId();
            Date check_time = new Date();
            String check_user_name = user.getUserName();
            baiseeLeaveRecordService.toreceive(check_time, check_user_name, check_user, status, reject_reason, id);
            Map<String, String> map = new HashMap<>();
            BaiseeLeaveRecord baiseeLeaveRecord = baiseeLeaveRecordService.getId(id);
            String openId = baiseeLeaveRecordService.getOpenId(baiseeLeaveRecord.getStudent_no());
            String access_token = baiseeLeaveRecordService.getBaiseeAToken();
            TemplateUtil.sendLeaveTemplateInfo(openId,baiseeLeaveRecord,access_token);
            map.put("code", "000");
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, String> map = new HashMap<>();
            map.put("code", "111");
            return map;
        }
    }


    /*
     * 审批通过
     * */
    @ResponseBody
    @RequestMapping("/completeReceive")
    @Role(value = "QJJL_SP")
    public Object repairComplete(HttpServletRequest request, Integer id) throws Exception {
        //获取当前用户名称
        SessionUserInfo user = SessionUtil.getUserInfo(request);
        try {
            String status = "2";
            String check_user = user.getUserId();
            Date check_time = new Date();
            String reject_reason = "";
            String check_user_name = user.getUserName();
            baiseeLeaveRecordService.toreceive(check_time, check_user_name, check_user, status, reject_reason, id);
            Map<String, String> map = new HashMap<>();
            map.put("code", "000");
            BaiseeLeaveRecord baiseeLeaveRecord = baiseeLeaveRecordService.getId(id);
            String openId = baiseeLeaveRecordService.getOpenId(baiseeLeaveRecord.getStudent_no());
            String access_token = baiseeLeaveRecordService.getBaiseeAToken();
            TemplateUtil.sendLeaveTemplateInfo(openId,baiseeLeaveRecord,access_token);
            return map;
        } catch (Exception e) {
            Map<String, String> map = new HashMap<>();
            map.put("code", "111");
            return map;
        }
    }


    /*
     *
     * 统计请假记录
     *
     * */
    @RequestMapping("/sumLeave")
    @Role(value = "QJJL_JL")
    public ModelAndView sumLeave(HttpServletRequest request) {
        String student_name = request.getParameter("student_name");
        String c_name = request.getParameter("c_name");
        String start_time = request.getParameter("start_time");
        int pageNum = ParamUtils.getPageNumParameters(request);
        int pageSize = ParamUtils.getPageSizeParameters(request);
        ModelAndView mv = new ModelAndView("leave/leave_sumLeave");
        PageInfo<BaiseeLeaveRecord> claInfo = baiseeLeaveRecordService.getSumLeave(pageNum, pageSize, start_time, student_name, c_name);
        mv.addObject("student_name", student_name);
        mv.addObject("c_name", c_name);
        mv.addObject("start_time", start_time);
        mv.addObject("pageResult", claInfo);
        return mv;
    }

    /*
    * 拒绝原因/请假原因查询
    * */
    @ResponseBody
    @RequestMapping("/edit")
    @Role(value = "QJJL_XS")
    public Object edit(String cause){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("leave/leave_edit");
        mav.addObject("cause", cause);
        return mav;
    }
}