package cn.baisee.oa.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import cn.baisee.oa.model.SessionUserInfo;
import cn.baisee.oa.utils.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import cn.baisee.oa.annotation.Role;
import cn.baisee.oa.model.repairReceive.BaiseeRepairItem;
import cn.baisee.oa.model.repairReceive.BaiseeRepairPlace;
import cn.baisee.oa.model.repairReceive.BaiseeRepairType;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.BaiseeRepairItemService;
import cn.baisee.oa.service.BaiseeRepairPlaceService;
import cn.baisee.oa.service.BaiseeRepairTypeService;
import cn.baisee.oa.utils.ParamUtils;
/**
 * 报修字典Controller
 * @author liangfeng
 *
 */
@Controller
@RequestMapping("/repair/dictionaries")
public class RepairDictionariesController {
	
	@Autowired
	private BaiseeRepairPlaceService repairPlaceService;
	@Autowired
	private BaiseeRepairTypeService repairTypeService;
	@Autowired
	private BaiseeRepairItemService repairItemService;
	
	
	
	/**=============== 报修字典-校区 ===================*/
	/**
	 * 查询校区列表
	 * @param request
	 * @param itemlableSearch	查询条件	校区名称
	 * @return
	 */
	@RequestMapping("/placeList")
	@Role(value="XQGL")
	public ModelAndView toPlaceList(HttpServletRequest request, String itemlableSearch) {
		Integer pageNum=ParamUtils.getPageNumParameters(request);
		Integer pageSize=ParamUtils.getPageSizeParameters(request);
		//添加查询条件
		Map<String, String> map = new HashMap<>();
		//添加搜索查询条件
		map.put("itemlableSearch",itemlableSearch == null?"":itemlableSearch);
		//执行查询
		PageInfo<BaiseeRepairPlace> pageInfo=repairPlaceService.getRepairPlace(pageNum, pageSize, map);
		ModelAndView mv=new ModelAndView("repairReceive/place_list");
		mv.addObject("pageResult", pageInfo);
		mv.addObject("itemlableSearch", itemlableSearch);
		return mv;
	}
	
	/**
	 * 去报修字典-校区表添加页面
	 * @return
	 */
	@RequestMapping("/toAddPlace")
	@Role(value="XQGL_XZXQ")
	public ModelAndView toAddPlace(HttpServletRequest request){
		ModelAndView mav=new ModelAndView("repairReceive/place_add");
		return mav;
	}
	
	/**
	 * 执行报修字典-校区表添加
	 * @param request
	 * @param repairPlace	对象
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addPlace")
	@Role(value="XQGL_XZXQ")
	public ModelAndView addPlace(HttpServletRequest request, BaiseeRepairPlace repairPlace) throws Exception{
		//获取当前用户名称
		SessionUserInfo user = SessionUtil.getUserInfo(request);
		repairPlace.setcId(user.getUserId());//创建人id
		repairPlace.setcName(user.getUserName());//创建人名称
		repairPlace.setcTime(new Date());
		//执行添加
		repairPlaceService.saveOrUpdateAssigmentManage(repairPlace);
		return toPlaceList(request, null);
	}
	
	/**
	 * 根据id查询报修字典-校区信息
	 * @param id	任务id
	 * @return
	 */
	@RequestMapping("/selectPlaceId")
	@Role(value="XQGL_CXXQ")
	public ModelAndView selectByPlaceId(String id) {
		BaiseeRepairPlace repairPlace=repairPlaceService.getById(id);
		ModelAndView mav=new ModelAndView("repairReceive/place_update");
		mav.addObject("repairPlace", repairPlace);
		return mav;
	}
	
