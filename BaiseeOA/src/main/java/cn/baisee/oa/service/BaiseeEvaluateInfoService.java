package cn.baisee.oa.service;



import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.baisee.oa.model.BaiseeEvaluateInfo;
import cn.baisee.oa.model.StuAndEva;
import cn.baisee.oa.model.student.BaiseeStudent;
import cn.baisee.oa.page.pagehelper.PageInfo;

public interface BaiseeEvaluateInfoService {
	/**
	 * 分页/列表
	 * @param pageNum
	 * @param pageSize
	 * @param itemlableSearch
	 * @return
	 */
	public PageInfo<BaiseeEvaluateInfo> selectAll(int pageNum,int pageSize,String itemlableSearch,String clazz);
	/**
	 * 添加数据
	 * @param insert
	 */
	public void insertDate(BaiseeEvaluateInfo insert);
	/**
	 * 根据id查询一条数据
	 * @param name
	 * @return
	 */
	public BaiseeEvaluateInfo selectById(int evid);
	/**
	 * 修改数据
	 * @param info
	 */
	public void updateEVinfo(BaiseeEvaluateInfo info);
	/**
	 * 批量删除
	 * @param evid
	 */
	public void deleteEva(int[] evid);
	/**
	 * 所有班级
	 * @return
	 */
	public BaiseeEvaluateInfo Cname();
	/**
	 * 获取到班级名称
	 * @param sid
	 * @return
	 */
	public String getCname(String sid);
}
