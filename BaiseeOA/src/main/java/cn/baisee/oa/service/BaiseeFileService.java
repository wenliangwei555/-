package cn.baisee.oa.service;


import javax.servlet.http.HttpServletRequest;

import cn.baisee.oa.exception.OAServiceException;
import cn.baisee.oa.model.student.BaiseeStudentFile;
import cn.baisee.oa.page.pagehelper.PageInfo;

public interface BaiseeFileService {
	public PageInfo<BaiseeStudentFile> selectFile(HttpServletRequest request)  throws OAServiceException;
	public Integer insertFile(HttpServletRequest request)  throws OAServiceException;
	public Integer deletFile(HttpServletRequest request)  throws OAServiceException;
}