	/**
	 * 执行任务修改
	 * @param request
	 * @param receive	对象
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updatePlace")
	@Role(value="XQGL_XGXQ")
	public ModelAndView updatePlace(HttpServletRequest request, BaiseeRepairPlace repairPlace) throws Exception{
		//获取当前用户名称
		SessionUserInfo user = SessionUtil.getUserInfo(request);
		repairPlace.setuId(user.getUserId());//设置更新人id
		repairPlace.setuName(user.getUserName());//设置更新人名称
		repairPlace.setuTime(new Date());//设置更新时间
		repairPlaceService.saveOrUpdateAssigmentManage(repairPlace);
		return toPlaceList(request, null);
	}
	
	
	/**
	 * 根据任务id，批量删除任务
	 * @param request
	 * @param ids	数组中装的是页面列表选中的任务id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deletePlace")
	@Role(value="XQGL_SCXQ")
	public ModelAndView delPlace(HttpServletRequest request, String[] ids) throws Exception{
		repairPlaceService.delRepairPlace(ids);
		return toPlaceList(request, null);
	}
	
	/**
	 * 检查校区表中是否已存在此校区名称
	 * @param pName	校区名称
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/checkPname")
	@Role(value="XQGL_CXXQ")
	public Object checkPname(String pName){
		//查看分配表中是否有此分配人
		List<BaiseeRepairPlace> repairPlaces = repairPlaceService.checkAssignmentPerson(pName);
		Map<String, String> map = new HashMap<>();
		//判断repairPlaces中是否有数据,如果为000说明没有此地理位置名称，反之111说明有此地理位置名称
		if (repairPlaces != null && repairPlaces.size() > 0) {
			map.put("codes", "111");
		}else {
			map.put("codes", "000");
		}
		return map;		
	}	
	
	
	
	
	/**=============== 报修字典-故障地点 ===================*/
	/**
	 * 查询报修字典-故障地点列表
	 * @param request
	 * @param itemlableSearch	查询条件	故障点名称
	 * @return
	 */
	@RequestMapping("/typeList")
	@Role(value="DDGL")
	public ModelAndView toTypeList(HttpServletRequest request, String itemlableSearch) {
		Integer pageNum=ParamUtils.getPageNumParameters(request);
		Integer pageSize=ParamUtils.getPageSizeParameters(request);
		//添加查询条件
		Map<String, String> map = new HashMap<>();
		//添加搜索查询条件
		map.put("itemlableSearch",itemlableSearch == null?"":itemlableSearch);
		//执行查询
		PageInfo<BaiseeRepairType> pageInfo=repairTypeService.getRepairType(pageNum, pageSize, map);
		ModelAndView mv=new ModelAndView("repairReceive/type_list");
		mv.addObject("pageResult", pageInfo);
		mv.addObject("itemlableSearch", itemlableSearch);
		return mv;
	}
	
	/**
	 * 去报修字典-故障地点表添加页面
	 * @return
	 */
	@RequestMapping("/toAddType")
	@Role(value="DDGL_XZDD")
	public ModelAndView toAddType(HttpServletRequest request){
		//查询地理位置表
		List<BaiseeRepairPlace> repairPlaces = repairPlaceService.getRepairPlaceAll();
		ModelAndView mav=new ModelAndView("repairReceive/type_add");
		mav.addObject("repairPlaces", repairPlaces);
		return mav;
	}
	
	/**
	 * 执行报修字典-报修类型表添加
	 * @param request
	 * @param repairType	对象
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addType")
	@Role(value="DDGL_XZDD")
	public ModelAndView addType(HttpServletRequest request, BaiseeRepairType repairType) throws Exception{
		//获取当前用户名称
		SessionUserInfo user = SessionUtil.getUserInfo(request);
		repairType.setcId(user.getUserId());//创建人id
		repairType.setcName(user.getUserName());//创建人名称
		repairType.setcTime(new Date());
		//执行添加
		repairTypeService.saveOrUpdateAssigmentManage(repairType);
		return toTypeList(request, null);
	}
	
	/**
	 * 根据id查询报修字典-报修类型信息
	 * @param id	任务id
	 * @return
	 */
	@RequestMapping("/selectTypeId")
	@Role(value="DDGL_CXDD")
	public ModelAndView selectByTypeId(String id) {
		//查询地理位置表
		List<BaiseeRepairPlace> repairPlaces = repairPlaceService.getRepairPlaceAll();
		BaiseeRepairType repairType=repairTypeService.getById(id);
		ModelAndView mav=new ModelAndView("repairReceive/type_update");
		mav.addObject("repairPlaces", repairPlaces);
		mav.addObject("repairType", repairType);
		return mav;
	}
	
