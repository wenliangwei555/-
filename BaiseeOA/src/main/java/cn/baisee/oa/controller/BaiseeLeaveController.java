package cn.baisee.oa.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.baisee.oa.model.student.BaiseeStudent;
import cn.baisee.oa.annotation.Role;
import cn.baisee.oa.exception.OAServiceException;
import cn.baisee.oa.model.IcipBusDict;
import cn.baisee.oa.model.student.BaiseeStudentLeave;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.BaiseeLeaveService;
import cn.baisee.oa.service.DictionariesService;
import cn.baisee.oa.service.StudentService;
import cn.baisee.oa.utils.ParamUtils;

@Controller
@RequestMapping("/leave")
public class BaiseeLeaveController {

    @Autowired
    private BaiseeLeaveService baiseeLeaveService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private DictionariesService dictionariesService;

    /**
     * 删除
     *
     * @param request
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/deltStu")
    @Role(value = "QJGL_SP")
    public int deltStu(HttpServletRequest request) throws Exception {
        System.out.println("进入删除请假controller");
        Integer id = baiseeLeaveService.delt(request);
        System.out.println(id);
        return id;
    }


    @ResponseBody
    @RequestMapping("/saveStu")
    @Role(value = "QJGL_SP")
    public Integer saveStu(HttpServletRequest request) throws Exception {
        return baiseeLeaveService.Merge(request);
    }

    @RequestMapping("/toStuList")
    @Role(value = "QJGL_ZS")
    public ModelAndView getStuList(HttpServletRequest request) throws OAServiceException {
        ModelAndView mv = new ModelAndView("leave/stu_ForLeave");
        PageInfo<BaiseeStudentLeave> pageInfo = baiseeLeaveService.queryLeaveStu(request);

        String itemlableSearch = request.getParameter("itemlableSearch");
        String isleave = request.getParameter("isleave");
        mv.addObject("itemlableSearch", itemlableSearch);
        if (isleave == null || "".equals(isleave)) {
            isleave = "0";
        }
        mv.addObject("isleave", isleave);
        mv.addObject("pageResult", pageInfo);
        return mv;
    }

    /**
     * 进入新增 或者 修改  请假 视图
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/toLeave")
    @Role(value = "QJGL_SP")
    public ModelAndView getLevInfo(HttpServletRequest request) throws Exception {
        ModelAndView mv = new ModelAndView("leave/leave_Info");
        PageInfo<BaiseeStudentLeave> pageInfo = baiseeLeaveService.queryLeaveStu(request);
        BaiseeStudentLeave levInfo = null;
        if ("0".equals(request.getParameter("id"))) {
            levInfo = new BaiseeStudentLeave();
        } else {
            levInfo = pageInfo.getList().get(0);
        }
        List<IcipBusDict> dicts = dictionariesService.selectIcipBusDictByDictName("请假类型");
        mv.addObject("dicts", dicts);
        mv.addObject("leave", levInfo);
        return mv;
    }

    @RequestMapping("/toStu")
    @Role(value = "QJGL_SP")
    public ModelAndView getStuInfo(HttpServletRequest request) throws Exception {
        ModelAndView mv = new ModelAndView("leave/checkstuList");
        Integer pageNum = ParamUtils.getPageNumParameters(request);
        Integer pageSize = 5;
        String str = ParamUtils.getParameter(request, "itemlableSearch");
        String itemlableSearch = null;
        if (str != null && !"".equals(str)) {
            itemlableSearch = str;
        }
        PageInfo<BaiseeStudent> pageInfo = studentService.getForList(pageNum, pageSize, itemlableSearch, request);
        if (pageInfo.getTotal() == 0) {
            mv.addObject("operationSuccess", "暂无数据");
        }
        mv.addObject("pageResult", pageInfo);
        mv.addObject("itemlableSearch", itemlableSearch);
        return mv;
    }

    @RequestMapping("toleaveinfo")
    @Role(value = "QJGL_SP")
    public ModelAndView toleaveinfo(String sid, String cname, String sname, HttpServletRequest request) throws Exception {
        ModelAndView view = getLevInfo(request);
        view.addObject("sid", sid);
        view.addObject("sname", sname);
        view.addObject("cname", cname);
        return view;
    }

}
