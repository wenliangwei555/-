package cn.baisee.oa.service;

import javax.servlet.http.HttpServletRequest;

import cn.baisee.oa.exception.OAServiceException;
import cn.baisee.oa.model.student.BaiseeStudentLeave;
import cn.baisee.oa.page.pagehelper.PageInfo;

public interface BaiseeLeaveService {

    /**
     * 查询所以的请假记录
     * @param request
     * @return
     * @throws OAServiceException
     */
    PageInfo<BaiseeStudentLeave> queryLeaveStu(HttpServletRequest request) throws OAServiceException;

    Integer Merge(HttpServletRequest request) throws Exception;

    /**
     * 删除请假记录
     * @param request
     * @return
     * @throws Exception
     */
    Integer delt(HttpServletRequest request) throws Exception;
}
