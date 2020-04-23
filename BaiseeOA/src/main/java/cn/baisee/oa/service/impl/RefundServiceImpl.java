package cn.baisee.oa.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import cn.baisee.oa.dao.baisee.BaiseeRefundMapper;
import cn.baisee.oa.model.student.BaiseeStudentRefund;
import cn.baisee.oa.page.pagehelper.PageHelper;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.RefundService;
import cn.baisee.oa.utils.ParamUtils;

/**
 * 退费规则管理的Service实现
 *
 * @author WANGBAOXIANG
 */
@Service
public class RefundServiceImpl implements RefundService {
    @Autowired
    private BaiseeRefundMapper refundMapper;

    /**
     * @param request
     * @return
     */
    @Override
    public ModelAndView getRefundInfo(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("refund/ref_list");
        int pageNum = ParamUtils.getPageNumParameters(request);
        int pageSize = ParamUtils.getPageSizeParameters(request);
        PageHelper.startPage(pageNum, pageSize);
        List<BaiseeStudentRefund> list = refundMapper.getRefundList();
        PageInfo<BaiseeStudentRefund> pageInfo = new PageInfo<>(list);
        model.addObject("pageResult", pageInfo);
        return model;
    }

    @Override
    public Map<String, Object> delRefund(HttpServletRequest request) {
        String rId = ParamUtils.getParameter(request, "rId");
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("rId", rId);

        List<BaiseeStudentRefund> refund = refundMapper.getRefundById(paramMap);
        Map<String, Object> map = new HashMap<String, Object>();
        if (refund == null && refund.size() == 0) {
            map.put("flag", "notDel");
        } else {
            int r = refundMapper.delRefund(paramMap);
            if (r > 0) {
                refundMapper.delTiitionRefundByRid(paramMap);
                map.put("flag", "success");
            } else {
                map.put("flag", "error");
            }
        }
        return map;
    }

    @Override
    public ModelAndView toRefundInfo(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("refund/ref_info");
        String rId = ParamUtils.getParameter(request, "rId");
        if (rId != null && !"".equals(rId)) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("rId", rId);
            List<BaiseeStudentRefund> refund = refundMapper.getRefundById(map);
            model.addObject("refund", refund.get(0));
        } else {
            model.addObject("refund", new BaiseeStudentRefund());
        }
        return model;
    }

    /**
     * @param refund
     * @return
     */
    @Override
    public ModelAndView saveOrUpdate(HttpServletRequest request,BaiseeStudentRefund refund) {
        ModelAndView model = new ModelAndView("refund/ref_list");
        model.addObject("refund", refund);
        if (refund.getrId() != null && !"".equals(refund.getrId())) {
            int r = refundMapper.updateRefund(refund);
            if (r > 0) {
                model.addObject("prompt", "修改成功！");
            } else {
                model.addObject("prompt", "修改出错了！");
            }
        } else {
            int r = refundMapper.insertRefund(refund);
            if (r > 0) {
                model.addObject("prompt", "添加成功！");
            } else {
                model.addObject("prompt", "添加出错了！");
            }
        }
        return getRefundInfo(request);
    }


}
