package cn.baisee.oa.controller;

import cn.baisee.oa.annotation.Role;
import cn.baisee.oa.model.*;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.BaiseeNoticeStaffService;
import cn.baisee.oa.utils.ParamUtils;
import cn.baisee.oa.utils.PropertiesUtil;
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
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("notice")
public class BaiseeNoticeStaffController {
    @Autowired
    private BaiseeNoticeStaffService baiseeNoticeStaffService;

    /*
     *
     * 获取主页面
     * */
    @RequestMapping("staffList")
    @Role("YGTZ")
    public ModelAndView staffList(HttpServletRequest request) {
        SessionUserInfo user = SessionUtil.getUserInfo(request);
        String create_id = user.getUserId();
        int pageNum = ParamUtils.getPageNumParameters(request);
        int pageSize = ParamUtils.getPageSizeParameters(request);
        String t_title = request.getParameter("t_title");
        ModelAndView mv = new ModelAndView("notice/notice_staffList");
        PageInfo<BaiseeNoticeStaff> pageInfo = baiseeNoticeStaffService.staffList(pageNum, pageSize, t_title, create_id);
        mv.addObject("t_title", t_title);
        mv.addObject("pageResult", pageInfo);
        return mv;
    }


    /*
     *
     *
     * 跳转到处理页面
     *
     * */
    @RequestMapping("viewTemplate")
    @Role("YGTZ")
    public ModelAndView viewTemplate(HttpServletRequest request, String id) {
        ModelAndView mv = new ModelAndView("notice/notice_staffList2");
        BaiseeNoticeStaff b = baiseeNoticeStaffService.viewTemplate(id);
        mv.addObject("b", b);
        BaiseeUser currUser = (BaiseeUser) SessionUtil.getUserInfo(request);
        mv.addObject("currUser", currUser);
        return mv;
    }

    /*
     *
     * 跳转到创建模板页面
     * */
    @RequestMapping("addTemplate")
    @Role("YGTZ_CJMB")
    public ModelAndView addTemplate(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("notice/notice_addTemplate");
        SessionUserInfo user = SessionUtil.getUserInfo(request);
        String create_id = user.getUserId();
        int pageNum = ParamUtils.getPageNumParameters(request);
        int pageSize = ParamUtils.getPageSizeParameters(request);
        String t_title = request.getParameter("t_title");
        PageInfo<BaiseeNoticeStaff> pageInfo = baiseeNoticeStaffService.addTemplate(pageNum, pageSize, t_title, create_id);
        mv.addObject("t_title", t_title);
        mv.addObject("pageResult", pageInfo);
        return mv;
    }


    /*
     *
     * 模板创建
     * */
    @RequestMapping("toAddTemplate")
    @Role("YGTZ_CJMB")
    public ModelAndView toAddTemplate(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("notice/notice_toAddTemplate");
        List<IcipBusDict> list = baiseeNoticeStaffService.getList();//获取所有部门
        mv.addObject("list", list);
        return mv;
    }

    /*
     *
     *
     *模糊搜索角色
     * */
    @RequestMapping("select2UserList1")
    @ResponseBody
    @Role(ignore = true)
    public List<IcipBusDict> select2UserList1(HttpServletRequest request) {
        Integer pageNum = ParamUtils.getPageNumParameters(request);
        Integer pageSize = ParamUtils.getPageSizeParameters(request);
        String id = ParamUtils.getParameter(request, "id");
        String itemlableSearch = ParamUtils.getParameter(request, "q2");
        PageInfo<IcipBusDict> pageInfo = baiseeNoticeStaffService.select2UserList1(pageNum, pageSize, itemlableSearch, id);
        List<IcipBusDict> list = pageInfo.getList();
        return list;
    }

    /*
     *
     * 模板保存操作
     * */
    @RequestMapping("addSend")
    @ResponseBody
    @Role("YGTZ_CJMB")
    public Map<String, String> addSend(HttpServletRequest request) {
        SessionUserInfo user = SessionUtil.getUserInfo(request);
        String create_id = user.getUserId();
        String userName = user.getUserName();
        Map<String, String> resultMap = new HashMap<String, String>();
        String t_title = request.getParameter("t_title");
        String t_content = request.getParameter("t_content");
        String DICT_ID = request.getParameter("DICT_ID");
        String[] DICT_ID1 = request.getParameterValues("DICT_ID1");
        String temlate_id = new PropertiesUtil("WeChat.properties").get("emp.notice.temlate_id");
        int flag = baiseeNoticeStaffService.addId(t_title, t_content, DICT_ID, DICT_ID1, userName, create_id, temlate_id);
        if (flag > 0) {
            resultMap.put("code", "SUCCESS");
            resultMap.put("msg", "保存成功");
            return resultMap;
        } else {
            resultMap.put("msg", "保存失败！可能是角色下面没有员工！");
            return resultMap;
        }
    }

