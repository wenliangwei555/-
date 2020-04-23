package cn.baisee.oa.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.baisee.oa.constants.AppConstants;
import cn.baisee.oa.core.file.FileDir;
import cn.baisee.oa.core.file.FileUploadUtil;
import cn.baisee.oa.dao.baisee.BaiseeDormMapper;
import cn.baisee.oa.dao.baisee.BaiseeFileMapper;
import cn.baisee.oa.exception.OAServiceException;
import cn.baisee.oa.model.SessionUserInfo;
import cn.baisee.oa.model.dorm.BaiseeDorm;
import cn.baisee.oa.model.dorm.BaiseeStudorm;
import cn.baisee.oa.model.student.BaiseeStudent;
import cn.baisee.oa.model.student.BaiseeStudentFile;
import cn.baisee.oa.page.pagehelper.PageHelper;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.BaiseeDormService;
import cn.baisee.oa.service.BaiseeFileService;
import cn.baisee.oa.utils.ParamUtils;
import cn.baisee.oa.utils.poi.WordUtils;

@Service
public class BaiseeFileServiceImpl implements BaiseeFileService {
	
	@Autowired
	private BaiseeFileMapper baiseeFileMapper;

	@Override
	public PageInfo<BaiseeStudentFile> selectFile(HttpServletRequest request) throws OAServiceException {
		Integer pageNum = ParamUtils.getPageNumParameters(request);
		Integer pageSize = ParamUtils.getPageSizeParameters(request);
		String stuId = ParamUtils.getParameter(request, "stuId");
		PageHelper.startPage(pageNum, pageSize);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("stuId", stuId);
		List<BaiseeStudentFile> list = baiseeFileMapper.selectFile(map);
		PageInfo<BaiseeStudentFile> pageInfo = new PageInfo<BaiseeStudentFile>(list);
		return pageInfo;
	}

	@Override
	public Integer insertFile(HttpServletRequest request) throws OAServiceException {
		Map<String, Object> map = new HashMap<String, Object>();
		String fileList = ParamUtils.getParameter(request, "fileList");
		String fileType =  ParamUtils.getParameter(request, "fileType");
		String sid = ParamUtils.getParameter(request, "stuId");
		map.put("fileList", fileList);
		map.put("fileType", fileType);
		map.put("stuId", sid);
		return baiseeFileMapper.insertFile(map);
	}

	@Override
	public Integer deletFile(HttpServletRequest request) throws OAServiceException {
		Map<String, Object> map = new HashMap<String, Object>();
		String id = ParamUtils.getParameter(request, "id");
		map.put("id", id);
		return baiseeFileMapper.delFile(map);
	}
	
}