	/**
	 * 执行任务修改
	 * @param request
	 * @param receive	对象
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateType")
	@Role(value="DDGL_XGDD")
	public ModelAndView updateType(HttpServletRequest request, BaiseeRepairType repairType) throws Exception{
		//获取当前用户名称
		SessionUserInfo user = SessionUtil.getUserInfo(request);
		repairType.setuId(user.getUserId());//设置更新人id
		repairType.setuName(user.getUserName());//设置更新人名称
		repairType.setuTime(new Date());//设置更新时间
		repairTypeService.saveOrUpdateAssigmentManage(repairType);
		return toTypeList(request, null);
	}
	
	
	/**
	 * 根据任务id，批量删除任务
	 * @param request
	 * @param ids	数组中装的是页面列表选中的任务id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteType")
	@Role(value="DDGL_SCDD")
	public ModelAndView delType(HttpServletRequest request, String[] ids) throws Exception{
		repairTypeService.delRepairType(ids);
		return toTypeList(request, null);
	}
	
	/**
	 * 校验故障表中是否存在同属于一个校区并且名称一样的故障点
	 * @param tName	类型名称
	 * @param pId	所属校区id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/checkTname")
	@Role(value="DDGL_CXDD")
	public Object checkTname(String tName, String pId){
		//查看故障表中是否存在同属于一个校区并且名称一样的故障点
		List<BaiseeRepairType> repairTypes = repairTypeService.checkTname(tName, pId);
		Map<String, String> map = new HashMap<>();
		//判断repairTypes中是否有数据,如果为000说明没有此故障点名称，反之111说明有此故障点名称
		if (repairTypes != null && repairTypes.size() > 0) {
			map.put("codes", "111");
		}else {
			map.put("codes", "000");
		}
		return map;		
	}	
	
	/**
	 * 根据pid查询该校区下的所有故障地点
	 * @param pId	所属校区id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getRepairTypeByPid")
	@Role(value="DDGL_CXDD")
	public Object getRepairTypeByPid(String pId){
		//查看所属校区下的故障点
		List<BaiseeRepairType> repairTypes = repairTypeService.getRepairTypeByPid(pId);
		Map<String, Object> map = new HashMap<>();
		map.put("repairTypes", repairTypes);
		return map;		
	}
	
	/**
	 * 根据任务id，校验这些任务下是否有子节点
	 * @param request
	 * @param ids	数组中装的是页面列表选中的任务id
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/checkTypeSonNode")
	@Role(value="DDGL_CXDD")
	public Object checkTypeSonNode(String ids) throws Exception{
		Map<String, String> map = new HashMap<>();
		//查询这些任务下是否有子节点
		List<BaiseeRepairType> repairTypes = repairTypeService.getRepairTypeByPids(ids.split(","));
		if (repairTypes != null && repairTypes.size() > 0) {
			//说明有子节点不可删除
			map.put("codes", "111");
			return map;
		}
		//可删除
		map.put("codes", "000");
		return map;
	}
	
	
	
	
	/**=============== 报修字典-故障类型 ===================*/
	/**
	 * 查询报修字典-故障类型列表
	 * @param request
	 * @param itemlableSearch	查询条件	故障类型名称
	 * @return
	 */
	@RequestMapping("/itemList")
	@Role(value="LXGL")
	public ModelAndView toItemList(HttpServletRequest request, String itemlableSearch) {
		Integer pageNum=ParamUtils.getPageNumParameters(request);
		Integer pageSize=ParamUtils.getPageSizeParameters(request);
		//添加查询条件
		Map<String, String> map = new HashMap<>();
		//添加搜索查询条件
		map.put("itemlableSearch",itemlableSearch == null?"":itemlableSearch);
		//执行查询
		PageInfo<BaiseeRepairItem> pageInfo=repairItemService.getRepairItem(pageNum, pageSize, map);
		ModelAndView mv=new ModelAndView("repairReceive/item_list");
		mv.addObject("pageResult", pageInfo);
		mv.addObject("itemlableSearch", itemlableSearch);
		return mv;
	}
	
	/**
	 * 去报修字典-故障类型表添加页面
	 * @return
	 */
	@RequestMapping("/toAddItem")
	@Role(value="LXGL_XZLX")
	public ModelAndView toAddItem(HttpServletRequest request){
		//查询所有校区
		List<BaiseeRepairPlace> repairPlaces = repairPlaceService.getRepairPlaceAll();
		List<BaiseeRepairType> repairTypes = null;
		if (repairPlaces != null && repairPlaces.size() > 0) {
			//选择第一个校区默认查询该校区下的故障点
			repairTypes = repairTypeService.getRepairTypeByPid(repairPlaces.get(0).getId());
		}
		ModelAndView mav=new ModelAndView("repairReceive/item_add");
		mav.addObject("repairPlaces", repairPlaces);
		mav.addObject("repairTypes", repairTypes);
		return mav;
	}
	
