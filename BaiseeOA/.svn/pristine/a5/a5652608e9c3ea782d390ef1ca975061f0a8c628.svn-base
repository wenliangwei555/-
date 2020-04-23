package cn.baisee.oa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.baisee.oa.importExcel.dto.BaseDto;
import cn.baisee.oa.model.BaiseeUser;
import cn.baisee.oa.model.ReturnInfo;
import cn.baisee.oa.page.pagehelper.PageInfo;

public interface LoginService {

	public PageInfo<BaiseeUser> getList(int pageNum, int pageSize);

	public ReturnInfo login(HttpServletRequest request)  throws Exception;

	public void insert(List<BaseDto> list);
	
}