    /*
     *
     * 模板删除
     * */
    @RequestMapping("deleteTemplate")
    @Role("YGTZ_CJMB")
    public ModelAndView deleteTemplate(HttpServletRequest request, String[] ids) {
        baiseeNoticeStaffService.deleteTemplate(ids);
        return addTemplate(request);
    }

    /*
     * 跳转到模板编辑
     * 根据id查询模板信息返回到页面
     * */
    @RequestMapping("selectTemplate")
    @Role("YGTZ_CJMB")
    public ModelAndView selectTemplate(HttpServletRequest request, String id) {
        ModelAndView mv = new ModelAndView("notice/selectTemplate");
        BaiseeNoticeStaff baiseeNoticeStaff = baiseeNoticeStaffService.selectTemplate(id);
        mv.addObject("b", baiseeNoticeStaff);
        List<IcipBusDict> list = baiseeNoticeStaffService.getList();
        mv.addObject("list", list);
        return mv;
    }

    /*
     *
     * 模板编辑操作
     * */
    @RequestMapping("updateSend")
    @ResponseBody
    @Role("YGTZ_CJMB")
    public Map<String, String> updateSend(HttpServletRequest request) {
        Map<String, String> resultMap = new HashMap<String, String>();
        String t_title = request.getParameter("t_title");
        String t_id = request.getParameter("t_id");
        String t_content = request.getParameter("t_content");
        String DICT_ID = request.getParameter("DICT_ID");
        String[] DICT_ID1 = request.getParameterValues("DICT_ID1");
        baiseeNoticeStaffService.deleteTemplate1(t_id);
        int flag = baiseeNoticeStaffService.addId1(t_id, t_title, t_content, DICT_ID, DICT_ID1);
        if (flag > 0) {
            resultMap.put("code", "SUCCESS");
            resultMap.put("msg", "修改成功");
            return resultMap;
        } else {
            resultMap.put("msg", "修改失败！可能是角色下面没有员工！");
            return resultMap;
        }
    }

    /*
     *
     * 模板消息发送页面
     * */
    @RequestMapping("sendList")
    @Role("YGTZ")
    public ModelAndView sendList(HttpServletRequest request) {
        SessionUserInfo user = SessionUtil.getUserInfo(request);
        ModelAndView mv = new ModelAndView("notice/sendList");
        String create_id = user.getUserId();
        int pageNum = ParamUtils.getPageNumParameters(request);
        int pageSize = ParamUtils.getPageSizeParameters(request);
        String t_title = request.getParameter("t_title");
        PageInfo<BaiseeNoticeStaff> pageInfo = baiseeNoticeStaffService.addTemplate(pageNum, pageSize, t_title, create_id);
        mv.addObject("pageResult", pageInfo.getList());
        return mv;
    }

    /*
     *
     * 模板信息发送操作
     * */
    @RequestMapping("record")
    @Role("YGTZ_FSTZ")
    @ResponseBody
    public Map<String, String> record(HttpServletRequest request, String t_id) throws Exception {
        List<BaiseeNoticeStaff> list = baiseeNoticeStaffService.getTid(t_id);
        Map<String, String> map = new HashMap<>();
        SessionUserInfo user = SessionUtil.getUserInfo(request);
        BaiseeNoticeStaff baiseeNoticeStaff = baiseeNoticeStaffService.selectTemplate(t_id);
        int flag = 0;
        for (BaiseeNoticeStaff b : list) {
            flag += baiseeNoticeStaffService.addrecord(baiseeNoticeStaff.getT_title(), user.getUserName(), new Date(), user.getUserId(), t_id, b.getUSER_ID());//存储发送信息
            String openId = baiseeNoticeStaffService.getopenId(b.getUSER_ID());//获取openid
            if (openId != null) {
                String access_token = baiseeNoticeStaffService.getAccess_token();
                TemplateUtil.sendTemplateInfo(openId, baiseeNoticeStaff, access_token);//发送模板消息到微信
            }
        }
        if (flag > 0) {
            map.put("code", "000");
        }
        return map;
    }

    /*
     *
     * 处理通知
     *
     * */
    @RequestMapping("handle1")
    @Role("YGTZ")
    @ResponseBody
    public Map<String, String> handle1(HttpServletRequest request, String noticeId) {
        Map<String, String> map = new HashMap<>();
        int flag = baiseeNoticeStaffService.handle1(noticeId);
        if (flag > 0) {
            map.put("code", "SUCCESS");
            map.put("msg", "处理成功");
        }
        return map;
    }

    /*
     *
     * 关闭通知
     *
     * */
    @RequestMapping("closeNotice1")
    @Role("YGTZ")
    @ResponseBody
    public Map<String, String> closeNotice1(HttpServletRequest request, String noticeId) {
        Map<String, String> map = new HashMap<>();
        int flag = baiseeNoticeStaffService.closeNotice1(noticeId);
        if (flag > 0) {
            map.put("code", "SUCCESS");
            map.put("msg", "处理成功");
        }
        return map;
    }
}
