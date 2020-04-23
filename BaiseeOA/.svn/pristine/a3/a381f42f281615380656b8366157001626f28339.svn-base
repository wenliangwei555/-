package cn.baisee.oa.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import cn.baisee.oa.model.repairReceive.BaiseeRepair;
import cn.baisee.oa.page.pagehelper.PageInfo;
/**
 * 物品报修 业务逻辑层
 * @author liangfeng
 */
public interface BaiseeRepairService {

	/**
	 * 根据登录者ID，查询当前登录者所创建的报修管理任务，然后跳到报修管理页面。分页查询
	 * @param pageNum	每页显示多少条
	 * @param pageSize	显示第几页
	 * @param map		查询条件
	 * @return
	 */
	public PageInfo<BaiseeRepair> getRepairList(int pageNum,int pageSize, Map<String, String> map);
	
	/**
	 * 根据登录者ID，查询当前登录者所要修复的报修任务，然后跳到报修任务列表页面。分页查询
	 * @param pageNum	每页显示多少条
	 * @param pageSize	显示第几页
	 * @param map		查询条件
	 * @return
	 */
	public PageInfo<BaiseeRepair> getRepairHandleList(int pageNum,int pageSize, Map<String, String> map);
	
	/**
	 * 根据报修管理任务的id，查询报修管理任务
	 * @param id	报修任务的id
	 * @return
	 */
	public BaiseeRepair getById(String id);
	
	
	/**
	 * 新增或修改报修管理任务
	 * @param repair	报修任务对象
	 * @param input_file	报修图片
	 * @return
	 */
	public Integer saveOrUpdateRepair(BaiseeRepair repair) throws Exception;
	
	/**
	 * 根据页面传来的报修任务的ID，更新报修任务的状态和最终处理人。
	 * @param repair	报修任务对象
	 * @return
	 */
	public Integer repairComplete(BaiseeRepair repair);
	
	/**
	 * 分配报修保存时。根据页面传来报修管理任务，更新报修管理任务的维修人。
	 * @param repair	报修任务对象
	 * @return
	 */
	public Integer distributeTask(BaiseeRepair repair);
	
	
	/**
	 * 删除报修管理任务
	 * @param ids	数组中装的是报修任务id
	 * @return
	 */
	public Integer delRepair(String [] ids);
	
}