	/**
	 * 执行报修字典-报修类型表添加
	 * @param request
	 * @param repairItem	对象
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addItem")
	@Role(value="LXGL_XZLX")
	public ModelAndView addItem(HttpServletRequest request, BaiseeRepairItem repairItem) throws Exception{
		//获取当前用户名称
		SessionUserInfo user = SessionUtil.getUserInfo(request);
		repairItem.setcId(user.getUserId());//创建人id
		repairItem.setcName(user.getUserName());//创建人名称
		repairItem.setcTime(new Date());
		//执行添加
		repairItemService.saveOrUpdateAssigmentManage(repairItem);
		return toItemList(request, null);
	}
	
	/**
	 * 根据id查询报修字典-报修类型信息
	 * @param id	任务id
	 * @return
	 */
	@RequestMapping("/selectItemId")
	@Role(value="LXGL_CXLX")
	public ModelAndView selectByItemId(String id) {
		BaiseeRepairItem repairItem=repairItemService.getById(id);
		//查询校区列表
		List<BaiseeRepairPlace> repairPlaces = repairPlaceService.getRepairPlaceAll();
		//查询所属校区下的故障地点
		List<BaiseeRepairType> repairTypes = repairTypeService.getRepairTypeByPid(repairItem.getpId());
		ModelAndView mav=new ModelAndView("repairReceive/item_update");
		mav.addObject("repairPlaces", repairPlaces);
		mav.addObject("repairItem", repairItem);
		mav.addObject("repairTypes", repairTypes);
		return mav;
	}
	
	/**
	 * 执行任务修改
	 * @param request
	 * @param receive	对象
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateItem")
	@Role(value="LXGL_XGLX")
	public ModelAndView updateItem(HttpServletRequest request, BaiseeRepairItem repairItem) throws Exception{
		//获取当前用户名称
		SessionUserInfo user = SessionUtil.getUserInfo(request);
		repairItem.setuId(user.getUserId());//设置更新人id
		repairItem.setuName(user.getUserName());//设置更新人名称
		repairItem.setuTime(new Date());//设置更新时间
		repairItemService.saveOrUpdateAssigmentManage(repairItem);
		return toItemList(request, null);
	}
	
	
	/**
	 * 根据任务id，批量删除任务
	 * @param request
	 * @param ids	数组中装的是页面列表选中的任务id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteItem")
	@Role(value="LXGL_SCLX")
	public ModelAndView delItem(HttpServletRequest request, String[] ids) throws Exception{
		repairItemService.delRepairItem(ids);
		return toItemList(request, null);
	}
	
	/**
	 * 校验故障表中是否存在同属于一个校区并且名称一样的故障点
	 * @param iName	类型名称
	 * @param tId	故障点id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/checkIname")
	@Role(value="LXGL_CXLX")
	public Object checkIname(String iName, String tId){
		//查看故障表中是否存在同属于一个校区并且名称一样的故障点
		List<BaiseeRepairItem> repairItems = repairItemService.checkIname(iName, tId);
		Map<String, String> map = new HashMap<>();
		//判断repairItems中是否有数据,如果为000说明没有此故障点名称，反之111说明有此故障点名称
		if (repairItems != null && repairItems.size() > 0) {
			map.put("codes", "111");
		}else {
			map.put("codes", "000");
		}
		return map;		
	}	
	
	/**
	 * 根据tid查询该故障地点下的所有故障类型
	 * @param tId	故障地点id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getRepairItemByTid")
	@Role(value="LXGL_CXLX")
	public Object getRepairItemByTid(String tId){
		//查看所属校区下的故障点
		List<BaiseeRepairItem> repairItems = repairItemService.getRepairItemByTid(tId);
		Map<String, Object> map = new HashMap<>();
		map.put("repairItems", repairItems);
		return map;		
	}
	
	/**
	 * 根据任务id，校验这些任务下是否有子节点
	 * @param request
	 * @param ids	数组中装的是页面列表选中的任务id
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/checkItemSonNode")
	@Role(value="LXGL_CXLX")
	public Object checkItemSonNode(String ids) throws Exception{
		Map<String, String> map = new HashMap<>();
		//查询这些任务下是否有子节点
		List<BaiseeRepairItem> repairItems = repairItemService.getRepairItemByTids(ids.split(","));
		if (repairItems != null && repairItems.size() > 0) {
			//说明有子节点不可删除
			map.put("codes", "111");
			return map;
		}
		//可删除
		map.put("codes", "000");
		return map;
	}
}
