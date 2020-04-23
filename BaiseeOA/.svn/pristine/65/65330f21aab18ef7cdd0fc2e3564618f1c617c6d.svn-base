package cn.baisee.oa.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.baisee.oa.model.repairReceive.BaiseeReceive;
import cn.baisee.oa.page.pagehelper.PageInfo;
/**
 * 物品申领业务逻辑层
 * @author liangfeng
 */
public interface BaiseeReceiveService {

	/**
	 * 根据当前登录者，查询该登录者所有的申领管理任务。然后跳转到审理管理列表页面。分页查询
	 * @param pageNum	每页显示条数
	 * @param pageSize	要显示的页数
	 * @param map		查询条件
	 * @return
	 */
	public PageInfo<BaiseeReceive> getReceiveList(int pageNum,int pageSize, Map<String, String> map);
	

	/**
	 * 根据Id查询物品申领详情
	 * @param id	物品申领任务id
	 * @return
	 */
	public BaiseeReceive getById(String id);
	
	
	/**
	 * 新增或修改申领管理任务
	 * @param repair	物品申领对象
	 * @return
	 */
	public Integer saveOrUpdateReceive(BaiseeReceive receive);
	
	/**
	 * 完成申领任务,根据申领任务的id，更新任务状态
	 * @param receive	物品申领对象
	 * @return
	 */
	public Integer completeReceive(BaiseeReceive receive);
	
	/**
	 * 取消申领任务，根据申领任务的id，更新申领任务的状态
	 * @param receive	物品申领对象
	 * @return
	 */
	public Integer cancelReceive(BaiseeReceive receive);
	
	/**
	 * 根据申领任务的id，批量删除申领任务
	 * @param ids	数组中是物品申领对象的id集合
	 * @return
	 */
	public Integer delRepair(String [] ids);
	
	/**
	 * 查询申领开始时间到结束时间这个时间段的任务
	 * @param startTime	开始时间
	 * @param endTime	结束时间
	 * @return
	 */
	public List<BaiseeReceive> getStartTimeAndEndTime(String startTime, String endTime);
	
}
