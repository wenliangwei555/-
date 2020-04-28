package cn.baisee.oa.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.baisee.oa.model.SessionUserInfo;
import cn.baisee.oa.utils.SessionUtil;
import cn.baisee.oa.utils.StringUtil;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.baisee.oa.ExcelUtils.ExcelBeanUtil;
import cn.baisee.oa.ExcelUtils.ExcelUtil;
import cn.baisee.oa.ExcelUtils.WebUtil;
import cn.baisee.oa.annotation.Role;
import cn.baisee.oa.model.goods.BaiseeGoodsRepertory;
import cn.baisee.oa.model.goods.BaiseeGoodsType;
import cn.baisee.oa.model.repairReceive.BaiseeReceive;
import cn.baisee.oa.page.pagehelper.PageHelper;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.BaiseeGoodsService;
import cn.baisee.oa.service.BaiseeReceiveService;
import cn.baisee.oa.service.BiseeRepertoryService;
import cn.baisee.oa.utils.ParamUtils;
/**
 * 物品申领Controller
 * @author liangfeng
 *
 */
@Controller
@RequestMapping("/receive")
public class ReceiveController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private BaiseeReceiveService receiveService;
	@Autowired
	private BaiseeGoodsService goodsService;
	@Autowired
	private BiseeRepertoryService repertoryService;
	
	private static final String sheetProductName = "物品申领表";
	private static final String excelProductName = "物品申领列表.xls";
	
	
	/**
	 * 
	 * 点击申领管理菜单，根据当前登录者，查询该登录者所有的申领管理任务。然后跳转到审理管理列表页面
	 * @param request
	 * @param itemlableSearch	查询条件	申领的物品名称
	 * @return
	 */
	@RequestMapping("/receiveList")
	@Role(value="WPSL")
	public ModelAndView toReceiveList(HttpServletRequest request, String itemlableSearch) {
		//获取当前用户名称
		SessionUserInfo user = SessionUtil.getUserInfo(request);
		Integer pageNum=ParamUtils.getPageNumParameters(request);
		Integer pageSize=ParamUtils.getPageSizeParameters(request);
		//添加查询条件
		Map<String, String> map = new HashMap<>();
		//添加搜索查询条件
		map.put("itemlableSearch",itemlableSearch == null?"":itemlableSearch);
		//获取登录账号，只查询当前登录者创建的申领管理任务
		map.put("userId", user.getUserId());
		//执行查询
		PageInfo<BaiseeReceive> pageInfo=receiveService.getReceiveList(pageNum, pageSize, map);
		ModelAndView mv=new ModelAndView("repairReceive/receive_list");
		mv.addObject("pageResult", pageInfo);
		mv.addObject("itemlableSearch", itemlableSearch);
		return mv;
	}
	
	
	/**
	 * 去申领管理添加页面,并查询所有库存物品
	 * @return
	 */
	@RequestMapping("/toAddReceive")
	@Role(value="WPSL_XZSL")
	public ModelAndView toAddReceive(HttpServletRequest request){
		//获取当前用户名称
		SessionUserInfo user = SessionUtil.getUserInfo(request);
		ModelAndView mav=new ModelAndView("repairReceive/receive_add");
		//查询所有的物品类型
		List<BaiseeGoodsType> goodsTypes = goodsService.getAllGoodsTypeList();
		List<BaiseeGoodsRepertory> repertorys = null;
		if (goodsTypes != null && goodsTypes.size() >0 ) {
			//默认查询物品类型集合中的第一个物品类型下的所有库存
			repertorys = repertoryService.findGoodsRepertoryByGoodsTypeId(goodsTypes.get(0).getTypeId());
		}
		//将物品类型集合和物品库存集合返回到页面
		mav.addObject("goodsTypes", goodsTypes);
		mav.addObject("repertorys", repertorys);
		mav.addObject("deptId", user.getEmpDept());
		return mav;
	}
	
	/**
	 * 根据物品类型的id，查询该物品下的所有库存物品
	 * @param goodsId	库存物品id
	 * @return	库存数量
	 */
	@ResponseBody
	@RequestMapping("/getRepertoryNum")
	@Role(value="WPSL_CKSL")
	public Object getRepertoryNum(String goodsId){
		Map<String, Integer> map = new HashMap<>();
		if (!StringUtil.isEmpty(goodsId)) {
			//根据物品库存id查询，物品详情
			BaiseeGoodsRepertory repertory = repertoryService.getOneGodsRepertory(goodsId);
			int repertoryNum = 0;
			if (StringUtil.isNotEmpty(repertory.getGoodsNum())) {
				repertoryNum = Integer.valueOf(repertory.getGoodsNum());
			}
			//将该物品的库存返回前端
			map.put("repertoryNum", repertoryNum);
			return map;	
		}
		map.put("repertoryNum", 0);
		return map;	
	}
	
	/**
	 * 根据物品类型的id，查询该物品下的所有库存物品
	 * @param id	物品类型id
	 * @return	该类型下的物品库存
	 */
	@ResponseBody
	@RequestMapping("/findGoodsTypeById")
	@Role(value="WPSL_XZSL")
	public Object findGoodsTypeById(String id){
		//根据物品类型的id，查询该物品类型下的所有物品库存
		List<BaiseeGoodsRepertory> repertorys = repertoryService.findGoodsRepertoryByGoodsTypeId(id);
		//将查询到的物品库存集合返回给页面
		Map<String, List<BaiseeGoodsRepertory>> map = new HashMap<>();
		map.put("repertorys", repertorys);
		return map;		
	}
	
	
	/**
	 * 执行申领管理任务添加，然后跳转到申领管理列表页面
	 * @param request
	 * @param receive	物品申领对象
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addReceive")
	@Role(value="WPSL_XZSL")
	public ModelAndView addReceive(HttpServletRequest request, BaiseeReceive receive) throws Exception{
		//获取当前用户名称
		SessionUserInfo user = SessionUtil.getUserInfo(request);
		receive.setcId(user.getUserId());//申领人id
		receive.setcName(user.getUserName());//申领人名称
		receive.setCreateTime(new Date());
		//添加申领任务状态，0：申报中
		receive.setState("0");
		//执行添加
		receiveService.saveOrUpdateReceive(receive);
		return toReceiveList(request, null);
	}
	
	/**
	 * 根据id查询申领任务信息
	 * @param id	申领任务id
	 * @return
	 */
	@RequestMapping("/selectReceiveId")
	@Role(value="WPSL_CKSL")
	public ModelAndView selectByReceiveId(String id) {
		ModelAndView mav=new ModelAndView("repairReceive/receive_update");
		if (StringUtil.isNotEmpty(id)) {
			//查询任务申领详情
			BaiseeReceive receive=receiveService.getById(id);
			//根据物品库存id，查询物品库存详情
			BaiseeGoodsRepertory repertory = repertoryService.getOneGodsRepertory(receive.getGoodsId());
			//查询所有的物品类型
			List<BaiseeGoodsType> goodsTypes = goodsService.getAllGoodsTypeList();
			//根据上次选中的物品库存的物品类型id，查询物品存库的集合。查询这个因为要把该物品类型下的所有物品库存查询出来，返回给页面
			List<BaiseeGoodsRepertory> repertorys = repertoryService.findGoodsRepertoryByGoodsTypeId(repertory.getGoodsTypeId());
			mav.addObject("goodsTypes", goodsTypes);
			mav.addObject("repertorys", repertorys);
			mav.addObject("receive", receive);
			//记录物品类型的id，返回给页面是让select框默认选中
			mav.addObject("typeId", repertory.getGoodsTypeId());
		}
		return mav;
	}
	
	/**
	 * 执行任务修改
	 * @param request
	 * @param receive	物品申领对象
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateReceive")
	@Role(value="WPSL_XGSL")
	public ModelAndView updateReveive(HttpServletRequest request, BaiseeReceive receive) throws Exception{
		receiveService.saveOrUpdateReceive(receive);
		return toReceiveList(request, null);
	}
	
	
	/**
	 * 根据任务id，批量删除申领任务
	 * @param request
	 * @param ids	数组中装的是列表页面选中的物品申领对象
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteReceive")
	@Role(value="WPSL_SCSL")
	public ModelAndView delReceive(HttpServletRequest request, String[] ids) throws Exception{
		//ids数组中装的是列表页面选中的物品申领对象
		receiveService.delRepair(ids);
		return toReceiveList(request, null);
	}
	
	/**
	 * 
	 * 查询所有需要财务处理的申领任务
	 * @param request
	 * @param itemlableSearch	查询条件	物品名称
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/receiveTask")
	@Role(value="WPCL")
	public ModelAndView receiveTaskList(HttpServletRequest request, String itemlableSearch) throws Exception{
		Integer pageNum=ParamUtils.getPageNumParameters(request);
		Integer pageSize=ParamUtils.getPageSizeParameters(request);
		//添加查询条件
		Map<String, String> map = new HashMap<>();
		//添加搜索查询条件
		map.put("itemlableSearch",itemlableSearch == null?"":itemlableSearch);
		//执行查询
		PageInfo<BaiseeReceive> pageInfo=receiveService.getReceiveList(pageNum, pageSize, map);
		ModelAndView mv=new ModelAndView("repairReceive/receive_task_list");
		mv.addObject("pageResult", pageInfo);
		mv.addObject("itemlableSearch", itemlableSearch);
		return mv;
	}
	
	/**
	 * 点击审核申请，根据页面传来的申领任务的id，查询该条申领任务。并且根据申领任务中物品库存id，查询该条物品库存。最后比较申领的数量和库存的数量。然后弹出审核申请弹框
	 * @param id	物品申领任务id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/findReceive")
	@Role(value="WPSL_CLSL")
	public ModelAndView findReceive(String id) throws Exception{
		//根据任务id，申领任务详情
		BaiseeReceive receive=receiveService.getById(id);
		//根据物品库存id，查询物品库存详情
		BaiseeGoodsRepertory repertory = repertoryService.getOneGodsRepertory(receive.getGoodsId());
		int repertoryNum = 0;
		//判断物品库存是否为空
		if (repertory != null) {
			repertoryNum = Integer.valueOf(repertory.getGoodsNum());
		}
		ModelAndView mav=new ModelAndView("repairReceive/receive_info");
		mav.addObject("receive", receive);
		mav.addObject("repertoryNum", repertoryNum);
		return mav;
		
	}
	
	/**
	 * 根据申领任务id改变申领任务的状态，并且根据物品库存id减去申领任务的数量。
	 * @param request
	 * @param id		物品申领任务id
	 * @param goodsNum	物品申领的数量
	 * @param lastNum	物品剩余的库存数量
	 * @param goodsId	物品库存任务id
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/completeReceive")
	@Role(value="WPSL_WCSL")
	public Object repairComplete(HttpServletRequest request, String id, String goodsNum, String lastNum, String goodsId) throws Exception{
		//获取当前用户名称
		SessionUserInfo user = SessionUtil.getUserInfo(request);
		try {
			//创建物品申领对象，并设置id和申领的物品数量
			BaiseeReceive receive = new BaiseeReceive();
			receive.setId(id);
			receive.setGoodsNum(goodsNum);
			//更新状态，1：已领取
			receive.setState("1");
			receive.setUpdatePersonId(user.getUserId());//物品申领人id
			receive.setUpdatePerson(user.getUserName());//物品申领人名称
			receive.setUpdateTime(new Date());
			//执行更新
			int n = receiveService.completeReceive(receive);
			if (n > 0) {
				//创建物品库存对象，并设置物品库存对象的id和剩余库存数量
				BaiseeGoodsRepertory goodsRepertory = new BaiseeGoodsRepertory();
				goodsRepertory.setGoodsId(goodsId);
				goodsRepertory.setGoodsNum(lastNum);
				//执行更新物品库存对象
				repertoryService.updateOneGodsRepertory(goodsRepertory);
			}
			Map<String, String> map = new HashMap<>();
			map.put("code", "000");
			return map;			
		}catch (Exception e) {
			Map<String, String> map = new HashMap<>();
			map.put("code", "111");
			return map;	
		}
		
	}
	
	/**
	 * 由财务取消申领任务
	 * @param request
	 * @param id	物品申领任务id
	 * @param refuseReason	拒绝原因
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/cancelReceive")
	@Role(value="WPSL_JJSL")
	public Object cancelReceive(HttpServletRequest request, String id, String refuseReason) throws Exception{
		//获取当前用户名称
		SessionUserInfo user = SessionUtil.getUserInfo(request);
		try {
			//创建物品申领对象，并设置id、状态处理人
			BaiseeReceive receive = new BaiseeReceive();
			receive.setId(id);
			//更新状态，2：已取消
			receive.setState("2");
			receive.setRefuseReason(refuseReason);//拒绝原因
			receive.setUpdatePersonId(user.getUserId());//物品申领人id
			receive.setUpdatePerson(user.getUserName());//物品申领人名称
			receive.setUpdateTime(new Date());
			//执行更新
			receiveService.cancelReceive(receive);
			Map<String, String> map = new HashMap<>();
			map.put("code", "000");
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, String> map = new HashMap<>();
			map.put("code", "111");
			return map;
		}
	}
	
	/**
	 * 下载excel
	 * @param response
	 * @return
	 */
	@RequestMapping(value="exportExcel")
	@Role("WPSL_CKSL")
	@ResponseBody
	public void exportExcel(HttpServletRequest request,HttpServletResponse response, String createStartTime, String createEndTime){
		try {
			List<BaiseeReceive> list = receiveService.getStartTimeAndEndTime(createStartTime, createEndTime);
			String[] headers=new String[]{"申领人","物品名称","领取数量","领取状态","处理人","拒绝原因","申请日期","处理日期"};
			List<Map<Integer, Object>> dataList=ExcelBeanUtil.exportReceiveTaskList(list);
			logger.info("下载excel ：",dataList);
			Workbook wb=new HSSFWorkbook();
			ExcelUtil.fillExcelSheetData(dataList, wb, headers, sheetProductName);
			WebUtil.downloadExcel(response, wb, excelProductName);
		} catch (Exception e) {
			logger.error("下载excel 发生异常：",e.fillInStackTrace());
		}
		
		
		
	}
	
}
