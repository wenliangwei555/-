package cn.baisee.oa.service;

import java.util.Map;

import cn.baisee.oa.exception.OAServiceException;
import cn.baisee.oa.model.Insurance.BaiseeInsurance;
import cn.baisee.oa.page.pagehelper.PageInfo;

public interface InsuranceInputService {

	/**
	 * 分页显示保险信息
	 * @param insurance 分页条件
	 * @param pageNum 当前页
	 * @param pageSize 每页条数
	 * @return
	 */
	PageInfo<BaiseeInsurance> selectPageInsurance(BaiseeInsurance insurance, Integer pageNum, Integer pageSize);

	/**
	 * 添加一条保险信息
	 * @param insurance
	 * @return
	 */
	String addInsurance(BaiseeInsurance insurance);
	
	/**
	 * 删除一条保险
	 * @param id
	 * @return
	 */
	Map<String, Object> delInsurance(String id) throws OAServiceException;

	/**
	 * 修改一条保险
	 * @param ins
	 * @return
	 */
	Boolean updInsurance(BaiseeInsurance ins);


}
