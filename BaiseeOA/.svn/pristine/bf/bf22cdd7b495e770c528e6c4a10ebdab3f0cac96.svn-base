package cn.baisee.oa.service;

import java.util.List;
import java.util.Map;

import cn.baisee.oa.model.repairReceive.BaiseeRepairType;
import cn.baisee.oa.page.pagehelper.PageInfo;
/**
 * 报修字典-故障地点 业务逻辑层
 * @author liangfeng
 */
public interface BaiseeRepairTypeService {

	/**
	 * 查询报修字典-故障地点表
	 * @param pageNum	每页显示多少条
	 * @param pageSize	显示第几页
	 * @param map		查询条件
	 * @return
	 */
	public PageInfo<BaiseeRepairType> getRepairType(int pageNum,int pageSize, Map<String, String> map);
	
	/**
	 * 查询报修字典-故障地点表
	 * @return
	 */
	public List<BaiseeRepairType> getRepairTypeAll();
	
	/**
	 * 根据所属校区的id，查询该校区下的故障地点
	 * @param pId	所属校区id
	 * @return
	 */
	public List<BaiseeRepairType> getRepairTypeByPid(String pId);
	
	/**
	 * 根据校区的id集合，查询校区下是否有故障地点
	 * @param ids	校区id集合
	 * @return
	 */
	public List<BaiseeRepairType> getRepairTypeByPids(String[] ids);
	
	/**
	 * 根据报id查询报修字典-故障地点表详情
	 * @param id	任务id
	 * @return
	 */
	public BaiseeRepairType getById(String id);
	
	/**
	 * 新增或修改任务
	 * @param repairType	任务对象
	 * @return
	 */
	public Integer saveOrUpdateAssigmentManage(BaiseeRepairType repairType);
	
	
	/**
	 * 批量删除任务
	 * @param ids	数组中装的是任务id
	 * @return
	 */
	public Integer delRepairType(String [] ids);
	
	/**
	 * 查询故障地点表中是否已有此故障点名称并且故障点名称是所属同一校区的
	 * @param tName	故障点名称	
	 * @param pId	所属校区id名称	
	 * @return 
	 */
	public List<BaiseeRepairType> checkTname(String tName, String pId);
	
}
