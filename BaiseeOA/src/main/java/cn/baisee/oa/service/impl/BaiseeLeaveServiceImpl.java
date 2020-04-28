package cn.baisee.oa.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.baisee.oa.constants.AppConstants;
import cn.baisee.oa.dao.baisee.BaiseeDormMapper;
import cn.baisee.oa.dao.baisee.BaiseeLeaveMapper;
import cn.baisee.oa.exception.OAServiceException;
import cn.baisee.oa.model.SessionUserInfo;
import cn.baisee.oa.model.dorm.BaiseeDorm;
import cn.baisee.oa.model.dorm.BaiseeStudorm;
import cn.baisee.oa.model.student.BaiseeStudent;
import cn.baisee.oa.model.student.BaiseeStudentLeave;
import cn.baisee.oa.page.pagehelper.PageHelper;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.BaiseeDormService;
import cn.baisee.oa.service.BaiseeLeaveService;
import cn.baisee.oa.utils.ParamUtils;

@Service
public class BaiseeLeaveServiceImpl implements BaiseeLeaveService {

    @Autowired
    private BaiseeLeaveMapper baiseeLeaveMapper;

    @Override
    public PageInfo<BaiseeStudentLeave> queryLeaveStu(HttpServletRequest request) throws OAServiceException {
        Map<String, Object> map = new HashMap<String, Object>();
        String itemlableSearch = request.getParameter("itemlableSearch");
        String id = request.getParameter("id");
        String isleave = request.getParameter("isleave");
		/*String claStatus= request.getParameter("claStatus");
		String floor= request.getParameter("floor");
		String dormStatus= request.getParameter("dormStatus");
		String choiceStuState= request.getParameter("choiceStuState");
		SessionUserInfo user = (SessionUserInfo)request.getSession().getAttribute(AppConstants.SESSION_USER_INFO);*/
        Integer pageNum = ParamUtils.getPageNumParameters(request);
        Integer pageSize = ParamUtils.getPageSizeParameters(request);
        PageHelper.startPage(pageNum, pageSize);
        map.put("itemlableSearch", itemlableSearch);
        map.put("id", id);
        if (isleave == null || "".equals(isleave)) {
            isleave = "0";
        }
        map.put("isleave", isleave);
        List<BaiseeStudentLeave> list = baiseeLeaveMapper.selectLeaveStu(map);
        PageInfo<BaiseeStudentLeave> page = new PageInfo<BaiseeStudentLeave>(list);
        return page;
    }

    @Override
    public Integer Merge(HttpServletRequest request) throws Exception {
        String sid = request.getParameter("sid");
        String id = request.getParameter("id");
        String rtype = request.getParameter("rtype");
        String reason = request.getParameter("reason");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        Map<String, Object> map = new HashMap<String, Object>();
        SessionUserInfo user = (SessionUserInfo) request.getSession().getAttribute(AppConstants.SESSION_USER_INFO);
        map.put("userId", user.getUserId());
        map.put("id", id);
        map.put("sid", sid);
        map.put("rtype", rtype);
        map.put("reason", reason);
        map.put("startDate", startDate);
        map.put("endDate", endDate);

        Integer ref = 0;
        if (id == null || "".equals(id)) {
            ref = baiseeLeaveMapper.insertLeaveStu(map);
        } else {
            ref = baiseeLeaveMapper.updateleaveStu(map);
        }
        return ref;
    }

    /**
     * 删除请假记录
     *
     * @param request
     * @return
     * @throws Exception
     */
    @Override
    public Integer delt(HttpServletRequest request) throws Exception {
        System.out.println("进入删除请假servace");
        String id = request.getParameter("id");
        if (null == id || "".equals(id)) {
            System.out.println("参数null");
            return 0;
        }
        return baiseeLeaveMapper.delleaveStu(id);
    }
}
