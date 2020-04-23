package cn.baisee.oa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.baisee.oa.exception.OAServiceException;
import cn.baisee.oa.model.dorm.BaiseeDorm;
import cn.baisee.oa.model.dorm.BaiseeStudorm;
import cn.baisee.oa.model.student.BaiseeStudent;
import cn.baisee.oa.page.pagehelper.PageInfo;

public interface BaiseeDormService {
	public PageInfo<BaiseeDorm> queryDorm(HttpServletRequest request)  throws OAServiceException;
	public List<BaiseeStudorm> queryCheckList(HttpServletRequest request)  throws OAServiceException;
	public PageInfo<BaiseeStudent> queryCheckStuList(HttpServletRequest request)  throws OAServiceException;
	public BaiseeDorm selectDorm(HttpServletRequest request)  throws OAServiceException;
	Integer insertStu(String stuId,String dormid) throws Exception;
	Integer deleteStu(String stuId) throws Exception;
	Integer mergeDorm(BaiseeDorm dorm) throws Exception;
	Integer deleteDorm(String dormId) throws Exception;
}
